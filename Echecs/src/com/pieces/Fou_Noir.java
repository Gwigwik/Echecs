package com.pieces;

import javax.swing.ImageIcon;

public class Fou_Noir extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Fou_Noir (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 3, false);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/fou_noir.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
