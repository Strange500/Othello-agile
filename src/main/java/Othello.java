package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Othello {
    static boolean debugMode = false;
    Grille plateau;

    public Othello() {
        this.plateau = new Grille();
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static int[] selectionCase(Grille g) {
        Scanner sc = new Scanner(System.in);
        int[] tab = new int[2];
        System.out.println("Veuillez saisir la ligne de la case suivi de la colonne choisie (ex: A1) : ");
        String tmp = sc.next().toUpperCase();
        tab[0] = toInt(tmp.charAt(0));
        tab[1] = tmp.charAt(1) - '1';
        return tab;
        
    }

    private static int toInt(char c) {
        return c - 'A';
    }
    
    public boolean jeuFini(int nbPlayerStuck){
        for(ArrayList<Pion> ligne : this.plateau.grille){
            for(Pion p : ligne){
                if(p == null) return false;
            }
        }
        if(nbPlayerStuck != 2) return false;
        return true;
    }

    @SuppressWarnings("unused")
    private static boolean PionAdverseAdjacent(int x, int y, Couleur c, Grille g) {
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (i >= 0 && i < 8 && j >= 0 && j < 8 && !(i == x && j == y)) {
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

    
    public void afficheBestPlayer(){
        List<Joueur> bestPlayer = Joueur.getBestPlayers();
        System.out.println("Meilleurs joueurs All-Time : ");
        for (Joueur j : bestPlayer) {
            System.out.println(j.name + " : " + j.score);
        }
    }
}
    