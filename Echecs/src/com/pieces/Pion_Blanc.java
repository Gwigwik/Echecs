package com.pieces;

import javax.swing.ImageIcon;

public class Pion_Blanc extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Pion_Blanc (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 5, true);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/pion_blanc.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
