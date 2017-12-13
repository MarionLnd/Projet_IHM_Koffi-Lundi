import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Cette classe représente le controleur de la classe RechercheClient. 
 * 
 * @author Marion Lundi, Judicael KOFFI
 * @version 1.0
 *
 */
public class ControleurClient implements ActionListener, WindowListener, MouseListener{


    /**
     * Cet attribut représente la fenêtre.
     *
     */
    private JFrame fenetre;

    /**
     * Cet attribut représente le champ de texte pour le nom du client.
     *
     */
    private JTextField nom;

    /**
     * Cet attribut représente le champ de texte pour le prénom du client.
     *
     */
    private JTextField prenom;

    /**
     * Cet attribut représente un objet permet d'accéder à la base de données des réservations.
     *
     */
    private ModeleRecherche modelBaseIHM;


    /**
     * Cet attribut représente un objet permet d'accéder à la base de données des chambres à attribuer au client.
     *
     */
    private ModeleChambre modelBaseSecond;


    /**
     * Cet attribut représente le nom entrer dans le champ de texte du nom.
     *
     */
    private String nomEntre;

    /**
     * Cet attribut représente le prénom entrer dans le champ de texte du prénom.
     *
     */
    private String prenomEntre;

    /**
     * Cet attribut représente la reservation du client.
     *
     */
    private Reservation reservation;

    /**
     * Cet attribut représente les reservations du client si il en a plus d'une.
     *
     */
    private Reservation[] reservations;

    /**
     * Cet attribut représente la réference vers la vue du mode recherche avec le nom et prénom du client.
     *
     */
    private RechercheClient vueRechercheClient;

    /**
     * Cet attribut représente la position dans le tableau des réservations du client.
     *
     */
    private int indexReservation;

    /**
     * Cet attribut représente le nombre total de reservation d'un client.
     *
     */  
    private int nbreDeReservation;



    /**
     * C'est un constructeur de la classe ContoleurClient, elle instance la connection à la base de données des réservations. 
     *
     * @param f représente la fenêtre actuel.
     *
     */
    public ControleurClient(JFrame f){
        this.fenetre = f;

        this.modelBaseIHM = new ModeleRecherche("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");

    }

    /**
     * C'est un constructeur de la classe ContoleurClient, elle instance la connection aux deux bases de données. 
     *
     * @param f représente la fenêtre actuel.
     *
     * @param champNom représente le champ de texte du nom.
     *
     * @param champPrenom représente le champ de texte du prénom.
     *
     * @param vue représente la référence à la vue de la classe RechercheClient.
     *
     */
    public ControleurClient(JFrame f, JTextField champNom, JTextField champPrenom, RechercheClient vue){
        this.fenetre = f;
        this.nom = champNom;
        this.prenom = champPrenom;
        this.modelBaseIHM = new ModeleRecherche("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");
        this.vueRechercheClient = vue;
        this.modelBaseSecond = new ModeleChambre("jdbc:mariadb://dwarves.iut-fbleau.fr/lundi","lundi","Coucou"); 
        this.indexReservation = 0;
    }


    /**
     * C'est la méthode qui permet de récuperer la réservation d'un client.
     *
     * @return une réservation.
     *
     */
    public Reservation getReservationActuel(){
        return this.reservation;
    }

    /**
     * C'est la méthode qui permet de récuperer les réservations d'un client.
     *
     * @return un tableau de réservation.
     *
     */
    public Reservation[] getReservationActuelMulti(){
        return this.reservations;
    }

    /**
     * C'est la méthode qui permet de récuperer le nombre de chambre restant correspondant à la catégorie d'une réservation.
     *
     * @param reservation correspond à une réservation.
     *
     * @return un entier.
     *
     */
    public int getNombreChambreRestantParCategorie(Reservation reservation){
        int nbreTotalChambre = this.modelBaseSecond.getNombreChambreLibreParCategorie(reservation);
        return nbreTotalChambre;
    }

