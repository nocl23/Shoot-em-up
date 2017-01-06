import java.awt.Font;
import java.util.ArrayList;

import MG2D.*;
import MG2D.geometrie.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Fenetre f = new Fenetre("Shoot'em Up", 700, 650);
		Clavier clavier = new Clavier();
		Souris souris = new Souris(480);
		f.addKeyListener(clavier);
		f.addMouseListener(souris);
		f.getP().addMouseListener(souris);
		f.getP().addMouseMotionListener(souris);
		f.addMouseMotionListener(souris);
		
		// valeurs amenées à changer en fonction du mode
		Texture fond = new Texture("./img/fond.png", new Point(0, 0), 1000, 700);
		Joueur vaisseau = new Joueur("./img/vaisseau.png", new Point(295, 40), 49, 85);
		ArrayList<Tir> munitionJ = new ArrayList<Tir>();
		ArrayList<Tir> munitionE = new ArrayList<Tir>();
		ArrayList<Bonus> bonus = new ArrayList<Bonus>();
		
		// Fréquence des tirs du joueur
		int freqJoueur = 0;
		int freqEnnemi = 0;
		int freqBonus = 0;
		
		//Savoir quel bonus touche le vaisseau
		int bonusToucheVaisseau = 0;
		// 3 bonus différents
		boolean bonusVitesse = false;
		boolean malusVitesse = false;
		boolean bonusTirVaisseau = false;
		
		f.ajouter(fond);
		// Compteur d'ennemis touchés par le vaisseau
		int AllEnnemisTouche = 0;
		
		//Nombre d'ennemis aléatoire à chaque partie
		int randomennemis = (int) (Math.random() * (20) + 1);
		
		Ennemi ennemis[] = new Ennemi[randomennemis];
	
		// Position initiale aléatoire des ennemis
		int xEnnemi;
		int yEnnemi = f.getHeight() - 150;
		
		/*
		 * Boucle parcourant tous les ennemis , affiche aléatoirement la moitié sur une ligne et l'autre moitié
		 * sur la deuxième ligne
		 */
		
		for (int i = 0; i < randomennemis; i++) { 
			xEnnemi = (int) (Math.random() * (f.getWidth() - 116));
			if (i > (randomennemis / 2)) { 	
				yEnnemi = f.getHeight() - 250;
			}
			ennemis[i] = (new Ennemi("./img/alien.png", new Point(xEnnemi, yEnnemi), 100, 100));
			f.ajouter(ennemis[i]);
		}

		// Ajout du vaisseau joueur
		f.ajouter(vaisseau);
		
		Rectangle contourJauge = new Rectangle(Couleur.JAUNE, new Point(10,10),100,35);
		Rectangle jauge = new Rectangle(Couleur.VERT, new Point(10,10), 100, 35,true);
		f.ajouter(jauge);
		f.ajouter(contourJauge);
		
		/* Texte affichant la vie du vaisseau (initialement à 5)
				Texte vieJoueur = new Texte(Couleur.BLANC,"" + vaisseau.getVie(), new Font("tahoma", 12, 12),
						new Point(50, 50));
				f.ajouter(vieJoueur);*/
		
		// boucl si le joueur a encore des vies et si tous les ennemis n
		// ont pas ete touches
		while (true && vaisseau.getVie() > 0 && AllEnnemisTouche < randomennemis) {
			try {
				Thread.sleep(30);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			// On incrémente les fréquences à chaque tour
			freqBonus++;
			freqJoueur++;
			freqEnnemi++;
			
			f.setAffichageFPS(true);

			// Déplacement du vaisseau si bonus vitesse actif
			if (bonusVitesse) { 
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
			// Déplacement du vaisseau si malus vitesse
			else if (malusVitesse) { 
				if (clavier.getDroite() && vaisseau.getB().getX() < f.getWidth()) {
					vaisseau.translater(4, 0);
				} else if (clavier.getGauche() && vaisseau.getA().getX() > 0) {
					vaisseau.translater(-4, 0);
				} else if (clavier.getHaut() && vaisseau.getB().getY() < f.getHeight() / 2) {
					vaisseau.translater(0, 4);
				} else if (clavier.getBas() && vaisseau.getB().getY() > vaisseau.getHauteur()) {
					vaisseau.translater(0, -4);
				}
				// Sinon vitesse normale
			}else if (clavier.getDroite() && vaisseau.getB().getX() < f.getWidth()) { 
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
			// Tir sans bonus Tir , fréquence "normale"
				if (!bonusTirVaisseau && freqJoueur > 15 ) {
					Tir missileJ = new Tir("./img/missileJ.png",
							new Point((vaisseau.getA().getX() + vaisseau.getLargeur() / 2),
									((vaisseau.getB().getY())) + 10),
							21, 34);
					munitionJ.add(missileJ);
				    f.ajouter(missileJ);
					freqJoueur = 0;
				
				}
				// Tir avec bonus Tir, augmente la fréquence des tirs >> réduction de la fréquence de tir
				else if(bonusTirVaisseau && freqJoueur > 5){  
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
				munitionJ.get(indexMunitionJ).translater(0, 15);
				
				// Parcours des ennemis 
				for (int ennemy = 0; ennemy < ennemis.length; ennemy++) {
					// Si le tir du joueur est encore dans la fenêtre
					if(munitionJ.get(indexMunitionJ).getA().getY() < f.getHeight()){
						//Si c'est un Tir qui n'a encore touché aucun ennemi et qu'il touche un ennemi pas encore touché
						if (munitionJ.get(indexMunitionJ).intersection(ennemis[ennemy])
								&& !munitionJ.get(indexMunitionJ).getAttaque() && !ennemis[ennemy].getEnnemiTouche()) {
							ennemis[ennemy].setEnnemiTouche(true);
							
							// On supprime l'ennemi et le Tir
							f.supprimer(ennemis[ennemy]);
							f.supprimer(munitionJ.get(indexMunitionJ));
							
							// La munition a attaqué un ennemi, elle ne pourra plus toucher d'autres ennemis
							munitionJ.get(indexMunitionJ).setAttaque(true);
							
							// Incrémentation du compteur d'ennemis touchés
							AllEnnemisTouche++;
						}
					}
				}
			}
			
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
				// Réinitialisation de la fréquence
				freqEnnemi = 0;
				// Choix aléatoire du vaisseau ennemi
				int numeroVaisseau = (int) (Math.random() * randomennemis);
				
				// Vérification si l'ennemi existe encore, si oui Tir autorisé
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
				if (munitionE.get(i).intersection(vaisseau) && !vaisseau.getTouche()) {
					
					vaisseau.setTouche(true);
					//Décrémente une vie
					vaisseau.setVie(vaisseau.getVie() - 1);
					// Mise à jour de l'affichage
			//		vieJoueur.setTexte("" + vaisseau.getVie());
					jauge.setLargeur(jauge.getLargeur()-20);
					
					if(vaisseau.getVie() == 2){
						jauge.setCouleur(Couleur.ORANGE);
					}else if(vaisseau.getVie() == 1){
						jauge.setCouleur(Couleur.ROUGE);
					}
					
					f.supprimer(munitionE.get(i));
					munitionE.remove(munitionE.get(i));
					//System.out.println(vaisseau.getVie()+"Vie");
					vaisseau.setTouche(false);
				}
			}

			// Apparition bonus à chaque fois frequence = 300
			if (freqBonus == 300) {
				// Choix random du bonus
				int choixBonus = (int)( Math.random()*3);
				
				switch(choixBonus){
				case 0:
					Bonus missileBonus = new Bonus("./img/bonusVitesse.png", new Point((int) (Math.random() * 1000), 600), 21, 34);
					bonus.add(missileBonus);
					f.ajouter(missileBonus);
					
					bonusToucheVaisseau = 1;
					break;
					
				case 1:
					Bonus missileMalus = new Bonus("./img/malusVitesse.png", new Point((int) (Math.random() * 1000), 600), 21, 34);
					bonus.add(missileMalus);
					f.ajouter(missileMalus);
					
					bonusToucheVaisseau = 2;
					break;
					
				case 2:
					Bonus bonusTir = new Bonus("./img/bonusTir.png", new Point((int) (Math.random() * 1000), 600), 21, 34);
					bonus.add(bonusTir);
					f.ajouter(bonusTir);
					
					bonusToucheVaisseau = 3;
					break;
				}
			}
			
			//Translation du bonus
			for (int i = 0; i < bonus.size(); i++) {
				bonus.get(i).translater(0, -5);
				// Différenciation des bonus que le joueur attrape
				
				if (bonus.get(i).intersection(vaisseau) && bonusToucheVaisseau == 1) {
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
		Texture fin = new Texture("./img/accueil.jpg", new Point(0, 0), 700, 700);
		Texte gameover = new Texte(Couleur.BLANC, "GAME OVER tu n'as pas réussi ta mission...", new Font("tahoma", 30, 30), new Point(350, 400));
		Texte ennemiLoose = new Texte(Couleur.BLANC, "BRAVO petit padawan", new Font("tahoma", 40, 40), new Point(350, 300));
		f.ajouter(fin);
		if (vaisseau.getVie() > 0) {
			f.ajouter(ennemiLoose);
		} else {
			f.ajouter(gameover);
			int ennemies = randomennemis - AllEnnemisTouche;
			Texte stats = new Texte(Couleur.BLANC, "Il restait encore "+ ennemies + " ennemis à tuer!", new Font("tahoma", 30, 30), new Point(350, 300));
			f.ajouter(stats);
		}
	
	}

}
