import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

/**
 * Cette classe gère l'affichage du taux d'occupation en fonction d'une période saisie
 * 
 * @author Judicaël et Marion
 *
 */
public class TauxOccupationPeriode extends JFrame {
	
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
	 * Le champ de texte pour saisir la date de début de la période
	 */
	private JFormattedTextField dateDebut;
	
	/**
	 * Le champ de texte pour saisir la date de fin de la période
	 */
	private JFormattedTextField dateFin;
	
	/**
	 * Le controleur du taux d'occupation par période
	 * 
	 * @see ControleurTauxPeriode
	 */
	private ControleurTauxPeriode controleur;	

	/**
	 * Le constructeur de la classe TauxOccupationPeriode
	 */
	public TauxOccupationPeriode(){
		this.setTitle("Hotel Hibuscus - Taux d'occupation");
		this.setSize(760,620);
		this.setIconImage(new ImageIcon("./imgs/chart.png").getImage());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.retour = new JButton("Retour a l'accueil");
		this.retour.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		this.panneauBouton = new JPanel(new GridLayout(1,1));
		this.panneauInfo = new JPanel();

		this.panneauBouton.add(this.retour, BorderLayout.SOUTH);
		this.panneauBouton.setBackground(Color.decode("#78A419"));

		this.champPeriode(this);

		this.add(panneauBouton, BorderLayout.SOUTH);
		this.add(this.panneauInfo, BorderLayout.CENTER);

		this.setVisible(true);
	}

	/**
	 * Cette méthode gère l'affichage du champ de saisie de texte pour la date.
	 * 
	 * @param f
	 * 		Cette fenetre est celle dans laquelle on ajoutera le champ
	 */
	public void champPeriode(JFrame f){

		this.panneauChamp = new JPanel(new GridLayout(6,1));

		Border border = BorderFactory.createTitledBorder("Par p\u00E9riode");
		panneauChamp.setBorder(border);

		JLabel description = new JLabel("Vous avez choisi de consulter le taux d'occupation par p\u00E9riode.");
		description.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		description.setHorizontalAlignment(JLabel.CENTER);		

		this.panneauChamp.add(description);		

		JLabel labelDateDebut = new JLabel("Veuillez saisir la date de debut au format JJ/MM/YYYY (Jour/Mois/Ann\u00E9e) :");
		JLabel labelDateFin = new JLabel("Veuillez saisir la date de fin au format JJ-MM-YYYY (Jour/Mois/Ann\u00E9e) :");
		labelDateDebut.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		labelDateFin.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		this.dateDebut = new JFormattedTextField(DateFormat.getDateInstance());		
		this.dateFin = new JFormattedTextField(DateFormat.getDateInstance());

		this.dateDebut.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		this.dateFin.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		try{
			MaskFormatter date = new MaskFormatter("##/##/####");

			this.dateDebut = new JFormattedTextField(date);
			this.dateFin = new JFormattedTextField(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.controleur = new ControleurTauxPeriode(this.dateDebut, this.dateFin, this.panneauInfo, this);
		this.retour.addActionListener(this.controleur);

		this.dateDebut.addKeyListener(this.controleur);
		this.dateFin.addKeyListener(this.controleur);

		this.dateDebut.setPreferredSize(new Dimension(150,35));
		this.dateFin.setPreferredSize(new Dimension(150,35));

		this.panneauChamp.add(labelDateDebut);
		this.panneauChamp.add(this.dateDebut, BorderLayout.WEST);
		this.panneauChamp.add(labelDateFin);				
		this.panneauChamp.add(this.dateFin, BorderLayout.WEST);

		f.add(this.panneauChamp, BorderLayout.NORTH);

	}
}
