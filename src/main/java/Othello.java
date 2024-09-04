package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Othello {
    Grille plateau;

    public Othello() {
        this.plateau = new Grille();
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public int[] selectionCase(Grille g,ArrayList<int[]> tabCoups) {
        tabCoups = g.coupsPossible(g, Intro.getJoueur1());
        if(tabCoups.size() == 0){
            tabCoups = null;
            return null;
        }

        System.out.println("Choisissez le coup à effectuer (entre 1 et " + tabCoups.size() + ")");

        Scanner sc = new Scanner(System.in);
        int[] tab = tabCoups.get(Integer.parseInt(sc.nextLine()) - 1);
        return tab;
        
    }
    
    public boolean jeuFini(){
        for(ArrayList<Pion> ligne : this.plateau.grille){
            for(Pion p : ligne){
                if(p == null) return false;
            }
        }
        return true;
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

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
        // intro
        //  toDo
        try {
            Intro.affichage();
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e);
        }


        Othello othello = new Othello();
        othello.plateau.initialise();
        ArrayList<int[]> tabCoups = null;

        // boucle de jeu
        while (!othello.jeuFini()) {
            System.out.println(othello.plateau.afficher(tabCoups));
            System.out.println(othello.plateau.afficherScore());
            try{
                System.out.println(Intro.getJoueur1());
                int[] tab = othello.selectionCase(othello.plateau, tabCoups);
                if(tab == null){ // Aucun mouvement possible
                    // Todo: passer à l'autre joueur
                }
                else {
                    System.out.println(tab[0] + " " + tab[1]);
                    othello.plateau.setCase(Intro.getJoueur1().getColor(), tab[0], tab[1]);
                }
            }
            catch(IndexOutOfBoundsException e){System.out.println("Mauvaise entrée !");}
            catch(InputMismatchException e){System.out.println("Mauvaise entrée !");}
            finally{TimeUnit.SECONDS.sleep(1);}
            System.out.flush();
        }

        Joueur.savePlayer(Intro.getJoueur1());
        Joueur.savePlayer(Intro.getJoueur2());

        if(othello.plateau.verifScore().get(Couleur.NOIR) > othello.plateau.verifScore().get(Couleur.BLANC)){
            Intro.fin(Intro.getJoueur1(), othello.plateau.verifScore().get(Couleur.NOIR));
        }else{
            Intro.fin(Intro.getJoueur2(), othello.plateau.verifScore().get(Couleur.BLANC));
        }
  
        
    }
}
    