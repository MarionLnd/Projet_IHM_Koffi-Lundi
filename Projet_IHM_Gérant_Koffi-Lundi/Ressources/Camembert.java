import javax.swing.*;
import java.awt.*;

public class Camembert extends JPanel{
	private double tauxPourDessin;
	private double tauxPourDessinPourcentage;
	private String typeDeTaux;

	public Camembert(double taux, String typeTaux){
		this.tauxPourDessinPourcentage = taux;
		this.tauxPourDessin = taux / 100;
		this.typeDeTaux = typeTaux;
	}

	@Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque()) {
			secondPinceau.setColor(this.getBackground());
			secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		int x=this.getWidth()/4;
		int y=this.getHeight()/4;
		int xx=this.getWidth()/2;
		int yy=this.getHeight()/2;


		int startArcVert=(int)(((this.tauxPourDessin*360)/2)-90);
		int startArcRouge=(int)(((this.tauxPourDessin*360)/2)+90);

		double arcV=(this.tauxPourDessin*360.0);
		double arcR=(360-(this.tauxPourDessin*360.0));

		
		if(this.typeDeTaux.equals("TauxNonPresentation")){
			secondPinceau.setColor(Color.BLACK);
			secondPinceau.drawString("Pourcentage de pr\u00E9sence ("+(100-this.tauxPourDessinPourcentage)+ "%)",(xx/x)+50,(yy/y)+30);
			secondPinceau.drawString("Pourcentage de non pr\u00E9sentation ("+this.tauxPourDessinPourcentage+ "%)",(xx/x)+50,(yy/y)+80);
			
			secondPinceau.setColor(Color.GREEN);
			secondPinceau.fillArc(x,y,xx,yy,startArcRouge,360);
			secondPinceau.fillRect(xx/x,yy/y,30,50);

			secondPinceau.setColor(Color.RED);
			secondPinceau.fillArc(x,y,xx,yy,startArcVert,(int)arcV);
			secondPinceau.fillRect(xx/x,(yy/y)+50,30,50);

		}else{
			secondPinceau.setColor(Color.RED);
			secondPinceau.fillArc(x,y,xx,yy,startArcRouge,360);
			secondPinceau.fillRect(xx/x,yy/y,30,50);

			secondPinceau.setColor(Color.GREEN);
			secondPinceau.fillArc(x,y,xx,yy,startArcVert,(int)arcV);
			secondPinceau.fillRect(xx/x,(yy/y)+50,30,50);

			secondPinceau.setColor(Color.BLACK);
			secondPinceau.drawString("Pourcentage des chambre non occup\u00E9 ("+(100-this.tauxPourDessinPourcentage)+ "%)",(xx/x)+50,(yy/y)+30);
			secondPinceau.drawString("Pourcentage des chambres occup\u00E9 ("+ this.tauxPourDessinPourcentage+"%)",(xx/x)+50,(yy/y)+80);
		}

	}
}
