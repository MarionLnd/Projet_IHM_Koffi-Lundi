import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
//import java.sql.Connection;

/**
 * Cette classe est le modèle permettant d'intéragir avec la base de données "projetihm"
 *
 * @author Judicaël Koffi et Marion Lundi
 *
 */
public class ModeleRecherche{

  /**
   * Cette variable permet la connexion à la base de données
   */
  private static Connection db = null;

  /**
   * Le constructeur de la classe ModeleRecherche
   * 
   * @param adresse
   *    L'adresse de la base de données
   * @param login
   *    Le login pour se connecter à la base de données
   * @param password
   *    Le mot de passe pour se connecter à la base de données
   */
  public ModeleRecherche(String adresse, String login, String password){
    ModeleRecherche.getInstance(adresse,login,password);
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

    if(ModeleRecherche.db == null){

      try{
        Class.forName("org.mariadb.jdbc.Driver");

        try{
          ModeleRecherche.db = DriverManager.getConnection(adresse,login,password);
        }catch(SQLException e){
          System.err.println(e.getMessage());
        }
      }catch(ClassNotFoundException e){
        System.err.println("Pilote non trouve");
      }
    }
  }
  /**
   * Cette méthode retourne une reservation et ses informations
   *
   * @param referenceReservation
   *    La référence de la reservation
   *
   * @return la réservation et ses informations
   */
  public Reservation getReservation(String referenceReservation){
    Reservation reservationActuel = null;
    Client clientActuel = null;

    try{
      PreparedStatement requete = ModeleRecherche.db.prepareStatement("SELECT Reservation.id,reference,debut,nuits,categorie,client,prenom,nom FROM Reservation JOIN Client WHERE Reservation.reference = ? AND client = Client.id AND debut >= CURRENT_DATE");
      requete.setString(1,referenceReservation);
      ResultSet res = requete.executeQuery();

      if(res.first()){
        clientActuel = new Client(res.getString(7), res.getString(8));
        reservationActuel = new Reservation(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getInt(6),clientActuel);
        clientActuel.setReservation(reservationActuel);
      }else{
        System.err.println("Pas de tuple pour getReservation");
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
    return reservationActuel;
  }

  /**
   * Cette méthode retourne une réservation et ses informations
   *
   * @param nomC
   *    Le nom du client dont on souhaite avoir la réservation 
   * @param prenomC
   *    Le prénom du client dont on souhaite avoir la réservation
   *
   * @return la réservation et ses informations
   */
  public Reservation getReservationClient(String nomC, String prenomC){
    Reservation actuel = null;
    String prenom;
    String nom;
    Client clientActuel = null;

    try{
      PreparedStatement requete = ModeleRecherche.db.prepareStatement("SELECT Reservation.id, reference, debut, nuits, categorie, client, nom, prenom FROM Reservation JOIN Client WHERE Client.id = client AND prenom = ? AND nom = ? AND debut >= CURRENT_DATE");
      requete.setString(1, prenomC);
      requete.setString(2, nomC);
      ResultSet res = requete.executeQuery();

      if(res.next()){
        nom = res.getString(7);
        nom = nom.trim();
        prenom = res.getString(8);
        prenom = prenom.trim();

        clientActuel = new Client(nom, prenom);
        actuel = new Reservation(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getInt(6), clientActuel);
      } else {
        System.err.println("Pas de tuple pour getReservationClient");
      }

      res.close();
      requete.close();

    } catch(SQLException e) {
      System.err.println(e.getMessage());
    }
    return actuel;
  }

  /**
   * Cette méthode vérifie le nombre de réservation d'un client
   *
   * @param prenom
   *    Le prénom du client dont on souhaite avoir le nombre de réservations
   * @param nom
   *    Le nom du client dont on souhaite avoir le nombre de réservations
   *
   * @return le nombre de réservations d'un client
   */
  public int verifPlusieursReservation(String prenom, String nom){
    int nbreDeReservation = 0;

    try{
      PreparedStatement requete = db.prepareStatement("SELECT COUNT(*) FROM Client JOIN Reservation ON Client.id = Reservation.client WHERE Client.prenom = ? AND Client.nom = ? AND debut >= CURRENT_DATE");
      requete.setString(1,prenom);
      requete.setString(2,nom);
      ResultSet res = requete.executeQuery();

      if(res.first()){
        nbreDeReservation = res.getInt(1);
      }else{
        System.err.println("Pas de tuple pour verifPlusieursReservation");
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
    return nbreDeReservation;
  }

  /**
   * Cette méthode retourne les différentes réservations d'un client avec les informations les concernant
   *
   * @param nomC
   *     Le nom du client dont on souhaite avoir les différentes réservations
   * @param prenomC
   *    Le prénom du client dont on souhaite avoir les différentes réservations
   * @param nbreRevers
   *
   * @return un tableau de toutes les réservation du client
   */
  public Reservation[] getReservationMultiClient(String nomC, String prenomC,int nbreRevers){
    Reservation[] actuel = new Reservation[nbreRevers];
    String prenom;
    String nom;
    Client clientActuel = null;
    int compteur = 0;

    try{
      PreparedStatement requete = ModeleRecherche.db.prepareStatement("SELECT Reservation.id, reference, debut, nuits, categorie, client, nom, prenom FROM Reservation JOIN Client WHERE Client.id = client AND prenom = ? AND nom = ? AND debut >= CURRENT_DATE");
      requete.setString(1, prenomC);
      requete.setString(2, nomC);
      ResultSet res = requete.executeQuery();

      while(res.next()){
        nom = res.getString(7);
        nom = nom.trim();
        prenom = res.getString(8);
        prenom = prenom.trim();

        clientActuel = new Client(nom, prenom);
        actuel[compteur] = new Reservation(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getInt(6), clientActuel);
        compteur++;
      } 

      res.close();
      requete.close();

    } catch(SQLException e) {
      System.err.println(e.getMessage());
    }
    return actuel;
  }

  /**
   * Cette méthode ferme la connexion à la base de données
   */
  public void closeConnection(){
    try{
      ModeleRecherche.db.close();
    }catch(SQLException e){
      System.err.println(e.getMessage());
    }
  }


}
