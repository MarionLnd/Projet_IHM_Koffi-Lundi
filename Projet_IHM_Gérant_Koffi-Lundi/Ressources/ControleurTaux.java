import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Cette classe est le controleur du taux d'occupation par date
 * 
 * @author Judicaël et Marion
 *
 */
public class ControleurTaux implements KeyListener,ActionListener{

	/**
	 * Le champ de saisie de la date
	 */
	private JFormattedTextField champ;
	
	/**
	 * Le panneau dans lequel le taux sera affiché
	 */
	private JPanel panelInfo;

	/**
	 * La vue TauxOccupationDate
	 */
	private TauxOccupationDate vue;
	
	
	/**
	 * Le modèle avec lequel le controleur interagira
	 * 
	 * @see ModeleStatistiques
	 */
	private ModeleStatistiques modele;
	
	/**
	 * Le constructeur de la classe ControleurTaux
	 * 
	 * @param champDate
	 * 		Le champ de texte de la date
	 * @param panneauInfo
	 * 		Le panneau dans lequel le taux est affiché
	 */
	public ControleurTaux(JFormattedTextField champDate, JPanel panneauInfo, TauxOccupationDate view){
		this.champ = champDate;
		this.panelInfo = panneauInfo;
		this.vue = view;
		this.modele = new ModeleStatistiques("jdbc:mariadb://dwarves.iut-fbleau.fr/lundi","lundi","Coucou");
	}

	public void keyPressed(KeyEvent event){
		String dateRecupString;
		int nbOccupation;
		int nbChambresTotal;
		double tauxOccupation;
		ImageIcon icon = new ImageIcon("./imgs/sad.png");

		try{
			if(event.getKeyCode() == KeyEvent.VK_ENTER){
				dateRecupString = this.champ.getText();
				String dateRecupBonFormat = Calculs.changerFormatDate(dateRecupString);

				if(this.panelInfo != null){
					this.panelInfo.removeAll();
					this.panelInfo.revalidate();
				}
				nbOccupation = this.modele.getNombreOccupationDateDonnee(dateRecupBonFormat);
				nbChambresTotal = this.modele.getNombreChambresLibres();

				tauxOccupation = Calculs.getTauxOccupation(nbOccupation, nbChambresTotal);
								
                this.panelInfo.setLayout(new BorderLayout());
                this.panelInfo.add(new Camembert(tauxOccupation,"Taux"), BorderLayout.CENTER);
                this.panelInfo.revalidate();
                if(tauxOccupation == 0.0){
                   JOptionPane.showMessageDialog(null, "Le taux d'occupation est nul.", "Taux d'occupation nul", JOptionPane.INFORMATION_MESSAGE, icon);
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
