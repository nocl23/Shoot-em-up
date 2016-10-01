import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Color;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Fenetre f = new Fenetre("f", 640, 480);
		Clavier clavier = new Clavier();
		Souris souris = new Souris(480);
		f.addKeyListener(clavier);
		f.addMouseListener(souris);
		f.getP().addMouseListener(souris);
		f.getP().addMouseMotionListener(souris);
		f.addMouseMotionListener(souris);
		Texture vaisseau = new Texture ("./img/vaisseau.png",new Point(40,40),49,85);
		f.ajouter(vaisseau);

		while (true) {
			try {
				Thread.sleep(20);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			if (clavier.getDroite() && vaisseau.getB().getX() < 635) {
				vaisseau.translater(5, 0);
			} else if (clavier.getGauche() && vaisseau.getA().getX() > 0) {
				vaisseau.translater(-5, 0);
			} else if (clavier.getHaut() && vaisseau.getA().getY() < 395){
				vaisseau.translater(0, 5);
			} else if (clavier.getBas() && vaisseau.getB().getY() > vaisseau.getHauteur()){
				vaisseau.translater(0, -5);
			} 

			/*if (souris.getClicGauche()) {
				
				if (vaisseau.intersection(souris.getPosition())) {

					System.out.println("Rectangle");
					vaisseau.setCouleur(Couleur.JAUNE);

				} else {
					vaisseau.setCouleur(Couleur.BLEU);
				}
			}*/
			
			
			f.rafraichir();
		}

	}

}
