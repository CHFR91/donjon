package net.idf.java;

import java.util.Scanner;

public class NiveauDeux extends Contenu {
	

	static int[] territoire = new int[4];
	
	static int un = 0; // héros
	static int deux = 0; // escalier
	
	static int vie = 5;

	
	public static void chapitreDeux() {
		
		introduction();
		creation();
		deplacement();
		conclusion();
		
	}

	static void introduction() {
		
		System.out.println(""
		+ "\n"
		+ "\n"
		+ "\n"
		+ "======================================\n"
		+ " NIVEAU 2\n"
		+ "======================================\n"
		+ "\n"
		+ "Vous vous trouvez dans une pièce de 2 x 2.\n"
		+ "Vous vous trouvez en haut à gauche de la pièce.\n"
		+ "Il fait noir, vous ne voyez pas ce qui est autour de vous.\n"
		+ "Dans la pièce se trouve aussi un escalier.\n"
		+ "L'escalier vous permet de peut-être trouver la sortie.\n"
		+ "\n"
		+ "Vous découvrez un nouveau concept dans cette tour ... la vie.\n"
		+ "En descendant l'escalier, vous avez gagné 5 Points de Vie.\n"
		+ "\n"
		+ "VIE = 5 p.v.\n"
		+ "\n");

	}
	

	/*
	 * Création de la pièce en 2x2
	 * 0 : vide
	 * 1 : héros
	 * 2 : escalier
	 * 
	 * Génération aléatoire
	 * vérification que le héros et l'escalier n'y sont qu'une fois
	 * et si ce n'est pas le cas :
	 * 0 fois ->  ajout non aléatoire
	 * plusieurs fois -> transformation en 0
	 * 
	 */
	

	static void creation () {
		

		for (int i=0; i < territoire.length; i++) {
			
			int hasard = (int) (Math.random() * 3);
			
			if (i == 0)
				hasard = 1; 
			
			if ((hasard == 1) && (i > 0)) {
				hasard = 0;
			}
			
			if ((hasard == 2) && (deux == 0)) {
				deux = 2;
			} else if ((hasard == 2) && (deux == 2)) {
				hasard = 0;
			}
						
			if ((hasard == 0) && (deux == 0) && (i == 3))
				hasard = 2;

			territoire[i] = hasard;
			
			/*
				Solution niveau 2
			
			System.out.print(territoire[i]);
			if ((i == 1) || (i == 3))
					System.out.print("\n");
			*/
			
		}
	}

	/*
	 * Déplacement du héros
	 * Proposition des options de déplacement (haut, droite, bas, gauche)
	 * Lancement de la résolution du déplacement avec situation(POSITION)
	 * 
	 */
	
	static void deplacement() {
		
		Scanner clavier = new Scanner(System.in);
		String reponse;
		
		while (true) {

			if (territoire[0] == 1) {
				coinHautGauche();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("d")) {
					situation(1);
				} else if (reponse.equalsIgnoreCase("b")) {
					situation(2);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(0);
				} else {
					mauvaiseReponse(reponse);
				}
				if (territoire[0] == 1) {
					territoire[0] = 0;
				}

			} else if (territoire[1] == 1) {
				coinHautDroit();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("b")) {
					situation(3);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(0);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(1);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[1] = 0;
				
			} else if (territoire[2] == 1) {
				coinBasGauche();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(0);
				} else if (reponse.equalsIgnoreCase("d")) {
					situation(3);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(2);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[2] = 0;
				
			} else if (territoire[3] == 1) {
				coinBasDroit();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(1);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(2);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(3);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[3] = 0;
				
			} else {
				break;
			}
						
		}
		
		clavier.close();
	}
	
	/*
	 * Résolution du déplacement
	 * 0 : rien
	 * 2 : escalier et gagnant
	 * 
	 */
	
	static void situation(int position) {
		if (territoire[position] == 0) {
			territoire[position] = 1;
		} else if (territoire[position] == 2) {
			System.out.println("\n"
					+ "Enfin l'escalier ...");
			territoire[0] = 7;
		} else {
			System.out.println("PROBLEME : au niveau de la methode situation() sur NiveauDeux");
		}
	}

	/*
	 * Si une mauvaise touche est tapée que celles demandées
	 * 
	 */
	
	static void mauvaiseReponse(String reponse) {
		System.out.println(reponse + " est une mauvaise réponse.");
		System.out.println("Vous avez perdu !");
		territoire[0] = 9;		
	}

	
	/*
	 * Gestion du contenu de vos poches
	 * 
	 */

	static void aide(int position) {
		System.out.println("Vous n'avez rien dans vos poches sauf un téléphone mobile inutile.\n");
		deplacement();
	}

	
	static void conclusion() {
		
		if (territoire[0] == 7) {
			System.out.println("Vous vous échappez de la pièce en descendant l'escalier.");
		}
		else if (territoire[0] == 9) {
			System.out.println("Vous êtes au cimetière des mauvaises réponses."
					+ "\n"
					+ "(vous avez tapé sur une mauvaise touche !!)"
					+ "\n"
					+ "FIN DU JEU"
					+ "\n"
					+ "\n");
			
			Main.recommencer();

		}
	}


	
	
}
