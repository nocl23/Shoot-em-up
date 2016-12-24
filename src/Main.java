import java.awt.Font;
import java.util.ArrayList;

import MG2D.*;
import MG2D.geometrie.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Fenetre f = new Fenetre("Shoot'em Up", 1000, 650);
		Clavier clavier = new Clavier();
		Souris souris = new Souris(480);
		f.addKeyListener(clavier);
		f.addMouseListener(souris);
		f.getP().addMouseListener(souris);
		f.getP().addMouseMotionListener(souris);
		f.addMouseMotionListener(souris);

		// valeurs amenées à changer en fonction du mode
		Rectangle fond = new Rectangle(Couleur.BLANC, new Point(0, 0), 1000, 700, true);
		Joueur vaisseau = new Joueur("./img/vaisseau.png", new Point(295, 40), 49, 85);
		ArrayList<Tir> munitionJ = new ArrayList<Tir>();
		ArrayList<Tir> munitionE = new ArrayList<Tir>();
		ArrayList<Bonus> bonus = new ArrayList<Bonus>();
		// freqJoueur pour que le joueur ne tire pas de missiles à chaque tour
		// de
		// boucle while
		int freqJoueur = 0;
		int freqEnnemi = 0;
		int freqBonus = 0;
		boolean touche = false;
		int bonusToucheVaisseau = 0; // 0 normal 1:rapide  2:lent
		boolean bonusVitesse = false;
		boolean malusVitesse = false;
		boolean bonusTirVaisseau = false;
		f.ajouter(fond);
		int AllEnnemisTouche = 0;
		int randomennemis = (int) (Math.random() * (20) + 1);
		Ennemi ennemis[] = new Ennemi[randomennemis];
		System.out.println(randomennemis+"randomennemis");
		Texte vieJoueur = new Texte("Vie du joueur: " + vaisseau.getVie(), new Font("tahoma", 12, 12),
				new Point(50, 50));
		f.ajouter(vieJoueur);

		// Position initiale aléatoire des ennemis
		int xEnnemi;
		int yEnnemi = f.getHeight() - 100;
		//int xAvant=0;
		for (int i = 0; i < randomennemis; i++) { // creation de 8 ennemis
													// positionnés aléatoirement
													// en abscisse

			xEnnemi = (int) (Math.random() * (f.getWidth() - 116));
		//	xAvant=xEnnemi;

			if (i > (randomennemis / 2)) { // Placement de 4 ennemis par ligne
											// en changant l'ordonnée des 4
											// derniers ennemis
				yEnnemi = f.getHeight() - 200;
			}

			ennemis[i] = (new Ennemi("./img/ennemi.png", new Point(xEnnemi, yEnnemi), 116, 59));
			f.ajouter(ennemis[i]);
		}

		f.ajouter(vaisseau);

		// boucle tourne si le joueur a encore des vies et si tous les ennemis n
		// ont pas ete touches
		while (true && vaisseau.getVie() > 0 && AllEnnemisTouche < randomennemis) {
			try {
				Thread.sleep(30);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			freqBonus++;
			freqJoueur++;
			freqEnnemi++;
			f.setAffichageFPS(true);
//******* PROBLEME LIMITE VAISSEAU *****
			// Déplacement du vaisseau normal
			if (bonusVitesse) { //si bonusVitesse actif > augmente la vitesse
				if (clavier.getDroite() && vaisseau.getB().getX() < f.getWidth()) {
					vaisseau.translater(20, 0);
				} else if (clavier.getGauche() && vaisseau.getA().getX() > 0) {
					vaisseau.translater(-20, 0);
				} else if (clavier.getHaut() && vaisseau.getB().getY() < f.getHeight() / 2) {
					vaisseau.translater(0, 20);
				} else if (clavier.getBas() && vaisseau.getB().getY() > vaisseau.getHauteur()) {
					vaisseau.translater(0, -20);
				}
			}
			else if (malusVitesse) { //si malusVitesse actif > reduit la vitesse
				if (clavier.getDroite() && vaisseau.getB().getX() < f.getWidth()) {
					vaisseau.translater(4, 0);
				} else if (clavier.getGauche() && vaisseau.getA().getX() > 0) {
					vaisseau.translater(-4, 0);
				} else if (clavier.getHaut() && vaisseau.getB().getY() < f.getHeight() / 2) {
					vaisseau.translater(0, 4);
				} else if (clavier.getBas() && vaisseau.getB().getY() > vaisseau.getHauteur()) {
					vaisseau.translater(0, -4);
				}
			}else if (clavier.getDroite() && vaisseau.getB().getX() < f.getWidth()) { //sinon vitesse normal
				vaisseau.translater(8, 0);
			} else if (clavier.getGauche() && vaisseau.getA().getX() > 0) {
				vaisseau.translater(-8, 0);
			} else if (clavier.getHaut() && vaisseau.getB().getY() < f.getHeight() / 2) {
				vaisseau.translater(0, 8);
			} else if (clavier.getBas() && vaisseau.getB().getY() > vaisseau.getHauteur()) {
				vaisseau.translater(0, -8);
			}


			// Tir d'un missile Joueur
			if (clavier.getEspace()) {
			
				if (!bonusTirVaisseau && freqJoueur > 15 ) {
					Tir missileJ = new Tir("./img/missileJ.png",
							new Point((vaisseau.getA().getX() + vaisseau.getLargeur() / 2),
									((vaisseau.getB().getY())) + 10),
							21, 34);
					munitionJ.add(missileJ);
					//System.out.println(indiceMissile+" indice du missile ajouté");
					//f.ajouter((Dessin) munitionJ.get(indiceMissile));
				    f.ajouter(missileJ);
					freqJoueur = 0;
				
				}
				else if(bonusTirVaisseau && freqJoueur > 5){  //Tir bonus du joueur
					Tir missileBonusJoueur = new Tir("./img/missileBonus.png",
							new Point((vaisseau.getA().getX() + vaisseau.getLargeur() / 2),
									((vaisseau.getB().getY())) + 10),
							21, 34);
					munitionJ.add(missileBonusJoueur);
					f.ajouter(missileBonusJoueur);
				
					
					freqJoueur = 0;
				}
			}

			// Avancement des missiles Joueur + collisions ennemis
			
			for (int indexMunitionJ = 0; indexMunitionJ < munitionJ.size(); indexMunitionJ++) {
				System.out.println(munitionJ.size() + "munition size");
				munitionJ.get(indexMunitionJ).translater(0, 15);
				for (int ennemy = 0; ennemy < ennemis.length; ennemy++) {
					if(munitionJ.get(indexMunitionJ).getA().getY()<f.getHeight()){
						
						if (munitionJ.get(indexMunitionJ).intersection(ennemis[ennemy])
								&& !munitionJ.get(indexMunitionJ).getAttaque() && !ennemis[ennemy].getEnnemiTouche()) {
							ennemis[ennemy].setEnnemiTouche(true);
							
							f.supprimer(ennemis[ennemy]);
							System.out.println(munitionJ.size()+"size");
							f.supprimer(munitionJ.get(indexMunitionJ));
							
							munitionJ.get(indexMunitionJ).setAttaque(true);
							System.out.println(munitionJ.size()+"size");
							
							
							AllEnnemisTouche++;
						}
					}
				}
			}
			
			
			// System.out.println(AllEnnemisTouche+" ennemi touche");
			// Déplacement des ennemis de droite à gauche
			for (int i = 0; i < ennemis.length; i++) {

				if (ennemis[i].getB().getX() < f.getWidth() + 1 && !(ennemis[i]).getParoi()) {
					ennemis[i].translater(10, 0);

				}
				if (ennemis[i].getB().getX() > f.getWidth()) {
					ennemis[i].setParoi(true);
				}
				if (ennemis[i].getParoi()) {

					ennemis[i].translater(-10, 0);
				}
				if (ennemis[i].getA().getX() < 0) {
					ennemis[i].setParoi(false);
				}

			}

			// Tir aléatoire des ennemis
			if (freqEnnemi == 60) {
				freqEnnemi = 0;
				int numeroVaisseau = (int) (Math.random() * randomennemis);
				// System.out.println(numeroVaisseau + "numero vaisseau");
				if (!ennemis[numeroVaisseau].getEnnemiTouche()) {
					Tir missileE = new Tir("./img/missileE.png", new Point((ennemis[numeroVaisseau].getB().getX() - 45),
							((ennemis[numeroVaisseau].getB().getY()) - 100)), 21, 34);
					munitionE.add(missileE);
					f.ajouter(missileE);
				}
			}
			// Avancement des missiles ennemis
			for (int i = 0; i < munitionE.size(); i++) {
				munitionE.get(i).translater(0, -5);

				// Collisions missiles ennemis sur Joueur
				if (munitionE.get(i).intersection(vaisseau) && !touche) {
					touche = true;
					vaisseau.setVie(vaisseau.getVie() - 1);
					vieJoueur.setTexte("Vie du joueur: " + vaisseau.getVie());
					f.supprimer(munitionE.get(i));
					munitionE.remove(munitionE.get(i));
					System.out.println(vaisseau.getVie()+"Vie");
					touche = false;

				}

			}

			// Apparition bonus vitesse
			if (freqBonus == 300) {
				int choixBonus = (int)( Math.random()*3);
				System.out.println(choixBonus+"choix bonus");
				if(choixBonus == 0){
					Bonus missileBonus = new Bonus("./img/bonusVitesse.png", new Point((int) (Math.random() * 1000), 600), 21, 34);
					bonus.add(missileBonus);
					f.ajouter(missileBonus);
					//bonusVitesse=true;
					bonusToucheVaisseau = 1;
				
				}else if(choixBonus == 1){
					Bonus missileMalus = new Bonus("./img/malusVitesse.png", new Point((int) (Math.random() * 1000), 600), 21, 34);
					bonus.add(missileMalus);
					f.ajouter(missileMalus);
					//malusVitesse=true;
					bonusToucheVaisseau = 2;
				}else if(choixBonus == 2){
					Bonus bonusTir = new Bonus("./img/bonusTir.png", new Point((int) (Math.random() * 1000), 600), 21, 34);
					bonus.add(bonusTir);
					f.ajouter(bonusTir);
					//malusVitesse=true;
					bonusToucheVaisseau = 3;
				}
			}
			for (int i = 0; i < bonus.size(); i++) {
				bonus.get(i).translater(0, -5);

				if (bonus.get(i).intersection(vaisseau) && bonusToucheVaisseau == 1) {
					// System.out.println("freqBonus avant "+freqBonus);
					f.supprimer(bonus.get(i));
					bonus.remove(bonus.get(i));
					bonusVitesse = true;

				}else if(bonus.get(i).intersection(vaisseau) && bonusToucheVaisseau == 2){
					f.supprimer(bonus.get(i));
					bonus.remove(bonus.get(i));
					malusVitesse = true;
				}
				else if(bonus.get(i).intersection(vaisseau) && bonusToucheVaisseau == 3){
					f.supprimer(bonus.get(i));
					bonus.remove(bonus.get(i));
					bonusTirVaisseau = true;
				}
				// Temps du bonus
				if (freqBonus == 310) {
					bonusVitesse = false;
					malusVitesse = false;
					bonusTirVaisseau = false;
					freqBonus = 0;
				}

			}

			f.rafraichir();
		}

		// Affichage ecran de fin de jeu
		Rectangle fondGO = new Rectangle(Couleur.NOIR, new Point(0, 0), 1000, 700, true);
		Texte gameover = new Texte(Couleur.BLANC, "GAME OVER", new Font("tahoma", 120, 120), new Point(500, 300));
		Texte ennemiLoose = new Texte(Couleur.BLANC, "YOU WIN", new Font("tahoma", 120, 120), new Point(500, 300));
		f.ajouter(fondGO);
		if (vaisseau.getVie() > 0) {
			f.ajouter(ennemiLoose);
		} else {
			f.ajouter(gameover);
		}

	}

}
