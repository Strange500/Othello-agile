package main.java;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Intro {

    private static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

    


    public static void affichage() throws FileNotFoundException, IOException{

        try(BufferedReader br = new BufferedReader(new FileReader(new File("./src/main/ressources/txt/logo.txt")))){
            while(br.ready()){
                System.out.println(br.readLine());
            }
            
        }

        try(BufferedReader br = new BufferedReader(new FileReader(new File("./src/main/ressources/txt/Regles.txt")))){
            while(br.ready()){
                System.out.println(br.readLine());
            }
            
        }
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

    public static void main(String[] args) throws FileNotFoundException, IOException {
        affichage();
    }

}