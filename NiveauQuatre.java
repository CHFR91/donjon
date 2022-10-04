package net.idf.java;

import java.util.Scanner;

public class NiveauQuatre extends Contenu {

	static int[] territoire = new int[16];
	
	static int un = 0; // héros
	static int deux = 0; // escalier
	static int trois = 0; // petit monstre 1
	static int quatre = 0; // petit monstre 2
	static int cinq = 0; // parchemin
	
	static int vie = 15;
	
	public static void chapitreQuatre() {
		
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
		+ " NIVEAU 4\n"
		+ "======================================\n"
		+ "\n"
		+ "Vous vous trouvez en haut à gauche dans une pièce noire de 4 x 4.\n"
		+ "Dans la pièce se trouve un escalier, deux petits monstres et un parchemin.\n"
		+ "L'escalier vous permet de peut-être trouver la sortie.\n"
		+ "Pour les petits monstres, chacun vous enlève 10 p.v. (soit 2 monstres x 10 p.v.) ...\n"
		+ "... avec la vie que vous possédez, vous devez pouvoir gérer !\n"
		+ "Enfin, en utilisant le parchemin, vous découvrez les cases autour de vous.\n"
		+ "\n"
		+ "Vous gagnez 5 Points de Vie supplémentaires.\n"
		+ "\n"
		+ "VIE = 15 p.v.\n"
		+ "\n"
		+ "ATTENTION, dans l'escalier, vous trouvez une potion rouge (sur l'étiquette vous lisez +15)\n"
		+ "et une épée courte (pour vous défendre, évidemment) que vous gardez en main !\n"
		+ "\n"
		+ "Tapez à tout moment AIDE sur l'interface pour accéder aux objets dans vos poches.\n"
		+ "ATTENTION : la potion et autres objets dans ce niveau sont utilisables que dans ce niveau !!"
		+ "\n");

