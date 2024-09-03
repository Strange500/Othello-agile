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
    public static int[] selectionCase(Grille g ) {
        Scanner sc = new Scanner(System.in);
        int[] tab = new int[2];
        System.out.println("Veuillez saisir la ligne de la case choisie (A - H) : ");
        tab[0] = toInt(sc.next().toUpperCase().charAt(0));
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
        
        //othello.plateau.afficher();
        // boucle de jeu
        while (!othello.jeuFini()) {
        while (!othello.jeuFini()) {
            System.out.println(othello.plateau.toString());
            System.out.println(othello.plateau.afficherScore());
            try{
                System.out.println(Intro.getJoueur1());
                int[] tab = selectionCase(othello.plateau);
                System.out.println(tab[0] + " " + tab[1]);
                if(othello.plateau.mouvementPossible(tab[0], tab[1], Intro.getJoueur1())){
                    othello.plateau.setCase(Intro.getJoueur1().getColor(), tab[0], tab[1]);
                if(othello.plateau.isEmpty(tab[1], tab[0])){
                    othello.plateau.setCase(Couleur.NOIR, tab[0], tab[1]);
                }else{
                    throw new IndexOutOfBoundsException();
                }
                
            }
            catch(IndexOutOfBoundsException e){System.out.println("Mauvaise entré !");}
            catch(InputMismatchException e){System.out.println("Mauvaise entréeeeeee !");}
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
