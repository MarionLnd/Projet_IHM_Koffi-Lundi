import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Cette classe affiche le mode d'emploi de l'application sous forme de captures d'écran
 * 
 * @author Judicaël et Marion
 *
 */
public class GalerieInstructions extends JFrame implements ActionListener{

  /**
   * Le tableau dans lequel les images sont stockées
   */
  private JLabel[] image;
  
  /**
   * Le compteur pour changer d'image
   */
  private int compteur;

  /**
   * Le gestionnaire qui permet afficher le changement d'images
   */
  private CardLayout gestionnaire;

  /**
   * Le panneau qui contient toutes les images
   */
  private JPanel panel;

  /**
   * Le constructeur de la classe GalerieInstructions
   */
  public GalerieInstructions(){
    super("Mode d'emploi");
    this.setSize(900,700);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setIconImage(new ImageIcon("./imgs/hibiscus-teal-green-md.png").getImage());
    

    JButton accueil = new JButton("Retour a l\'accueil");
    accueil.addActionListener(this);

    this.panel = new JPanel();

    JButton precedent = new JButton("\u003C");
    JButton suivant = new JButton("\u003E");

    precedent.addActionListener(this);
    suivant.addActionListener(this);

    this.add(precedent, BorderLayout.WEST);
    this.add(suivant, BorderLayout.EAST);

    this.add(accueil, BorderLayout.SOUTH);

    this.gestionnaire = new CardLayout();
    this.compteur = 0;

    this.panel.setLayout(this.gestionnaire);

    this.image = new JLabel[7];

    for(int i=0; i<this.image.length;i++){
      this.image[i] = new JLabel(new ImageIcon("./imgs/screenshots/"+i+".png"));
      this.panel.add(this.image[i],"LABEL"+i);
    }

    this.add(this.panel, BorderLayout.CENTER);
  }

  @Override
  public void actionPerformed(ActionEvent evenement){
    if(evenement.getActionCommand().equals("Retour a l\'accueil")){
      this.dispose();
      PreAccueil a = new PreAccueil();
      a.afficherfenetreInitiale();
    }

    if(evenement.getActionCommand().equals("\u003C")){
      if(this.compteur <= 0){
        this.compteur = this.image.length;
      }
      this.gestionnaire.previous(this.panel);

    }

    if(evenement.getActionCommand().equals("\u003E")){
      if(this.compteur >= this.image.length-1){
        this.compteur = 0;
      }
      this.gestionnaire.next(this.panel);
    }
  }
  
}
