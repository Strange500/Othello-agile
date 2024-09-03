package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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

    public Map<Couleur,Integer> verifScore(){
        Map<Couleur, Integer> score = new HashMap<>();
        for(ArrayList<Pion> ligne : grille){
            for(Pion p : ligne){
                if(p != null){
                    if(score.containsKey(p.couleur)){
                        score.put(p.couleur, score.get(p.couleur)+1);
                    }
                    else score.put(p.couleur, 1);
                }
            }
        }
        return score;
    }

    public void setCase(Couleur c, int x, int y) {
        grille.get(x).set(y, new Pion(c));
    }

    @Override
    public String toString() {
        String res = "";
        res += "┌───┬───┬───┬───┬───┬───┬───┬───┐\n";
        for(int i=0;i<8;i++){
            res += "│ ";
            if(grille.get(0).get(i) == null){
                res += "  ";
            }
            else if(grille.get(0).get(i).couleur == Couleur.NOIR){
                res += "⛀ ";
            } else {
                res += "⛂ ";
            }
        }
        for(int i=1;i<8;i++){
            res += "│\n├───┼───┼───┼───┼───┼───┼───┼───┤\n";
            for(int j=0;j<8;j++){
                res += "│ ";
                if(grille.get(i).get(j) == null){
                    res += "  ";
                }
                else if(grille.get(i).get(j).couleur == Couleur.NOIR){
                    res += "⛀ ";
                } else {
                    res += "⛂ ";
                }
            }
        }
        res += "│\n└───┴───┴───┴───┴───┴───┴───┴───┘\n";
        return res;
    
    }

    public boolean isEmpty(int x, int y){
        return grille.get(x).get(y) == null;
    }

    public Pion getCase(int x, int y){
        return grille.get(x).get(y);
    }

}