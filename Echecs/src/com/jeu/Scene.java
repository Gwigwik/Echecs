package com.jeu;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.pieces.Cavalier_Blanc;
import com.pieces.Cavalier_Noir;
import com.pieces.Dame_Blanc;
import com.pieces.Dame_Noir;
import com.pieces.Fou_Blanc;
import com.pieces.Fou_Noir;
import com.pieces.Piece;
import com.pieces.Pion_Blanc;
import com.pieces.Pion_Noir;
import com.pieces.Roi_Blanc;
import com.pieces.Roi_Noir;
import com.pieces.Tour_Blanc;
import com.pieces.Tour_Noir;

@SuppressWarnings("serial")
public class Scene extends JPanel implements MouseListener, ActionListener {
	
	private int posSourisX, posSourisY;
	private boolean sourisPressee;
	
	private int rotation = 0;
	private boolean rotaAuto =  false;
	private boolean rotaArreteeSurBlanc = true;
	JButton activationRotation = new JButton();
	JLabel messageRotation = new JLabel();
	
	private boolean afficherCoups =  false;
	JButton activationAfficherCoups = new JButton();
	JLabel messageAfficherCoups = new JLabel();
	
	private boolean sourisSurEchiquier = false;
	
	String nomBloc;
	JButton[][] bloc = new JButton[8][8];
	JButton boutonRecupereName = null;
	String stringXClique;
	String stringYClique;
	
	JButton cavalier_blanc = new JButton();
	JButton fou_blanc = new JButton();
	JButton tour_blanc = new JButton();
	JButton dame_blanc = new JButton();
	
	JButton cavalier_noir = new JButton();
	JButton fou_noir = new JButton();
	JButton tour_noir = new JButton();
	JButton dame_noir = new JButton();
	
	private ImageIcon ico_petit_cavalier_blanc, ico_petit_fou_blanc, ico_petit_tour_blanc, ico_petit_dame_blanc;
	private ImageIcon ico_petit_cavalier_noir, ico_petit_fou_noir, ico_petit_tour_noir, ico_petit_dame_noir;
	
	private boolean transfoPion; //permet de bloquer le jeu quand le pion doit se transformer
	private int xTransfo, yTransfo;
	
	private int placePieceDeplacee = -1; //garde en memoire la piece qui va etre d�plac�e potentiellement
	
	private boolean pieceDeplacee = false; //eviter de ravoir les points apr�s avoir d�plac� une pi�ce
	
	private int nbCoups = 0; //compte le nombre de coups jou�s et permet de v�rifier si le roque est r�alisable
	
	private ImageIcon icoEchiquier;
	private Image imgEchiquier;
	
	private int matEchiquier[][] = new int [8][8];
	private int positionsPossibles[][] = new int [8][8];
	
	
	public Pion_Blanc pion_blanc1, pion_blanc2, pion_blanc3, pion_blanc4, pion_blanc5, pion_blanc6, pion_blanc7, pion_blanc8;
	public Pion_Noir pion_noir1, pion_noir2, pion_noir3, pion_noir4, pion_noir5, pion_noir6, pion_noir7, pion_noir8;
	public Cavalier_Blanc cavalier_blanc1, cavalier_blanc2;
	public Cavalier_Noir cavalier_noir1, cavalier_noir2;
	public Fou_Blanc fou_blanc1, fou_blanc2;
	public Fou_Noir fou_noir1, fou_noir2;
	public Tour_Blanc tour_blanc1, tour_blanc2;
	public Tour_Noir tour_noir1, tour_noir2;
	public Dame_Blanc dame_blanc1;
	public Dame_Noir dame_noir1;
	public Roi_Blanc roi_blanc;
	public Roi_Noir roi_noir;
	
