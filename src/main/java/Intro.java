package main.java;


import java.util.ArrayList;
import java.util.Scanner;

public class Intro {
    

    private final static String NOMPROJET = "\n" + //
                "\n" + //
                " ██████  ████████ ██   ██ ███████ ██      ██       ██████  \n" + //
                "██    ██    ██    ██   ██ ██      ██      ██      ██    ██ \n" + //
                "██    ██    ██    ███████ █████   ██      ██      ██    ██ \n" + //
                "██    ██    ██    ██   ██ ██      ██      ██      ██    ██ \n" + //
                " ██████     ██    ██   ██ ███████ ███████ ███████  ██████  \n" + //
                "                                                           \n" + //
                "                                                           \n" + //
                "\n";

    private static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

    


    public static void affichage(){
        System.out.println(NOMPROJET);
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez choisir le PSEUDO du joueur 1 (Ayant les pions noirs) :  \n");
        String nom1 = scan.nextLine();
        joueurs.add(new Joueur(nom1, Couleur.NOIR));
        System.out.println("Veuillez choisir le PSEUDO du joueur 2 (Ayant les pions blancs) :  \n");
        String nom2 = scan.nextLine();
        joueurs.add(new Joueur(nom2, Couleur.BLANC));
        scan.close();
    }

    public static Joueur getJoueur1() {
        return joueurs.get(0);
    }

    public static Joueur getJoueur2() {
        return joueurs.get(1);
    }

}