import javax.swing.*;

/**
 * Cette classe est une classe comportant toutes les informations d'une réservation
 * 
 * @author Judicäel et Marion
 *
 */
public class Reservation{
  
  /**
   * L'id de la réservation
   */
  private int idReservation;
  
  /**
   * La référence de la réservation
   */
  private String reference;
  
  /**
   * La date de réservation
   */
  private String date;
  
  /**
   * La date de fin du séjour
   */
  private String dateFin;
  
  /**
   * Le nombre de nuits
   */
  private int nuits;
  
  /**
   * La catégorie de la chambre souhaitée dans la réservation
   */
  private int categorie;
  
  /**
   * L'id du client
   */
  private int idClient;
  
  /**
   * Le client actuel
   * 
   * @see Client
   */
  private Client clientActuel;
  
  /**
   * Le texte qui contiendra les informations de la réservation
   */
  private JLabel infosReservation;
  
  /**
   * Le numéro de la chambre attribuée
   */
  private int numChambreAttribuer;

  /**
   * Le constructeur de la class Réservation
   * 
   * @param id
   *    L'id de la réservation
   * @param ref
   *    La référence de la réservation
   * @param dat
   *    La date de réservation
   * @param nuit
   *    Le nombre de nuits
   * @param cat
   *    La catégorie de la chambre souhaitée
   * @param idc
   *    L'id du client
   * @param client
   *    Le client
   */
  public Reservation(int id, String ref, String dat, int nuit, int cat, int idc, Client client){
    this.idReservation = id;
    this.reference = ref;
    this.date = dat;
    this.nuits = nuit;
    this.categorie = cat;
    this.idClient = idc;
    this.clientActuel = client;
  }


  /**
   * Un deuxième constructeur de la classe Réservation
   */
  public Reservation(){
    this.idReservation = 0;
    this.reference = "";
    this.date = "";
    this.nuits = 0;
    this.categorie = 0;
    this.idClient = 0;
  }

  /**
   * Cette méthode mets les informations de la réservation dans le label (étiquette)
   * 
   * @return l'étiquette contenant les informations
   */
  public JLabel setInfosDansLabel(){
    String type = null;
    if(this.categorie == 1){
      type = "Lit Simple";
    }
    if(this.categorie == 2){
      type = "Lit Double";
    }
    if(this.categorie == 3){
      type = "Deux Lits Simple";
    }
    this.infosReservation = new JLabel("<html><p>Caracteristique de la r\u00E9servation:</p><br><ul><li>Id R\u00E9servation: "+this.idReservation+"</li><br><li>R\u00E9ference: "+this.reference +"</li><br><li>Date: "+this.date+"</li><br><li>Nombre de nuits: "+this.nuits+ "</li><br><li>Cat\u00E9gorie de chambre: "+type+"</li><br><li>Infos client: "+this.clientActuel.getPrenom()+" "+this.clientActuel.getNom()+"</li><br><li>Chambre propose: "+this.numChambreAttribuer+" </ul></html>");
    return this.infosReservation;
  }

  /**
   * Cette méthode mets les informations de la réservation dans le label (étiquette)
   * 
   * @param indexReservation
   *    L'index de la réservation
   * 
   * @return l'étiquette contenant les informations
   */
  public JLabel setInfosDansLabel(int indexReservation){
    String type = null;
    if(this.categorie == 1){
      type = "Lit Simple";
    }
    if(this.categorie == 2){
      type = "Lit Double";
    }
    if(this.categorie == 3){
      type = "Deux Lits Simple";
    }
    this.infosReservation = new JLabel("<html><p>Caracteristique de la r\u00E9servation:</p><br><ul><li>Id R\u00E9servation: "+this.idReservation+"</li><br><li>R\u00E9ference: "+this.reference +"</li><br><li>Date: "+this.date+"</li><br><li>Nombre de nuits: "+this.nuits+ "</li><br><li>Cat\u00E9gorie de chambre: "+type+"</li><br><li>Infos client: "+this.clientActuel.getPrenom()+" "+this.clientActuel.getNom()+"</li><br><li>Chambre propose: "+this.numChambreAttribuer+" </ul></html>");
    return this.infosReservation;
  }

  /**
   * Cette méthode attribue un numéro de chambre
   * 
   * @param numChambre
   *    Le numéro de la chambre
   */
  public void setNumChambreParDefaut(int numChambre){
    this.numChambreAttribuer = numChambre;
  }

  /**
   * Cette méthode attribue une date de fin de séjour
   * 
   * @param dateDeFin
   *    La date de din de séjour
   */
  public void setDateFin(String dateDeFin){
    this.dateFin = dateDeFin;
  }

  /**
   * Cette méthode retourne la catégorie de la réservation actuelle
   * 
   * @return la catégorie de la réservation
   */
  public int getCategorie(){
    return this.categorie;
  }

  /**
   * Cette méthode retourne la référence de la réservation actuelle
   * 
   * @return la référence de la réservation
   */
  public String getReference(){
    return this.reference;
  }

  /**
   * Cette éthode retourne la date de début de la réservation actuelle
   * 
   * @return la date de début de la réservation
   */
  public String getDateDebut(){
    return this.date;
  }

  /**
   * Cette méthode retourne la chambre par défaut
   * 
   * @return le numéro de la chambre attribuée par défaut
   */
  public int getNumChambreParDefaut(){
    return this.numChambreAttribuer;
  }

  /**
   * Cette méthode retourne le client
   * 
   * @see Client
   * 
   * @return le client concerné par la réservation
   */
  public Client getClient(){
    return this.clientActuel;
  }

  /**
   * Cette méthode retourne l'id du client
   * 
   * @return l'id du client
   */
  public int getIdClient(){
    return this.idClient;
  }

  /**
   * Cette méthode retourne la référence de la réservation
   * 
   * @return la référence de la réservation
   */
  public String getReferenceReservation(){
    return this.reference;
  }

  /**
   * Cette méthode retourne le nombre de nuits indiqués dans la réservation
   * 
   * @return le nombre de nuits
   */
  public int getNbreNuits(){
    return this.nuits;
  }

  /**
   * Cette méthode retourne la date de fin du séjour
   * 
   * @return la date de fin du séjour
   */
  public String getDateFin(){
    return this.dateFin;
  }

  /**
   * Cette réecriture de méthode réecrit la manière dont l'objet Reservation sera affiché
   */
  @Override
  public String toString(){
    return "Id: "+this.idReservation+" Reference: "+this.reference +" Date: "+this.date+" Nuit: "+this.nuits+ " Categorie: "+this.categorie+" NoClient: "+ this.idClient;
  }
}