	private ArrayList<Piece> tabPieces;
	
	
	public Scene() {
		
		super();
		icoEchiquier = new ImageIcon(getClass().getResource("/images/echiquier.png"));
		imgEchiquier = this.icoEchiquier.getImage();
		
		ico_petit_cavalier_blanc = new ImageIcon(getClass().getResource("/images/petit_cavalier_blanc.png"));
		ico_petit_fou_blanc = new ImageIcon(getClass().getResource("/images/petit_fou_blanc.png"));
		ico_petit_tour_blanc = new ImageIcon(getClass().getResource("/images/petit_tour_blanc.png"));
		ico_petit_dame_blanc = new ImageIcon(getClass().getResource("/images/petit_dame_blanc.png"));
		
		ico_petit_cavalier_noir = new ImageIcon(getClass().getResource("/images/petit_cavalier_noir.png"));
		ico_petit_fou_noir = new ImageIcon(getClass().getResource("/images/petit_fou_noir.png"));
		ico_petit_tour_noir = new ImageIcon(getClass().getResource("/images/petit_tour_noir.png"));
		ico_petit_dame_noir = new ImageIcon(getClass().getResource("/images/petit_dame_noir.png"));
		
		for (int x = 0; x <= 7; x++)  {
			for (int y = 0; y <= 1; y++)  {
				matEchiquier[x][y] = 1;  
				matEchiquier[x][y] = 1; 
			}
			for (int y = 6; y <= 7; y++)  {
				matEchiquier[x][y] = 1;  
				matEchiquier[x][y] = 1; 
			}
		}
		
		cavalier_blanc.addActionListener(this);
		cavalier_blanc.setBounds(-100, 0, 50, 50);
		cavalier_blanc.setIcon(ico_petit_cavalier_blanc);
		cavalier_blanc.setContentAreaFilled(false);
		cavalier_blanc.setBorderPainted(false);
		this.add(cavalier_blanc);
		fou_blanc.addActionListener(this);
		fou_blanc.setBounds(-50, 0, 50, 50);
		fou_blanc.setIcon(ico_petit_fou_blanc);
		fou_blanc.setContentAreaFilled(false);
		fou_blanc.setBorderPainted(false);
		this.add(fou_blanc);
		tour_blanc.addActionListener(this);
		tour_blanc.setBounds(-100, 50, 50, 50);
		tour_blanc.setIcon(ico_petit_tour_blanc);
		tour_blanc.setContentAreaFilled(false);
		tour_blanc.setBorderPainted(false);
		this.add(tour_blanc);
		dame_blanc.addActionListener(this);
		dame_blanc.setBounds(-50, 50, 50, 50);
		dame_blanc.setIcon(ico_petit_dame_blanc);
		dame_blanc.setContentAreaFilled(false);
		dame_blanc.setBorderPainted(false);
		this.add(dame_blanc);
		
		cavalier_noir.addActionListener(this);
		cavalier_noir.setBounds(-100, 0, 50, 50);
		cavalier_noir.setIcon(ico_petit_cavalier_noir);
		cavalier_noir.setContentAreaFilled(false);
		cavalier_noir.setBorderPainted(false);
		this.add(cavalier_noir);
		fou_noir.addActionListener(this);
		fou_noir.setBounds(-50, 0, 50, 50);
		fou_noir.setIcon(ico_petit_fou_noir);
		fou_noir.setContentAreaFilled(false);
		fou_noir.setBorderPainted(false);
		this.add(fou_noir);
		tour_noir.addActionListener(this);
		tour_noir.setBounds(-100, 50, 50, 50);
		tour_noir.setIcon(ico_petit_tour_noir);
		tour_noir.setContentAreaFilled(false);
		tour_noir.setBorderPainted(false);
		this.add(tour_noir);
		dame_noir.addActionListener(this);
		dame_noir.setBounds(-50, 50, 50, 50);
		dame_noir.setIcon(ico_petit_dame_noir);
		dame_noir.setContentAreaFilled(false);
		dame_noir.setBorderPainted(false);
		this.add(dame_noir);
		
		
		for (int x = 0; x <= 7; x++) {
			for (int y = 0; y <= 7; y++) {
				nomBloc = "bloc" + x + "_" + y;
				bloc[x][y] = new JButton();
				bloc[x][y].addMouseListener(this);
				this.bloc[x][y].setBounds(x*100, y*100, 100, 100);
				this.add(bloc[x][y]);
				bloc[x][y].setContentAreaFilled(false);
				bloc[x][y].setBorderPainted(false);
				this.bloc[x][y].setName(nomBloc);
			}
		}
		
		pion_blanc1 = new Pion_Blanc(0, 6, true); //x, y, enVie
		pion_blanc2 = new Pion_Blanc(1, 6, true);
		pion_blanc3 = new Pion_Blanc(2, 6, true);
		pion_blanc4 = new Pion_Blanc(3, 6, true);
		pion_blanc5 = new Pion_Blanc(4, 6, true);
		pion_blanc6 = new Pion_Blanc(5, 6, true);
		pion_blanc7 = new Pion_Blanc(6, 6, true);
		pion_blanc8 = new Pion_Blanc(7, 6, true);
		
		pion_noir1 = new Pion_Noir(0, 1, true);
		pion_noir2 = new Pion_Noir(1, 1, true);
		pion_noir3 = new Pion_Noir(2, 1, true);
		pion_noir4 = new Pion_Noir(3, 1, true);
		pion_noir5 = new Pion_Noir(4, 1, true);
		pion_noir6 = new Pion_Noir(5, 1, true);
		pion_noir7 = new Pion_Noir(6, 1, true);
		pion_noir8 = new Pion_Noir(7, 1, true);
		
		cavalier_blanc1 = new Cavalier_Blanc(1, 7, true);
		cavalier_blanc2 = new Cavalier_Blanc(6, 7, true);
		
		cavalier_noir1 = new Cavalier_Noir(1, 0, true);
		cavalier_noir2 = new Cavalier_Noir(6, 0, true);
		
		fou_blanc1 = new Fou_Blanc(2, 7, true);
		fou_blanc2 = new Fou_Blanc(5, 7, true);
		
		fou_noir1 = new Fou_Noir(2, 0, true);
		fou_noir2 = new Fou_Noir(5, 0, true);
		
		tour_blanc1 = new Tour_Blanc(0, 7, true);
		tour_blanc2 = new Tour_Blanc(7, 7, true);
		
		tour_noir1 = new Tour_Noir(0, 0, true);
		tour_noir2 = new Tour_Noir(7, 0, true);
		
		dame_blanc1 = new Dame_Blanc(3, 7, true);
		
		dame_noir1 = new Dame_Noir(3, 0, true);
		
		roi_blanc = new Roi_Blanc(4, 7, true);
		
		roi_noir = new Roi_Noir(4, 0, true);
		
		tabPieces = new ArrayList<Piece>();

		this.tabPieces.add(pion_blanc1);
		this.tabPieces.add(pion_blanc2);
		this.tabPieces.add(pion_blanc3);
		this.tabPieces.add(pion_blanc4);
		this.tabPieces.add(pion_blanc5);
		this.tabPieces.add(pion_blanc6);
		this.tabPieces.add(pion_blanc7);
		this.tabPieces.add(pion_blanc8);
		
		this.tabPieces.add(pion_noir1);
		this.tabPieces.add(pion_noir2);
		this.tabPieces.add(pion_noir3);
		this.tabPieces.add(pion_noir4);
		this.tabPieces.add(pion_noir5);
		this.tabPieces.add(pion_noir6);
		this.tabPieces.add(pion_noir7);
		this.tabPieces.add(pion_noir8);
		
		this.tabPieces.add(cavalier_blanc1);
		this.tabPieces.add(cavalier_blanc2);

		this.tabPieces.add(cavalier_noir1);
		this.tabPieces.add(cavalier_noir2);
		
		this.tabPieces.add(fou_blanc1);
		this.tabPieces.add(fou_blanc2);
		
		this.tabPieces.add(fou_noir1);
		this.tabPieces.add(fou_noir2);
		
		this.tabPieces.add(tour_blanc1);
		this.tabPieces.add(tour_blanc2);
		
		this.tabPieces.add(tour_noir1);
		this.tabPieces.add(tour_noir2);
		
		this.tabPieces.add(dame_blanc1);
		
		this.tabPieces.add(dame_noir1);
		
		this.tabPieces.add(roi_blanc);
		this.tabPieces.add(roi_noir);
		
		for (int i = 0; i < tabPieces.size(); i++)  {
			tabPieces.get(i).setDernierMouvement(0);
		}
		
		
		activationRotation.setBorderPainted(false);
		activationRotation.setBounds(810, 770, 20, 20);
		activationRotation.setBackground(Color.LIGHT_GRAY);
		activationRotation.addMouseListener(this);
		activationRotation.addActionListener(this);
		this.add(activationRotation);
		
		messageRotation = new JLabel();
		messageRotation.setBounds(835, 771, 200, 16);
		messageRotation.setText("Activer la rotation automatique");
		this.add(messageRotation); 
		
		activationAfficherCoups.setBorderPainted(false);
		activationAfficherCoups.setBounds(810, 740, 20, 20);
		activationAfficherCoups.setBackground(Color.LIGHT_GRAY);
		activationAfficherCoups.addMouseListener(this);
		activationAfficherCoups.addActionListener(this);
		this.add(activationAfficherCoups);
		
		messageAfficherCoups = new JLabel();
		messageAfficherCoups.setBounds(835, 741, 200, 16);
		messageAfficherCoups.setText("Afficher les coups l�gaux");
		this.add(messageAfficherCoups); 
		
		setLayout(null);
		setFocusable(true);
		this.requestFocusInWindow();
		addMouseListener(this);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = (Graphics2D)g;
		
		g2.drawImage(imgEchiquier, 0, 0, null);
		
		if ((rotation%2 == 0 && rotaAuto) || (!rotaAuto && rotaArreteeSurBlanc)) {
			if (placePieceDeplacee >= 0) {
				g2.fillRect(tabPieces.get(placePieceDeplacee).getX()*100, tabPieces.get(placePieceDeplacee).getY()*100, 101, 101);
			}
			for (int i = 0; i < tabPieces.size(); i++) { // affichage des pi�ces en vie
				if (tabPieces.get(i).isEnVie()) {
					if (!sourisPressee || i != placePieceDeplacee){
						g2.drawImage(tabPieces.get(i).getImgPiece(), tabPieces.get(i).getX()*100, tabPieces.get(i).getY()*100, null);
					}
				}
			}
			if (afficherCoups) {
	 			for (int x = 0; x <= 7; x++)  {
					for (int y = 0; y <= 7; y++)  {
						if (positionsPossibles[x][y] == 1) {
							if (!sourisPressee || !(posSourisX == x && posSourisY == y)) {
								g2.fillOval(100*x + 30, 100*y + 30, 40, 40);
							}
						}
					}
				}
			}
			if (transfoPion == true) {
				if (yTransfo == 0) { //blancs
					g2.setColor(Color.BLACK);
					g2.fillRect(xTransfo*100, yTransfo*100, 101, 101);
				}
				if (yTransfo == 7) { // noirs
					g2.setColor(Color.WHITE);
					g2.fillRect(xTransfo*100, yTransfo*100, 100, 100);
				}
			}
			if (sourisPressee && placePieceDeplacee != -1) {
				g2.drawImage(tabPieces.get(placePieceDeplacee).getImgPiece(), posSourisX*100, posSourisY*100, null);
			}
		} else {
			if (placePieceDeplacee >= 0) {
				g2.fillRect(700-tabPieces.get(placePieceDeplacee).getX()*100, 700-tabPieces.get(placePieceDeplacee).getY()*100, 101, 101);
			}
			for (int i = 0; i < tabPieces.size(); i++) { // affichage des pi�ces en vie
				if (tabPieces.get(i).isEnVie()) {
					if (!sourisPressee || i != placePieceDeplacee){
						g2.drawImage(tabPieces.get(i).getImgPiece(), 700-tabPieces.get(i).getX()*100, 700-tabPieces.get(i).getY()*100, null);
					}
				}
			}
			if (afficherCoups) {
				for (int x = 0; x <= 7; x++)  {
					for (int y = 0; y <= 7; y++)  {
						if (positionsPossibles[x][y] == 1) {
							if (!sourisPressee || !(posSourisX == x && posSourisY == y)) {
								g2.fillOval(700-100*x + 30, 700-100*y + 30, 40, 40);
							}
						}
					}
				}
			}
			if (transfoPion == true) {
				if (yTransfo == 0) { //blancs
					g2.setColor(Color.BLACK);
					g2.fillRect(700-xTransfo*100, 700-yTransfo*100, 101, 101);
				}
				if (yTransfo == 7) { // noirs
					g2.setColor(Color.WHITE);
					g2.fillRect(700-xTransfo*100, 700-yTransfo*100, 100, 100);
				}
			}
			if (sourisPressee && placePieceDeplacee != -1) {
				g2.drawImage(tabPieces.get(placePieceDeplacee).getImgPiece(), 700-posSourisX*100, 700-posSourisY*100, null);
			}
		}
		
	}

