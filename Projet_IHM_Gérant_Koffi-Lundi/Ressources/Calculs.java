/**
 * Cette classe contient tous les calculs effectués dans ce projet
 * 
 * @author Judicaël et Marion
 *
 */
public class Calculs{

	/**
	 * Cette méthode convertit la date saisie au format SQL pour la base de données
	 * 
	 * @param La date saisie dans le champ de texte
	 * 
	 * @return La date au bon format pour la base de données
	 */
	public static String changerFormatDate (String date){	 //"20/10/2017" --> 2017/10/20
        return date.substring(6,10) + "-"+ date.substring(3,5) + "-" + date.substring(0,2);
  	}

	/**
	 * Cette méthode calcule le taux d'occupation pour une date saisie
	 * 
	 * @param nombreChambresOccupees
	 * 		Le nombre de chambre occupées à une date saisie
	 * @param nombreChambreTotal
	 * 		Le nombre de chambres totales de l'hotel
	 * 
	 * @return Le taux d'occupation en pourcentage
	 */
	public static double getTauxOccupation(int nombreChambresOccupees, int nombreChambresTotal){
		double tauxOccupation;

		tauxOccupation = (double)((nombreChambresOccupees*100)/nombreChambresTotal);
		tauxOccupation = Math.abs(tauxOccupation);
		return tauxOccupation;
	}

	/**
	 * Cette méthode calcule le taux de non-présence à une date saisie
	 * 
	 * @param nombreChambresOccupees
	 * 		Le nombre de chambre occupées à une date saisie
	 * @param nbReservationsTotalDateDonnee
	 * 		Le nombre total de réservations effectuées à un date saisie
	 * 
	 * @return Le taux de non-présence en pourcentage
	 */
	public static double getTauxNonPresence(int nombreChambresOccupees, int nbReservationsTotalDateDonnee){
		double tauxNonPresence;

		if ((nombreChambresOccupees == 0) && (nbReservationsTotalDateDonnee == 0)){
            tauxNonPresence = 100.0;
        } else {
			tauxNonPresence = (double)( ((nombreChambresOccupees - nbReservationsTotalDateDonnee )*100)/nbReservationsTotalDateDonnee);
		}
		tauxNonPresence = Math.abs(tauxNonPresence);
		return tauxNonPresence;
	}

	/**
	 * Cette méthode calcule le taux d'occupation pour une date saisie
	 * 
	 * @param nombreChambresOccupees
	 * 		Le nombre de chambre occupées à une date saisie
	 * @param nombreChambresTotal
	 * 		Le nombre de chambres totales de l'hotel
	 * @param nbJoursPeriode
	 * 		Le nombre de jours dans une période saisie
	 * 
	 * @return Le taux d'occupation en pourcentage
	 */
	public static double getTauxOccupation(int nombreChambresOccupees, int nombreChambresTotal, int nbJoursPeriode){
		double tauxOccupation;

		nombreChambresTotal = nombreChambresTotal * nbJoursPeriode;
		tauxOccupation = (double)((nombreChambresOccupees*100)/nombreChambresTotal);
		tauxOccupation = Math.abs(tauxOccupation);
		return tauxOccupation;
	}
}
