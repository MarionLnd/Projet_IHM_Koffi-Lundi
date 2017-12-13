/**
  * Cette classe représente la table Client de la base de données. 
  * 
  * @author Marion Lundi, Judicael KOFFI
  * @version 1.0
  *
  */
public class Client{

	/**
    * Cet attribut représente le nom du client.
    *
    */
	private String nom;

	/**
    * Cet attribut représente le prénom du client.
    *
    */
	private String prenom;

	/**
    * Cet attribut représente la réservation du client.
    *
    */
	private Reservation reservation;

	/**
    * Cet attribut représente l'id du client.
    *
    */
	private int idClient;

	/**
    * Cet attribut représente tous les réservations du client lorsqu'il a plus d'une réservation.
    *
    */
	private Reservation[] reservations;

	/**
    * C'est un constructeur de la classe Client. 
    * @param n représente le nom du client.
    * @param p représente le prénom du client.
    * @param r représente la réservation du client.
    *
    */
	public Client(String n, String p, Reservation r){
		this.nom = n;
		this.prenom= p;
		this.reservation = r;
	}

	/**
    * C'est un constructeur de la classe Client. 
    * @param n représente le nom du client.
    * @param p représente le prénom du client.
    * @param r représente les réservations du client.
    * @param idC représente l'id du client.
    *
    */
	public Client(String n, String p, Reservation[] r, int idC){
		this.nom = n;
		this.prenom= p;
		this.reservations = r;
		this.idClient = idC;
	}

	/**
    * C'est un constructeur de la classe Client. 
    * @param n représente le nom du client.
    * @param p représente le prénom du client.
    *
    */
	public Client(String n, String p){
		this.nom = n;
		this.prenom= p;
	}

	/**
    * C'est la méthode qui permet d'attribuer une réservation à un client. 
    * @param r représente la réservation du à attribuer au client.
    *
    */
	public void setReservation(Reservation r){
		this.reservation = r;
	}

	/**
    * C'est la méthode qui permet client de récuperer le nom d'un client.
    * @return le nom d'un client.
    *
    */
	public String getNom(){
		return this.nom;
	}

	/**
    * C'est la méthode qui permet client de récuperer le prénom d'un client.
    * @return le prénom d'un client.
    *
    */
	public String getPrenom(){
		return this.prenom;
	}
}