	public void mouvementPionBlanc(int tableau[][], int placePiece) {
		
		boolean oui = true; //oui est utile pour la mise en echecs
		int x = tabPieces.get(placePiece).getX();
		int y = tabPieces.get(placePiece).getY();
		
		for (int i = 0; i < tabPieces.size(); i++) {
			if (tabPieces.get(i).isEnVie() ==  true && tabPieces.get(i).getX() == x && tabPieces.get(i).getY() == y && placePiece != i) {
				oui = false;
			}
		}
		
		if (oui) {
			if (tabPieces.get(placePiece).getY() >= 1) {
				if (matEchiquier[x][y - 1] == 0) {
					//pion avance d'un case
					tableau[x][y - 1] = 1;
					
					//si pion jamais d�plac�
					if (y == 6) {
						if (matEchiquier[x][y - 2] == 0) {
							tableau[x][y - 2] = 1;
						}
					}
				}
			
				//manger en diagonale � gauche
				if (x >= 1) {
					if (matEchiquier[x - 1][y - 1] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x - 1 && tabPieces.get(i).getY() == y - 1 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x - 1][y - 1] = 1;
								}
							}
						}
					}
					//prise en passant � gauche
					if (y == 3) {
						if (matEchiquier[x - 1][y] == 1) {
							for (int i = 0; i < tabPieces.size(); i++) {
								if (tabPieces.get(i).getType() == 5 && tabPieces.get(i).getDernierMouvement() == 1 &&
										tabPieces.get(i).getX() == x - 1 && tabPieces.get(i).getY() == 3 && tabPieces.get(i).isEnVie()) {
									if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
										tableau[x - 1][y - 1] = 1;
									}
								}
							}
						}
					}
				}
				//manger en diagonale � droite
				if (x <= 6) {
					if (matEchiquier[x + 1][y - 1] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x + 1 && tabPieces.get(i).getY() == y - 1 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x + 1][y - 1] = 1; 
								}
							}
						}
					}
					//prise en passant � droite
					if (tabPieces.get(placePiece).getY() == 3) {
						if (matEchiquier[x + 1][y] == 1) {
							for (int i = 0; i < tabPieces.size(); i++) {
								if (tabPieces.get(i).getType() == 5 && tabPieces.get(i).getDernierMouvement() == 1 &&
										tabPieces.get(i).getX() == x + 1 && tabPieces.get(i).getY() == 3 && tabPieces.get(i).isEnVie()) {
									if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
										tableau[x + 1][y - 1] = 1;
									}
								}
							}
						}	
					}
				}	
			}
		}
	}
	
	public void mouvementPionNoir(int tableau[][], int placePiece) {
		
		boolean oui = true; //oui est utile pour la mise en echecs
		int x = tabPieces.get(placePiece).getX();
		int y = tabPieces.get(placePiece).getY();
		
		for (int i = 0; i < tabPieces.size(); i++) {
			if (tabPieces.get(i).isEnVie() ==  true && tabPieces.get(i).getX() == x && tabPieces.get(i).getY() == y && placePiece != i) {
				oui = false;
			}
		}
		
		if (oui) {
			
			if (tabPieces.get(placePiece).getY() <= 6) {
				if (matEchiquier[x][y + 1] == 0) {
					//pion avance d'un case
					tableau[x][y + 1] = 1;
					
					//si pion jamais d�plac�
					if (y == 1) {
						if (matEchiquier[x][y + 2] == 0) {
							tableau[x][y + 2] = 1;
						}
					}
				}
				//manger en diagonale � gauche
				if (x >= 1) {
					if (matEchiquier[x - 1][y + 1] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x - 1 && tabPieces.get(i).getY() == y + 1 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x - 1][y + 1] = 1;
								}
							}
						}
					}
					//prise en passant � gauche
					if (y == 4) {
						if (matEchiquier[x - 1][y] == 1) {
							for (int i = 0; i < tabPieces.size(); i++) {
								if (tabPieces.get(i).getType() == 5 && tabPieces.get(i).getDernierMouvement() == 1 &&
										tabPieces.get(i).getX() == x - 1 && tabPieces.get(i).getY() == 4 && tabPieces.get(i).isEnVie()) {
									if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
										tableau[x - 1][y + 1] = 1;
									}
								}
							}
						}	
					}
				}
				//manger en diagonale � droite
				if (x <= 6) {
					if (matEchiquier[x + 1][y + 1] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x + 1 && tabPieces.get(i).getY() == y + 1 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x + 1][y + 1] = 1; 
								}
							}
						}
					}
					//prise en passant � droite
					if (y == 4) {
						if (matEchiquier[x + 1][y] == 1) {
							for (int i = 0; i < tabPieces.size(); i++) {
								if (tabPieces.get(i).getType() == 5 && tabPieces.get(i).getDernierMouvement() == 1 &&
										tabPieces.get(i).getX() == x + 1 && tabPieces.get(i).getY() == 4 && tabPieces.get(i).isEnVie()) {
									if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
										tableau[x + 1][y + 1] = 1;
									}
								}
							}
						}	
					}
				}
			}
		}
	}

	public void mouvementCavalier(int tableau[][], int placePiece) {
		
		boolean oui = true; //oui est utile pour la mise en echecs
		int x = tabPieces.get(placePiece).getX();
		int y = tabPieces.get(placePiece).getY();
		
		for (int i = 0; i < tabPieces.size(); i++) {
			if (tabPieces.get(i).isEnVie() ==  true && tabPieces.get(i).getX() == x && tabPieces.get(i).getY() == y && placePiece != i) {
				oui = false;
			}
		}
		
		if (oui) {
			if (y >= 2) {
				//2 haut 1 gauche
				if (x >= 1) {
					if (matEchiquier[x - 1][y - 2] == 0) {
						tableau[x - 1][y- 2] = 1;
					}
					if (matEchiquier[x - 1][y - 2] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x - 1 && tabPieces.get(i).getY() == y- 2 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x - 1][y - 2] = 1;
								}
							}
						}
					}
				}
				//2 haut 1 droite
				if (x <= 6) {
					if (matEchiquier[x + 1][y - 2] == 0) {
						tableau[x + 1][y - 2] = 1;
					}
					if (matEchiquier[x + 1][y - 2] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x + 1 && tabPieces.get(i).getY() == y - 2 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x + 1][y - 2] = 1;
								}
							}
						}
					}
				}
			}
			if (y >= 1) {
				//1 haut 2 gauche
				if (x >= 2) {
					if (matEchiquier[x - 2][y - 1] == 0) {
						tableau[x - 2][y - 1] = 1;
					}
					if (matEchiquier[x - 2][y - 1] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x - 2 && tabPieces.get(i).getY() == y - 1 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x - 2][y - 1] = 1;
								}
							}
						}
					}
				}
				//1 haut 2 droite
				if (x <= 5) {
					if (matEchiquier[x + 2][y - 1] == 0) {
						tableau[x + 2][y - 1] = 1;
					}
					if (matEchiquier[x + 2][y - 1] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x + 2 && tabPieces.get(i).getY() == y - 1 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x + 2][y - 1] = 1;
								}
							}
						}
					}
				}
			}
			if (y <= 5) {
				//2 bas 1 gauche
				if (x >= 1) {
					if (matEchiquier[x - 1][y + 2] == 0) {
						tableau[x- 1][y + 2] = 1;
					}
					if (matEchiquier[x - 1][y + 2] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x - 1 && tabPieces.get(i).getY() == y + 2 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x - 1][y + 2] = 1;
								}
							}
						}
					}
				}
				if (x <= 6) {
					//2 bas 1 droite
					if (matEchiquier[x + 1][y + 2] == 0) {
						tableau[x + 1][y + 2] = 1;
					}
					if (matEchiquier[x + 1][y + 2] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x + 1 && tabPieces.get(i).getY() == y + 2 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x + 1][y + 2] = 1;
								}
							}
						}
					}
				}
			}
			if (y <= 6) {
				//1 bas 2 gauche
				if (x >= 2) {
					if (matEchiquier[x - 2][y + 1] == 0) {
						tableau[x - 2][y + 1] = 1;
					}
					if (matEchiquier[x - 2][y + 1] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x - 2 && tabPieces.get(i).getY() == y + 1 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x - 2][y + 1] = 1;
								}
							}
						}
					}
				}
				if (x <= 5) {
					//1 bas 2 droite
					if (matEchiquier[x + 2][y + 1] == 0) {
						tableau[x + 2][y + 1] = 1;
					}
					if (matEchiquier[x + 2][y + 1] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x + 2 && tabPieces.get(i).getY() == y + 1 && tabPieces.get(i).isEnVie()) {
								if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
									tableau[x + 2][y + 1] = 1;
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	public void mouvementFou(int tableau[][], int placePiece) {
		
		boolean bloque = false;
		boolean oui = true; //oui est utile pour la mise en echecs
		int x = tabPieces.get(placePiece).getX();
		int y = tabPieces.get(placePiece).getY();
		
		for (int i = 0; i < tabPieces.size(); i++) {
			if (tabPieces.get(i).isEnVie() ==  true && tabPieces.get(i).getX() == x && tabPieces.get(i).getY() == y && placePiece != i) {
				oui = false;
			}
		}
		
		if (oui) {
			//d�placement haut gauche
			for (int j = 1; j <= 7; j++) {
				if (x >= j && y >= j && !bloque) {
					if (matEchiquier[x - j][y - j] == 0) {
						tableau[x - j][y - j] = 1;
					}
					//possibilit� de d�placer et bloque si piece de l'autre couleur en face
					if (matEchiquier[x - j][y - j] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x - j && tabPieces.get(i).getY() == y - j && 
								tabPieces.get(i).isEnVie() && tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[x - j][y - j] = 1;
							}
						}
						bloque = true;
					}
				}
			}
			
			bloque = false;
			
			//d�placement haut droite
			for (int j = 1; j <= 7; j++) {
				if (x <= 7-j && y >= j && !bloque) {
					if (matEchiquier[x + j][y - j] == 0) {
						tableau[x + j][y - j] = 1;
					}
					//possibilit� de d�placer et bloque si piece de l'autre couleur en face
					if (matEchiquier[x + j][y - j] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x + j && tabPieces.get(i).getY() == y - j && 
								tabPieces.get(i).isEnVie() && tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[x + j][y - j] = 1;
							}
						}
						bloque = true;
					}
					
				}
			}
	
			bloque = false;
			
			//d�placement bas gauche
			for (int j = 1; j <= 7; j++) {
				if (x >= j && y <= 7-j && !bloque) {
					if (matEchiquier[x - j][y + j] == 0) {
						tableau[x - j][y + j] = 1;
					}
					//possibilit� de d�placer et bloque si piece de l'autre couleur en face
					if (matEchiquier[x - j][y + j] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x - j && tabPieces.get(i).getY() == y + j && 
								tabPieces.get(i).isEnVie() && tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[x - j][y + j] = 1;
							}
						}
						bloque = true;
					}
				}
			}
			
			bloque = false;
			
			//d�placement bas droite
			for (int j = 1; j <= 7; j++) {
				if (x <= 7-j && y <= 7-j && !bloque) {
					if (matEchiquier[x + j][y + j] == 0) {
						tableau[x + j][y + j] = 1;
					}
					//possibilit� de d�placer et bloque si piece de l'autre couleur en face
					if (matEchiquier[x + j][y + j] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x + j && tabPieces.get(i).getY() == y + j && 
								tabPieces.get(i).isEnVie() && tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[x + j][y + j] = 1;
							}
						}
						bloque = true;
					}
				}
			}
		}
		
	}

	public void mouvementTour(int tableau[][], int placePiece) {
		
		boolean bloque = false;
		boolean oui = true; //oui est utile pour la mise en echecs
		int x = tabPieces.get(placePiece).getX();
		int y = tabPieces.get(placePiece).getY();
		
		for (int i = 0; i < tabPieces.size(); i++) {
			if (tabPieces.get(i).isEnVie() ==  true && tabPieces.get(i).getX() == x && tabPieces.get(i).getY() == y && placePiece != i) {
				oui = false;
			}
		}
		
		if (oui) {
			//d�placement haut
			for (int j = 1; j <= 7; j++) {
				if (y >= j && !bloque) {
					if (matEchiquier[x][y - j] == 0) {
						tableau[x][y - j] = 1;
					}
					//possibilit� de d�placer et bloque si piece de l'autre couleur en face
					if (matEchiquier[x][y - j] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x && tabPieces.get(i).getY() == y - j && 
								tabPieces.get(i).isEnVie() && tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[x][y - j] = 1;
							}
						}
						bloque = true;
					}
				}
			}
			
			bloque = false;
			
			//d�placement bas
			for (int j = 1; j <= 7; j++) {
				if (y <= 7-j && !bloque) {
					if (matEchiquier[x][y + j] == 0) {
						tableau[x][y + j] = 1;
					}
					//possibilit� de d�placer et bloque si piece de l'autre couleur en face
					if (matEchiquier[x][y + j] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x && tabPieces.get(i).getY() == y + j && 
								tabPieces.get(i).isEnVie() && tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[x][y + j] = 1;
							}
						}
						bloque = true;
					}
				}
			}
			
			bloque = false;
			
			//d�placement gauche
			for (int j = 1; j <= 7; j++) {
				if (x >= j && !bloque) {
					if (matEchiquier[x - j][y] == 0) {
						tableau[x - j][y] = 1;
					}
					//possibilit� de d�placer et bloque si piece de l'autre couleur en face
					if (matEchiquier[x - j][y] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x - j && tabPieces.get(i).getY() == y && 
								tabPieces.get(i).isEnVie() && tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[x - j][y] = 1;
							}
						}
						bloque = true;
					}
				}
			}
			
			bloque = false;
			
			//d�placement droite
			for (int j = 1; j <= 7; j++) {
				if (x <= 7-j && !bloque) {
					if (matEchiquier[x + j][y] == 0) {
						tableau[x + j][y] = 1;
					}
					//possibilit� de d�placer et bloque si piece de l'autre couleur en face
					if (matEchiquier[x + j][y] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == x + j && tabPieces.get(i).getY() == y && 
								tabPieces.get(i).isEnVie() && tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[x + j][y] = 1;
							}
						}
						bloque = true;
					}
				}
			}
		}
	}
	
	public void mouvementRoi(int tableau[][], int placePiece) {
		
		if (tabPieces.get(placePiece).getY() >= 1) {
			//haut
			if (matEchiquier[tabPieces.get(placePiece).getX()][tabPieces.get(placePiece).getY() - 1] == 0) {
				tableau[tabPieces.get(placePiece).getX()][tabPieces.get(placePiece).getY() - 1] = 1;
			}
			if (matEchiquier[tabPieces.get(placePiece).getX()][tabPieces.get(placePiece).getY() - 1] == 1) {
				for (int i = 0; i < tabPieces.size(); i++) {
					if (tabPieces.get(i).getX() == tabPieces.get(placePiece).getX() && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() - 1 && 
							tabPieces.get(i).isEnVie()) {
						if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
							tableau[tabPieces.get(placePiece).getX()][tabPieces.get(placePiece).getY() - 1] = 1;
						}
					}
				}
			}
			//haut gauche
			if (tabPieces.get(placePiece).getX() >= 1) {
				if (matEchiquier[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY() - 1] == 0) {
					tableau[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY() - 1] = 1;
				}
				if (matEchiquier[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY() - 1] == 1) {
					for (int i = 0; i < tabPieces.size(); i++) {
						if (tabPieces.get(i).getX() == tabPieces.get(placePiece).getX() - 1 && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() - 1 && 
								tabPieces.get(i).isEnVie()) {
							if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY() - 1] = 1;
							}
						}
					}
				}
			}
			//haut droite
			if (tabPieces.get(placePiece).getX() <= 6) {
				if (matEchiquier[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY() - 1] == 0) {
					tableau[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY() - 1] = 1;
				}
				if (matEchiquier[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY() - 1] == 1) {
					for (int i = 0; i < tabPieces.size(); i++) {
						if (tabPieces.get(i).getX() == tabPieces.get(placePiece).getX() + 1 && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() - 1 && 
								tabPieces.get(i).isEnVie()) {
							if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY() - 1] = 1;
							}
						}
					}
				}
			}
		}
		
		if (tabPieces.get(placePiece).getY() <= 6) {
			//bas
			if (matEchiquier[tabPieces.get(placePiece).getX()][tabPieces.get(placePiece).getY() + 1] == 0) {
				tableau[tabPieces.get(placePiece).getX()][tabPieces.get(placePiece).getY() + 1] = 1;
			}
			if (matEchiquier[tabPieces.get(placePiece).getX()][tabPieces.get(placePiece).getY() + 1] == 1) {
				for (int i = 0; i < tabPieces.size(); i++) {
					if (tabPieces.get(i).getX() == tabPieces.get(placePiece).getX() && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() + 1 && 
							tabPieces.get(i).isEnVie()) {
						if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
							tableau[tabPieces.get(placePiece).getX()][tabPieces.get(placePiece).getY() + 1] = 1;
						}
					}
				}
			}
			//bas gauche
			if (tabPieces.get(placePiece).getX() >= 1) {
				if (matEchiquier[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY() + 1] == 0) {
					tableau[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY() + 1] = 1;
				}
				if (matEchiquier[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY() + 1] == 1) {
					for (int i = 0; i < tabPieces.size(); i++) {
						if (tabPieces.get(i).getX() == tabPieces.get(placePiece).getX() - 1 && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() + 1 && 
								tabPieces.get(i).isEnVie()) {
							if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY() + 1] = 1;
							}
						}
					}
				}
			}
			//bas droite
			if (tabPieces.get(placePiece).getX() <= 6) {
				if (matEchiquier[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY() + 1] == 0) {
					tableau[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY() + 1] = 1;
				}
				if (matEchiquier[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY() + 1] == 1) {
					for (int i = 0; i < tabPieces.size(); i++) {
						if (tabPieces.get(i).getX() == tabPieces.get(placePiece).getX() + 1 && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() + 1 && 
								tabPieces.get(i).isEnVie()) {
							if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
								tableau[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY() + 1] = 1;
							}
						}
					}
				}
			}
		}
		//gauche
		if (tabPieces.get(placePiece).getX() >= 1) {
			if (matEchiquier[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY()] == 0) {
				tableau[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY()] = 1;
			}
			if (matEchiquier[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY()] == 1) {
				for (int i = 0; i < tabPieces.size(); i++) {
					if (tabPieces.get(i).getX() == tabPieces.get(placePiece).getX() - 1 && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() && 
							tabPieces.get(i).isEnVie()) {
						if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
							tableau[tabPieces.get(placePiece).getX() - 1][tabPieces.get(placePiece).getY()] = 1;
						}
					}
				}
			}
		}
		//gauche
		if (tabPieces.get(placePiece).getX() <= 6) {
			if (matEchiquier[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY()] == 0) {
				tableau[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY()] = 1;
			}
			if (matEchiquier[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY()] == 1) {
				for (int i = 0; i < tabPieces.size(); i++) {
					if (tabPieces.get(i).getX() == tabPieces.get(placePiece).getX() + 1 && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() && 
							tabPieces.get(i).isEnVie()) {
						if (tabPieces.get(i).isBlanc() != tabPieces.get(placePiece).isBlanc()) {
							tableau[tabPieces.get(placePiece).getX() + 1][tabPieces.get(placePiece).getY()] = 1;
						}
					}
				}
			}
		}
		//roques
		if (nbCoups == tabPieces.get(placePiece).getDernierMouvement()) {
			//petit roque
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == 7 && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() && 
						tabPieces.get(i).isEnVie() && nbCoups == tabPieces.get(i).getDernierMouvement()) {
					if (matEchiquier[5][tabPieces.get(placePiece).getY()] == 0 && matEchiquier[6][tabPieces.get(placePiece).getY()] == 0) {
						tableau[6][tabPieces.get(placePiece).getY()] = 1;
					}
				}
			}
			//grand roque
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == 0 && tabPieces.get(i).getY() == tabPieces.get(placePiece).getY() && 
						tabPieces.get(i).isEnVie() && nbCoups == tabPieces.get(i).getDernierMouvement()) {
					if (matEchiquier[3][tabPieces.get(placePiece).getY()] == 0 && matEchiquier[2][tabPieces.get(placePiece).getY()] == 0 &&
						matEchiquier[1][tabPieces.get(placePiece).getY()] == 0) {
						tableau[2][tabPieces.get(placePiece).getY()] = 1;
					}
				}
			}
			
		}
		
		
	}
	
	public void enleverPossibilites() {
		
		int memoireX = tabPieces.get(placePieceDeplacee).getX();
		int memoireY = tabPieces.get(placePieceDeplacee).getY();
		matEchiquier[memoireX][memoireY] = 0;
		
		for (int x = 0; x <= 7; x++) {
			for (int y = 0; y <= 7; y++) {
				if (positionsPossibles[x][y] == 1) {
					tabPieces.get(placePieceDeplacee).setX(x);
					tabPieces.get(placePieceDeplacee).setY(y);
					boolean echec = false;
					boolean verif = false; // bugg� sur les cases ou la piece mange
					if (matEchiquier[x][y] == 0) {
						verif = true;
					}
					matEchiquier[x][y] = 1;
					
					int possibilites[][] = new int [8][8];
					if (tabPieces.get(placePieceDeplacee).isBlanc()) {
						for (int i = 0; i < tabPieces.size(); i++)  {
							if (!tabPieces.get(i).isBlanc() && tabPieces.get(i).isEnVie()) {
								//mouvement pions
								if (tabPieces.get(i).getType() == 5) { mouvementPionNoir(possibilites, i); }
								//mouvements cavaliers
								if (tabPieces.get(i).getType() == 4) { mouvementCavalier(possibilites, i); }
								//mouvements fous
								if (tabPieces.get(i).getType() == 3) { mouvementFou(possibilites, i); }
								//mouvements tours
								if (tabPieces.get(i).getType() == 2) { mouvementTour(possibilites, i); }
								//mouvement dames
								if (tabPieces.get(i).getType() == 1) { mouvementTour(possibilites, i); mouvementFou(possibilites, i); }
								//mouvement rois
								if (tabPieces.get(i).getType() == 0) { mouvementRoi(possibilites, i); }
							}
						}
						for (int i = 0; i < tabPieces.size(); i++)  {
							if (tabPieces.get(i).isBlanc() && tabPieces.get(i).getType() == 0) {
								if (possibilites[tabPieces.get(i).getX()][tabPieces.get(i).getY()] == 1) {
									echec = true;
								}
							}
						}
					} else {
						for (int i = 0; i < tabPieces.size(); i++)  {
							if (tabPieces.get(i).isBlanc() && tabPieces.get(i).isEnVie()) {
								//mouvement pions
								if (tabPieces.get(i).getType() == 5) { mouvementPionBlanc(possibilites, i); }
								//mouvements cavaliers
								if (tabPieces.get(i).getType() == 4) { mouvementCavalier(possibilites, i); }
								//mouvements fous
								if (tabPieces.get(i).getType() == 3) { mouvementFou(possibilites, i); }
								//mouvements tours
								if (tabPieces.get(i).getType() == 2) { mouvementTour(possibilites, i); }
								//mouvement dames
								if (tabPieces.get(i).getType() == 1) { mouvementTour(possibilites, i); mouvementFou(possibilites, i); }
								//mouvement rois
								if (tabPieces.get(i).getType() == 0) { mouvementRoi(possibilites, i); }
							}
						}
						for (int i = 0; i < tabPieces.size(); i++)  {
							if (!tabPieces.get(i).isBlanc() && tabPieces.get(i).getType() == 0) {
								if (possibilites[tabPieces.get(i).getX()][tabPieces.get(i).getY()] == 1) {
									echec = true;
								}
							}
						}
					}
					if (echec) {
						positionsPossibles[x][y] = 0;
					}
					if (verif) {
					matEchiquier[x][y] = 0;
					}
				}
			}
		}
		
		matEchiquier[memoireX][memoireY] = 1;
		tabPieces.get(placePieceDeplacee).setX(memoireX);
		tabPieces.get(placePieceDeplacee).setY(memoireY);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == activationRotation) {
			rotaAuto = !rotaAuto;
			if (rotaAuto) {
				activationRotation.setBackground(Color.BLACK);
			} else {
				activationRotation.setBackground(Color.LIGHT_GRAY);
			}
			rotaArreteeSurBlanc = rotation%2 == 0;
			
			if (transfoPion) {
				for (int i = 0; i < tabPieces.size(); i++)  {
					if (tabPieces.get(i).getType() == 5 && (tabPieces.get(i).getY() == 0 || tabPieces.get(i).getY() == 7)) {
						int x = 0;
						int y = 0;
						x = xTransfo;
						y = yTransfo;
						if ((rotation%2 == 0 && rotaAuto) || (!rotaAuto && rotaArreteeSurBlanc)) {
							//blancs
							if (tabPieces.get(i).getY() == 0) {
								cavalier_blanc.setBounds(x*100, 0, 50, 50);
								fou_blanc.setBounds(x*100 + 50, 0, 50, 50);
								tour_blanc.setBounds(x*100, 50, 50, 50);
								dame_blanc.setBounds(x*100 + 50, 50, 50, 50);
							}
							//noirs
							else {
								cavalier_noir.setBounds(x*100, 700, 50, 50);
								fou_noir.setBounds(x*100 + 50, 700, 50, 50);
								tour_noir.setBounds(x*100, 750, 50, 50);
								dame_noir.setBounds(x*100 + 50, 750, 50, 50);
							}
							
						} else {
							//blancs
							if (tabPieces.get(i).getY() == 0) {
								cavalier_blanc.setBounds(x*100, 700, 50, 50);
								fou_blanc.setBounds(x*100 + 50, 700, 50, 50);
								tour_blanc.setBounds(x*100, 750, 50, 50);
								dame_blanc.setBounds(x*100 + 50, 750, 50, 50);
							}
							//noirs
							else {
								cavalier_noir.setBounds(700-x*100, 0, 50, 50);
								fou_noir.setBounds(700-x*100 + 50, 0, 50, 50);
								tour_noir.setBounds(700-x*100, 50, 50, 50);
								dame_noir.setBounds(700-x*100 + 50, 50, 50, 50);
							}
						}
						transfoPion = true;
						xTransfo = x;
						yTransfo = y;
					}
				}
			}
			
		}
		
		if (e.getSource() == activationAfficherCoups) {
			afficherCoups = !afficherCoups;
			if (afficherCoups) {
				activationAfficherCoups.setBackground(Color.BLACK);
			} else {
				activationAfficherCoups.setBackground(Color.LIGHT_GRAY);
			}
			repaint();
		}
		
		
		if (e.getSource() == cavalier_blanc) {
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == xTransfo && tabPieces.get(i).getY() == yTransfo && tabPieces.get(i).getType() == 5) {
					tabPieces.get(i).setType(4);
					cavalier_blanc.setBounds(-50, -50, 50, 50);
					fou_blanc.setBounds(-50, -50, 50, 50);
					tour_blanc.setBounds(-50, -50, 50, 50);
					dame_blanc.setBounds(-50, -50, 50, 50);
					tabPieces.get(i).setImgPiece(cavalier_blanc1.getImgPiece());
					transfoPion = false;
					rotation++;
				}
			}
		}
		if (e.getSource() == fou_blanc) {
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == xTransfo && tabPieces.get(i).getY() == yTransfo && tabPieces.get(i).getType() == 5) {
					tabPieces.get(i).setType(3);
					cavalier_blanc.setBounds(-50, -50, 50, 50);
					fou_blanc.setBounds(-50, -50, 50, 50);
					tour_blanc.setBounds(-50, -50, 50, 50);
					dame_blanc.setBounds(-50, -50, 50, 50);
					tabPieces.get(i).setImgPiece(fou_blanc1.getImgPiece());
					transfoPion = false;		
					rotation++;
				}
			}
		}
		if (e.getSource() == tour_blanc) {
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == xTransfo && tabPieces.get(i).getY() == yTransfo && tabPieces.get(i).getType() == 5) {
					tabPieces.get(i).setType(2);
					cavalier_blanc.setBounds(-50, -50, 50, 50);
					fou_blanc.setBounds(-50, -50, 50, 50);
					tour_blanc.setBounds(-50, -50, 50, 50);
					dame_blanc.setBounds(-50, -50, 50, 50);
					tabPieces.get(i).setImgPiece(tour_blanc1.getImgPiece());
					transfoPion = false;
					rotation++;
				}
			}
		}
		if (e.getSource() == dame_blanc) {
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == xTransfo && tabPieces.get(i).getY() == yTransfo && tabPieces.get(i).getType() == 5) {
					tabPieces.get(i).setType(1);
					cavalier_blanc.setBounds(-50, -50, 50, 50);
					fou_blanc.setBounds(-50, -50, 50, 50);
					tour_blanc.setBounds(-50, -50, 50, 50);
					dame_blanc.setBounds(-50, -50, 50, 50);
					tabPieces.get(i).setImgPiece(dame_blanc1.getImgPiece());
					transfoPion = false;
					rotation++;
				}
			}
		}
		
		if (e.getSource() == cavalier_noir) {
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == xTransfo && tabPieces.get(i).getY() == yTransfo && tabPieces.get(i).getType() == 5) {
					tabPieces.get(i).setType(4);
					cavalier_noir.setBounds(-50, -50, 50, 50);
					fou_noir.setBounds(-50, -50, 50, 50);
					tour_noir.setBounds(-50, -50, 50, 50);
					dame_noir.setBounds(-50, -50, 50, 50);
					tabPieces.get(i).setImgPiece(cavalier_noir1.getImgPiece());
					transfoPion = false;
					rotation++;
				}
			}
		}
		if (e.getSource() == fou_noir) {
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == xTransfo && tabPieces.get(i).getY() == yTransfo && tabPieces.get(i).getType() == 5) {
					tabPieces.get(i).setType(3);
					cavalier_noir.setBounds(-50, -50, 50, 50);
					fou_noir.setBounds(-50, -50, 50, 50);
					tour_noir.setBounds(-50, -50, 50, 50);
					dame_noir.setBounds(-50, -50, 50, 50);
					tabPieces.get(i).setImgPiece(fou_noir1.getImgPiece());
					transfoPion = false;
					rotation++;
				}
			}
		}
		if (e.getSource() == tour_noir) {
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == xTransfo && tabPieces.get(i).getY() == yTransfo && tabPieces.get(i).getType() == 5) {
					tabPieces.get(i).setType(2);
					cavalier_noir.setBounds(-50, -50, 50, 50);
					fou_noir.setBounds(-50, -50, 50, 50);
					tour_noir.setBounds(-50, -50, 50, 50);
					dame_noir.setBounds(-50, -50, 50, 50);
					tabPieces.get(i).setImgPiece(tour_noir1.getImgPiece());
					transfoPion = false;
					rotation++;
				}
			}
		}
		if (e.getSource() == dame_noir) {
			for (int i = 0; i < tabPieces.size(); i++) {
				if (tabPieces.get(i).getX() == xTransfo && tabPieces.get(i).getY() == yTransfo && tabPieces.get(i).getType() == 5) {
					tabPieces.get(i).setType(1);
					cavalier_noir.setBounds(-50, -50, 50, 50);
					fou_noir.setBounds(-50, -50, 50, 50);
					tour_noir.setBounds(-50, -50, 50, 50);
					dame_noir.setBounds(-50, -50, 50, 50);
					tabPieces.get(i).setImgPiece(dame_noir1.getImgPiece());
					transfoPion = false;
					rotation++;
				}
			}
		}
		if ((rotation%2 == 0 && rotaAuto) || (!rotaAuto && rotaArreteeSurBlanc)) {
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {
					this.bloc[i][j].setBounds(i*100, j*100, 100, 100);
				}
			}
		} else {
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {
					this.bloc[i][j].setBounds(700-i*100, 700-j*100, 100, 100);
				}
			}
		}
		
		repaint();
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		if (SwingUtilities.isLeftMouseButton(e) && sourisSurEchiquier) {
			
			sourisPressee = true;
			
			if (!transfoPion) {
				//deplacement piece
				for (int x = 0; x <= 7; x++)  {
					for (int y = 0; y <= 7; y++)  {
						if (positionsPossibles[x][y] == 1 && x == posSourisX && y == posSourisY) {
							
							//enregistrement des positions des pi�ces
							for (int i = 0; i < tabPieces.size(); i++)  {
								tabPieces.get(i).setAncienX(tabPieces.get(i).getX());
								tabPieces.get(i).setAncienY(tabPieces.get(i).getY());
							}
							
							//mange une pi�ce
							if (matEchiquier[x][y] == 1) {
								for (int i = 0; i < tabPieces.size(); i++)  {
										if (tabPieces.get(i).getX() == x && tabPieces.get(i).getY() == y && 
												tabPieces.get(i).isBlanc() != tabPieces.get(placePieceDeplacee).isBlanc()) {
											tabPieces.get(i).setEnVie(false);
									}
								}
							}
							
							//prise en passant 
							if (matEchiquier[x][y] == 0) {
								for (int j = 0; j < tabPieces.size(); j++ ) {
									//des blancs
									if (tabPieces.get(j).getX() == x && tabPieces.get(j).getY() == y + 1 && !tabPieces.get(j).isBlanc() &&
											tabPieces.get(placePieceDeplacee).getType() == 5 && tabPieces.get(placePieceDeplacee).isBlanc()) {
										tabPieces.get(j).setEnVie(false);
										matEchiquier[x][y+1] = 0;
									}
									//des noirs
									if (tabPieces.get(j).getX() == x && tabPieces.get(j).getY() == y - 1 && tabPieces.get(j).isBlanc() &&
											tabPieces.get(placePieceDeplacee).getType() == 5 && !tabPieces.get(placePieceDeplacee).isBlanc()) {
										tabPieces.get(j).setEnVie(false);
										matEchiquier[x][y-1] = 0;
									}
								}
							}
							
							//roques
							if (matEchiquier[x][y] == 0 && tabPieces.get(placePieceDeplacee).getType() == 0) {
								for (int j = 0; j < tabPieces.size(); j++ ) {
									//petit roque
									if (tabPieces.get(j).getX() == 7 && tabPieces.get(j).getY() == tabPieces.get(placePieceDeplacee).getY() &&
										tabPieces.get(j).getType() == 2 &&
										x == 6 ) {
										tabPieces.get(j).setX(5);
										matEchiquier[4][y] = 0;
										matEchiquier[5][y] = 1;
										matEchiquier[6][y] = 1;
										matEchiquier[7][y] = 0;
										tabPieces.get(j).setDernierMouvement(0);
									}
									//grand roque
									if (tabPieces.get(j).getX() == 0 && tabPieces.get(j).getY() == tabPieces.get(placePieceDeplacee).getY() &&
										tabPieces.get(j).getType() == 2 &&
										x == 2) {
										tabPieces.get(j).setX(3);
										matEchiquier[0][y] = 0;
										matEchiquier[1][y] = 0;
										matEchiquier[2][y] = 1;
										matEchiquier[3][y] = 1;
										matEchiquier[4][y] = 0;
										tabPieces.get(j).setDernierMouvement(0);
									}
								}
							}
							
							//deplacement de la piece
							matEchiquier[tabPieces.get(placePieceDeplacee).getX()][tabPieces.get(placePieceDeplacee).getY()] = 0;
							tabPieces.get(placePieceDeplacee).setX(x);
							tabPieces.get(placePieceDeplacee).setY(y);
							tabPieces.get(placePieceDeplacee).setDernierMouvement(0);
							matEchiquier[x][y] = 1;
							pieceDeplacee = true;
							
							//transformation des pions
							for (int i = 0; i < tabPieces.size(); i++)  {
								if (tabPieces.get(i).getType() == 5 && (tabPieces.get(i).getY() == 0 || tabPieces.get(i).getY() == 7)) {
									if ((rotation%2 == 0 && rotaAuto) || (!rotaAuto && rotaArreteeSurBlanc)) {
										//blancs
										if (tabPieces.get(i).getY() == 0) {
											cavalier_blanc.setBounds(x*100, 0, 50, 50);
											fou_blanc.setBounds(x*100 + 50, 0, 50, 50);
											tour_blanc.setBounds(x*100, 50, 50, 50);
											dame_blanc.setBounds(x*100 + 50, 50, 50, 50);
										}
										//noirs
										else {
											cavalier_noir.setBounds(x*100, 700, 50, 50);
											fou_noir.setBounds(x*100 + 50, 700, 50, 50);
											tour_noir.setBounds(x*100, 750, 50, 50);
											dame_noir.setBounds(x*100 + 50, 750, 50, 50);
										}
										
									} else {
										//blancs
										if (tabPieces.get(i).getY() == 0) {
											cavalier_blanc.setBounds(x*100, 700, 50, 50);
											fou_blanc.setBounds(x*100 + 50, 700, 50, 50);
											tour_blanc.setBounds(x*100, 750, 50, 50);
											dame_blanc.setBounds(x*100 + 50, 750, 50, 50);
										}
										//noirs
										else {
											cavalier_noir.setBounds(700-x*100, 0, 50, 50);
											fou_noir.setBounds(700-x*100 + 50, 0, 50, 50);
											tour_noir.setBounds(700-x*100, 50, 50, 50);
											dame_noir.setBounds(700-x*100 + 50, 50, 50, 50);
										}
									}
									transfoPion = true;
									xTransfo = x;
									yTransfo = y;
								}
							}
							
							//augmente de 1 pour toutes les pieces le nombre de tours depuis lequel elles n'ont pas jou�
							//utlis� pour la prise en passant et les roques
							for (int i = 0; i < tabPieces.size(); i++)  {
								tabPieces.get(i).setDernierMouvement(tabPieces.get(i).getDernierMouvement() + 1);
							}
							
							nbCoups++;
							if (!transfoPion) {
								rotation++;
							}
							if ((rotation%2 == 0 && rotaAuto) || (!rotaAuto && rotaArreteeSurBlanc)) {
								for (int i = 0; i <= 7; i++) {
									for (int j = 0; j <= 7; j++) {
										this.bloc[i][j].setBounds(i*100, j*100, 100, 100);
									}
								}
							} else {
								for (int i = 0; i <= 7; i++) {
									for (int j = 0; j <= 7; j++) {
										this.bloc[i][j].setBounds(700-i*100, 700-j*100, 100, 100);
									}
								}
							}
							
						}
					}
				}
			
			
				for (int x = 0; x <= 7; x++)  {
					for (int y = 0; y <= 7; y++)  {
						positionsPossibles[x][y] = 0;
					}
				}
				
				boolean trouve = false; // permet de griser la case selectionn�e
				
				if (!pieceDeplacee) {
					if (matEchiquier[posSourisX][posSourisY] == 1) {
						for (int i = 0; i < tabPieces.size(); i++) {
							if (tabPieces.get(i).getX() == posSourisX && tabPieces.get(i).getY() == posSourisY ) {
								trouve = true;
								if (tabPieces.get(i).isEnVie()) {
									placePieceDeplacee = i;
									if ((nbCoups%2 != 0 && !tabPieces.get(placePieceDeplacee).isBlanc()) || 
											(nbCoups%2 == 0 && tabPieces.get(placePieceDeplacee).isBlanc())) {
										//mouvement pions blancs
										if (tabPieces.get(i).isBlanc()) {
											if (tabPieces.get(i).getType() == 5) { mouvementPionBlanc(positionsPossibles, placePieceDeplacee); }
										}
										//mouvement pions noirs
										else {
											if (tabPieces.get(i).getType() == 5) { mouvementPionNoir(positionsPossibles, placePieceDeplacee); }
										}
										//mouvements cavaliers
										if (tabPieces.get(i).getType() == 4) { mouvementCavalier(positionsPossibles, placePieceDeplacee); }
										//mouvements fous
										if (tabPieces.get(i).getType() == 3) { mouvementFou(positionsPossibles, placePieceDeplacee); }
										//mouvements tours
										if (tabPieces.get(i).getType() == 2) { mouvementTour(positionsPossibles, placePieceDeplacee); }
										//mouvement dames
										if (tabPieces.get(i).getType() == 1) {
											mouvementTour(positionsPossibles, placePieceDeplacee);
											mouvementFou(positionsPossibles, placePieceDeplacee); }
										//mouvement rois
										if (tabPieces.get(i).getType() == 0) { mouvementRoi(positionsPossibles, placePieceDeplacee); }
									}
								}
							}
						}
					}
				}
				
				if (placePieceDeplacee != -1) { enleverPossibilites(); } // enleve les cases qui mettent le roi en echec
				
				//v�rification de l'�chec --------------------------------------------------------------------------------------------------------------------------
				
				for (int x = 0; x <= 7; x++)  {
					for (int y = 0; y <= 7; y++)  {
						if (positionsPossibles[x][y] == 1) { trouve = true; }
					}
				}
				
				if (!trouve) { placePieceDeplacee = -1; }
				
				pieceDeplacee = false;
			}
			
			repaint();
		}
	}

	public void mouseEntered(MouseEvent e) {
		
		
		if (e.getSource() == cavalier_blanc || e.getSource() == fou_blanc || e.getSource() == tour_blanc || e.getSource() == dame_blanc || 
				e.getSource() == cavalier_noir || e.getSource() == fou_noir || e.getSource() == tour_noir || e.getSource() == dame_noir ||
				e.getSource() == activationRotation || e.getSource() == activationAfficherCoups) {
			
		} else {
			if (e.getSource() != Main.scene) { //empecher d'envoyer un message d'erreur quand on quitter la fenetre
			boutonRecupereName = (JButton) e.getSource();
			nomBloc=boutonRecupereName.getName();
			stringXClique = nomBloc.substring(4 , nomBloc.indexOf( "_" ) );
			stringYClique = nomBloc.substring(nomBloc.indexOf( "_" ) + 1, nomBloc.length());
			posSourisX = Integer.parseInt(stringXClique); // transforme les string en int
			posSourisY = Integer.parseInt(stringYClique);
			//System.out.println(posSourisX + ", " + posSourisY + ", " + matEchiquier[posSourisX][posSourisY]);
			sourisSurEchiquier = true;
			}
		}
		repaint();
	}
	
	public void mouseReleased(MouseEvent e) { 
		
		sourisPressee =  false;
		
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (placePieceDeplacee != -1 && (posSourisX != tabPieces.get(placePieceDeplacee).getX() || posSourisY != tabPieces.get(placePieceDeplacee).getY())) {
			
			if (!transfoPion) {
				//deplacement piece
				for (int x = 0; x <= 7; x++)  {
					for (int y = 0; y <= 7; y++)  {
						if (positionsPossibles[x][y] == 1 && x == posSourisX && y == posSourisY) {
							
							//enregistrement des positions des pi�ces
							for (int i = 0; i < tabPieces.size(); i++)  {
								tabPieces.get(i).setAncienX(tabPieces.get(i).getX());
								tabPieces.get(i).setAncienY(tabPieces.get(i).getY());
							}
							
							//mange une pi�ce
							if (matEchiquier[x][y] == 1) {
								for (int i = 0; i < tabPieces.size(); i++)  {
										if (tabPieces.get(i).getX() == x && tabPieces.get(i).getY() == y && 
												tabPieces.get(i).isBlanc() != tabPieces.get(placePieceDeplacee).isBlanc()) {
											tabPieces.get(i).setEnVie(false);
									}
								}
							}
							
							//prise en passant 
							if (matEchiquier[x][y] == 0) {
								for (int j = 0; j < tabPieces.size(); j++ ) {
									//des blancs
									if (tabPieces.get(j).getX() == x && tabPieces.get(j).getY() == y + 1 && !tabPieces.get(j).isBlanc() &&
											tabPieces.get(placePieceDeplacee).getType() == 5 && tabPieces.get(placePieceDeplacee).isBlanc()) {
										tabPieces.get(j).setEnVie(false);
										matEchiquier[x][y+1] = 0;
									}
									//des noirs
									if (tabPieces.get(j).getX() == x && tabPieces.get(j).getY() == y - 1 && tabPieces.get(j).isBlanc() &&
											tabPieces.get(placePieceDeplacee).getType() == 5 && !tabPieces.get(placePieceDeplacee).isBlanc()) {
										tabPieces.get(j).setEnVie(false);
										matEchiquier[x][y-1] = 0;
									}
								}
							}
							
							//roques
							if (matEchiquier[x][y] == 0 && tabPieces.get(placePieceDeplacee).getType() == 0) {
								for (int j = 0; j < tabPieces.size(); j++ ) {
									//petit roque
									if (tabPieces.get(j).getX() == 7 && tabPieces.get(j).getY() == tabPieces.get(placePieceDeplacee).getY() &&
										tabPieces.get(j).getType() == 2 &&
										x == 6 ) {
										tabPieces.get(j).setX(5);
										matEchiquier[4][y] = 0;
										matEchiquier[5][y] = 1;
										matEchiquier[6][y] = 1;
										matEchiquier[7][y] = 0;
										tabPieces.get(j).setDernierMouvement(0);
									}
									//grand roque
									if (tabPieces.get(j).getX() == 0 && tabPieces.get(j).getY() == tabPieces.get(placePieceDeplacee).getY() &&
										tabPieces.get(j).getType() == 2 &&
										x == 2) {
										tabPieces.get(j).setX(3);
										matEchiquier[0][y] = 0;
										matEchiquier[1][y] = 0;
										matEchiquier[2][y] = 1;
										matEchiquier[3][y] = 1;
										matEchiquier[4][y] = 0;
										tabPieces.get(j).setDernierMouvement(0);
									}
								}
							}
							
							//deplacement de la piece
							matEchiquier[tabPieces.get(placePieceDeplacee).getX()][tabPieces.get(placePieceDeplacee).getY()] = 0;
							tabPieces.get(placePieceDeplacee).setX(x);
							tabPieces.get(placePieceDeplacee).setY(y);
							tabPieces.get(placePieceDeplacee).setDernierMouvement(0);
							matEchiquier[x][y] = 1;
							pieceDeplacee = true;
							
							//transformation des pions
							for (int i = 0; i < tabPieces.size(); i++)  {
								if (tabPieces.get(i).getType() == 5 && (tabPieces.get(i).getY() == 0 || tabPieces.get(i).getY() == 7)) {
									if ((rotation%2 == 0 && rotaAuto) || (!rotaAuto && rotaArreteeSurBlanc)) {
										//blancs
										if (tabPieces.get(i).getY() == 0) {
											cavalier_blanc.setBounds(x*100, 0, 50, 50);
											fou_blanc.setBounds(x*100 + 50, 0, 50, 50);
											tour_blanc.setBounds(x*100, 50, 50, 50);
											dame_blanc.setBounds(x*100 + 50, 50, 50, 50);
										}
										//noirs
										else {
											cavalier_noir.setBounds(x*100, 700, 50, 50);
											fou_noir.setBounds(x*100 + 50, 700, 50, 50);
											tour_noir.setBounds(x*100, 750, 50, 50);
											dame_noir.setBounds(x*100 + 50, 750, 50, 50);
										}
										
									} else {
										//blancs
										if (tabPieces.get(i).getY() == 0) {
											cavalier_blanc.setBounds(x*100, 700, 50, 50);
											fou_blanc.setBounds(x*100 + 50, 700, 50, 50);
											tour_blanc.setBounds(x*100, 750, 50, 50);
											dame_blanc.setBounds(x*100 + 50, 750, 50, 50);
										}
										//noirs
										else {
											cavalier_noir.setBounds(700-x*100, 0, 50, 50);
											fou_noir.setBounds(700-x*100 + 50, 0, 50, 50);
											tour_noir.setBounds(700-x*100, 50, 50, 50);
											dame_noir.setBounds(700-x*100 + 50, 50, 50, 50);
										}
									}
									transfoPion = true;
									xTransfo = x;
									yTransfo = y;
								}
							}
							
							//augmente de 1 pour toutes les pieces le nombre de tours depuis lequel elles n'ont pas jou�
							//utlis� pour la prise en passant et les roques
							for (int i = 0; i < tabPieces.size(); i++)  {
								tabPieces.get(i).setDernierMouvement(tabPieces.get(i).getDernierMouvement() + 1);
							}
							
							nbCoups++;
							if (!transfoPion) {
								rotation++;
							}
							if ((rotation%2 == 0 && rotaAuto) || (!rotaAuto && rotaArreteeSurBlanc)) {
								for (int i = 0; i <= 7; i++) {
									for (int j = 0; j <= 7; j++) {
										this.bloc[i][j].setBounds(i*100, j*100, 100, 100);
									}
								}
							} else {
								for (int i = 0; i <= 7; i++) {
									for (int j = 0; j <= 7; j++) {
										this.bloc[i][j].setBounds(700-i*100, 700-j*100, 100, 100);
									}
								}
							}
						}
					}
				}
			
				for (int x = 0; x <= 7; x++)  {
					for (int y = 0; y <= 7; y++)  {
						positionsPossibles[x][y] = 0;
					}
				}
				
				boolean trouve = false; // permet de griser la case selectionn�e
				
				for (int x = 0; x <= 7; x++)  {
					for (int y = 0; y <= 7; y++)  {
						if (positionsPossibles[x][y] == 1) { trouve = true; }
					}
				}
				
				if (!trouve) { placePieceDeplacee = -1; }
				
				pieceDeplacee = false;
			}	
			
			
			repaint();
			}
		}
	}
	
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == cavalier_blanc || e.getSource() == fou_blanc || e.getSource() == tour_blanc || e.getSource() == dame_blanc || 
				e.getSource() == cavalier_noir || e.getSource() == fou_noir || e.getSource() == tour_noir || e.getSource() == dame_noir ||
				e.getSource() == activationRotation) {
			
		} else {
			sourisSurEchiquier = false;
		}
	}
	
	public void mouseClicked(MouseEvent e) { } //nul celui-l�

}
