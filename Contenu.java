package net.idf.java;

import java.util.ArrayList;

abstract public class Contenu {

	/*
	 * Tableau avec potion, parchemin
	 * 
	 * 0 : potion de soin - potion
	 * 1 : parchemin vision totale - parchemin
	 */
	
	public static ArrayList<Integer> poche = new ArrayList<Integer>();
	
	// ajouter un ou plusieurs objets similaires dans une poche
	public static void ajoutPoche(int objet, int quantite) {
		poche.add(objet, quantite);
	}
	
	// utiliser un objet (et donc le supprimer de la poche)
	public static void utiliserPoche(int objet) {
		int quantite = poche.get(objet);
		
		if (quantite == 0) {
			System.out.println("Vous ne possédez plus cet objet. Il est inutile d'essayer de l'utiliser.\n");
		} else {
			quantite = quantite -1;
			
			poche.set(objet, quantite);
		}
	}

	// verifier le contenu de la poche
	public static void contenuPoche() {
		String nom = null;
		System.out.println("Vous avez dans vos poche :");
		for(int i = 0; i < poche.size(); i++) {
			if (i == 0) {
				nom = "potion de soin + 15 P.V. (syntaxe : POTION)";
			} else if (i == 1) {
				nom = "parchemin de vision magique (syntaxe : PARCHEMIN)"; 
			}

			if (poche.get(i) > 0) {
				System.out.println(nom + " : " + poche.get(i));
			}
		}
		System.out.println("\n");
	}

	
	/*
	 * Commandes pour le déplacement
	 */
	
	static void coinHautGauche() {
		System.out.println("Vous pouvez aller à droite(d) ou en bas(b).");
		System.out.println("Quelle direction prenez-vous ?");
	}

	static void coinHautDroit() {
		System.out.println("Vous pouvez aller en bas(b) ou à gauche(g).");
		System.out.println("Quelle direction prenez-vous ?");
	}

	static void coinBasGauche() {
		System.out.println("Vous pouvez aller en haut(h) ou à droite(d).");
		System.out.println("Quelle direction prenez-vous ?");
	}

	static void coinBasDroit() {
		System.out.println("Vous pouvez aller en haut(h) ou à gauche(g).");
		System.out.println("Quelle direction prenez-vous ?");
	}
	
	
	static void haut() {
		System.out.println("Vous pouvez aller à droite(d), en bas(b) ou à gauche(g).");
		System.out.println("Quelle direction prenez-vous ?");
	}
	
	static void gauche() {
		System.out.println("Vous pouvez aller en haut(h), à droite(d) ou en bas(b).");
		System.out.println("Quelle direction prenez-vous ?");
	}
	
	static void droit() {
		System.out.println("Vous pouvez aller en haut(h), en bas(b) ou à gauche(g).");
		System.out.println("Quelle direction prenez-vous ?");
	}
	
	static void bas() {
		System.out.println("Vous pouvez aller en haut(h), à droite(d) ou à gauche(g).");
		System.out.println("Quelle direction prenez-vous ?");
	}
	

	static void centre() {
		System.out.println("Vous pouvez aller en haut(h), à droite(d), en bas(b) ou à gauche(g).");
		System.out.println("Quelle direction prenez-vous ?");
	}
	
}
