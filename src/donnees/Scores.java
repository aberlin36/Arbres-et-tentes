
package donnees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Scores {
    
    private ArrayList meilleurs;//liste des meilleurs scores
    private String fichier;//fichier ou sont les scores
    //nombre de scores maximums enregistres
    private static final int nscores = 10;
    
    public Scores(String f) {
        fichier = f;
        meilleurs = new ArrayList<Joueur>();
        try {//on lit les scores
            FileReader fr = new FileReader(fichier);
            BufferedReader br = new BufferedReader(fr);
            String s = new String();
            while ((s = br.readLine()) != null) {
                Joueur j = new Joueur(s, Integer.valueOf(br.readLine()));
                meilleurs.add(j);//on les met dans la liste
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void afficher() {
        triBulle();
        Iterator<Joueur> i = meilleurs.iterator();
        while (i.hasNext()) {
            i.next().afficher();
        }
    }
    
    //affiche les scores dans un text area
    public void afficher(javax.swing.JTextArea t) {
        triBulle();
        if (meilleurs.size() != 0) {
            Iterator<Joueur> i = meilleurs.iterator();
            while (i.hasNext()) {
                i.next().afficher(t);
            }
        } else {
            t.append("Aucun score");
        }
    }
    
    //trie les scores dans l'ordre décroissant des nombres de points
    public void triBulle() {
        int i;
        boolean b;
        do {
            b = false;
            for (i = 0; i < meilleurs.size() - 1; i++) {
                Joueur j1 = (Joueur) meilleurs.get(i);
                Joueur j2 = (Joueur) meilleurs.get(i + 1);
                if (j1.getPoints() > j2.getPoints()) {
                    meilleurs.set(i, j2);
                    meilleurs.set(i + 1, j1);
                    b = true;
                }
            }
        } while (b);
    }
    
    //ajoute un score dans la liste
    public void ajouterScore(Joueur j) {
        if (meilleurs.size() < nscores) {
            meilleurs.add(j);
        } else {
            triBulle();
            if (j.getPoints() < ((Joueur) meilleurs.get(nscores - 1)).getPoints()) {
                meilleurs.set(nscores-1, j);
            }
        }
        ecrireScores();
    }
    
    //enregistre les scores dans le fichier
    public void ecrireScores() {
        try {
            FileWriter fw = new FileWriter(fichier);
            BufferedWriter bw = new BufferedWriter(fw);
            Iterator<Joueur> i = meilleurs.iterator();
            while (i.hasNext()) {
                Joueur j = i.next();
                bw.write(j.getNom());
                bw.flush();
                bw.newLine();
                bw.write(Long.toString(j.getPoints()));
                bw.flush();
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
