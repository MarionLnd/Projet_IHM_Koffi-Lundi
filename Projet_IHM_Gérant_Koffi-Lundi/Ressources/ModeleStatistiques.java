import java.sql.*;

/**
 * Cette classe est le modèle qui gère les données à partir de la base "projetihm"
 * 
 * @author Judicaël et Marion
 *
 */
public class ModeleStatistiques{
	
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
	public ModeleStatistiques(String addr, String login, String password){

		ModeleStatistiques.getInstance(addr, login, password);
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

		if(ModeleStatistiques.db == null){

			try{
				Class.forName("org.mariadb.jdbc.Driver");

				try{
					ModeleStatistiques.db = DriverManager.getConnection(addr, login, password);
				} catch (SQLException e){
					System.err.println(e.getMessage());
				}
			} catch (ClassNotFoundException e){
				System.err.println("Pilote non trouve");
			}
		}
	}

	/**
	 * Cette méthode retourne le nombre de chambres libres
	 * 
	 * @return le nombre de chambres libres
	 */
	public int getNombreChambresLibres(){
		int nombreOccupation = 0;

		try{
			PreparedStatement requete = ModeleStatistiques.db.prepareStatement("SELECT COUNT(*) FROM Chambre");
			ResultSet res = requete.executeQuery();

			if(res.first()){
				nombreOccupation = res.getInt(1);
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

		return nombreOccupation;
	}

	/**
	 * Cette méthode retourne le nombre de chambres occupées à une date donnée
	 * 
	 * @param date
	 * 		La date saisie dans le champ de texte
	 * 
	 * @return le nombre de chambres occupées à une date donnée
	 */
	public int getNombreOccupationDateDonnee(String date){
		int nombreOccupation = 0;

		try{
			PreparedStatement requete = ModeleStatistiques.db.prepareStatement("SELECT COUNT(dateReservation) FROM Chambre WHERE dateReservation = ? AND disponibilite = ?");
			requete.setString(1, date);
			requete.setInt(2, 1);
			ResultSet res = requete.executeQuery();

			if(res.first()){
				nombreOccupation = res.getInt(1);
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

		return nombreOccupation;
	}

	/**
	 * Cette méthode retourne le nombre de chambre occupées pour une période donnée
	 * 
	 * @param dateDebut
	 * 		La date du début de la période saisie dans le champ de texte
	 * @param dateFin
	 * 		La date de fin de la période saisie dans le champ de texte
	 * 
	 * @return le nombre de chambre occupées pour une période donnée
	 */
	public int getNombreOccupationPeriodeDonnee(String dateDebut, String dateFin){
		int nombreOccupation = 0;

		try{
			PreparedStatement requete = ModeleStatistiques.db.prepareStatement("SELECT COUNT(dateReservation) FROM Chambre WHERE dateReservation BETWEEN ? AND ? AND disponibilite = ?  ");
			requete.setString(1, dateDebut);
			requete.setString(2, dateFin);
			requete.setInt(3, 1);
			ResultSet res = requete.executeQuery();

			if(res.first()){
				nombreOccupation = res.getInt(1);
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
		return nombreOccupation;
	}

	/**
	 * Cette méthode retourne le nombre de jours dans une période
	 * 
	 * @param dateDebut
	 * 		La date du début de la période saisie dans le champ de texte
	 * @param dateFin
	 * 		La date de fin de la période saisie dans le champ de texte
	 * 
	 * @return le nombre de jours dans une période
	 */
	public int getNombreJoursDansPeriode(String dateDebut, String dateFin){
		int nombreJoursPeriode = 0;

		try{
			PreparedStatement requete = ModeleStatistiques.db.prepareStatement("SELECT DATEDIFF( ?, ? )");
			requete.setString(1, dateDebut);
			requete.setString(2, dateFin);
			requete.setInt(3, 1);
			ResultSet res = requete.executeQuery();

			if(res.first()){
				nombreJoursPeriode = res.getInt(1);
				nombreJoursPeriode = Math.abs(nombreJoursPeriode);
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
		return nombreJoursPeriode;
	}
	
	/**
	 * Cette méthode ferme la connexion à la base de données
	 */
	public void closeConnection(){
	    try{
	   		ModeleStatistiques.db.close();
	    }catch(SQLException e){
	   		System.err.println(e.getMessage());
	    }
  	}
}
