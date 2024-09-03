package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Joueur implements Serializable {

    private static final String PATH= "./src/main/ressources/joueur/";

    String name;
    Couleur color;
    int score;
    
    public Joueur(String name, Couleur color) {
        
        this.name = name;
        if (playerExist(null)) {
            Joueur tmp = loadJoueur(name);
            this.score = tmp.score;
        }
        this.color = color;
        savePlayer(this);

    }

    public String getName() {
        return name;
    }

    public Couleur getColor() {
        return color;
    }

    public String toString() {
        return this.getName() + ", couleur : " + this.getColor().toString().substring(0, 1)+this.getColor().toString().substring(1).toLowerCase();
    }

    private static String makeFilename(String name) {
        return  PATH + name + ".ser";
    }

    public static boolean playerExist(String name) {
        File file = new File(makeFilename(name));
        return file.exists();
    }



    public static void savePlayer(Joueur j) {

        try (FileOutputStream fileOutputStream = new FileOutputStream("./src/main/ressources/joueur/" + j.name + ".ser")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(j);
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Joueur loadJoueur(String name) {

        try (FileInputStream fileInputStream = new FileInputStream(makeFilename(name)) ) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Joueur j = (Joueur) objectInputStream.readObject();
            return j;
        } catch (IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }
    public static void main(String[] args) {
        // test serialization
        Joueur j = new Joueur("toto", Couleur.NOIR);
        savePlayer(j);
        Joueur j2 = loadJoueur("toto");
        System.out.println(j2);
    }
}