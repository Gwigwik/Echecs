package com.pieces;

import javax.swing.ImageIcon;

public class Fou_Blanc extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Fou_Blanc (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 3, true);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/fou_blanc.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
