import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Cette classe est la première fenetre appelée dans le programme
 *
 * @author Judicaël Koffi et Marion Lundi
 *
 */
public class PreAccueil implements ActionListener{
  
  /**
   * Cette fenêtre est la fenêtre principale
   */
  private JFrame fenetreInitiale;
  
  /**
   * Ce panneau est le panneau principal
   */
  private JPanel fenetrePanel;

  /**
   * Ce panneau contient les boutons
   */
  private JPanel panelBouton;
  
  /**
   * Ce boutons permet d'entre dans le menu de choix de consultations
   */
  private JButton demarrer;
  
  /**
   * Ce bouton permet d'afficher le mode d'emploi
   */
  private JButton instructions;
  
  /**
   * Cette étiquette contient le titre principal
   */
  private JLabel titre;
  
  /**
   * Cette étiquette contient l'image principale
   */
  private JLabel image;


  /**
   * Le constructeur de la classe PreAccueil
   */
  public PreAccueil(){
    this.fenetreInitiale = new JFrame("Hotel Hibiscus");
    this.panelBouton = new JPanel();
    this.fenetrePanel = new JPanel();

    this.demarrer = new JButton("D\u00E9marrer");
    this.instructions =  new JButton("Mode d'emploi");
    this.titre = new JLabel("Hotel Hibiscus");
    this.image = new JLabel(new ImageIcon("./imgs/beach.png"));
  }

  /**
   * Cette méthode affiche la fenêtre principale et son contenu
   */
  public void afficherfenetreInitiale(){
    this.fenetreInitiale.setSize(760,620);
    this.fenetreInitiale.setIconImage(new ImageIcon("./imgs/hibiscus-teal-green-md.png").getImage());
    this.fenetreInitiale.setLocationRelativeTo(null);
    this.fenetreInitiale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.fenetrePanel.setLayout(new BorderLayout());

    this.fenetrePanel.add(this.titre, BorderLayout.NORTH);
    this.titre.setFont(new Font("ARIAL",Font.BOLD,25));
    this.titre.setHorizontalAlignment(JLabel.CENTER);

    this.demarrer.addActionListener(this);
    this.instructions.addActionListener(this);
    this.demarrer.setFont(new Font("Calibri Light", Font.PLAIN, 14));
    this.instructions.setFont(new Font("Calibri Light", Font.PLAIN, 14));

    this.fenetrePanel.add(this.image, BorderLayout.CENTER);

    this.panelBouton.add(demarrer);
    this.panelBouton.add(instructions);
    this.fenetrePanel.add(this.panelBouton, BorderLayout.SOUTH);

    this.panelBouton.setBackground(Color.decode("#63c66c"));
    this.fenetrePanel.setBackground(Color.decode("#EFF0FF"));

    this.fenetreInitiale.add(this.fenetrePanel);
    this.fenetreInitiale.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent evenement){
    if(evenement.getActionCommand().equals("D\u00E9marrer")){
      this.fenetreInitiale.dispose();
      new Accueil();
    }
    if(evenement.getActionCommand().equals("Mode d'emploi")){
      this.fenetreInitiale.dispose();
      new GalerieInstructions().setVisible(true);
    }
  }
}
