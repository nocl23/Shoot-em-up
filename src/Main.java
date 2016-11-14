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
		
		//valeurs amenées à changer en fonction du mode
		Rectangle fond = new Rectangle(Couleur.BLANC, new Point(0, 0), 1000, 700, true);
		Joueur vaisseau = new Joueur("./img/vaisseau.png", new Point(295, 40), 49, 85);
		ArrayList<Tir> munitionJ = new ArrayList<Tir>();
		ArrayList<Tir> munitionE = new ArrayList<Tir>();
		ArrayList<Tir> bonus = new ArrayList<Tir>();
		// freqJoueur pour que le joueur ne tire pas de missiles à chaque tour de
		// boucle while
		int freqJoueur = 0;
		int indiceMissile = 0;
		int freqEnnemi = 0;
		int freqBonus=0;
		boolean touche = false;
		boolean bonusRapide = false;
		f.ajouter(fond);
		int AllEnnemisTouche = 0;
		int randomennemis=(int) (Math.random()*(20)+1);
		Ennemi ennemis [] = new Ennemi[randomennemis];
		System.out.println(randomennemis);
		Texte vieJoueur = new Texte("Vie du joueur: "+vaisseau.getVie(),new Font("tahoma",12,12),new Point(50,50));
		f.ajouter(vieJoueur);

		// Position initiale aléatoire des ennemis
		int xEnnemi;
		int yEnnemi = f.getHeight() - 100;

		for (int i = 0; i < randomennemis; i++) { // creation de 8 ennemis positionnés aléatoirement en abscisse

			xEnnemi = (int) (Math.random()*(f.getWidth() - 116));

			if(i>(randomennemis/2)){      // Placement de 4 ennemis par ligne en changant l'ordonnée des 4 derniers ennemis
				yEnnemi = f.getHeight()-200;
			}

			ennemis[i]=(new Ennemi("./img/ennemi.png", new Point(xEnnemi, yEnnemi), 116, 59));
			f.ajouter(ennemis[i]);
		}
		
		f.ajouter(vaisseau);
		

		// boucle tourne si le joueur a encore des vies et si tous les ennemis n ont pas ete touches
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
			// Déplacement du vaisseau
			if (clavier.getDroite() && vaisseau.getB().getX() < f.getWidth() - 5 && !bonusRapide) {
				vaisseau.translater(5, 0);
			}else if (clavier.getDroite() && vaisseau.getA().getX() < f.getWidth() - 5 && bonusRapide) {
					vaisseau.translater(10, 0);	
			}else if (clavier.getGauche() && vaisseau.getA().getX() > 0 && !bonusRapide) {
				vaisseau.translater(-5, 0);
			}else if (clavier.getGauche() && vaisseau.getA().getX() > 0 && bonusRapide) {
					vaisseau.translater(-10, 0);
			} else if (clavier.getHaut() && vaisseau.getB().getY() < f.getHeight()/2 && !bonusRapide) {
				vaisseau.translater(0, 5);
			}else if (clavier.getHaut() && vaisseau.getB().getY() < f.getHeight()/2 && bonusRapide) {
				vaisseau.translater(0, 10);
			}else if (clavier.getBas() && vaisseau.getB().getY() > vaisseau.getHauteur() && !bonusRapide) {
				vaisseau.translater(0, -5);
			}else if (clavier.getBas() && vaisseau.getB().getY() > vaisseau.getHauteur() &&bonusRapide) {
				vaisseau.translater(0, -10);
			}

			// Tir d'un missile Joueur
			if (clavier.getEspace()) {

				if (freqJoueur > 15) {
					Tir missileJ = new Tir("./img/missileJ.png", new Point((vaisseau.getA().getX() + vaisseau.getLargeur()/2), ((vaisseau.getB().getY())) + 10),21 , 34);
					munitionJ.add(missileJ);
					f.ajouter((Dessin) munitionJ.get(indiceMissile));
					indiceMissile++;
					freqJoueur = 0;
				}

			}

			// Avancement des missiles Joueur
			
				for (int indexMunitionJ = 0; indexMunitionJ < munitionJ.size(); indexMunitionJ++) {
					munitionJ.get(indexMunitionJ).translater(0,15);
					for (int ennemy=0;ennemy < ennemis.length; ennemy++){
					if(munitionJ.get(indexMunitionJ).intersection(ennemis[ennemy]) && !ennemis[ennemy].getEnnemiTouche()){
				    	ennemis[ennemy].setEnnemiTouche(true);
						//System.out.println(ennemis.length);
						f.supprimer(ennemis[ennemy]);
						//System.out.println(ennemis.length+"cc");
						AllEnnemisTouche++;
					}
					
					
				}
			}
				System.out.println(AllEnnemisTouche+" ennemi touche");
				// Déplacement des ennemis de droite à gauche
			for (int i = 0; i < ennemis.length; i++) {

				if(ennemis[i].getB().getX() < f.getWidth()+1 && !(ennemis[i]).getParoi()){
					ennemis[i].translater(10, 0);

				}
				if (ennemis[i].getB().getX() > f.getWidth()){
					ennemis[i].setParoi(true);
				} 
				if(ennemis[i].getParoi()){

					ennemis[i].translater(-10,0);
				}
				if(ennemis[i].getA().getX() < 0){
					ennemis[i].setParoi(false);
				}	

			}

			//Tir aléatoire des ennemis
			if(freqEnnemi == 60){
				freqEnnemi = 0;
				int numeroVaisseau = (int) (Math.random()*randomennemis);
				System.out.println(numeroVaisseau + "numero vaisseau");
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
					vieJoueur.setTexte("Vie du joueur: "+vaisseau.getVie());
					f.supprimer(munitionE.get(i));
					munitionE.remove(munitionE.get(i));
					System.out.println(vaisseau.getVie());
					touche=false;
					
				}

			}
			
			
			if(freqBonus == 300){
				Tir missileBonus = new Tir("./img/missileJ.png", new Point((int) (Math.random()*1000),600), 21, 34);
				bonus.add(missileBonus);
				f.ajouter(missileBonus);
			}
				for (int i = 0 ; i < bonus.size();i++){
				bonus.get(i).translater(0, -5);
				
				if(bonus.get(i).intersection(vaisseau)){
					bonusRapide = true;
					System.out.println("freqBonus avant "+freqBonus);
				}
				if (freqBonus == 350){
					bonusRapide = false;
					freqBonus = 0;
				}
					
					
				
				
			}
			
			

			f.rafraichir();
		}
		
		Rectangle fondGO = new Rectangle(Couleur.NOIR, new Point(0, 0), 1000, 700, true);
		Texte gameover = new Texte(Couleur.BLANC,"GAME OVER",new Font("tahoma",120,120) , new Point(500,300));
		Texte ennemiLoose = new Texte(Couleur.BLANC,"YOU WIN",new Font("tahoma",120,120) , new Point(500,300));
		f.ajouter(fondGO);
		if(vaisseau.getVie()>0){
		f.ajouter(ennemiLoose);
		}else{
			f.ajouter(gameover);
		}
			
	}

}
