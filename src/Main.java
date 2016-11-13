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
		Ennemi ennemis [] = new Ennemi[8];
		ArrayList<Tir> munitionE = new ArrayList<Tir>();
		// freqJoueur pour que le joueur ne tire pas de missiles à chaque tour de
		// boucle while
		int freqJoueur = 0;
		int indiceMissile = 0;
		int freqEnnemi = 0;
		boolean touche = false;
		f.ajouter(fond);
		int AllEnnemisTouche = 0;

		// Position initiale aléatoire des ennemis
		int xEnnemi;
		int yEnnemi = 390;

		for (int i = 0; i < 8; i++) { // creation de 8 ennemis positionnés aléatoirement en abscisse

			xEnnemi = (int) (Math.random()*(f.getWidth() - 116));

			if(i>3){      // Placement de 4 ennemis par ligne en changant l'ordonnée des 4 derniers ennemis
				yEnnemi = 300;
			}

			ennemis[i]=(new Ennemi("./img/ennemi.png", new Point(xEnnemi, yEnnemi), 116, 59));
			f.ajouter(ennemis[i]);
		}

		f.ajouter(vaisseau);

		while (true && vaisseau.getVie() > 0 && AllEnnemisTouche < 8) {
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
					freqJoueur = 0;
				}

			}

			// Avancement des missiles Joueur
			
				for (int indexMunitionJ = 0; indexMunitionJ < munitionJ.size(); indexMunitionJ++) {
					for (int ennemy=0;ennemy < ennemis.length; ennemy++){

					munitionJ.get(indexMunitionJ).translater(0, 2);

					if(munitionJ.get(indexMunitionJ).intersection(ennemis[ennemy]) && !ennemis[ennemy].getEnnemiTouche()){
						ennemis[ennemy].setEnnemiTouche(true);
						System.out.println(ennemis.length);
						f.supprimer(ennemis[ennemy]);
						System.out.println(ennemis.length+"cc");
						AllEnnemisTouche++;
					}
					
					
				}
			}
				System.out.println(AllEnnemisTouche+" ennemi touche");

			for (int i = 0; i < ennemis.length; i++) {

				if(ennemis[i].getB().getX() < f.getWidth()+1 && !(ennemis[i]).getParoi()){
					ennemis[i].translater(5, 0);

				}
				if (ennemis[i].getB().getX() > f.getWidth()){
					ennemis[i].setParoi(true);
				} 
				if(ennemis[i].getParoi()){

					ennemis[i].translater(-5,0);
				}
				if(ennemis[i].getA().getX() < 0){
					ennemis[i].setParoi(false);
				}	

			}

			//Tir aléatoire des ennemis
			if(freqEnnemi == 60){
				freqEnnemi = 0;
				int numeroVaisseau = (int) (Math.random()*7+1);
				if(!ennemis[numeroVaisseau].getEnnemiTouche()){
					Tir missileE = new Tir("./img/missileE.png", 
							new Point((ennemis[numeroVaisseau].getB().getX()-45), ((ennemis[numeroVaisseau].getB().getY()) - 100)),21 , 34);
					munitionE.add(missileE);
					f.ajouter(missileE);

				}
			}
			//Avancement des missiles ennemis
			for(int i = 0; i<munitionE.size();i++){
				munitionE.get(i).translater(0, -5);


				// Collisions missiles ennemis sur Joueur
				if(munitionE.get(i).intersection(vaisseau) && !touche){
					touche=true;
					vaisseau.setVie(vaisseau.getVie()-1);
					f.supprimer(munitionE.get(i));
					munitionE.remove(munitionE.get(i));
					System.out.println(vaisseau.getVie());
					touche=false;

				}


			}


			f.rafraichir();
		}
	}

}
