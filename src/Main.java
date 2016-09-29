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
		Rectangle r = new Rectangle(Couleur.BLEU, new Point(40, 0), 50, 70, true);
		f.ajouter(r);

		while (true) {
			try {
				Thread.sleep(20);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			if (clavier.getDroite() && r.getB().getX() < 640) {
				r.translater(5, 0);
			} else if (clavier.getGauche() && r.getA().getX() > 0) {
				r.translater(-5, 0);
			}

			if (souris.getClicGauche()) {
				// System.out.println(souris.getPosition());
				if (r.intersection(souris.getPosition())) {

					System.out.println("Rectangle");
					r.setCouleur(Couleur.JAUNE);

				} else {
					r.setCouleur(Couleur.BLEU);
				}
			}
			
			
			f.rafraichir();
		}

	}

}
