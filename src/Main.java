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
		Joueur vaisseau = new Joueur("./img/vaisseau.png", new Point(295, 40), 49, 85);
		ArrayList<Tir> munitionJ = new ArrayList<Tir>();
		ArrayList<Ennemi> ennemis = new ArrayList<Ennemi>();
		ArrayList<Tir> munitionE = new ArrayList<Tir>();
		// freqJoueur pour que le joueur ne tire pas de missiles à chaque tour de
		// boucle while
		int freqJoueur = 0;
		int indiceMissile = 0;
		int freqEnnemi = 0;
		f.ajouter(fond);
		
		
		// Position initiale aléatoire des ennemis
		int xEnnemi;
		int yEnnemi = 390;
		
		for (int i = 0; i < 8; i++) { // creation de 8 ennemis positionnés aléatoirement en abscisse
			
			xEnnemi = (int) (Math.random()*(f.getWidth() - 116));
			
			if(i>3){      // Placement de 4 ennemis par ligne en changant l'ordonnée des 4 derniers ennemis
				yEnnemi = 300;
			}

			ennemis.add(new Ennemi("./img/ennemi.png", new Point(xEnnemi, yEnnemi), 116, 59));
			

			f.ajouter(ennemis.get(i));
		}
		
		f.ajouter(vaisseau);

		while (true) {
			try {
				Thread.sleep(30);


			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			freqJoueur++;
			freqEnnemi++;
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

			// Tir d'un missile Joueur
			if (clavier.getEspace()) {

				// Rectangle missile = new Rectangle(Couleur.JAUNE,new
				// Point((vaisseau.getB().getX())-30,((vaisseau.getB().getY()))
				// + 10),10,10,true);
				// idée pour calcule Rectangle missile = new
				// Rectangle(Couleur.JAUNE,new Point(((vaisseau.getA().getX()) +
				// vaisseau.getLargeur())/2,(vaisseau.getB().getY())),10,10,true);
				if (freqJoueur > 15) {
					Tir missileJ = new Tir("./img/missileJ.png", new Point((vaisseau.getB().getX()) - 30, ((vaisseau.getB().getY())) + 10),21 , 34);
					munitionJ.add(missileJ);
					f.ajouter((Dessin) munitionJ.get(indiceMissile));
					indiceMissile++;
					// missile.atteint(f);
					freqJoueur = 0;
				}

			}
			// Avancement des missiles Joueur
			for (int i = 0; i < munitionJ.size(); i++) {
				munitionJ.get(i).translater(0, 10);
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
			
			//Tir des ennemis
			if(freqEnnemi == 60){
				freqEnnemi = 0;
				int numeroVaisseau = (int) (Math.random()*7+1);
				Tir missileE = new Tir("./img/missileE.png", 
						new Point((ennemis.get(numeroVaisseau).getB().getX()-45), ((ennemis.get(numeroVaisseau).getB().getY()) - 100)),21 , 34);
				munitionE.add(missileE);
				f.ajouter(missileE);
			}
			//Avancement des missiles ennemis
			for(int i = 0; i<munitionE.size();i++){
				munitionE.get(i).translater(0, -5);
			}
			

			f.rafraichir();
		}

	}

}
