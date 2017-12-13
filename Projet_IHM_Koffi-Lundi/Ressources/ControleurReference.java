import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Cette classe représente le controleur de la classe RechercheReference. 
 * 
 * @author Marion Lundi, Judicael KOFFI
 * @version 1.0
 *
 */

public class ControleurReference implements ActionListener, WindowListener, MouseListener{

   /**
    * Cette attribut représente la fenêtre.
    *
    */
   private JFrame fenetre;

   /**
    * Cette attribut représente le champ de texte pour la réference de la réservation.
    *
    */
   private JTextField reference;

   /**
    * Cette attribut représente un objet permet d'accéder à la base de données des chambres à attribuer au client.
    *
    */
   private ModeleRecherche modelBaseIHM;

   /**
    * Cette attribut représente un objet permet d'accéder à la base de données des chambres à attribuer au client.
    *
    */
   private ModeleChambre modelBaseSecond;

   /**
    * Cette attribut représente la référence entrer dans le champ de texte de la réservation.
    *
    */
   private String referenceEntrer;

   /**
    * Cette attribut représente la reservation du client.
    *
    */
   private Reservation reservation;

   /**
    * Cette attribut représente la réference vers la vue du mode recherche avec la référence.
    *
    */
   private RechercheReference vueRechercheReference;

   /**
    * C'est un constructeur de la classe ControleurReference, elle instance la connection à la base de données des réservations. 
    * @param f représente la fenêtre actuel.
    *
    */
   public ControleurReference(JFrame f){
      this.fenetre = f;

      this.modelBaseIHM = new ModeleRecherche("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");

   }

   /**
    * C'est un constructeur de la classe ControleurReference, elle instance la connection aux deux bases de données. 
    * @param f représente la fenêtre actuel.
    * @param ref représente le champ de texte de la référence.
    * @param vue représente la référence à la vue de la classe RechercheReference.
    *
    */
   public ControleurReference(JFrame f, JTextField ref, RechercheReference vue){
      this.fenetre = f;
      this.reference = ref;
      this.vueRechercheReference = vue;
      this.modelBaseIHM = new ModeleRecherche("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");
      this.modelBaseSecond = new ModeleChambre("jdbc:mariadb://dwarves.iut-fbleau.fr/lundi","lundi","Coucou"); 

   }

   /**
    * C'est la méthode qui permet de récuperer la réservation d'un client.
    * @return une réservation.
    *
    */
   public Reservation getReservationActuel(){
      return this.reservation;
   }

   /**
    * C'est la méthode qui permet de récuperer le nombre de chambre restant correspondant à la catégorie d'une réservation.
    * @param reservation correspond à une réservation.
    * @return un entier.
    *
    */
   public int getNombreChambreRestantParCategorie(Reservation reservation){
      int nbreTotalChambre = this.modelBaseSecond.getNombreChambreLibreParCategorie(reservation);
      return nbreTotalChambre;
   }

   /**
    * C'est la méthode qui permet de récuperer la liste de toutes les chambre libre, et correspondant à la catégorie d'une réservation.
    * @param reservation correspond à une réservation.
    * @param taille correspond à la taille du taibleau de chambre.
    * @return un tableau de chambre.
    *
    */
   public Chambre[] getListeChambreDispo(Reservation reservation, int taille){
      return this.modelBaseSecond.getListeChambreDisponible(reservation,taille);
   }

   /**
    * C'est la méthode qui permet de réagir en fonction des boutons clicquer par l'employer.
    * @param e correspond à un ActionEvent.
    *
    */
   @Override
    public void actionPerformed(ActionEvent e){

       if(e.getActionCommand().equals("Retour \u00E0 l'accueil") || e.getActionCommand().equals("Retour a l'accueil")){
          this.fenetre.dispose();
          new Accueil();
       }

       if(e.getActionCommand().equals("Page pr\u00E9c\u00E9dente")){
          this.vueRechercheReference.afficherInfos();
       }

       if(e.getActionCommand().equals("Rechercher") || e.getSource() ==  this.reference){

          this.referenceEntrer = this.reference.getText();

          this.reservation = this.modelBaseIHM.getReservation(this.referenceEntrer);

          if(this.reservation != null){
             int chambreParDefaut = this.modelBaseSecond.getNumChambreParDefaut(this.reservation);
             this.reservation.setNumChambreParDefaut(chambreParDefaut);
             this.reservation.setDateFin(ModeleChambre.calculerDateFin(this.reservation.getDateDebut(),this.reservation.getNbreNuits()));

             this.vueRechercheReference.afficherInfos();
          }else{
             ImageIcon img = new ImageIcon("imgs/alert.png");
             JOptionPane.showMessageDialog(null, "Cette r\u00E9servation n\'existe pas", "Information", JOptionPane.ERROR_MESSAGE,img);
          }
       }


       if(e.getActionCommand().equals("Attribuer une chambre")){

          if(!this.modelBaseSecond.verifSiReservationDejaAttribuer(this.reservation)){

             if(this.modelBaseSecond.attribuerChambre(this.reservation)){
                ImageIcon img = new ImageIcon("imgs/valider.png");
                JOptionPane.showMessageDialog(null, "La chambre "+this.reservation.getNumChambreParDefaut()+" a ete attribu\u00E9e au client "+this.reservation.getClient().getPrenom()+" "+this.reservation.getClient().getNom(), "Information", JOptionPane.INFORMATION_MESSAGE,img);
             }
             this.fenetre.dispose();
	    		new RechercheReference();
	    	}else{
	    		ImageIcon img = new ImageIcon("imgs/information.png");
	    		JOptionPane.showMessageDialog(null, "La r\u00E9servation "+this.reservation.getReferenceReservation()+" a deja une chambre attribu\u00E9e au client "+this.reservation.getClient().getPrenom()+" "+this.reservation.getClient().getNom(), "Information", JOptionPane.INFORMATION_MESSAGE,img);
	    	}
	    }

	    if(e.getActionCommand().equals("Changer de chambre")){
	    	this.vueRechercheReference.listerToutesLesChambres();
	    }
	}


