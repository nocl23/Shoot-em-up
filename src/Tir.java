/**
 * Classe représentant le tir d'un missile par un Joueur.
 */

import java.awt.Graphics;

import MG2D.*;
import MG2D.geometrie.*;


public class Tir extends Rectangle{
	
	public Tir() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tir(Couleur couleur, Point a, int largeur, int hauteur, boolean plein) {
		super(couleur, a, largeur, hauteur, plein);
		// TODO Auto-generated constructor stub
	}

	public Tir(Couleur couleur, Point a, int largeur, int hauteur, int arcLargeur, int arcHauteur, boolean plein) {
		super(couleur, a, largeur, hauteur, arcLargeur, arcHauteur, plein);
		// TODO Auto-generated constructor stub
	}

	public Tir(Couleur couleur, Point a, int largeur, int hauteur, int arcLargeur, int arcHauteur) {
		super(couleur, a, largeur, hauteur, arcLargeur, arcHauteur);
		// TODO Auto-generated constructor stub
	}

	public Tir(Couleur couleur, Point a, int largeur, int hauteur) {
		super(couleur, a, largeur, hauteur);
		// TODO Auto-generated constructor stub
	}

	public Tir(Couleur couleur, Point a, Point b, boolean plein) {
		super(couleur, a, b, plein);
		// TODO Auto-generated constructor stub
	}

	public Tir(Couleur couleur, Point a, Point b, int arcLargeur, int arcHauteur, boolean plein) {
		super(couleur, a, b, arcLargeur, arcHauteur, plein);
		// TODO Auto-generated constructor stub
	}

	public Tir(Couleur couleur, Point a, Point b, int arcLargeur, int arcHauteur) {
		super(couleur, a, b, arcLargeur, arcHauteur);
		// TODO Auto-generated constructor stub
	}

	public Tir(Couleur couleur, Point a, Point b) {
		super(couleur, a, b);
		// TODO Auto-generated constructor stub
	}

	public Tir(Point a, int largeur, int hauteur, boolean plein) {
		super(a, largeur, hauteur, plein);
		// TODO Auto-generated constructor stub
	}

	public Tir(Point a, int largeur, int hauteur, int arcLargeur, int arcHauteur, boolean plein) {
		super(a, largeur, hauteur, arcLargeur, arcHauteur, plein);
		// TODO Auto-generated constructor stub
	}

	public Tir(Point a, int largeur, int hauteur, int arcLargeur, int arcHauteur) {
		super(a, largeur, hauteur, arcLargeur, arcHauteur);
		// TODO Auto-generated constructor stub
	}

	public Tir(Point a, int largeur, int hauteur) {
		super(a, largeur, hauteur);
		// TODO Auto-generated constructor stub
	}

	public Tir(Point a, Point b, boolean plein) {
		super(a, b, plein);
		// TODO Auto-generated constructor stub
	}

	public Tir(Point a, Point b, int arcLargeur, int arcHauteur, boolean plein) {
		super(a, b, arcLargeur, arcHauteur, plein);
		// TODO Auto-generated constructor stub
	}

	public Tir(Point a, Point b, int arcLargeur, int arcHauteur) {
		super(a, b, arcLargeur, arcHauteur);
		// TODO Auto-generated constructor stub
	}

	public Tir(Point a, Point b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public Tir(Rectangle r) {
		super(r);
		// TODO Auto-generated constructor stub
	}
	

	
	
	
	/**
	 * Fonction qui détermine si un missile a atteint la fenetre
	 * @param f
	 */
	
	public boolean atteint(Fenetre f){
		int i;
		boolean rslt = false;
		for (i = this.getB().getY();i < f.getHeight();i=i+30){
			System.out.println(this.getB().getY());
				this.translater(0,30);
				//f.rafraichir();
				rslt = true;
				
		}
		return rslt;
		
	}

	
}