		// ajout dans la poche : potion (0), quantité +1;
		ajoutPoche(0, 1);
	}
	


	/*
	 * Création de la pièce en 4x4
	 * 0 : vide
	 * 1 : héros
	 * 2 : escalier
	 * 3 : monstre 1
	 * 4 : monstre 2
	 * 5 : parchemin
	 * 
	 * Génération aléatoire
	 * vérification que le héros, l'escalier, chaque monstre et le parchemin n'y sont qu'une fois
	 * et si ce n'est pas le cas :
	 * 0 fois ->  ajout non aléatoire
	 * plusieurs fois -> transformation en 0
	 * 
	 */
	
	static int verificateur = 0;
	
	static void creation () {
		
		for (int i=0; i < territoire.length; i++) {
			
			int hasard = (int) (Math.random() * 6);
			
			if (i == 0)
				hasard = 1; 
			
			if ((i == 1) || (i == 4))
				hasard = 0;
			
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
			
			if ((hasard == 4) && (quatre == 0)) {
				quatre = 4;
			} else if ((hasard == 4) && (quatre == 4)) {
				hasard = 0;
			}
			
			if ((hasard == 5) && (cinq == 0)) {
				cinq = 5;
			} else if ((hasard == 5) && (cinq == 5)) {
				hasard = 0;
			}
			
			
			if (i == 15) {
				verificateur = hasard;
				
				if (deux == 0) {
					if (hasard == 0) {
						hasard = 2;
					} else {
						territoire[11] = 2;
					}
				}
				
				if (trois == 0) {
					if (hasard == 0) {
						hasard = 3;
					} else {
						territoire[8] = 3;
					}
				}

				if (quatre == 0) {
					if (hasard == 0) {
						hasard = 4;
					} else {
						territoire[6] = 4;
					}
				}

				if (cinq == 0) {
					if (hasard == 0) {
						hasard = 5;
					} else {
						territoire[13] = 5;
					}
				}
				
				
			}

			territoire[i] = hasard;
		}
		
		/*
		DEBUG / VERIFICATION
		verificateur permet de savoir la valeur "hasard" 15 d'origine
		deux, trois, quatre, cinq permet de savoir s'ils sont affichés d'origine ou non
		
		System.out.println("hasard 15 = " + verificateur);
		System.out.println("deux = " + deux + " - trois = " + trois + " - quatre = " + quatre + " - cinq = " + cinq);
		*/
		
		/*
			Solution niveau 4 corrigée
		
		for (int j = 0; j < territoire.length; j++) {
			System.out.print(territoire[j]);
			if ((j == 3) || (j == 7) || (j == 11) || (j == 15))
					System.out.print("\n");
		}
		*/
	}
	
	
	/*
	 * Déplacement du héros
	 * Proposition des options de déplacement (haut, droite, bas, gauche)
	 * Lancement de la résolution du déplacement avec situation(POSITION)
	 * 
	 * haut, gauche, droit, bas sont sous forme de méthodes tout en bas de la classe
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
					situation(4);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(0);
				} else if (reponse.equalsIgnoreCase("potion")) {
					potion();
				} else if (reponse.equalsIgnoreCase("parchemin")) {
					parchemin(0);
				} else {
					mauvaiseReponse(reponse);
				}
				if (territoire[0] == 1) {
					territoire[0] = 0;
				}
				
			} else if (territoire[1] == 1) {
				haut();
				localHaut(1);
			} else if (territoire[2] == 1) {
				haut();
				localHaut(2);
			} else if (territoire[3] == 1) {
				coinHautDroit();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("b")) {
					situation(7);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(2);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(3);
				} else if (reponse.equalsIgnoreCase("potion")) {
					potion();
				} else if (reponse.equalsIgnoreCase("parchemin")) {
					parchemin(3);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[3] = 0;
				
			} else if (territoire[4] == 1) {
				gauche();
				localGauche(4);
			} else if (territoire[5] == 1) {
				centre();
				localCentre(5);
			} else if (territoire[6] == 1) {
				centre();
				localCentre(6);
			} else if (territoire[7] == 1) {
				droit();
				localDroit(7);
			} else if (territoire[8] == 1) {
				gauche();
				localGauche(8);
			} else if (territoire[9] == 1) {
				centre();
				localCentre(9);
			} else if (territoire[10] == 1) {
				centre();
				localCentre(10);
			} else if (territoire[11] == 1) {
				droit();
				localDroit(11);
			} else if (territoire[12] == 1) {
				coinBasGauche();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(8);
				} else if (reponse.equalsIgnoreCase("d")) {
					situation(13);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(12);
				} else if (reponse.equalsIgnoreCase("potion")) {
					potion();
				} else if (reponse.equalsIgnoreCase("parchemin")) {
					parchemin(12);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[12] = 0;
				
			} else if (territoire[13] == 1) {
				bas();
				localBas(13);
			} else if (territoire[14] == 1) {
				bas();
				localBas(14);
			} else if (territoire[15] == 1) {
				coinBasDroit();
				reponse = clavier.nextLine();

				if (reponse.equalsIgnoreCase("h")) {
					situation(11);
				} else if (reponse.equalsIgnoreCase("g")) {
					situation(14);
				} else if (reponse.equalsIgnoreCase("aide")) {
					aide(15);
				} else if (reponse.equalsIgnoreCase("potion")) {
					potion();
				} else if (reponse.equalsIgnoreCase("parchemin")) {
					parchemin(15);
				} else {
					mauvaiseReponse(reponse);
				}			
				territoire[15] = 0;
				
			} else {
				break;
			}
			
		}
		
		clavier.close();
		
	}
	
	/*
	 * Résolution du déplacement
	 * 0 : rien
	 * 2 : escalier et niveau suivant
	 * 3 : monstre 1 et - 10pv
	 * 4 : monstre 2 et - 10pv
	 * 5 : parchemin
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
			vie = vie -10;
			
			if (vie < 0)
			{
				System.out.println("\n"
						+ "Blessé, vous affrontez un autre monstre. Il pointe vers vous sa lance et s'élance.\n"
						+ "Il vous embroche plusieurs fois et vos coups d'épée ne servent à rien.\n"
						+ "Vous êtes ... mort.\n"
						+ "\n"
						+ "VIE = 0 p.v.\n"
						+ "\n");
				territoire[0] = 8;
			} else {
				System.out.println("\n"
						+ "Vous affrontez un monstre. Ricanant, il pointe vers vous sa lance et s'élance.\n"
						+ "Il vous blesse mais d'un coup maladroit, vous transpersez un de ses organes vitaux.\n"
						+ "Vous êtes ... blessé mais lui est mort.\n"
						+ "\n"
						+ "VIE = " + vie + " p.v.\n"
						+ "\n");
				territoire[position] = 1;
			}
		} else if (territoire[position] == 4) {
			vie = vie -10;
			
			if (vie < 0)
			{
				System.out.println("\n"
						+ "Blessé, vous vous trouvez nez à nez avec un monstre. Il tient une grosse massue.\n"
						+ "Vous vous mettez en position mais sans comprendre comment, la massue explose votre tête.\n"
						+ "Vous êtes ... mort.\n"
						+ "\n"
						+ "VIE = 0 p.v.\n"
						+ "\n");
				territoire[0] = 8;
			} else {
				System.out.println("\n"
						+ "Ce monstre tient une massue dans ses mains. Il s'élance vers vous et ... trébuche.\n"
						+ "Le monstre, maladroit, tombe tête la première sur votre épée et lache sa massue sur votre jambe.\n"
						+ "Vous êtes ... blessé mais lui est mort.\n"
						+ "\n"
						+ "VIE = " + vie + " p.v.\n"
						+ "\n");
				territoire[position] = 1;
			}
		} else if (territoire[position] == 5) {
			System.out.println("\n"
					+ "Vous trouvez un parchemin magique.\n"
					+ "Il vous permet, une seule fois, de découvrir ce qui se trouve autour de vous dans ce niveau.\n"
					+ "Vous le mettez dans une de vos poches.\n"
					+ "ATTENTION, ce parchemin n'est utilisable que dans ce niveau.\n"
					+ "\n"
					+ "PARCHEMIN : pour utiliser le parchemin\n"
					+ "AIDE : pour accéder à ce que vous avez dans vos poches.\n"
					+ "\n");
			// objet : scrollCarteDeux 1 -- quantité : 1;
			ajoutPoche(1, 1);
			territoire[position] = 1;
		} else {
			System.out.println("PROBLEME : au niveau de la méthode situation() sur NiveauQuatre");
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
	 * 0 : potion de soin - potion
	 * 1 : parchemin de vision sur 2 cases - parchemin
	 * 
	 */

	// aide
	static void aide(int position) {
		System.out.println("\nVous avez " + vie + " p.v. et ...");
		contenuPoche();
		deplacement();
	}

	// potion de soin
	static void potion() {
		if (poche.get(0) > 0) {
			utiliserPoche(0);
			vie = vie + 15;
			System.out.println("\n"
							+ "Vous venez de boire votre potion de soin\n"
							+ "et vous avez désormais " + vie + " p.v.\n"
							+ "Vous avez une potion en moins dans votre poche."
							+ "\n");
		} else {
			utiliserPoche(0);
		}
		deplacement();
	}
	
	// parchemin de vision magique
	// selon la position du héros, la vision est différente
	static void parchemin(int position) {
		
		int pos = position;
		
		if (poche.get(1) > 0) {
			utiliserPoche(1);
						
			if (pos == 0) {
				for (int j = 0; j < 12; j++) {
					if ((j == 3) || (j == 7) || (j == 11)) {
						System.out.print("\n");
					} else {
						System.out.print(territoire[j]);
					}
				}

			} else if ((pos == 1) || (pos == 2)) {
				for (int j = 0; j < 11; j++) {
					System.out.print(territoire[j]);
					if ((j == 3) || (j == 7) || (j == 11))
							System.out.print("\n");
				}
			
			} else if (pos == 3) {
				for (int j = 1; j < 13; j++) {
					if ((j == 4) || (j == 8) || (j == 12)) {
						System.out.print("\n");
					} else {
						System.out.print(territoire[j]);
					}
				}
				
			} else if ((pos == 4) || (pos == 8)) {
				for (int j = 0; j < 16; j++) {
					if ((j == 3) || (j == 7) || (j == 11) || (j == 15)) {
						System.out.print("\n");
					} else {
						System.out.print(territoire[j]);
					}
				}

			} else if ((pos == 7) || (pos == 11)) {
				for (int j = 1; j < 16; j++) {
					if ((j == 4) || (j == 8) || (j == 12)) {
						System.out.print("\n");
					} else {
						System.out.print(territoire[j]);
					}
				}
				
			} else if (pos == 12) {
				for (int j = 4; j < 16; j++) {
					if ((j == 7) || (j == 11) || (j == 15)) {
						System.out.print("\n");
					} else {
						System.out.print(territoire[j]);
					}
				}

			} else if ((pos == 13) || (pos == 14)) {
				for (int j = 4; j < 16; j++) {
					System.out.print(territoire[j]);
					if ((j == 7) || (j == 11) || (j == 15))
							System.out.print("\n");
				}

			} else if (pos == 15) {
				for (int j = 5; j < 17; j++) {
					if ((j == 8) || (j == 12) || (j == 16)) {
						System.out.print("\n");
					} else {
						System.out.print(territoire[j]);
					}
				}
				
			} else {
				for (int j = 0; j < 16; j++) {
					System.out.print(territoire[j]);
					if ((j == 3) || (j == 7) || (j == 11) || (j == 15))
							System.out.print("\n");
				}
			}
			
			System.out.println("\n"
					+ "EXPLICATIONS :\n"
					+ "0 : il n'y a rien ici !\n"
					+ "1 : c'est vous !!\n"
					+ "2 : l'escalier, la sortie !!\n"
					+ "3 : un monstre\n"
					+ "4 : un autre monstre\n"
					+ "\n");
			
		} else {
			utiliserPoche(1);
		}
		deplacement();
	}
	
	/*
	 * Conclusion du niveau 4
	 * 
	 */
	
	static void conclusion() {
		
		if (territoire[0] == 7) {
			System.out.println("Vous vous échappez de la pièce en descendant l'escalier.");
		}
		else if (territoire[0] == 8) {
			System.out.println("Vous vous retrouvez dans le cimetière des héros inconnus.\n"
					+ "\n"
					+ "\n"
					+ "FIN DU JEU\n"
					+ "\n");
			
			Main.recommencer();

		}
		else if (territoire[0] == 9) {
			System.out.println("Vous êtes au cimetière des mauvaises réponses.\n"
					+ "\n"
					+ "(vous avez tapé sur une mauvaise touche !!)\n"
					+ "\n"
					+ "FIN DU JEU\n"
					+ "\n");
			
			Main.recommencer();

		}
	}
	
	/*
	 * Eléments pour deplacement()
	 * 
	 * localHaut, localGauche, localCentre, localDroit, localBas
	 * 
	 */
	
	static void localHaut(int position) {
		Scanner clavier = new Scanner(System.in);
		String reponse;
		
		reponse = clavier.nextLine();

		if (reponse.equalsIgnoreCase("d")) {
			situation(position+1);
		} else if (reponse.equalsIgnoreCase("b")) {
			situation(position+4);
		} else if (reponse.equalsIgnoreCase("g")) {
			situation(position-1);
		} else if (reponse.equalsIgnoreCase("aide")) {
			aide(position);
		} else if (reponse.equalsIgnoreCase("potion")) {
			potion();
		} else if (reponse.equalsIgnoreCase("parchemin")) {
			parchemin(position);
		} else {
			mauvaiseReponse(reponse);
		}			
		territoire[position] = 0;
		
	}
	
	static void localGauche(int position) {
		Scanner clavier = new Scanner(System.in);
		String reponse;
		
		reponse = clavier.nextLine();

		if (reponse.equalsIgnoreCase("h")) {
			situation(position-4);
		} else if (reponse.equalsIgnoreCase("d")) {
			situation(position+1);
		} else if (reponse.equalsIgnoreCase("b")) {
			situation(position+4);
		} else if (reponse.equalsIgnoreCase("aide")) {
			aide(position);
		} else if (reponse.equalsIgnoreCase("potion")) {
			potion();
		} else if (reponse.equalsIgnoreCase("parchemin")) {
			parchemin(position);
		} else {
			mauvaiseReponse(reponse);
		}			
		territoire[position] = 0;
		
	}

	static void localCentre(int position) {
		Scanner clavier = new Scanner(System.in);
		String reponse;
		
		reponse = clavier.nextLine();

		if (reponse.equalsIgnoreCase("h")) {
			situation(position-4);
		} else if (reponse.equalsIgnoreCase("d")) {
			situation(position+1);
		} else if (reponse.equalsIgnoreCase("b")) {
			situation(position+4);
		} else if (reponse.equalsIgnoreCase("g")) {
			situation(position-1);
		} else if (reponse.equalsIgnoreCase("aide")) {
			aide(position);
		} else if (reponse.equalsIgnoreCase("potion")) {
			potion();
		} else if (reponse.equalsIgnoreCase("parchemin")) {
			parchemin(position);
		} else {
			mauvaiseReponse(reponse);
		}
		territoire[position] = 0;

	}
	
	static void localDroit(int position) {
		Scanner clavier = new Scanner(System.in);
		String reponse;

		reponse = clavier.nextLine();

		if (reponse.equalsIgnoreCase("h")) {
			situation(position-4);
		} else if (reponse.equalsIgnoreCase("b")) {
			situation(position+4);
		} else if (reponse.equalsIgnoreCase("g")) {
			situation(position-1);
		} else if (reponse.equalsIgnoreCase("aide")) {
			aide(position);
		} else if (reponse.equalsIgnoreCase("potion")) {
			potion();
		} else if (reponse.equalsIgnoreCase("parchemin")) {
			parchemin(position);
		} else {
			mauvaiseReponse(reponse);
		}			
		territoire[position] = 0;

	}
	
	static void localBas(int position) {
		Scanner clavier = new Scanner(System.in);
		String reponse;

		reponse = clavier.nextLine();

		if (reponse.equalsIgnoreCase("h")) {
			situation(position-4);
		} else if (reponse.equalsIgnoreCase("d")) {
			situation(position+1);
		} else if (reponse.equalsIgnoreCase("g")) {
			situation(position-1);
		} else if (reponse.equalsIgnoreCase("aide")) {
			aide(position);
		} else if (reponse.equalsIgnoreCase("potion")) {
			potion();
		} else if (reponse.equalsIgnoreCase("parchemin")) {
			parchemin(position);
		} else {
			mauvaiseReponse(reponse);
		}			
		territoire[position] = 0;

	}
	

}