    /**
     * C'est la méthode qui permet de récuperer la liste de toutes les chambre libre, et correspondant à la catégorie d'une réservation.
     *
     * @param reservation correspond à une réservation.
     *
     * @param taille correspond à la taille du tableau de chambre.
     *
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

        if(e.getActionCommand().equals("Retour \u00E0 l'accueil")){
            this.fenetre.dispose();
            new Accueil();
        }

        // Appuyer sur entrer 
        if(e.getSource() == this.vueRechercheClient.getButtonSuivant()){
            if(this.indexReservation==this.reservations.length-1){
                this.indexReservation = -1;
            }
            this.indexReservation++;
            this.vueRechercheClient.afficherInfosMultiReservation(this.indexReservation);
        }

        if(e.getSource() ==  this.nom || e.getSource() ==  this.prenom ){
            if(this.prenom.getText().equals("")){
                this.prenom.requestFocus();
            }

            if(this.nom.getText().equals("")){
                this.nom.requestFocus();
            }

            if((!this.nom.getText().equals("")) && (!this.prenom.getText().equals(""))){
                this.nomEntre = this.nom.getText();
                this.prenomEntre = this.prenom.getText();
                this.nbreDeReservation = this.modelBaseIHM.verifPlusieursReservation(this.prenomEntre,this.nomEntre);


                if(this.nbreDeReservation == 0){
                    ImageIcon img = new ImageIcon("imgs/alert.png");
                    JOptionPane.showMessageDialog(null, "Ce client n\'a pas de r\u00E9servation", "Information", JOptionPane.ERROR_MESSAGE,img);
                }

                ///Une reservation
                if(this.nbreDeReservation == 1){
                    this.reservation = this.modelBaseIHM.getReservationClient(this.nomEntre, this.prenomEntre);

                    if(this.reservation != null){
                        int chambreParDefaut = this.modelBaseSecond.getNumChambreParDefaut(this.reservation);
                        this.reservation.setNumChambreParDefaut(chambreParDefaut); // Attribuer une chambre par defaut a la reservation
                        this.vueRechercheClient.afficherInfos();
                    }else{
                        ImageIcon img = new ImageIcon("imgs/alert.png");
                        JOptionPane.showMessageDialog(null, "Cet r\u00E9servation n\'existe pas", "Information", JOptionPane.ERROR_MESSAGE,img);
                    }
                }

                /// Multi reservations
                if(this.nbreDeReservation > 1){

                    this.reservations = this.modelBaseIHM.getReservationMultiClient(this.nomEntre, this.prenomEntre,this.nbreDeReservation);


                    if(this.reservations != null){
                        int[] chambreParDefaut = new int[this.nbreDeReservation];

                        for(int i=0;i<this.nbreDeReservation;i++){
                            chambreParDefaut[i] = this.modelBaseSecond.getNumChambreParDefaut(this.reservations[i]);
                            this.reservations[i].setNumChambreParDefaut(chambreParDefaut[i]); // Attribuer une chambre par defaut a la reservation
                        }

                        this.vueRechercheClient.afficherInfosMultiReservation(0);

                    }else{
                        ImageIcon img = new ImageIcon("imgs/alert.png");
                        JOptionPane.showMessageDialog(null, "Cet r\u00E9servation n\'existe pas", "Information", JOptionPane.ERROR_MESSAGE,img);
                    }
                }
            }
        }

        // Appuyer sur le bouton
        if(e.getActionCommand().equals("Rechercher")){

            this.nomEntre = this.nom.getText();
            this.prenomEntre = this.prenom.getText();
            this.nbreDeReservation = this.modelBaseIHM.verifPlusieursReservation(this.prenomEntre,this.nomEntre);

            if(this.nbreDeReservation == 0){
                ImageIcon img = new ImageIcon("imgs/alert.png");
                JOptionPane.showMessageDialog(null, "Ce client n\'a pas de r\u00E9servation", "Information", JOptionPane.ERROR_MESSAGE,img);
            }

            ///Une reservation
            if(this.nbreDeReservation == 1){
                this.reservation = this.modelBaseIHM.getReservationClient(this.nomEntre, this.prenomEntre);

                if(this.reservation != null){
                    int chambreParDefaut = this.modelBaseSecond.getNumChambreParDefaut(this.reservation);
                    this.reservation.setNumChambreParDefaut(chambreParDefaut); // Attribuer une chambre par defaut a la reservation
                    this.vueRechercheClient.afficherInfos();
                }else{
                    ImageIcon img = new ImageIcon("imgs/alert.png");
                    JOptionPane.showMessageDialog(null, "Cet r\u00E9servation n\'existe pas", "Information", JOptionPane.ERROR_MESSAGE,img);
                }
            }

            // Multi reservations
            if(this.nbreDeReservation > 1){

                this.reservations = this.modelBaseIHM.getReservationMultiClient(this.nomEntre, this.prenomEntre,this.nbreDeReservation);

                if(this.reservations != null){
                    int[] chambreParDefaut = new int[this.nbreDeReservation];

                    for(int i=0;i<this.nbreDeReservation;i++){
                        chambreParDefaut[i] = this.modelBaseSecond.getNumChambreParDefaut(this.reservations[i]);
                        this.reservations[i].setNumChambreParDefaut(chambreParDefaut[i]); // Attribuer une chambre par defaut a la reservation
                    }

                    this.vueRechercheClient.afficherInfosMultiReservation(0);

                }else{
                    ImageIcon img = new ImageIcon("imgs/alert.png");
                    JOptionPane.showMessageDialog(null, "Cet r\u00E9servation n\'existe pas", "Information", JOptionPane.ERROR_MESSAGE,img);
                }
            }
        }

        if(e.getActionCommand().equals("Attribuer une chambre")){

            if(this.nbreDeReservation == 1){
                if(!this.modelBaseSecond.verifSiReservationDejaAttribuer(this.reservation)){

                    if(this.modelBaseSecond.attribuerChambre(this.reservation)){
                        ImageIcon img = new ImageIcon("imgs/valider.png");
                        JOptionPane.showMessageDialog(null, "La chambre "+this.reservation.getNumChambreParDefaut()+" a ete attribu\u00E9e au client "+this.reservation.getClient().getPrenom()+" "+this.reservation.getClient().getNom(), "Information", JOptionPane.INFORMATION_MESSAGE,img);
                    }

                    this.fenetre.dispose();
                    new RechercheClient();
                }else{
                    ImageIcon img = new ImageIcon("imgs/information.png");
                    JOptionPane.showMessageDialog(null, "La reservation "+this.reservation.getReferenceReservation()+" a deja une chambre attribu\u00E9e au client "+this.reservation.getClient().getPrenom()+" "+this.reservation.getClient().getNom(), "Information", JOptionPane.INFORMATION_MESSAGE,img);
                }
            }

            if(this.nbreDeReservation > 1){

                if(!this.modelBaseSecond.verifSiReservationDejaAttribuer(this.reservations[this.indexReservation])) {

                    if(this.modelBaseSecond.attribuerChambre(this.reservations[this.indexReservation])){
                        ImageIcon img = new ImageIcon("imgs/valider.png");
                        JOptionPane.showMessageDialog(null, "La chambre "+this.reservations[this.indexReservation].getNumChambreParDefaut()+" a ete attribu\u00E9e au client "+this.reservations[this.indexReservation].getClient().getPrenom()+" "+this.reservations[this.indexReservation].getClient().getNom(), "Information", JOptionPane.INFORMATION_MESSAGE,img);    
                    }

                }else{
                    ImageIcon img = new ImageIcon("imgs/information.png");
                    JOptionPane.showMessageDialog(null, "La reservation "+this.reservations[this.indexReservation].getReferenceReservation()+" a deja une chambre attribu\u00E9e au client "+this.reservations[this.indexReservation].getClient().getPrenom()+" "+this.reservations[this.indexReservation].getClient().getNom(), "Information", JOptionPane.INFORMATION_MESSAGE,img);
                }

                for(int i=0; i<this.reservations.length;i++){
                    this.reservations[i].setNumChambreParDefaut(this.modelBaseSecond.getNumChambreParDefaut(this.reservations[i])); // Attribuer une chambre par defaut a la reservation
                }

                this.vueRechercheClient.afficherInfosMultiReservation(this.indexReservation);
            }
        }

        if(e.getActionCommand().equals("Changer de chambre")){

            if(this.nbreDeReservation == 1){
                this.vueRechercheClient.listerToutesLesChambres();
            }
            if(this.nbreDeReservation > 1){
                this.vueRechercheClient.listerToutesLesChambresMultiReservation(reservations[this.indexReservation]);
            }
        }

        if(e.getActionCommand().equals("Page precedente")){
            if(this.nbreDeReservation == 1){
                this.vueRechercheClient.afficherInfos();
            }
            if(this.nbreDeReservation > 1){
                this.vueRechercheClient.afficherInfosMultiReservation(this.indexReservation);
            }
        }
    }


    /**
     * C'est la méthode qui permet de fermer les connection aux dases de données lorsque la fenêtre est fermer.
     *
     * @param evenement correspond à un WindowEvent.
     */
    @Override
    public void windowClosing(WindowEvent evenement){
        this.modelBaseIHM.closeConnection();
        this.modelBaseSecond.closeConnection();
    }  


