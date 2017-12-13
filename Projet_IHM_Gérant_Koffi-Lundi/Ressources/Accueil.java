import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;

/**
 * Cette classe comporte les choix que le gérant peut faire pour consulter les statistiques
 * 
 * @author Judicaël et Marion
 *
 */
public class Accueil extends JFrame implements ActionListener{

    /**
     * Le panneau qui contient les boutons
     */
    private JPanel panneauBoutons;
    
    /**
     * Le panneau qui contient la phrase de description
     */
    private JPanel panneauDescription;
    
    /**
     * Le bouton qui permet de choisir de consulter le taux d'occupation 
     */
    private JButton boutonOccupation;
    
    /**
     * Le bouton qui permet de choisir de consulter le taux de non-présence
     */
    private JButton boutonNonPresentaion;
    
    /**
     * Le bouton qui permet de retourner à l'accueil
     */
    private JButton boutonRetourPreAccueil;
    
    /**
     * La phrase de description des choix possibles
     */
    private JLabel description;
    
    /**
     * L'image principale
     */
    private JLabel image;

    /**
     * Constructeur de la classe Accueil 
     */
    public Accueil(){
        this.setTitle("Hotel Hibuscus - Consultation des statistiques");
        this.setSize(760,620);
        this.setIconImage(new ImageIcon("./imgs/chart.png").getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panneauBoutons = new JPanel();

        Font p = new Font("Calibri Light", Font.PLAIN, 14);
        Font pDescription = new Font("Calibri Light", Font.PLAIN, 18);

        this.boutonOccupation = new JButton("Taux d'occupation");
        this.boutonNonPresentaion = new JButton("Taux de non-pr\u00E9sentation");
        this.boutonRetourPreAccueil = new JButton("Page pr\u00E9c\u00E9dente");

        this.panneauBoutons.add(this.boutonOccupation, BorderLayout.WEST);
        this.panneauBoutons.add(this.boutonNonPresentaion, BorderLayout.EAST);
        this.panneauBoutons.add(this.boutonRetourPreAccueil, BorderLayout.EAST);

        this.boutonNonPresentaion.setFont(p);
        this.boutonOccupation.setFont(p);
        this.boutonRetourPreAccueil.setFont(p);

        this.add(this.panneauBoutons, BorderLayout.SOUTH);

        this.panneauDescription = new JPanel();
        this.panneauDescription.setBackground(Color.decode("#EFF0FF"));

        this.add(this.panneauDescription,BorderLayout.NORTH);

        this.description = new JLabel("Vous pouvez consulter le taux d'occupation ou le taux de non-pr\u00E9sentation");
        this.image = new JLabel(new ImageIcon("./imgs/cape.png"));

        this.description.setFont(pDescription);

        this.panneauDescription.add(this.description, BorderLayout.CENTER);

        this.add(this.image, BorderLayout.CENTER);

        this.boutonOccupation.addActionListener(this);
        this.boutonNonPresentaion.addActionListener(this);
        this.boutonRetourPreAccueil.addActionListener(this);

        this.getContentPane().setBackground(Color.decode("#EFF0FF"));
        this.panneauBoutons.setBackground(Color.decode("#9C0253"));

        this.setVisible(true);
    }

    /**
     * Cette méthode retourne le bouton de retour au pré-accueil
     * 
     * @return Le bouton pour retourner au pré-accueil
     */
    public JButton getButtonHome(){
        return this.boutonRetourPreAccueil;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        String question = "Souhaitez-vous afficher ce taux par date ou par p\u00E9riode ?";
        String info = "Date ou p\u00E9riode";
        ImageIcon icon = new ImageIcon("./imgs/calendar.png");
        String[] choix = {"Par date", "Par p\u00E9riode"};
        Object reponseUser;

        if(e.getActionCommand().equals("Taux d'occupation")){
            this.dispose();
            reponseUser = (String)JOptionPane.showInputDialog(null, question, info, JOptionPane.QUESTION_MESSAGE, icon, choix, "Par date");

            if(reponseUser==null){
                this.dispose();
                new Accueil();   
            }else{
                if(reponseUser.equals("Par date")) {
                    new TauxOccupationDate();     
                }
              
                if(reponseUser.equals("Par p\u00E9riode")){
                 new TauxOccupationPeriode();
                }
            }
        
        }

        if(e.getActionCommand().equals("Taux de non-pr\u00E9sentation")){
            this.dispose();
            new TauxNonPresentation();
        }

        if(e.getActionCommand().equals("Page pr\u00E9c\u00E9dente")){
            this.dispose();
            PreAccueil preccueil = new PreAccueil();
            preccueil.afficherFenetreInitial();
        }
    }
}
