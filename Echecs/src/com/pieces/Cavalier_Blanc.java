package com.pieces;

import javax.swing.ImageIcon;

public class Cavalier_Blanc extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Cavalier_Blanc (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 4, true);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/cavalier_blanc.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
