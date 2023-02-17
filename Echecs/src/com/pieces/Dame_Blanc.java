package com.pieces;

import javax.swing.ImageIcon;

public class Dame_Blanc extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Dame_Blanc (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 1, true);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/dame_blanc.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
