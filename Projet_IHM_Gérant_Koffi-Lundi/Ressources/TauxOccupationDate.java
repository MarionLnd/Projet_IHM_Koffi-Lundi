import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.text.MaskFormatter;

/**
 * Cette classe gère l'affichage du taux d'occupation en fonction d'une date saisie
 * 
 * @author Judicaël et Marion
 *
 */
public class TauxOccupationDate extends JFrame {
	
	/**
	 * Le bouton permattant de retourner au menu de choix de consultation
	 */
	private JButton retour;
	
	/**
	 * Le panneau qui contient le champ de texte
	 */
	private JPanel panneauChamp;
	
	/**
	 * Le panneau qui contient les boutons
	 */
	private JPanel panneauBouton;
	
	/**
	 * Le panneau qui affiche le taux
	 */
	private JPanel panneauInfo;

	/**
	 * Le champ de texte pour saisir la date
	 */
	private JFormattedTextField dateOccupation;
	
	/**
	 * Le controleur du taux d'occupation par date
	 * 
	 * @see ControleurTaux
	 */
	private ControleurTaux controleur;	

	/**
	 * Le constructeur de la classe TauxOccupationDate
	 */
	public TauxOccupationDate(){
		this.setTitle("Hotel Hibuscus - Taux d'occupation");
		this.setSize(760,620);
		this.setIconImage(new ImageIcon("./imgs/chart.png").getImage());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.retour = new JButton("Retour a l'accueil");
		this.retour.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		this.panneauBouton = new JPanel(new GridLayout(1,1));
		this.panneauInfo = new JPanel();

		panneauBouton.add(this.retour, BorderLayout.SOUTH);
		panneauBouton.setBackground(Color.decode("#78A419"));

		this.champDate(this);

		this.add(panneauBouton, BorderLayout.SOUTH);
		this.add(panneauInfo, BorderLayout.CENTER);

		this.setVisible(true);
	}

	/**
	*	Cette méthode gére l'affichage du champ de saisie de texte pour la date.
	*
	*	@param f
	*		Cette fenetre est celle dans laquelle on ajoutera le champ
	*
	*/
	public void champDate(JFrame f){

		this.panneauChamp = new JPanel(new GridLayout(3,1));

		Border border = BorderFactory.createTitledBorder("Par date");
		panneauChamp.setBorder(border);

		JLabel description = new JLabel("Vous avez choisi de consulter le taux d'occupation par date");
		description.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		description.setHorizontalAlignment(JLabel.CENTER);
		JLabel formatDateLabel = new JLabel("Veuillez saisir une date au format JJ/MM/YYYY (Jour/Mois/Ann\u00E9e) :");
		formatDateLabel.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		this.panneauChamp.add(description);
		this.panneauChamp.add(formatDateLabel);

		this.dateOccupation = new JFormattedTextField(DateFormat.getDateInstance());

		try{
			MaskFormatter date = new MaskFormatter("##/##/####");

			this.dateOccupation = new JFormattedTextField(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.controleur = new ControleurTaux(this.dateOccupation, this.panneauInfo,this);

		this.retour.addActionListener(this.controleur);

		this.dateOccupation.addKeyListener(this.controleur);

		this.dateOccupation.setPreferredSize(new Dimension(150,35));

		this.panneauChamp.add(this.dateOccupation, BorderLayout.WEST);

		f.add(this.panneauChamp, BorderLayout.NORTH);

	}
}
