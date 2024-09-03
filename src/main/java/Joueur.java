package main.java;

public class Joueur {

    String name;
    Couleur color;
    
    public Joueur(String name, Couleur color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Couleur getColor() {
        return color;
    }
}