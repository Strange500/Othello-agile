package main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class othelloTest {

    public Grille g;

    @BeforeEach
    public void setUp() {
        g = new Grille();
    }
    
    @Test
    public void testToInt() {
        assertEquals(0, Othello.toInt('A'));
        assertEquals(1, Othello.toInt('B'));
        assertEquals(2, Othello.toInt('C'));
        assertEquals(3, Othello.toInt('D'));
        assertEquals(4, Othello.toInt('E'));
        assertEquals(5, Othello.toInt('F'));
        assertEquals(6, Othello.toInt('G'));
        assertEquals(7, Othello.toInt('H'));
    }

    @Test
    public void testJeuFini() {
        Othello o = new Othello();
        assertFalse(o.jeuFini(0));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                o.plateau.setCase(Couleur.NOIR, i, j);
            }
        }
        assertTrue(o.jeuFini(2));
    }

    

}
