package main.java;

import java.io.IOException;
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


    private static boolean PionAdverseAdjacent(int x, int y, Couleur c, Grille g) {
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (i >= 0 && i < 8 && j >= 0 && j < 8 && !(i == x && j == y)) {
                    System.out.println(i + " " + j);
                    if (g.getCase(i, j) == null) {
                        continue;
                    } else if (g.getCase(i, j).couleur != c) {
                        return true;
                    }
                }
            }
        }
        return false;
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
        othello.plateau.setCase(Couleur.BLANC, 5, 5);
        // boucle de jeu
        while (true) {
            System.out.println(othello.plateau.toString());
            int[] tab = selectionCase(othello.plateau);
            System.out.println(tab[0] + " " + tab[1]);
            othello.plateau.setCase(Couleur.NOIR, tab[0], tab[1]);
            
        }
  
        
    }

    
}
