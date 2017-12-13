import java.awt.*;
import javax.swing.*;

/**
 * Cette classe est la vue de la recherche par client
 *
 * @author Judicaël Koffi et Marion Lundi
 *
 */
public class RechercheReference extends JFrame{

	/**
	 *  Le bouton qui permet d'effectuer une recherche
	 */
	private JButton retour;
	
	/**
	 * Le bouton qui permet de retourner à la page précédente
	 */
	private JButton rechercher;
	
	/**
	 * Le panneau qui contient les champs de saisie
	 */
	private JPanel panneauChamp;
	
	/**
	 * Le champ de saisie de la référence
	 */
	private JTextField champReference;
	
	/**
	 * Le controleur de cette vue
	 *
	 * @see ControleurReference
	 */
	private ControleurReference controleur;

	/**
	 * Le panneau qui contient les informations de la réservation
	 */
	private JPanel panelInfos;
	
	/**
	 * L'étiquette qui contient les informations de la réservation
	 */
	private JLabel labelInfos;
	
	/**
	 * L'étiquette qui contient le numéro de la chambre
	 */
	private JLabel labelChambre;
	
	/**
	 * Le penneau qui contient les boutons de décision pou une réservation
	 */
	private JPanel panelBoutonDecisionEmployer;
	
	/**
	 * Le panneau qui contient la liste des chambres disponibles
	 */
	private JPanel panneauListeChambre;
	
	/**
	 * La barre de défilement permettant de faire défiler la liste des chambres
	 */
	private JScrollPane scroll;

	/**
	 * Le constructeur de la classe RechercheReference
	 */
	public RechercheReference(){
		this.setTitle("Hotel Hibiscus -- Recherche par r\u00E9f\u00E9rence");
		this.setSize(760,620);
		this.setMinimumSize(new Dimension(760,620));
		this.setIconImage(new ImageIcon("./imgs/hibiscus-teal-green-md.png").getImage());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.champReference = new JTextField("");
		this.panneauChamp = new JPanel();
		this.panneauChamp.setLayout(new GridLayout(2,1));

		this.controleur = new ControleurReference(this,this.champReference,this);
		this.champReference.addActionListener(this.controleur);

		this.rechercher = new JButton("Rechercher");
		this.retour = new JButton("Retour \u00E0 l'accueil");
		this.rechercher.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		this.retour.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		JPanel panelBouton = new JPanel(new GridLayout(1,2));

		panelBouton.add(this.retour, BorderLayout.SOUTH);
		panelBouton.add(this.rechercher, BorderLayout.SOUTH);
		panelBouton.setBackground(Color.decode("#78A419"));

		this.retour.addActionListener(this.controleur);
		this.rechercher.addActionListener(this.controleur);

		this.add(panelBouton, BorderLayout.SOUTH);

		this.champRecherche(this);

		this.setVisible(true);
	}

	/**
	 * Cette méthode affiche les champs de recherche
	 *
	 * @param f
	 *		La fenetre dans laquelle les champs seront affichés
	 */
	public void champRecherche(JFrame f){

		Font police = new Font("Calibri Light", Font.PLAIN, 12);

		this.champReference.setPreferredSize(new Dimension(250,30));
		this.champReference.addActionListener(this.controleur);

		JLabel etiquette = new JLabel("Numero R\u00E9servation:");

		this.panneauChamp.add(etiquette, BorderLayout.CENTER);

		this.panneauChamp.add(this.champReference, BorderLayout.CENTER);

		f.add(this.panneauChamp, BorderLayout.NORTH);
	}

	/**
	 * Cette méthode affiche les informations concernant une réservation
	 */
	public void afficherInfos(){

		if(this.scroll != null){
			this.remove(this.scroll);
		}

		if(this.panelInfos != null){
			this.remove(this.panelInfos);
			this.panelBoutonDecisionEmployer.removeAll();
			this.panelInfos.revalidate();
		}

		this.retour.setText("Retour \u00E0 l'accueil");

		Reservation reserv = this.controleur.getReservationActuel();

		this.labelInfos = reserv.setInfosDansLabel();
		this.labelInfos.setFont(new Font("Calibri Light",Font.PLAIN,20));

		JButton valider = new JButton("Attribuer une chambre");
		JButton changerChambre = new JButton("Changer de chambre");
		valider.setBackground(Color.GREEN);
		valider.addActionListener(this.controleur);
		valider.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		changerChambre.setBackground(Color.ORANGE);
		changerChambre.addActionListener(this.controleur);
		changerChambre.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		this.panelInfos = new JPanel();
		this.panelInfos.setLayout(new BorderLayout());

		this.panelBoutonDecisionEmployer = new JPanel();
		this.panelBoutonDecisionEmployer.setLayout(new GridLayout(2,1));

		this.panelInfos.add(this.labelInfos,BorderLayout.CENTER);

		this.panelBoutonDecisionEmployer.add(valider);
		this.panelBoutonDecisionEmployer.add(changerChambre);
		this.panelInfos.add(this.panelBoutonDecisionEmployer, BorderLayout.EAST);

		this.add(this.panelInfos, BorderLayout.CENTER);

		this.revalidate();
	}

	/**
	 * Cette méthode liste toutes les chambres disponibles en fonction de la catégorie de la chambre souhaitée
	 */
	public void listerToutesLesChambres(){

		// 1 ---> Lit simple
		// 2 ---> lit double
		// 3 ---> Deux lit simple

		if(this.panelInfos != null){
			this.panelInfos.remove(this.panelBoutonDecisionEmployer);
			this.panelInfos.revalidate();
		}

		Reservation reserv = this.controleur.getReservationActuel();

		int nbreChambreDispo = this.controleur.getNombreChambreRestantParCategorie(reserv);

		this.retour.setText("Page pr\u00E9c\u00E9dente");

		this.remove(this.panelInfos); 

		int nbreDeChambreDejaOccuperPourCategorie = reserv.getNumChambreParDefaut()-1;

		int nbreLigne = Math.abs(nbreChambreDispo - nbreDeChambreDejaOccuperPourCategorie);

		Chambre[] tabChambre = this.controleur.getListeChambreDispo(reserv,nbreChambreDispo);

		this.panneauListeChambre = new JPanel(new GridLayout(tabChambre.length,1));

		for(Chambre i : tabChambre){
			JLabel labelChambre = new JLabel("Chambre "+i.getIdChambre());
			labelChambre.addMouseListener(this.controleur);
			this.panneauListeChambre.add(labelChambre);
		}

		this.scroll = new JScrollPane(this.panneauListeChambre);
		this.add(this.scroll,BorderLayout.CENTER);

		this.revalidate();
	}
}
