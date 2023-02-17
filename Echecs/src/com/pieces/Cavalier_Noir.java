package com.pieces;

import javax.swing.ImageIcon;

public class Cavalier_Noir extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Cavalier_Noir (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 4, false);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/cavalier_noir.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
