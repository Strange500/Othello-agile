package main.java;

import java.util.ArrayList;


class Grille {

    ArrayList<ArrayList<Pion>> grille;

    public Grille() {
        grille = new ArrayList<ArrayList<Pion>>();
        for (int i = 0; i < 8; i++) {
            ArrayList<Pion> ligne = new ArrayList<Pion>();
            for (int j = 0; j < 8; j++) {
                ligne.add(null);
            }
            grille.add(ligne);
        }
    }

    @Override
    public String toString() {
        String res = "";
        res += "┌───┬───┬───┬───┬───┬───┬───┬───┐\n";
        for(int i=0;i<8;i++){
            res += "│ ";
            if(grille.get(0).get(i).couleur == Couleur.NOIR){
                res += "⛀ ";
            } else if(grille.get(0).get(i).couleur == Couleur.BLANC){
                res += "⛂ ";
            } else {
            res += "  ";
            }
        }
        for(int i=0;i<8;i++){
            res += "│\n├───┼───┼───┼───┼───┼───┼───┼───┤\n";
            for(int j=0;j<8;j++){
                res += "│ ";
                if(grille.get(i).get(j).couleur == Couleur.NOIR){
                    res += "⛀ ";
                } else if(grille.get(i).get(j).couleur == Couleur.BLANC){
                    res += "⛂ ";
                } else {
                res += "  ";
                }
            }
        }
        res += "│\n└───┴───┴───┴───┴───┴───┴───┴───┘\n";
        return res;
    
    }

}