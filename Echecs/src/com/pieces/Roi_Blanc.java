package com.pieces;

import javax.swing.ImageIcon;

public class Roi_Blanc extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Roi_Blanc (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 0, true);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/roi_blanc.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
