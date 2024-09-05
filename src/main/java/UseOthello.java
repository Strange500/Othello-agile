package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UseOthello {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, MouvementException {
        // intro
        //  toDo
        boolean cte = false;
        int nbPlayerStuck = 0;
        try {
            Intro.affichage();
            Intro.Start();
        } catch (IOException e) {
            System.out.println(e);
        }

        Othello othello = new Othello();
        if(Othello.debugMode) othello.plateau.initialiseTestValideMove();
        else {
            othello.plateau.initialise();
        }
        
        //othello.plateau.afficher();
        // boucle de jeu
        int j_actu = 0;
        while (!othello.jeuFini(nbPlayerStuck) && !cte) {
            boolean skip = false; //détermine si le joueur skip son tour
            Joueur joueurCourrant = Intro.getJoueur(j_actu%2);
            System.out.println(othello.plateau.afficher(joueurCourrant));
            System.out.println(othello.plateau.afficherScore());
            try{
                System.out.println(joueurCourrant);
                if(othello.plateau.ValidMoves(joueurCourrant).size() == 0) {
                    nbPlayerStuck ++;
                    skip = true;
                } else nbPlayerStuck = 0;
                if(!skip){
                    int[] tab = joueurCourrant.selectionCase(othello.plateau);
                    if(Othello.debugMode){
                        System.out.println(tab[0] + " " + tab[1]);
                        if(tab[0] == 8 && tab[1] == 8) cte = true;
                    }
                    if(!cte){
                        if(othello.plateau.mouvementPossible(tab[1], tab[0], joueurCourrant)){
                            othello.plateau.setCase(joueurCourrant.getColor(), tab[0], tab[1]);
                            ArrayList<int[]> tmp = new ArrayList<int[]>();
                            for (List<int[]> coords : othello.plateau.pionARetourner) {
                                tmp.addAll(coords);
                            }
                            for (int[] coord : tmp) {
                                System.out.println(othello.plateau.afficher(joueurCourrant));
                                TimeUnit.MILLISECONDS.sleep(500);
                                othello.plateau.setCase(joueurCourrant.getColor(), coord[0], coord[1]);
                                
                            }
                            System.out.println(othello.plateau.afficher(joueurCourrant));
                        }
                        else{
                            throw new MouvementException();
                        }
                    }         
                } else {
                    System.out.println("Can't Move !");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
            catch(IndexOutOfBoundsException e){System.out.println("Mauvaise entrée !");j_actu--;}
            catch(InputMismatchException e){System.out.println("Mauvaise entrée !"); j_actu--;}
            catch(MouvementException e){System.out.println("Mouvement impossible !"); j_actu--;}
            finally{TimeUnit.SECONDS.sleep(1);}
            System.out.flush();
            j_actu ++;
        }

        

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