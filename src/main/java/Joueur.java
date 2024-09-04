package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Joueur implements Serializable {

    private static final String PATH= "./src/main/ressources/joueur/";
    private static final int NB_BEST_PLAYER = 10;

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
        return "Au tour de " + this.getName().toUpperCase() + "\nCouleur : " + this.getColor().toString().substring(0, 1)+this.getColor().toString().substring(1).toLowerCase() + "\n";
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


    private static List<Joueur> sortPlayerList(List<Joueur> list) {
        List<Joueur> l = new ArrayList<>();
        for (int j = 0;  j < NB_BEST_PLAYER; j++) {
            if (list.size() == 0) {
                break;
            }
            Joueur max = list.get(0);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null && list.get(i).score > max.score) {
                    max = list.get(i);
                }
            }
            if (max == null) {
                break;
            }
            l.add(max);
            list.remove(max);
        }
        return l;

    }
    public static List<Joueur> getBestPlayers() {
        File[] files = new File(PATH).listFiles();
        List<Joueur >list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            list.add(loadJoueur(files[i].getName().split(".ser")[0]));
        }
        return sortPlayerList(list);
    }

    

    public static void main(String[] args) {
        // test serialization
        Joueur j = new Joueur("totouille", Couleur.NOIR);
        savePlayer(j);
        Joueur j2 = loadJoueur("toto");
        j2.score = 30;
        //System.out.println(j2);
        // test getBestPlayer
        Joueur j3 = new Joueur("titi", Couleur.BLANC);
        j3.score = 10;
        savePlayer(j3);
        savePlayer(j2);
        List<Joueur> list = getBestPlayers();
        
    }
}