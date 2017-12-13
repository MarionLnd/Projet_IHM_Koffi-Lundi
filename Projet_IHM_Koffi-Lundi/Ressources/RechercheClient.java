import java.awt.*;
import javax.swing.*;

/**
 * Cette classe est la vue de la recherche par client
 *
 * @author Judicaël Koffi et Marion Lundi
 *
 */
public class RechercheClient extends JFrame{
	
	/**
	 * Le bouton qui permet d'effectuer une recherche
	 */
	private JButton rechercher;
	
	/**
	 * Le bouton qui permet de retourner à la page précédente
	 */
	private JButton retour;
	
	/**
	 * Le panneau qui contient les champs de saisie
	 */
	private JPanel panneauChamp;
	
	/**
	 * Le champ de saisie du nom
	 */
	private JTextField champNom;
	
	/**
	 * Le champ de saisie du prénom
	 */
	private JTextField champPrenom;
	
	/**
	 * Le controleur de cette vue
	 *
	 * @see ControleurClient
	 */
	private ControleurClient controleur;

	/**
	 * Le panneau qui contient les informations de la réservation
	 */
	private JPanel panelInfos;
	
	/**
	 * L'étiquette qui contient les informations de la réservation
	 */
	private JLabel labelInfos;
	
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
	 * Le bouton qui permet de passer à la réservation suivante
	 */
	private JButton reservSuivant;

	/**
	 * Le constructeur de la classe RechercheClient
	 */
	public RechercheClient(){
		this.setTitle("Hotel Hibiscus -- Recherche par nom et prénom du client");
		this.setSize(760,620);
		this.setMinimumSize(new Dimension(760,620));
		this.setIconImage(new ImageIcon("./imgs/hibiscus-teal-green-md.png").getImage());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panneauChamp = new JPanel(new GridLayout(4,1));
		this.champNom = new JTextField("");
		this.champPrenom = new JTextField("");

		this.controleur = new ControleurClient(this,this.champNom, this.champPrenom, this);
		this.champPrenom.addActionListener(this.controleur);
		this.champNom.addActionListener(this.controleur);

		this.rechercher = new JButton("Rechercher");
		this.rechercher.addActionListener(this.controleur);
		this.retour = new JButton("Retour \u00E0 l'accueil");
		this.retour.addActionListener(this.controleur);
		this.rechercher.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		this.retour.setFont(new Font("Calibri Light", Font.PLAIN, 14));

		JPanel panelBouton = new JPanel(new GridLayout(1,2));

		panelBouton.add(this.retour,BorderLayout.SOUTH);
		panelBouton.add(this.rechercher,BorderLayout.SOUTH);

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
		String infoClient[] = new String[2];

		this.champNom.addActionListener(this.controleur);
		this.champPrenom.addActionListener(this.controleur);

		JLabel etiquetteNom = new JLabel("Nom du client:");
		JLabel etiquettePrenom = new JLabel("Pr\u00E9nom du client:");

		Font police = new Font("Calibri Light", Font.PLAIN, 12);

		this.champNom.setPreferredSize(new Dimension(150,30));
		this.champPrenom.setPreferredSize(new Dimension(150,30));

		this.panneauChamp.add(etiquetteNom);
		this.panneauChamp.add(this.champNom);
		this.panneauChamp.add(etiquettePrenom);
		this.panneauChamp.add(this.champPrenom);

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
	 * Cette méthode affiche les informations de chacune des réservations
	 *
	 * @param indexReservs
	 * 		L'index de réservation parmi la liste(tableau) des réservations d'un client
	 */
	public void afficherInfosMultiReservation(int indexReservs){

		if(this.scroll != null){
			this.remove(this.scroll);
			this.revalidate();
		}
		if(this.panelInfos != null){
			this.remove(this.panelInfos);
			this.panelBoutonDecisionEmployer.removeAll();
			this.panelInfos.revalidate();
		}

		this.retour.setText("Retour \u00E0 l'accueil");

		Reservation[] reservs = this.controleur.getReservationActuelMulti();

		this.labelInfos = reservs[indexReservs].setInfosDansLabel();

		this.labelInfos.setFont(new Font("Calibri Light",Font.PLAIN,20));

		JButton valider = new JButton("Attribuer une chambre");
		JButton changerChambre = new JButton("Changer de chambre");
		this.reservSuivant = new JButton("R\u00E9servation suivante",new ImageIcon("./imgs/29455.png"));

		valider.setBackground(Color.GREEN);
		valider.addActionListener(this.controleur);
		valider.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		changerChambre.setBackground(Color.ORANGE);
		changerChambre.addActionListener(this.controleur);
		changerChambre.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		this.reservSuivant.addActionListener(this.controleur);

		this.panelInfos = new JPanel();
		this.panelInfos.setLayout(new BorderLayout());

		this.panelBoutonDecisionEmployer = new JPanel();
		this.panelBoutonDecisionEmployer.setLayout(new GridLayout(3,1));

		this.panelInfos.add(this.labelInfos,BorderLayout.CENTER);

		this.panelBoutonDecisionEmployer.add(this.reservSuivant);
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

		if(this.panelInfos != null){
			this.panelInfos.remove(this.panelBoutonDecisionEmployer);
			this.panelInfos.revalidate();
		}

		Reservation reserv = this.controleur.getReservationActuel();

		int nbreChambreDispo = this.controleur.getNombreChambreRestantParCategorie(reserv);

		this.retour.setText("Page precedente");

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

	/**
	 * Cette méthode affiche la liste de toutes les chambres disponibles dans le cas où le client à plusieurs réservations
	 *
	 * @param reserv
	 *		La réservation et ses informations
	 */
	public void listerToutesLesChambresMultiReservation(Reservation reserv){
		if(this.panelInfos != null){
			this.panelInfos.remove(this.panelBoutonDecisionEmployer);
			this.panelInfos.revalidate();
		}

		int nbreChambreDispo = this.controleur.getNombreChambreRestantParCategorie(reserv);

		this.retour.setText("Page precedente");

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

	/**
	 * Cette méthode retourne le bouton permettant de passer à la réservation suivante
	 *
	 * @return le bouton qui passe à la réservation suivante
	 */
	public JButton getButtonSuivant(){
		return this.reservSuivant;
	}
}
