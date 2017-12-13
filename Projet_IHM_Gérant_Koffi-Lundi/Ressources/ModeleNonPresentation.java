import java.sql.*;

/**
 * Cette classe est le modèle qui gère les données à partir de la base "projetihm"
 * 
 * @author Judicaël et Marion
 *
 */
public class ModeleNonPresentation{

	/**
	 * La variable permettant la connexion à la base de données
	 */
	private static Connection db = null;

	/**
	 * Ce constructeur permet de procéder à la connexion à la base de données
	 * 
	 * @param addr
	 * 		L'adresse de la base de données
	 * @param login
	 * 		Le login pour se connecter à la base de données
	 * @param password
	 * 		Le mot de passe pour se connecter à la base de données
	 * 
	 * @see getInstance
	 */
	public ModeleNonPresentation(String addr, String login, String password){

		ModeleNonPresentation.getInstance(addr, login, password);
	}

	/**
	 * Cette méthode instancie la connexion à la base de données
	 * 
	 * @param addr
	 * 		L'adresse de la base de données
	 * @param login
	 * 		Le login pour se connecter à la base de données
	 * @param password
	 * 		Le mot de passe pour se connecter à la base de données
	 */
	private static void getInstance(String addr, String login, String password){

		if(ModeleNonPresentation.db == null){

			try{
				Class.forName("org.mariadb.jdbc.Driver");

				try{
					ModeleNonPresentation.db = DriverManager.getConnection(addr, login, password);
				} catch (SQLException e){
					System.err.println(e.getMessage());
				}
			} catch (ClassNotFoundException e){
				System.err.println("Pilote non trouve");
			}
		}
	}

	/**
	 * Cette méthode retourne le nombre de reservation pour une date saisie
	 * 
	 * @param date
	 * 		La date pour laquelle on souhaite avoir un taux
	 * 
	 * @return le nombre de reservations à la date saisie
	 */
	public int getNombreReservationDateDonnee(String date){
		int nombreReservationDateDonnee = 0;

		try{
			PreparedStatement requete = ModeleNonPresentation.db.prepareStatement("SELECT COUNT(*) FROM `Reservation` WHERE debut = ? ");
			requete.setString(1, date);
			ResultSet res = requete.executeQuery();

			if(res.first()){
				nombreReservationDateDonnee = res.getInt(1);
				nombreReservationDateDonnee = Math.abs(nombreReservationDateDonnee);
			}

			try{
				res.close();
				requete.close();
			} catch (SQLException e){
				System.err.println(e.getMessage());
			}

		} catch (SQLException e){
			System.err.println(e.getMessage());
		}
		return nombreReservationDateDonnee;
	}

	/**
	 * Cette méthode ferme la connexion à la base de données
	 */
	public void closeConnection(){
		try {
			ModeleNonPresentation.db.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}