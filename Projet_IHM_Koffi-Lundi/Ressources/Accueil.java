import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.Dimension;

/**
  * Cette classe représente la fenêtre de l'accueil. 
  * Elle permet à l'employé d'effectuer une recherche soit par reference soit par nom et prénom du client.
  * 
  * @author Marion Lundi, Judicael KOFFI
  * @version 1.0
  *
  */

public class Accueil extends JFrame implements ActionListener{

  /**
    * Cet attribut représente le panneau contenant les boutons du mode de recherche.
    *
    */
  private JPanel panneauBoutons;


  /**
    * Cet attribut représente le bouton du mode recherche avec la reference.
    *
    */
  private JButton boutonReference;


  /**
    * Cet attribut représente le bouton du mode recherche avec le nom et le prénom du client.
    *
    */
  private JButton boutonClient;

  /**
    * Cet attribut représente le bouton du retour à la pré-accueil.
    *
    */
  private JButton boutonPreAccueil;


  /**
    * Cet attribut représente le label contenant l'image du la fenêtre d'accueil.
    *
    */
  private JLabel image;


  /**
    * C'est le constructeur de la classe Accueil. 
    * Il affiche également la fenêtre d'accueil.
    *
    */
  public Accueil(){
    this.setTitle("Hotel Hibiscus");
    this.setSize(760,620);
    this.setIconImage(new ImageIcon("./imgs/hibiscus-teal-green-md.png").getImage());
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.panneauBoutons = new JPanel();

    Font p = new Font("Calibri Light", Font.PLAIN, 14);

    this.boutonReference = new JButton("Rechercher par r\u00E9f\u00E9rence");
    this.boutonClient = new JButton("Rechercher par nom et pr\u00E9nom");
    this.boutonPreAccueil = new JButton("Page pr\u00E9c\u00E9dente");
    this.panneauBoutons.add(this.boutonReference);
    this.panneauBoutons.add(this.boutonClient);
    this.panneauBoutons.add(this.boutonPreAccueil);

    this.boutonClient.setFont(p);
    this.boutonReference.setFont(p);
    this.boutonPreAccueil.setFont(p);

    this.add(this.panneauBoutons, BorderLayout.SOUTH);

    this.image = new JLabel(new ImageIcon("./imgs/beach.png"));

    this.add(image, BorderLayout.CENTER);

    this.boutonReference.addActionListener(this);
    this.boutonClient.addActionListener(this);
    this.boutonPreAccueil.addActionListener(this);
    
    this.getContentPane().setBackground(Color.decode("#EFF0FF"));
    this.panneauBoutons.setBackground(Color.decode("#63c66c"));

    this.setVisible(true);
  }

  
  /**
    *Cette méthode permet d'éxecuter les actions en fontion du bouton cliquer.
    * @param e correspond à un ActionEvent.
    *
    */
  @Override
  public void actionPerformed(ActionEvent e){

    if(e.getActionCommand().equals("Rechercher par r\u00E9f\u00E9rence")){
      this.dispose();
      RechercheReference fenetreReference = new RechercheReference();
    }

    if(e.getActionCommand().equals("Rechercher par nom et pr\u00E9nom")){
      this.dispose();
      RechercheClient fenetreClient = new RechercheClient();
    }

     if(e.getActionCommand().equals("Page pr\u00E9c\u00E9dente")){
      this.dispose();
      PreAccueil preccueil = new PreAccueil();
      preccueil.afficherfenetreInitiale();
    }
  }
}
