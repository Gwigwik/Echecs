package com.pieces;

import javax.swing.ImageIcon;

public class Dame_Noir extends Piece {

	// VARIABLES//
	
	// CONSTRUCTEUR//
	public Dame_Noir (int x, int y, boolean enVie) {
		
		super(x, y, enVie, 1, false);
		super.icoPiece = new ImageIcon(getClass().getResource("/images/dame_noir.png"));
		super.imgPiece = this.icoPiece.getImage();
	}
	
	// GETTERS
		
	// SETTERS//
		
	// METHODES//
	
}
