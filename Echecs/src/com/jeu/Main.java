package com.jeu;

import javax.swing.JFrame;

public class Main {
	
	public static Scene scene;

	public static void main(String[] args) {

		JFrame fenetre = new JFrame("Echecs");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(1064, 838); //reelle taille fait 16 de moins en x et 39 de moins en y
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		fenetre.setAlwaysOnTop(true);
		
		scene = new Scene();

		fenetre.setContentPane(scene);
		fenetre.setVisible(true);
		//System.out.println("largeur : " + scene.getWidth()+ ", hauteur : " + scene.getHeight());
	}

}
