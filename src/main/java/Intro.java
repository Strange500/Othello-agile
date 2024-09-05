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

        if(Othello.debugMode) System.out.println("[Debug Mode]");

        try(BufferedReader br = new BufferedReader(new FileReader(new File("./src/main/ressources/txt/Regles.txt")))){
            while(br.ready()){
                System.out.println(br.readLine());
            }
            
        }

        try(BufferedReader br = new BufferedReader(new FileReader(new File("./src/main/ressources/txt/SelecMode.txt")))){
            while(br.ready()){
                System.out.println(br.readLine());
            }   
        }
    }

    public static void Start()  throws FileNotFoundException, IOException{
        Scanner scan = new Scanner(System.in);
        String choix = scan.nextLine();
        //Mode 1 contre ordinateur pas encore implémenté
        // if(Integer.parseInt(choix)==2){
        //     System.out.println("Coming soon... \n Appuyez sur Entrée pour revenir en arrière.");
        //     System.in.read();
        //     affichage();
        //     Start();
        // }
        while(choix.length() == 0 || (choix.charAt(0) != '1' && choix.charAt(0) != '2' && choix.charAt(0) != '3')){
            Intro.affichage();
            choix = scan.nextLine();
        }
        Intro.clear();
        if (choix.charAt(0) == '1' || choix.charAt(0) == '2') {
            System.out.println("Veuillez choisir le PSEUDO du joueur 1 (Ayant les pions noirs) :  \n");
            String nom1 = scan.nextLine();
            joueurs.add(new Joueur(nom1, Couleur.NOIR));
            if(choix.charAt(0) == '1'){ //si le monde choisie est le pvp, cela crée alors un 2e joueur (donc il n'y a que 1 joueur si vs bot)
                System.out.println("Veuillez choisir le PSEUDO du joueur 2 (Ayant les pions blancs) :  \n");
                String nom2 = scan.nextLine();
                joueurs.add(new Joueur(nom2, Couleur.BLANC));
        }
            else if(choix.charAt(0) == '2'){ //si le monde choisie est le pvp, cela crée alors un 2e joueur (donc il n'y a que 1 joueur si vs bot)
                joueurs.add(new Bot(Couleur.BLANC));
            }
            }
 
        else if(choix.charAt(0) == '3'){ 
            joueurs.add(new Bot(Couleur.NOIR));
            joueurs.add(new Bot(Couleur.BLANC));
        }
        //scan.close();
    }

    public static Joueur getJoueur1() {
        return joueurs.get(0);
    }

    public static Joueur getJoueur2() {
        return joueurs.get(1);
    }

    public static Joueur getJoueur(int j){
        return joueurs.get(j);
    }

    public static int getNbJoueur(){
        return joueurs.size();
    }

    public static void fin(Joueur j, int score) throws FileNotFoundException, IOException, InterruptedException{
        Intro.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(new File("./src/main/ressources/txt/winner.txt")))){
            while(br.ready()){
                System.out.println(br.readLine());
            }
            
        }
        System.out.println("\t\t\t\tLe joueur " + j.getName() + " vient de gagner la partie avec un score de " + score + " points");

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

    }
}