package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Othello {
    Grille plateau;

    public Othello() {
        this.plateau = new Grille();
    }

    public static int[] selectionCase(Grille g ) {
        Scanner sc = new Scanner(System.in);
        int[] tab = new int[2];
        System.out.println("Veuillez saisir la ligne de la case choisie (A - H) : ");
        tab[0] = toInt(sc.next().charAt(0));
        System.out.println("Veuillez saisir la colonne de la case choisie (1 - 8): ");
        tab[1] = sc.nextInt() -1;
        return tab;
        
    }

    private static int toInt(char c) {
        return c - 'A';
    }

    public boolean jeuFini(){
        for(ArrayList<Pion> ligne : this.plateau.grille){
            for(Pion p : ligne){
                if(p != null) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // intro
        //  toDo
        try {
            Intro.affichage();
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e);
        }


        Othello othello = new Othello();
        //othello.plateau.afficher();

        // boucle de jeu
        while (true) {
            System.out.println(othello.plateau.toString());
            int[] tab = selectionCase(othello.plateau);
            System.out.println(tab[0] + " " + tab[1]);
            othello.plateau.setCase(Couleur.NOIR, tab[0], tab[1]);
            System.out.flush();
        }
  
        
    }

    
}
