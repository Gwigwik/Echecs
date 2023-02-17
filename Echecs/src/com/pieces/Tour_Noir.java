package com.pieces;

import javax.swing.ImageIcon;

public class Tour_Noir extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Tour_Noir (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 2, false);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/tour_noir.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
