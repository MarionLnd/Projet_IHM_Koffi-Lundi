import java.sql.*;

/**
 * Cette classe est le modèle permettant d'intéragir avec la base de données "lundi"
 * 
 * @author Judicaël et Marion
 *
 */
public class ModeleChambre{

  /**
   * Cette variable permet la connexion à la base de données
   */
  private static Connection db = null;

  /**
   * Le constructeur de la classe ModeleChambre
   * 
   * @param adresse
   *    L'adresse de la base de données
   * @param login
   *    Le login pour se connecter à la base de données
   * @param password
   *    Le mot de passe pour se connecter à la base de données
   */
  public ModeleChambre(String adresse, String login, String password){
    ModeleChambre.getInstance(adresse,login,password);
  }

  /**
   * Cette méthode instancie la connexion à la base de données
   * 
   * @param adresse
   *    L'adresse de la base de données
   * @param login
   *    Le login pour se connecter à la base de données
   * @param password
   *    Le mot de passe pour se connecter à la base de données
   */
  private static void getInstance(String adresse, String login, String password){

    if(ModeleChambre.db == null){

      try{
        Class.forName("org.mariadb.jdbc.Driver");

        try{
          ModeleChambre.db = DriverManager.getConnection(adresse,login,password);
        }catch(SQLException e){
          System.err.println(e.getMessage());
        }
      }catch(ClassNotFoundException e){
        System.err.println("Pilote non trouve");
      }
    }
  }

  /**
   * Cette méthode calcule la date de fin du séjour à partir de la date de début et du nombre de nuits
   * 
   * @param dateDebut
   *    La date du début du séjour
   * @param nbreDeNuit
   *    Le nombre de nuits passées
   * 
   * @return la date de fin du séjour
   */
  public static String calculerDateFin(String dateDebut, int nbreDeNuit){
    int dd = Integer.parseInt(dateDebut.substring(8,10));
    dd = dd + nbreDeNuit;
    return dateDebut.substring(0,7) + "-" + dd; 
  }

  /**
   * Cette méthode attribue une chambre en fonction de la réservation
   * 
   * @param reservation
   *    La reservation et les informations la concernant
   * 
   * @return un booléen confirmant ou non l'attribution d'une chambre 
   */
  public boolean attribuerChambre(Reservation reservation){
    int numChambre = reservation.getNumChambreParDefaut();
    boolean attribuer = false;

    String dateFinSejour = ModeleChambre.calculerDateFin(reservation.getDateDebut(),reservation.getNbreNuits());

    try{
      PreparedStatement requete = db.prepareStatement("UPDATE Chambre SET idChambre=?,idClient=?,idCategorie=?,disponibilite=?,dateReservation=?,referenceReservation=?, nombreNuits=?, dateFinSejour=? WHERE idChambre = ?  ");

      requete.setInt(1,numChambre);
      requete.setInt(2,reservation.getIdClient());
      requete.setInt(3,reservation.getCategorie());
      requete.setInt(4,1);
      requete.setString(5,reservation.getDateDebut());
      requete.setString(6,reservation.getReference());
      requete.setInt(7,reservation.getNbreNuits());
      requete.setString(8,dateFinSejour);
      requete.setInt(9,numChambre);

      int res = requete.executeUpdate();

      if(res==1){
        attribuer = true;
      }else{
        System.err.println("Pas d'attribution de chambre");
      }

    }catch(SQLException e){
      System.err.println(e.getMessage());
    }
    return attribuer;
  }

  /**
   * Cette méthode retourne le numéro de la chambre du client
   * 
   * @param reservation
   *    La reservation et les informations la concernant
   * 
   * @return le numéro de la chambre du client
   */
  public int getChambreClient(Reservation reservation){

    int chambreClient=0;

    try{
      PreparedStatement requete = db.prepareStatement("SELECT idChambre FROM Chambre WHERE referenceReservation = ?");
      requete.setString(1,reservation.getReference());

      ResultSet res = requete.executeQuery();

      if(res.first()){
        chambreClient = res.getInt(1);
      }else{
        System.err.println("Pas recup ancienne chambre");
      }
    }catch(SQLException e){
      System.err.println(e.getMessage());
    }
    return chambreClient;
  }

  /**
   * Cette méthode échange l'ancienne chambre attribuée au client pour une nouvelle chambre
   * 
   * @param reservation
   *    La reservation et les informations la concernant
   * @param ancienneChambre
   *    L'ancienne chambre du client, la chambre déjà attribuée
   * @param idNouvelleChambre
   *    Le numéro de la chambre à échanger
   * 
   * @return un booléen confirmant ou non le changement d'une chambre
   */
  public boolean changerChambrePourReservation(Reservation reservation, int ancienneChambre, int idNouvelleChambre){
    int numChambre = reservation.getNumChambreParDefaut();
    boolean attribuer = false;

    try{
      PreparedStatement requete = db.prepareStatement("UPDATE Chambre SET idClient=?,disponibilite=?,dateReservation=?,referenceReservation=?,nombreNuits=?,dateFinSejour=? WHERE idChambre=?");

      requete.setInt(1,0);
      requete.setInt(2,0);
      requete.setString(3,"0000-00-00");
      requete.setString(4,"NULL");
      requete.setInt(5,0);
      requete.setString(6,"0000-00-00");
      requete.setInt(7,ancienneChambre);

      int res = requete.executeUpdate();

      if(res==1){

        try{
          PreparedStatement requete2 = db.prepareStatement("UPDATE Chambre SET idClient=?,idCategorie=?, disponibilite=?, dateReservation=?, referenceReservation=?, nombreNuits=?, dateFinSejour=? WHERE idChambre =?");

          requete2.setInt(1,reservation.getIdClient());
          requete2.setInt(2,reservation.getCategorie());
          requete2.setInt(3,1);
          requete2.setString(4,reservation.getDateDebut());
          requete2.setString(5,reservation.getReference());
          requete2.setInt(6,reservation.getNbreNuits());
          requete2.setString(7,reservation.getDateFin());
          requete2.setInt(8,idNouvelleChambre);

          int res2 = requete2.executeUpdate();

          if(res2==1){
            attribuer = true;
          }else{
            System.err.println("Pas d'attribution de la nouvelle chambre");
          }

        }catch(SQLException e){
          System.err.println(e.getMessage());
        }

      }else{
        System.err.println("Pas d'update pour ancienne chambre");
      }

    }catch(SQLException e){
      System.err.println(e.getMessage());
    }
    return attribuer;
  }

