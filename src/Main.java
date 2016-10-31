import java.util.ArrayList;
import java.util.Iterator;

import MG2D.*;
import MG2D.geometrie.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Fenetre f = new Fenetre("Shoot'em Up", 640, 480);
		Clavier clavier = new Clavier();
		Souris souris = new Souris(480);
		f.addKeyListener(clavier);
		f.addMouseListener(souris);
		f.getP().addMouseListener(souris);
		f.getP().addMouseMotionListener(souris);
		f.addMouseMotionListener(souris);
		Rectangle fond = new Rectangle(Couleur.BLANC, new Point(0, 0), 640, 480, true);
		Joueur vaisseau = new Joueur("./images/vaisseau.png", new Point(295, 40), 49, 85);
		ArrayList<Tir> munition = new ArrayList<Tir>();
		ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();
		// frequence pour que le joueur ne tire pas de missiles à chaque tour de
		// boucle while
		int frequence = 0;
		int indiceMissile = 0;
		f.ajouter(fond);
		
		
		// Position initiale aléatoire des ennemis
		int xEnnemi;
		int yEnnemi = 390;
		
		for (int i = 0; i < 8; i++) { // creation de 8 ennemis positionnés aléatoirement en abscisse
			
			xEnnemi = (int) (Math.random()*(f.getWidth() - 116));
			
			if(i>3){      // Placement de 4 ennemis par ligne en changant l'ordonnée des 4 derniers ennemis
				yEnnemi = 300;
			}

			ennemis.add(new Ennemi("./images/ennemi.png", new Point(xEnnemi, yEnnemi), 116, 59));
			

			f.ajouter(ennemis.get(i));
		}
		
		f.ajouter(vaisseau);

		while (true) {
			try {
				Thread.sleep(20);


			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			frequence++;
			// Déplacement du vaisseau
			if (clavier.getDroite() && vaisseau.getB().getX() < f.getWidth() - 5) {
				vaisseau.translater(5, 0);
			} else if (clavier.getGauche() && vaisseau.getA().getX() > 0) {
				vaisseau.translater(-5, 0);
			} else if (clavier.getHaut() && vaisseau.getA().getY() < f.getHeight() - 110) {
				vaisseau.translater(0, 5);
			} else if (clavier.getBas() && vaisseau.getB().getY() > vaisseau.getHauteur()) {
				vaisseau.translater(0, -5);
			}

			// Tir d'un missile
			if (clavier.getEspace()) {

				// Rectangle missile = new Rectangle(Couleur.JAUNE,new
				// Point((vaisseau.getB().getX())-30,((vaisseau.getB().getY()))
				// + 10),10,10,true);
				// idée pour calcule Rectangle missile = new
				// Rectangle(Couleur.JAUNE,new Point(((vaisseau.getA().getX()) +
				// vaisseau.getLargeur())/2,(vaisseau.getB().getY())),10,10,true);
				if (frequence > 15) {
					Tir missile = new Tir(Couleur.ROUGE,
							new Point((vaisseau.getB().getX()) - 30, ((vaisseau.getB().getY())) + 10), 10, 10, true);
					munition.add(missile);
					f.ajouter((Dessin) munition.get(indiceMissile));
					indiceMissile++;
					// missile.atteint(f);
					frequence = 0;
				}

			}
// Tir des munitions
			for (int i = 0; i < munition.size(); i++) {
				munition.get(i).translater(0, 10);
			}
// Déplacement aléatoire des ennemis
			for (int i = 0; i < ennemis.size(); i++) {
				
				if(ennemis.get(i).getB().getX() < f.getWidth()+1 && !(ennemis.get(i)).getParoi()){
					ennemis.get(i).translater(5, 0);
					
				}
				if (ennemis.get(i).getB().getX() > f.getWidth()){
					ennemis.get(i).setParoi(true);
				} 
				if(ennemis.get(i).getParoi()){
				
					ennemis.get(i).translater(-5,0);
				}
				if(ennemis.get(i).getA().getX() < 0){
					ennemis.get(i).setParoi(false);
				}
				
				
			}

			f.rafraichir();
		}

	}

}
