package com.pieces;

import javax.swing.ImageIcon;

public class Tour_Blanc extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Tour_Blanc (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 2, true);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/tour_blanc.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
