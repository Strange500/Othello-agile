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

    
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void affichage() throws FileNotFoundException, IOException{

        Intro.clear();
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
        //scan.close();
    }

    public static Joueur getJoueur1() {
        return joueurs.get(0);
    }

    public static Joueur getJoueur2() {
        return joueurs.get(1);
    }

    public static void fin(Joueur j, int score) throws FileNotFoundException, IOException{
        Intro.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(new File("./src/main/ressources/txt/winner.txt")))){
            while(br.ready()){
                System.out.println(br.readLine());
            }
            
        }
        System.out.println("\tLe joueur " + j.getName() + " vient de gagner la partie avec plus de " + score + " points");

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        fin(new Joueur("Brice", Couleur.NOIR), 89);
    }

}