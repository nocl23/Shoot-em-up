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
	
	public void atteint(Fenetre f){
		
		while (this.getB().getY() < f.getHeight()){
			System.out.println(this.getB().getY());
			try {
				Thread.sleep(200);
				this.translater(0,30);
				f.rafraichir();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
}
