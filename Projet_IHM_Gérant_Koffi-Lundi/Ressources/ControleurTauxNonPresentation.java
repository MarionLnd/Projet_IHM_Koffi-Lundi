import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Cette classe est le controleur du taux de non-présence par date
 * 
 * @author Judicaël et Marion
 *
 */
public class ControleurTauxNonPresentation implements KeyListener,ActionListener{

	/**
	 * Le champ de saisie de la date
	 */
	private JFormattedTextField champ;
	
	/**
	 * Le panneau dans lequel le taux sera affiché
	 */
	private JPanel panelInfo;

	/**
	 * La vue TauxNonPresentation
	 */
	private TauxNonPresentation vue;
	
	/**
	 * Le modèle avec lequel le controleur interagira
	 * 
	 * @see ModeleStatistiques
	 */
	private ModeleStatistiques modele;
	
	/**
	 * Le deuxième modèle avec lequel le controleur interagira
	 * 
	 * @see ModeleNonPresentation
	 */
	private ModeleNonPresentation modele2;

	/**
	 * Le constructeur de la classe ControleurTauxNonPresentation
	 * 
	 * @param champDate
	 * 		Le champ de texte de la date
	 * @param panneauInfo
	 * 		Le panneau dans lequel le taux est affiché
	 */
	public ControleurTauxNonPresentation(JFormattedTextField champDate, JPanel panneauInfo, TauxNonPresentation view ){
		this.champ = champDate;
		this.panelInfo = panneauInfo;
        this.vue = view;
        this.modele = new ModeleStatistiques("jdbc:mariadb://dwarves.iut-fbleau.fr/lundi","lundi","Coucou");
        this.modele2 = new ModeleNonPresentation("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");
    }

    public void keyPressed(KeyEvent event){
       String dateRecupString;
       int nbOccupation;
       int nbReservTotalDate;
       double tauxNonPresence = 0.0;
       ImageIcon icon = new ImageIcon("./imgs/happiness.png");
       ImageIcon icon2 = new ImageIcon("./imgs/sad.png");

       try{
          if(event.getKeyCode() == KeyEvent.VK_ENTER){
             dateRecupString = this.champ.getText();
             String dateRecupBonFormat = Calculs.changerFormatDate(dateRecupString);

             if(this.panelInfo != null){
                this.panelInfo.removeAll();
                this.panelInfo.revalidate();
             }

             nbOccupation = this.modele.getNombreOccupationDateDonnee(dateRecupBonFormat);
             
             nbReservTotalDate = this.modele2.getNombreReservationDateDonnee(dateRecupBonFormat);

             tauxNonPresence = Calculs.getTauxNonPresence(nbOccupation, nbReservTotalDate);

             this.panelInfo.setLayout(new BorderLayout());
             this.panelInfo.add(new Camembert(tauxNonPresence,"TauxNonPresentation"), BorderLayout.CENTER);
             this.panelInfo.revalidate();
            
             if(tauxNonPresence < 50.0){
                JOptionPane.showMessageDialog(null, "Le taux de non-pr\u00E9sence est inf\u00E9rieur a 50%.", "Taux de non-pr\u00E9sence faible", JOptionPane.INFORMATION_MESSAGE, icon);
             }
             if(tauxNonPresence == 100.0){
                JOptionPane.showMessageDialog(null, "Personne ne s'est pr\u00E9sent\u00E9 \u00E0 cette date.", "Taux de non-pr\u00E9sence \u00E9lev\u00E9", JOptionPane.INFORMATION_MESSAGE, icon2);
             }

          }	
       } catch (Exception e){

       }			
    }

    public void keyReleased(KeyEvent event){}

    public void keyTyped(KeyEvent event){}	

	@Override
	public void actionPerformed(ActionEvent event){
		if(event.getActionCommand().equals("Retour a l'accueil")){
			this.vue.dispose();
			new Accueil();
		}		
	}	

}
