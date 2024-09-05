package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends Joueur {

    public Bot(Couleur c) {
        super("Bot", c);
    }

    public int[] selectionCase(Grille g) {
        return getRandomElement(ValidMoves(g));
    }

    public List<int[]> ValidMoves(Grille g) {
        List<int[]> ligne = new ArrayList<>();
        for (int i = 0; i < g.grille.size(); i++) {
            for (int j = 0; j < g.grille.get(i).size(); j++) {
                //System.out.println("pion " + j +" " + i "=" + mouvementPossible(i, j, joueur));
                if (g.mouvementPossible(i, j, this)) {
                    ligne.add(new int[]{j, i});
                }
            }
        }
        return ligne;
    }

    public int[] getRandomElement(List<int[]> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()+1));
    }
    
}
