package com.pieces;

import javax.swing.ImageIcon;

public class Pion_Noir extends Piece { // 2 = largeur et 1 = hauteur

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Pion_Noir (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 5, false);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/pion_noir.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
