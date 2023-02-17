package com.pieces;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece {

	// VARIABLES//)
	private int x, y; // position de l'objet
	private boolean enVie;
	private int type; // 0 = roi, 1 = dame, 2 = tour, 3 = fou, 4 = cavalier, 5 = pion
	private boolean blanc;
	private int dernierMouvement;
	protected Image imgPiece;
	protected ImageIcon icoPiece;

	private int ancienX, ancienY;
	
	// CONSTRUCTEUR//
	public Piece(int x, int y, boolean enVie, int type, boolean blanc) {

		this.x = x;
		this.y = y;
		this.enVie = enVie;
		this.type = type;
		this.blanc = blanc;
	}


	// GETTERS//
	public int getX() { return x; }

	public int getY() { return y; }

	public boolean isEnVie() { return enVie; }
	
	public int getType() { return type; }

	public boolean isBlanc() { return blanc; }
	
	public int getDernierMouvement() { return dernierMouvement; }
	
	public Image getImgPiece() { return imgPiece; }
	
	public int getAncienX() { return ancienX; }

	public int getAncienY() { return ancienY; }
	
	
	// SETTERS//
	public void setX(int x) { this.x = x; }

	public void setY(int y) { this.y = y; }

	public void setEnVie(boolean enVie) { this.enVie = enVie; }

	public void setType(int type) { this.type = type; }

	public void setBlanc(boolean blanc) { this.blanc = blanc; }

	public void setDernierMouvement(int dernierMouvement) { this.dernierMouvement = dernierMouvement; }
	
	public void setImgPiece(Image imgPiece) { this.imgPiece = imgPiece; }
	
	public void setAncienX(int ancienX) { this.ancienX = ancienX; }
	
	public void setAncienY(int ancienY) { this.ancienY = ancienY; }
	
}
