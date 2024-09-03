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

}