  /**
    * C'est la méthode qui permet de fermer les connection aux dases de données lorsque la fenêtre est fermer.
    * @param evenement correspond à un WindowEvent.
    *
    */
	@Override
	public void windowClosing(WindowEvent evenement){
		this.modelBaseIHM.closeConnection();
		this.modelBaseSecond.closeConnection();
	}       

	
  /**
    * C'est la méthode qui permet de récuper la chambre sélectionner si l'employer décise de changer la chambe attribuer par défaut.
    * @param evenement correspond à un MouseEvent.
    *
    */
	public void mouseClicked(MouseEvent evenement){
		JLabel labelActuel = (JLabel) evenement.getSource();
		int reponse;
		int reponseChangementChambre;

		labelActuel.setBackground(Color.GREEN);
		labelActuel.setOpaque(true);

		if(!this.modelBaseSecond.verifSiReservationDejaAttribuer(this.reservation)){
			ImageIcon img = new ImageIcon("imgs/point_interrogation.png");
			reponse = JOptionPane.showConfirmDialog(null, "Souhaitez-vous attribuer la "+labelActuel.getText()+" au client "+this.reservation.getClient().getPrenom()+" "+this.reservation.getClient().getNom()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,img);
			
			// Si l'employe choisit l'option oui
			if(reponse == JOptionPane.YES_OPTION){
				int idNouvelleChambre = Integer.parseInt(labelActuel.getText().substring(8,10));
				this.fenetre.dispose();	
				this.reservation.setNumChambreParDefaut(idNouvelleChambre);
				if(this.modelBaseSecond.attribuerChambre(this.reservation)){
					new RechercheReference();	
				}else{
					JOptionPane.showMessageDialog(null, "La nouvelle chambre n'a pas \u00E9t\u00E9 attribu\u00E9e", "Erreur", JOptionPane.ERROR_MESSAGE,img);
				}
			}

			if(reponse == JOptionPane.NO_OPTION || reponse == JOptionPane.CLOSED_OPTION){
				Color couleurLabel = UIManager.getColor("Label.background");
				labelActuel.setBackground(couleurLabel);
				labelActuel.setOpaque(true);
			}
		}else{
			ImageIcon img = new ImageIcon("imgs/information.png");
			ImageIcon imgQuestion = new ImageIcon("imgs/49278.png");
	    	reponseChangementChambre = JOptionPane.showConfirmDialog(null, "La r\u00E9servation "+this.reservation.getReferenceReservation()+" a deja une chambre attribu\u00E9e au client "+this.reservation.getClient().getPrenom()+" "+this.reservation.getClient().getNom() + "\n\tVoulez-vous la changer par la nouvelle par la "+labelActuel.getText() + "?", "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,imgQuestion);

	    	if(reponseChangementChambre == JOptionPane.YES_OPTION){
	    		
	    		int idAncienneChambre = this.modelBaseSecond.getChambreClient(this.reservation);

	    		int idNouvelleChambre = Integer.parseInt(labelActuel.getText().substring(8,10));
	    		
	    		if(this.modelBaseSecond.changerChambrePourReservation(this.reservation,idAncienneChambre,idNouvelleChambre)){
					JOptionPane.showMessageDialog(null, "La chambre "+idNouvelleChambre+" a \u00E9t\u00E9 attribu\u00E9e au client "+this.reservation.getClient().getPrenom()+" \u00E0 la place de la chambre "+ this.reservation.getNumChambreParDefaut(), "Information", JOptionPane.INFORMATION_MESSAGE,img);
	    			this.fenetre.dispose();
	    			new RechercheReference();
	    		}
	    	}

	    	if(reponseChangementChambre == JOptionPane.NO_OPTION ||  reponseChangementChambre == JOptionPane.CLOSED_OPTION){
	    		
	    		Color couleurLabel = UIManager.getColor("Label.background");
				labelActuel.setBackground(couleurLabel);
				labelActuel.setOpaque(true);
	    	}
		}
	}		       

	public void mouseReleased(MouseEvent evenement){}       

	public void mouseEntered(MouseEvent evenement){}        

	public void mouseExited(MouseEvent evenement){}         

	public void mousePressed(MouseEvent evenement) {}     


	public void windowDeactivated(WindowEvent evenement){}  

	public void windowDeiconified(WindowEvent evenement){} 

	public void windowIconified(WindowEvent evenement){} 

	public void windowOpened(WindowEvent evenement){}       

	public void windowActivated(WindowEvent evenement){}   

	public void windowClosed(WindowEvent evenement){}       
}
