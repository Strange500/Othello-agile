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

    private List<int[]> ValidMoves(Grille g) {
        List<int[]> ligne = new ArrayList<>();
        for (int i = 0; i < g.grille.size(); i++) {
            for (int j = 0; j < g.grille.get(i).size(); j++) {
                if (g.mouvementPossible(i, i, this)) {
                    ligne.add(new int[]{i, j});
                }
            }
        }
        return ligne;
    }

    public int[] getRandomElement(List<int[]> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
    
}
