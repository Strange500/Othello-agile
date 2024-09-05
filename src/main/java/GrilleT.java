package main.java;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrilleT {

    public Grille g;

    @BeforeEach
    public void setUp() {
        g = new Grille();
    }
    
    @Test
    public void testGrille() {
        g = new Grille();
        assertNotNull(g);
    }

    @Test
    public void testVerifScore() {
        g = new Grille();
        g.setCase(Couleur.NOIR, 0, 0);
        g.setCase(Couleur.BLANC, 0, 1);
        g.setCase(Couleur.NOIR, 1, 0);
        g.setCase(Couleur.BLANC, 1, 1);
        g.setCase(Couleur.NOIR, 2, 0);
        g.setCase(Couleur.BLANC, 2, 1);
        g.setCase(Couleur.NOIR, 3, 0);
        g.setCase(Couleur.BLANC, 3, 1);
        g.setCase(Couleur.NOIR, 4, 0);
        g.setCase(Couleur.BLANC, 4, 1);
        g.setCase(Couleur.NOIR, 5, 0);
        g.setCase(Couleur.BLANC, 5, 1);
        g.setCase(Couleur.NOIR, 6, 0);
        g.setCase(Couleur.BLANC, 6, 1);
        g.setCase(Couleur.NOIR, 7, 0);
        g.setCase(Couleur.BLANC, 7, 1);
        assertEquals(8, g.verifScore().get(Couleur.NOIR));
        assertEquals(8, g.verifScore().get(Couleur.BLANC));
    }

    @Test
    public void testSetCase() {
        g = new Grille();
        g.setCase(Couleur.NOIR, 0, 0);
        assertEquals(Couleur.NOIR, g.getCase(0, 0).couleur);
    }

    

}
