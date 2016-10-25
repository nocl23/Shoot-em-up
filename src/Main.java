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
		Rectangle fond = new Rectangle(Couleur.BLEU, new Point(0, 0), 640, 480, true);
		Joueur vaisseau = new Joueur ("./img/vaisseau.png",new Point(295,40),49,85);
		ArrayList<Tir> munition = new ArrayList<Tir>();
		// frequence pour que le joueur ne tire pas de missiles à chaque tour de boucle while
		int frequence = 0;
		int indiceMissile = 0;
				
		/*for(int i = 0 ; i<50 ; i++){
			munition.add(i,missile);
		}*/
		
		
		/*Ajouter plusieurs missile dans ArrayList
		 * 
		 * */
		/*Besoin pour missiles illimités*/
		
		
		f.ajouter(fond);
		f.ajouter(vaisseau);

		while (true) {
			try {
				Thread.sleep(20);
		
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			frequence++;
			// Déplacement du vaisseau
			if (clavier.getDroite() && vaisseau.getB().getX() < 635) {
				vaisseau.translater(5, 0);
			} else if (clavier.getGauche() && vaisseau.getA().getX() > 0) {
				vaisseau.translater(-5, 0);
			} else if (clavier.getHaut() && vaisseau.getA().getY() < 395){
				vaisseau.translater(0, 5);
			} else if (clavier.getBas() && vaisseau.getB().getY() > vaisseau.getHauteur()){
				vaisseau.translater(0, -5);
			} 
			
			// Tir d'un missile
			if(clavier.getEspace()){
				
				//Rectangle missile = new Rectangle(Couleur.JAUNE,new Point((vaisseau.getB().getX())-30,((vaisseau.getB().getY())) + 10),10,10,true);
				// idée pour calcule Rectangle missile = new Rectangle(Couleur.JAUNE,new Point(((vaisseau.getA().getX()) + vaisseau.getLargeur())/2,(vaisseau.getB().getY())),10,10,true);
				if(frequence > 15){
				Tir missile = new Tir(Couleur.JAUNE,new Point((vaisseau.getB().getX())-30,((vaisseau.getB().getY())) + 10),10,10,true);
				munition.add(missile);
				f.ajouter((Dessin) munition.get(indiceMissile));
				indiceMissile++;
				//missile.atteint(f);
				frequence = 0;
				}			
				
				
			}
			
			for(int i = 0 ; i< munition.size() ; i++){
				munition.get(i).translater(0, 10);
			}
			
			
			f.rafraichir();
		}

	}

}
