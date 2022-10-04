package net.idf.java;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(""
		+ "\n"
		+ "\n"
		+ "\n"
		+ "======================================\n"
		+ " DONJON\n"
		+ "======================================\n"
		+ "version java / console\n"
		+ "\n");


		NiveauUn.chapitreUn();		

		NiveauDeux.chapitreDeux();

		NiveauTrois.chapitreTrois();

		NiveauQuatre.chapitreQuatre();

		
		System.out.println("/nD'autres niveaux plus tard ...");
		
	}
	
	
	static void recommencer() {
		
		Scanner grande = new Scanner(System.in);
		String question;

		
		System.out.println("Voulez-vous recommencer au début [o/n] ?");
		question = grande.nextLine();

		if (question.equalsIgnoreCase("o")) {
			main(null);
		} else {
			System.exit(0);
		}
		
		grande.close();
	}

}
