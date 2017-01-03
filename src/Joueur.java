/**
 * Classe Joueur représentant le joueur.
 */
import MG2D.*;
import MG2D.geometrie.*;

public class Joueur extends Texture {
	
	private int vie=5;
	boolean touche = false;


	public boolean getTouche() {
		return touche;
	}


	public void setTouche(boolean touche) {
		this.touche = touche;
	}


	public void setVie(int vie) {
		this.vie = vie;
	}


	public int getVie() {
		return vie;
	}


	public Joueur() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Joueur(Couleur couleur, String chemin, Point a, int larg, int haut) {
		super(couleur, chemin, a, larg, haut);
		// TODO Auto-generated constructor stub
	}


	public Joueur(Couleur couleur, String chemin, Point a) {
		super(couleur, chemin, a);
		// TODO Auto-generated constructor stub
	}


	public Joueur(String chemin, Point a, int larg, int haut) {
		super(chemin, a, larg, haut);
		// TODO Auto-generated constructor stub
	}


	public Joueur(String chemin, Point a) {
		super(chemin, a);
		// TODO Auto-generated constructor stub
	}


	public Joueur(Texture t) {
		super(t);
		// TODO Auto-generated constructor stub
	}


	

	
	
	


}
