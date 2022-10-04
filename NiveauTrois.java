package net.idf.java;

import java.util.Scanner;

public class NiveauTrois extends Contenu {
	

	static int[] territoire = new int[9];
	
	static int un = 0; // héros
	static int deux = 0; // escalier
	static int trois = 0; // monstre
	
	static int vie = 10;


	public static void chapitreTrois() {
		
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
		+ " NIVEAU 3\n"
		+ "======================================\n"
		+ "\n"
		+ "Vous vous trouvez dans une pièce de 3 x 3.\n"
		+ "Vous vous trouvez en haut à gauche de la pièce.\n"
		+ "Il fait noir, vous ne voyez pas ce qui est autour de vous.\n"
		+ "Dans la pièce se trouve aussi un escalier et un petit monstre.\n"
		+ "L'escalier vous permet de peut-être trouver la sortie.\n"
		+ "Quand au petit monstre, il est tout simplement mortel ...\n"
		+ "... et pour l'éviter, c'est de la chance !\n"
		+ "\n"
		+ "Vous gagnez 5 Points de Vie supplémentaires.\n"
		+ "\n"
		+ "VIE = 10 p.v.\n"
		+ "\n");

	}
	
	

	/*
	 * Création de la pièce en 3x3
	 * 0 : vide
	 * 1 : héros
	 * 2 : escalier
	 * 3 : monstre
	 * 
	 * Génération aléatoire
	 * vérification que le héros, l'escalier et le monstre n'y sont qu'une fois
	 * et si ce n'est pas le cas :
	 * 0 fois ->  ajout non aléatoire
	 * plusieurs fois -> transformation en 0
	 * 
	 * Règles
	 */
	
	static void creation () {
		

		for (int i=0; i < territoire.length; i++) {
			
			int hasard = (int) (Math.random() * 4);
			
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
			
			if ((hasard == 3) && (trois == 0)) {
				trois = 3;
			} else if ((hasard == 3) && (trois == 3)) {
				hasard = 0;
			}
			
			if ((hasard == 0) && (deux == 0) && (i == 8))
				hasard = 2;
			
			if ((hasard == 0) && (trois == 0) && (i == 8)) {
				hasard = 3;
			} else if ((hasard == 2) && (trois == 0) && (i == 8)) {
				territoire[2] = 3;	
			}

			territoire[i] = hasard;
			
			/*
				Solution niveau 3
			
			System.out.print(territoire[i]);
			if ((i == 2) || (i == 5) || (i == 8))
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
					situation(3);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(0);
				} else {
					mauvaiseReponse(reponse);
				}
				if (territoire[0] == 1) {
					territoire[0] = 0;
				}

			} else if (territoire[1] == 1) {
				haut();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("d")) {
					situation(2);
				} else if (reponse.equalsIgnoreCase("b")) {
					situation(4);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(0);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(1);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[1] = 0;
				
			} else if (territoire[2] == 1) {
				coinHautDroit();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("b")) {
					situation(5);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(1);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(2);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[2] = 0;

			} else if (territoire[3] == 1) {
				gauche();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(0);
				} else if (reponse.equalsIgnoreCase("d")) {
					situation(4);
				} else if (reponse.equalsIgnoreCase("b")) {
					situation(6);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(3);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[3] = 0;

			} else if (territoire[4] == 1) {
				centre();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(1);
				} else if (reponse.equalsIgnoreCase("d")) {
					situation(5);
				} else if (reponse.equalsIgnoreCase("b")) {
					situation(7);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(3);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(4);
				} else {
					mauvaiseReponse(reponse);
				}
				territoire[4] = 0;

			} else if (territoire[5] == 1) {
				droit();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(2);
				} else if (reponse.equalsIgnoreCase("b")) {
					situation(8);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(4);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(5);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[5] = 0;

			} else if (territoire[6] == 1) {
				coinBasGauche();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(3);
				} else if (reponse.equalsIgnoreCase("d")) {
					situation(7);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(6);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[6] = 0;

			} else if (territoire[7] == 1) {
				bas();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(4);
				} else if (reponse.equalsIgnoreCase("d")) {
					situation(8);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(6);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(7);
				} else {
					mauvaiseReponse(reponse);
				}
				territoire[7] = 0;

			} else if (territoire[8] == 1) {
				coinBasDroit();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(5);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(7);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(8);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[8] = 0;

			} else {
				break;
			}
			
		}
		
		// clavier.close();
	}
	
	/*
	 * Résolution du déplacement
	 * 0 : rien
	 * 2 : escalier et gagnant
	 * 3 : monstre et perdu
	 * 
	 */
	
	static void situation(int position) {
		if (territoire[position] == 0) {
			territoire[position] = 1;
		} else if (territoire[position] == 2) {
			System.out.println("\n"
					+ "Enfin l'escalier ...");
			territoire[0] = 7;			
		} else if (territoire[position] == 3) {
			System.out.println("\n"
					+ "Vous affrontez le petit monstre. Il pointe vers vous sa lance.\n"
					+ "Il vous embroche plusieurs fois. Vous êtes ... mort.\n"
					+ "\n"
					+ "VIE = 0 p.v.\n"
					+ "\n");
			territoire[0] = 8;
		} else {
			System.out.println("PROBLEME : au niveau de la methode situation() sur NiveauTrois");
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
		System.out.println("Vous n'avez rien dans vos poches qui peut vous être utile.\n");
		deplacement();
	}


	/*
	 * Conclusion du niveau 3
	 * 
	 */
	static void conclusion() {
		
		if (territoire[0] == 7) {
			System.out.println("Vous vous échappez de la pièce en descendant l'escalier.");
		}
		else if (territoire[0] == 8) {
			System.out.println("Vous vous retrouvez dans le cimetière des héros inconnus.\n"
					+ "\n"
					+ "FIN DU JEU\n"
					+ "\n");
			
			Main.recommencer();

		}
		else if (territoire[0] == 9) {
			System.out.println("Vous êtes au cimetière des mauvaises réponses.\n"
					+ "(vous avez tapé sur une mauvaise touche !!)\n"
					+ "\n"
					+ "FIN DU JEU\n"
					+ "\n");
			
			Main.recommencer();

		}
	}

}
