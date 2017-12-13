/**
  * Cette classe représente la table Chambre de la base de données. 
  * 
  * @author Marion Lundi, Judicael KOFFI
  * @version 1.0
  *
  */

public class Chambre{

	/**
    * Cet attribut représente l'id de la chambre, elle est unique.
    *
    */
	private int idChambre;

	/**
    * Cet attribut représente l'id du client qui à la chambre, elle est unique.
    *
    */
	private int idClient;

	/**
    * Cet attribut représente la catégorie de la chambre.
    *
    */
	private int categorie;

	/**
    * Cet attribut représente le statut de la chambre, elle peut prendre deux valeurs : true ou false.
    * Dans la base de données, si le statut est à 0, la chambre est disponible, sinon elle est occupée
    */
	private boolean statut; 

	 /**
    * C'est le constructeur de la classe Chambre.
    *
    * @param id représente l'id de la chambre.
    * @param client représente l'id du client qui à la chambre.
    * @param cat représente la catégorie de la chambre.
    * @param dispo représente le statut de la chambre.
    */
	public Chambre(int id, int client, int cat, boolean dispo){
		this.idChambre=id;
		this.idClient=client;
		this.categorie=cat;
		this.statut=dispo;

	}

	/**
		* Permet de récuperer l'id de la chambre.
		* @return l'id de la chambre
		*/
	public int getIdChambre(){
		return this.idChambre;
	}

	/**
		* Permet de récuperer la catégorie de la chambre.
		* @return la catégorie de la chambre
		*/
	public int getCategorie(){
		return this.categorie;
	}

	/**
		* Permet de récuperer l'affichage un objet de Chambre.
		* @return une chaine de caractère décrivant une chambre.
		*/
	@Override
  	public String toString(){
    	return "IdChambre: "+this.idChambre+" IdClient: "+this.idClient +" Categorie: "+this.categorie+" Disponibilite: "+ this.statut;
  	}
}