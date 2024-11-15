package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Grille {
    private static int cpt = 0;
    ArrayList<ArrayList<Pion>> grille;
    public List<List<int[]>> pionARetourner = new ArrayList<>();


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

    public List<int[]> ValidMoves(Joueur joueur) {
        List<int[]> ligne = new ArrayList<>();
        for (int i = 0; i < grille.size(); i++) {
            for (int j = 0; j < grille.get(i).size(); j++) {
                //System.out.println("pion " + j +" " + i "=" + mouvementPossible(i, j, joueur));
                if (mouvementPossible(i, j, joueur)) {
                    ligne.add(new int[]{i, j});
                }
            }
        }
        return ligne;
    }

    private static boolean equalsCoordonate(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

    private static boolean containsCoordonate(int x, int y, List<int[]> coordonnates) {
        boolean flag = false;
        int cpt = 0;
        while (!flag && cpt < coordonnates.size()) {
            if (equalsCoordonate(x,y,coordonnates.get(cpt)[0], coordonnates.get(cpt)[1])) {
                flag = true;
            }
            cpt++;
        }
        return flag;
    }



    public String afficher(Joueur joueur, boolean showMoves) {
        Grille.clear();
        String res = "";
        List<int[]> flags = ValidMoves(joueur);
        res += "    ┏━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┓\n    ┃ A ┃ B ┃ C ┃ D ┃ E ┃ F ┃ G ┃ H ┃\n┏━━━╃───╀───╀───╀───╀───╀───╀───╀───┦\n┃ 1 ";
        for(int i=0;i<8;i++){
            res += "│ ";
            if(grille.get(0).get(i) == null && containsCoordonate(0, i, flags) && showMoves){
                res+= "\u001B[42m \u001B[0m ";
                
            }
            else if (grille.get(0).get(i) == null){
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
                
                if(grille.get(i).get(j) == null && containsCoordonate(i, j, flags) && showMoves){
                    res+= "\u001B[42m \u001B[0m ";
                
                }
                else if (grille.get(i).get(j) == null){
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
        setCase(Couleur.BLANC, 3, 3);
        setCase(Couleur.BLANC, 4, 4);
        setCase(Couleur.NOIR, 3, 4);
        setCase(Couleur.NOIR, 4, 3);
    }
    public void initialisetest() {
        // creer une situation avec le bug 
        // blanc
        setCase(Couleur.BLANC, 1, 1);
        setCase(Couleur.BLANC, 2, 2);
        setCase(Couleur.BLANC, 3, 1);
        setCase(Couleur.BLANC, 3, 2);
        setCase(Couleur.BLANC, 3, 3);
        setCase(Couleur.BLANC, 4, 4);
        setCase(Couleur.BLANC, 6, 1);

        // noir
        setCase(Couleur.NOIR, 1, 2);
        setCase(Couleur.NOIR, 2, 1);
        setCase(Couleur.NOIR, 4, 2);
        setCase(Couleur.NOIR, 5, 2);
        setCase(Couleur.NOIR, 6, 2);
        setCase(Couleur.NOIR, 3, 4);
        setCase(Couleur.NOIR, 4, 3);
        setCase(Couleur.NOIR, 4, 5);
    }

    public void initialiseTestValideMove(){
        setCase(Couleur.NOIR, 5, 5);
        setCase(Couleur.NOIR, 3, 5);
        setCase(Couleur.NOIR, 7, 5);
        setCase(Couleur.NOIR, 5, 3);
        setCase(Couleur.NOIR, 5, 7);
        setCase(Couleur.NOIR, 3, 3);
        setCase(Couleur.NOIR, 3, 7);
        setCase(Couleur.NOIR, 7, 3);
        setCase(Couleur.NOIR, 7, 7);

        setCase(Couleur.BLANC, 4, 5);
        setCase(Couleur.BLANC, 4, 6);
        setCase(Couleur.BLANC, 5, 4);
        setCase(Couleur.BLANC, 5, 6);
        setCase(Couleur.BLANC, 4, 4);
        setCase(Couleur.BLANC, 4, 6);
        setCase(Couleur.BLANC, 6, 4);
        setCase(Couleur.BLANC, 6, 6);
        setCase(Couleur.BLANC, 6, 5);

    }

    public String afficherScore(){
        String result = "";
        int score1, score2;
        score1 = verifScore().get(Couleur.NOIR).intValue();
        score2 = verifScore().get(Couleur.BLANC).intValue();
        result += "Le score des noirs est de " + score1 + "\n";
        result += "Le score des blancs est de " + score2 + "\n\n";
        result += "Le score total est de " + (score1+score2) + "/64\n";
        return result;
    }

    public boolean mouvementPossible(int ligne, int colonne, Joueur joueur){
        Couleur colAdverse = (joueur.getColor() == Couleur.NOIR) ? Couleur.BLANC : Couleur.NOIR;
        if(!isEmpty(ligne, colonne)) return false;
        pionARetourner.clear();
        cpt = 0;
        boolean possible = false;
        // Check les 8 directions
        for (int directionLigne = -1; directionLigne <= 1; directionLigne++) {
            for (int directionColonne = -1; directionColonne <= 1; directionColonne++) {
                if (directionLigne == 0 && directionColonne == 0) continue; // Skip la direction (0,0)
                
                if (checkDirection(joueur.getColor(), colAdverse, ligne, colonne, directionLigne, directionColonne)) {
                    possible = true;
                }
                cpt++;
            }

        }
        return possible;
    }

    private boolean checkDirection(Couleur playerColor, Couleur colAdverse, int ligne, int colonne, int directionLigne, int directionColonne) {
        int l = ligne + directionLigne;
        int c = colonne + directionColonne;
        boolean pieceAdverse = false;
        boolean cleanExit = false;
        pionARetourner.add(new ArrayList<>());

        while (l >= 0 && l < 8 && c >= 0 && c < 8) {
            if (!isEmpty(l, c) && grille.get(l).get(c).couleur == colAdverse) {
                pionARetourner.get(cpt).add(new int[]{c, l});
                pieceAdverse = true;
            } else if (!isEmpty(l, c) && grille.get(l).get(c).couleur == playerColor) {
                return pieceAdverse; // Valide si on trouve au moins une pièce adverse
            } else {
                pionARetourner.get(cpt).clear();
                return false; // Vide, on arrête d'aller dans cette direction
            }
            l += directionLigne;
            c += directionColonne;
        }
        pionARetourner.get(cpt).clear();
        return false; // Pas de move possible dans cette direction
    }


}