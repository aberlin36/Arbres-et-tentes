
package donnees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;
import jeux.Arbre;
import jeux.Case;
import jeux.Grille;
import jeux.Tente;

public class Sauvegardes {

    public class Element {//element d'une sauvegarde

        private String nom;//nom de l'element
        private Grille grille;//grille sauvegardee

        public Element(String n, Grille g) {
            nom = n;
            grille = g;
        }

        public String getNom() {//acces au nom
            return nom;
        }

        public Grille getGrille() {//acces a la grille
            return grille;
        }
    }
    private String fichier;//fichier de sauvegarde
    private ArrayList elements;//listes des elements sauvegardes
    //caracteres pour coder la grille
    private static final char A='A';
    private static final char T='T';
    private static final char O='0';

    public Sauvegardes(String f) {
        fichier = f;
        elements = new ArrayList<Element>();
        try {//on lit le fichier
            FileReader fr = new FileReader(fichier);
            BufferedReader br = new BufferedReader(fr);
            String s = new String();
            while ((s = br.readLine()) != null) {
                //on recupere :
                String nom = s;//le nom
                int n = Integer.valueOf(br.readLine());//le nombre d'arbres
                int d = Integer.valueOf(br.readLine());//la difficulte
                int l = Integer.valueOf(br.readLine());//nombre de lignes
                int c = Integer.valueOf(br.readLine());//nombre de colonnes
                Case tab[][] = new Case[l][c];//le plateau
                for (int i = 0; i < l; i++) {
                    s = br.readLine();
                    for (int j = 0; j < c; j++) {
                        if (s.charAt(j) == A) {
                            tab[i][j] = new Arbre(i, j, false);
                        } else if (s.charAt(j) == T) {
                            tab[i][j] = new Tente(i, j, false);
                        } else {
                            tab[i][j] = new Case(true, i, j, false);
                        }
                    }
                }
                Grille g = new Grille(n,d, l, c, tab);//on cree la grille
                g.conterTentes(true);
                elements.add(new Element(nom, g));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //verifie la validite d'un nom pour une grille
    public boolean VerifierNom(String n) {
        int t = 0;
        Iterator<Element> i = elements.iterator();
        while (i.hasNext() && !(i.next().getNom().equals(n))) {
            t++;
        }
        if (t < elements.size()) {
            return true;
        }
        return false;
    }

    //ajoute une grille dans la liste des grilles sauvegardees
    public boolean ajouterGrille(String n, Grille g,int min) {
        System.out.println(n.length());
        if (!VerifierNom(n) && !(n.length() < min)) {
            g.nettoyer();
            g.reinitTest();
            g.resolution();
            elements.add(new Element(n, g));
            ecrireSauvegardes();
            return true;
        }
        return false;
    }

    //retire une grille de la liste des grille sauvegardees
    public void supprimerGrille(String n) {
        int l = elements.size();
        if (l != 0) {
            int i = 0;
            String s = ((Element) elements.get(0)).getNom();
            while (i < l & (s.compareTo(n) != 0)) {
                i++;
                s = ((Element) elements.get(i)).getNom();
            }
            if (i < l) {
                elements.remove(i);
            }
            ecrireSauvegardes();
        }
    }

    //sauvegarde dans le fichier les grilles dans la liste
    public void ecrireSauvegardes() {
        try {
            FileWriter fw = new FileWriter(fichier);
            BufferedWriter bw = new BufferedWriter(fw);
            Iterator<Element> i = elements.iterator();
            while (i.hasNext()) {
                Element e = i.next();
                bw.write(e.getNom());
                bw.flush();
                bw.newLine();
                Grille g = e.getGrille();
                int n = g.getNArbres();
                bw.write(Integer.toString(n));
                bw.flush();
                bw.newLine();             
                int d = g.getDifficulte();
                bw.write(Integer.toString(d));
                bw.flush();
                bw.newLine();
                int l = g.getNlignes();
                bw.write(Integer.toString(l));
                bw.flush();
                bw.newLine();
                int c = g.getNcolonnes();
                bw.write(Integer.toString(c));
                bw.flush();
                bw.newLine();
                Case tab[][] = g.getPlateau();
                for (int k = 0; k < l; k++) {
                    for (int j = 0; j < c; j++) {
                        if (tab[k][j].estArbre()) {
                            bw.write(A);
                        } else if (tab[k][j].estTente()) {
                            bw.write(T);
                        } else {
                            bw.write(O);
                        }
                        bw.flush();
                    }
                    bw.newLine();
                }
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //recherche une grille dans la liste
    public Grille charger(String n) {
        if (n != null) {
            int t = 0;
            Iterator<Element> i = elements.iterator();
            while (i.hasNext() && !(i.next().getNom().equals(n))) {
                t++;
            }
            if (t < elements.size()) {
                Grille g = ((Element) elements.get(t)).getGrille();
                g.nettoyer();
                return g;
            }
        }
        return null;
    }

    public ArrayList getElements() {//acces a la liste des grilles
        return elements;
    }
}
