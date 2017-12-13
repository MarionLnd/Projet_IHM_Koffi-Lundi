import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Cette classe est le controleur du taux d'occupation par période
 * 
 * @author Judicaël et Marion
 *
 */
public class ControleurTauxPeriode implements KeyListener,ActionListener{

	/**
	 * Le champ de saisie de la date de début
	 */
	private JFormattedTextField champ;
	
	/**
	 * Le champ de saisie de la date de fin
	 */
	private JFormattedTextField champ2;
	
	/**
	 * Le panneau dans lequel le taux sera affiché
	 */
	private JPanel panelInfo;
	
	/**
	 * La vue TauxNonPresentation
	 */
	private TauxOccupationPeriode vue;

	/**
	 * Le modèle avec lequel le controleur interagira
	 * 
	 * @see ModeleStatistiques
	 */
	private ModeleStatistiques modele;	

	/**
	 * Le constructeur de la classe ControleurTauxPeriode
	 * 
	 * @param champDateDebut
	 * 		Le champ de texte de la date de début de période
	 * @param champDateFin
	 * 		Le champ de texte de la date de fin de la période
	 * @param panneauInfo
	 * 		Le panneau dans lequel le taux est affiché
	 */
	public ControleurTauxPeriode(JFormattedTextField champDateDebut, JFormattedTextField champDateFin, JPanel panneauInfo, TauxOccupationPeriode view){
		this.champ = champDateDebut;
		this.champ2 = champDateFin;
		this.panelInfo = panneauInfo;
		this.vue = view;
		this.modele = new ModeleStatistiques("jdbc:mariadb://dwarves.iut-fbleau.fr/lundi","lundi","Coucou");
	}

	public void keyPressed(KeyEvent event){
		String dateDebutRecupString;
		String dateFinRecupString;
		int nbOccupation;
		int nbChambresTotal;
		int nbJoursPeriode;
		double tauxOccupation;
		ImageIcon icon2 = new ImageIcon("./imgs/sad.png");

		try{
			if(event.getKeyCode() == KeyEvent.VK_ENTER){
				dateDebutRecupString = this.champ.getText();
				String dateDebutRecupBonFormat = Calculs.changerFormatDate(dateDebutRecupString);
				dateFinRecupString = this.champ2.getText();
				String dateFinRecupBonFormat = Calculs.changerFormatDate(dateFinRecupString);
				
				nbOccupation = this.modele.getNombreOccupationPeriodeDonnee(dateDebutRecupBonFormat, dateFinRecupBonFormat);
				nbJoursPeriode = this.modele.getNombreJoursDansPeriode(dateDebutRecupBonFormat, dateFinRecupBonFormat);
				nbChambresTotal = this.modele.getNombreChambresLibres();

				tauxOccupation = Calculs.getTauxOccupation(nbOccupation, nbChambresTotal, nbJoursPeriode);
				if(tauxOccupation == 0.0){
					JOptionPane.showMessageDialog(null, "Le taux d'occupation est nul.", "Taux d'occupation nul", JOptionPane.INFORMATION_MESSAGE, icon2);
				}
				
				this.panelInfo.setLayout(new BorderLayout());
				this.panelInfo.add(new Camembert(tauxOccupation,"TauxPeriode"), BorderLayout.CENTER);
				this.panelInfo.revalidate();
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