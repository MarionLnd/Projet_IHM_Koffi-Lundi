import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Cette classe affiche la première fenêtre à l'ouverture du programme
 * 
 * @author Judicaël et Marion
 *
 */
public class PreAccueil implements ActionListener{

  /**
   * La fenetre principale
   */
  private JFrame fenetreInitial;
  
  /**
   * Le panneau principal
   */
  private JPanel fenetrePanel;
  
  /**
   * Le panneau des boutons
   */
  private JPanel panelBouton;
  
  /**
   * Le bouton permattant de consulter les statistiques
   */
  private JButton consulter;
  
  /**
   * Le bouton permattant d'afficher le mode d'emploi
   */
  private JButton instructions;
  
  /**
   * Le titre principal
   */
  private JLabel titre;
  
  /**
   * L'image principale
   */
  private JLabel image;


  /**
   * Constructeur de la classe PreAccueil
   */
  public PreAccueil(){
    this.fenetreInitial = new JFrame("Hotel Hibiscus - G\u00E9rant");
    this.panelBouton = new JPanel();
    this.fenetrePanel = new JPanel();

    this.consulter = new JButton("Consultation");
    this.instructions =  new JButton("Mode d'emploi");
    this.titre = new JLabel("Hotel Hibiscus");
    this.image = new JLabel(new ImageIcon("./imgs/cape.png"));
  }

  /**
   * Cette méthode affiche la fenêtre principale et ses composants
   */
  public void afficherFenetreInitial(){
    this.fenetreInitial.setSize(760,620);
    this.fenetreInitial.setLocationRelativeTo(null);
    this.fenetreInitial.setIconImage(new ImageIcon("./imgs/hibiscus-teal-green-md.png").getImage());
    this.fenetreInitial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.fenetrePanel.setLayout(new BorderLayout());

    this.fenetrePanel.add(this.titre, BorderLayout.NORTH);
    this.titre.setFont(new Font("Calibri Light",Font.BOLD,22));
    this.titre.setHorizontalAlignment(JLabel.CENTER);

    this.consulter.addActionListener(this);
    this.instructions.addActionListener(this);
    this.consulter.setFont(new Font("Calibri Light", Font.PLAIN, 14));
    this.instructions.setFont(new Font("Calibri Light", Font.PLAIN, 14));

    this.fenetrePanel.add(this.image, BorderLayout.CENTER);

    this.panelBouton.add(consulter);
    this.panelBouton.add(instructions);
    this.fenetrePanel.add(this.panelBouton, BorderLayout.SOUTH);

    this.panelBouton.setBackground(Color.decode("#9C0253"));
    this.fenetrePanel.setBackground(Color.decode("#EFF0FF"));

    this.fenetreInitial.add(this.fenetrePanel);
    this.fenetreInitial.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent evenement){
    if(evenement.getActionCommand().equals("Consultation")){
      this.fenetreInitial.dispose();
      new Accueil();
    }
    if(evenement.getActionCommand().equals("Mode d'emploi")){
      this.fenetreInitial.dispose();
      new GalerieInstructions().setVisible(true);
    }
  }
}
