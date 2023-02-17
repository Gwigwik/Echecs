package com.pieces;

import javax.swing.ImageIcon;

public class Roi_Noir extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Roi_Noir (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 0, false);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/roi_noir.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
