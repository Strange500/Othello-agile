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

    public void setCase(Couleur c, int y, int x) {
        grille.get(x).set(y, new Pion(c));
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public String toString() {
        Grille.clear();
        String res = "";
        res += "    ┏━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┓\n    ┃ A ┃ B ┃ C ┃ D ┃ E ┃ F ┃ G ┃ H ┃\n┏━━━╃───╀───╀───╀───╀───╀───╀───╀───┦\n┃ 1 ";
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
            res += "│\n┣━━━┽───┼───┼───┼───┼───┼───┼───┼───┤\n┃ " + (i+1) + " ";
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
        res += "│\n┗━━━┵───┴───┴───┴───┴───┴───┴───┴───┘\n";
        return res;
    
    }

    public boolean isEmpty(int x, int y){
        return grille.get(x).get(y) == null;
    }

    public Pion getCase(int x, int y){
        return grille.get(x).get(y);
    }

    public void initialise() {
        setCase(Couleur.NOIR, 3, 3);
        setCase(Couleur.NOIR, 4, 4);
        setCase(Couleur.BLANC, 3, 4);
        setCase(Couleur.BLANC, 4, 3);
    }

    public String afficherScore(){
        String result = "";
        int score1, score2;
        score1 = verifScore().get(Couleur.NOIR).intValue();
        score2 = verifScore().get(Couleur.BLANC).intValue();
        result += "Le score des noirs est de " + score1 + "\n";
        result += "Le score des blancs est de " + score2 + "\n";
        return result;
    }

}