    /**
     * C'est la méthode qui permet de récuper la chambre sélectionner si l'employer décise de changer la chambe attribuer par défaut.
     *
     * @param evenement correspond à un MouseEvent.
     */
    public void mouseClicked(MouseEvent evenement){
        JLabel labelActuel = (JLabel) evenement.getSource();
        int reponse;
        int reponseChangementChambre;

        labelActuel.setBackground(Color.GREEN);
        labelActuel.setOpaque(true);

        ImageIcon img = new ImageIcon("imgs/point_interrogation.png");

        ImageIcon imgInfo = new ImageIcon("imgs/information.png");
        ImageIcon imgQuestion = new ImageIcon("imgs/49278.png");

        if(this.nbreDeReservation == 1){

            if(!this.modelBaseSecond.verifSiReservationDejaAttribuer(this.reservation)){
                reponse = JOptionPane.showConfirmDialog(null, "Souhaitez-vous attribuer la "+labelActuel.getText()+" au client "+this.reservation.getClient().getPrenom()+" "+this.reservation.getClient().getNom()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,img);

                // Si l'employe choisit l'option oui
                if(reponse == JOptionPane.YES_OPTION){

                    int idNouvelleChambre = Integer.parseInt(labelActuel.getText().substring(8,10));

                    this.fenetre.dispose();

                    this.reservation.setNumChambreParDefaut(idNouvelleChambre);

                    if(this.modelBaseSecond.attribuerChambre(this.reservation)){
                        this.fenetre.dispose();
                        new RechercheClient();
                    }else{
                        JOptionPane.showMessageDialog(null, "La nouvelle chambre n'a pas \u00E9t\u00E9 attribu\u00E9e", "Erreur", JOptionPane.ERROR_MESSAGE,img);
                    }
                }

                if(reponse == JOptionPane.NO_OPTION){

                    Color couleurLabel = UIManager.getColor("Label.background");

                    labelActuel.setBackground(couleurLabel);
                    labelActuel.setOpaque(true);
                }

            }else{
                reponseChangementChambre = JOptionPane.showConfirmDialog(null, "La r\u00E9servation "+this.reservation.getReferenceReservation()+" a d\u00E9ja une chambre attribu\u00E9e au client "+this.reservation.getClient().getPrenom()+" "+this.reservation.getClient().getNom() + "\n\tVoulez-vous la changer par la nouvelle par la "+labelActuel.getText() + "?", "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,imgQuestion);

                if(reponseChangementChambre == JOptionPane.YES_OPTION){

                    int idAncienneChambre = this.modelBaseSecond.getChambreClient(this.reservation);

                    int idNouvelleChambre = Integer.parseInt(labelActuel.getText().substring(8,10));

                    if(this.modelBaseSecond.changerChambrePourReservation(this.reservation,idAncienneChambre,idNouvelleChambre)){
                        JOptionPane.showMessageDialog(null, "La chambre "+idNouvelleChambre+" a \u00E9t\u00E9 attribu\u00E9e au client "+this.reservation.getClient().getPrenom()+" \u00E0 la place de la chambre "+ this.reservation.getNumChambreParDefaut(), "Information", JOptionPane.INFORMATION_MESSAGE,imgInfo);
                        this.fenetre.dispose();
                        new RechercheClient();
                    }
                }
            }
        }

        if(this.nbreDeReservation > 1){
            if(!this.modelBaseSecond.verifSiReservationDejaAttribuer(this.reservations[this.indexReservation])) {  

                reponse = JOptionPane.showConfirmDialog(null, "Souhaitez-vous attribuer la "+labelActuel.getText()+" au client "+this.reservations[this.indexReservation].getClient().getPrenom()+" "+this.reservations[this.indexReservation].getClient().getNom()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,img);

                // Si l'employe choisit l'option oui
                if(reponse == JOptionPane.YES_OPTION){

                    int idNouvelleChambre = Integer.parseInt(labelActuel.getText().substring(8,10));

                    this.fenetre.dispose();

                    this.reservations[this.indexReservation].setNumChambreParDefaut(idNouvelleChambre);

                    if(this.modelBaseSecond.attribuerChambre(this.reservations[this.indexReservation])){
                        new RechercheClient();
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
                reponseChangementChambre = JOptionPane.showConfirmDialog(null, "La r\u00E9servation "+this.reservations[this.indexReservation].getReferenceReservation()+" a d\u00E9j\u00E0 une chambre attribu\u00E9e au client "+this.reservations[this.indexReservation].getClient().getPrenom()+" "+this.reservations[this.indexReservation].getClient().getNom() + "\n\tVoulez-vous la changer par la nouvelle par la "+labelActuel.getText() + "?", "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,imgQuestion);

                if(reponseChangementChambre == JOptionPane.YES_OPTION){

                    int idAncienneChambre = this.modelBaseSecond.getChambreClient(this.reservations[this.indexReservation]);

                    int idNouvelleChambre = Integer.parseInt(labelActuel.getText().substring(8,10));

                    if(this.modelBaseSecond.changerChambrePourReservation(this.reservations[this.indexReservation],idAncienneChambre,idNouvelleChambre)){
                        JOptionPane.showMessageDialog(null, "La chambre "+idNouvelleChambre+" a ete attribu\u00E9e au client "+this.reservations[this.indexReservation].getClient().getPrenom()+" \u00E0 la place de la chambre "+ this.reservations[this.indexReservation].getNumChambreParDefaut(), "Information", JOptionPane.INFORMATION_MESSAGE,imgInfo);
                        this.fenetre.dispose();
                        new RechercheClient();
                    }
                }

                if(reponseChangementChambre == JOptionPane.NO_OPTION ||  reponseChangementChambre == JOptionPane.CLOSED_OPTION){

                    Color couleurLabel = UIManager.getColor("Label.background");
                    labelActuel.setBackground(couleurLabel);
                    labelActuel.setOpaque(true);
                }
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
