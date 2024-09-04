package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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

    @SuppressWarnings("unused")
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

    
    public void afficheBestPlayer(){
        List<Joueur> bestPlayer = Joueur.getBestPlayers();
        System.out.println("Meilleurs joueurs All-Time : ");
        for (Joueur j : bestPlayer) {
            System.out.println(j.toString() + " : " + j.score);
        }
    }

<<<<<<< HEAD

=======
>>>>>>> 621dfeed45d7d4a252eddb732a86fdc62b66e675
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, MouvementException {
        // intro
        //  toDo
        try {
            Intro.affichage();
            Intro.Start();
        } catch (IOException e) {
            System.out.println(e);
        }


        Othello othello = new Othello();
        othello.plateau.initialise();
        
        //othello.plateau.afficher();
        // boucle de jeu
        int j_actu = 0;
        while (!othello.jeuFini()) {
            System.out.println(othello.plateau.toString());
            System.out.println(othello.plateau.afficherScore());
            Joueur joueurCourrant = Intro.getJoueur(j_actu%2);
            try{
                System.out.println(joueurCourrant);
                int[] tab = selectionCase(othello.plateau);
                System.out.println(tab[0] + " " + tab[1]);
                if(othello.plateau.mouvementPossible(tab[0], tab[1], joueurCourrant)){
                    othello.plateau.setCase(joueurCourrant.getColor(), tab[0], tab[1]);
                }
                else{
                    throw new MouvementException();
                }
            }
            catch(IndexOutOfBoundsException e){System.out.println("Mauvaise entrée !");j_actu--;}
            catch(InputMismatchException e){System.out.println("Mauvaise entrée !"); j_actu--;}
            catch(MouvementException e){System.out.println("Mouvement impossible !"); j_actu--;}
            finally{TimeUnit.SECONDS.sleep(1);}
            System.out.flush();
            j_actu ++;
        }

        Joueur.savePlayer(Intro.getJoueur1());
        Joueur.savePlayer(Intro.getJoueur2());

        if(othello.plateau.verifScore().get(Couleur.NOIR) > othello.plateau.verifScore().get(Couleur.BLANC)){
            Intro.fin(Intro.getJoueur1(), othello.plateau.verifScore().get(Couleur.NOIR));
            Intro.getJoueur1().score = Intro.getJoueur1().score + 1;
        }else{
            Intro.fin(Intro.getJoueur2(), othello.plateau.verifScore().get(Couleur.BLANC));
            Intro.getJoueur2().score = Intro.getJoueur2().score + 1;
        }
        Joueur.savePlayer(Intro.getJoueur1());
        Joueur.savePlayer(Intro.getJoueur2());

        othello.afficheBestPlayer();
  
        
    }
}
    