  /**
   * Cette méthode vérifie si une réservation a déjà été attribuée
   * 
   * @param reservation
   *    La reservation et les informations la concernant
   *    
   * @return un booléen confirmant ou non si le client a déjà une chambre 
   */
  public boolean verifSiReservationDejaAttribuer(Reservation reservation){
    boolean verif = false;
    int count=0;
    
    try{
      PreparedStatement requete = db.prepareStatement("SELECT COUNT(*) FROM Chambre WHERE referenceReservation = ?");
      requete.setString(1,reservation.getReference());
      ResultSet res = requete.executeQuery();

      if(res.first()){
        count = res.getInt(1);
      }else{
        System.err.println("Pas d'attribution de chambre");
      }
    }catch(SQLException e){
      System.err.println(e.getMessage());
    }
    if(count > 0){
      verif = true;
    }
    return verif;
  }

  /**
   * Cette méthode renvoie le nombre de chambres libres en fonction de la catégorie de la réservation
   * 
   * @param reserv
   *    La reservation et les informations la concernant
   * 
   * @return le nombre de chambres libres correspondant aux critères
   */
  public int getNombreChambreLibreParCategorie(Reservation reserv){
    int total = 0;

    try{
      PreparedStatement requete = db.prepareStatement("SELECT COUNT(*) FROM Chambre WHERE disponibilite=? AND idcategorie=?");
      requete.setInt(1,0);
      requete.setInt(2,reserv.getCategorie());
      ResultSet res = requete.executeQuery();

      if(res.first()){
        total = res.getInt(1);
      }else{
        System.err.println("Donnee nbre total chambre non recup base second");
      }

      try{
        res.close();  
        requete.close(); 
      }catch(SQLException e){
        System.err.println(e.getMessage());
      }

    }catch(SQLException e){
      System.err.println(e.getMessage());
    }    
    return total;
  }

  /**
   * Cette méthode renvoie une chambre libre en fonction de la catégorie de la réservation
   * 
   * @param reservation
   *    La reservation et les informations la concernant
   * 
   * @return le numéro de la première chambre libre correspondant aux critères
   */
  public int getNumChambreParDefaut(Reservation reservation){
    int numChambre = 0;

    try{
      PreparedStatement requete = db.prepareStatement("SELECT idChambre FROM Chambre WHERE disponibilite = ? AND idCategorie = ?");
      requete.setInt(1,0);
      requete.setInt(2,reservation.getCategorie());
      ResultSet res = requete.executeQuery();

      if(res.first()){
        numChambre = res.getInt(1);
      }else{
        System.err.println("Donnee non recup base second");
      }

      try{
        res.close();  
        requete.close(); 
      }catch(SQLException e){
        System.err.println(e.getMessage());
      }

    }catch(SQLException e){
      System.err.println(e.getMessage());
    }
    return numChambre;
  }

  /**
   * Cette méthode renvoie une liste de toutes les chambres disponibles d'une catégorie
   * 
   * @param reservation
   *    La reservation et les informations la concernant
   * @param taille
   *    La taille du tableau correspondant au nombre de chambres libres
   * 
   * @return un tableau de toutes les chambres libres d'une catégorie
   */
  public Chambre[] getListeChambreDisponible(Reservation reservation, int taille){
    Chambre[] tabChambreDispo = new Chambre[taille];
    int compteur=0;

    try{
      PreparedStatement requete = db.prepareStatement("SELECT idChambre,idClient,idCategorie,disponibilite FROM Chambre WHERE disponibilite = ? AND idCategorie = ?");
      requete.setInt(1,0);
      requete.setInt(2,reservation.getCategorie());
      ResultSet res = requete.executeQuery();

      while(res.next()){
        tabChambreDispo[compteur] = new Chambre(res.getInt(1),res.getInt(2),res.getInt(3),res.getBoolean(4));
        compteur++;
      }

      try{
        res.close();  
        requete.close(); 
      }catch(SQLException e){
        System.err.println(e.getMessage());
      }

    }catch(SQLException e){
      System.err.println(e.getMessage());
    }
    return tabChambreDispo;
  }

  /**
   *  Cette méthode ferme la connexion à la base de données
   */
  public void closeConnection(){
    try{
      ModeleChambre.db.close();
    }catch(SQLException e){
      System.err.println(e.getMessage());
    }
  }
}
