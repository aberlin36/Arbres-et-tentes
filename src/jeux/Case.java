package jeux;

import java.awt.Color;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Case {

    private boolean vide;//la case est vide ou non
    private boolean test;//la case est testé ou non
    private int x;//ordonne de la case
    private int y;//abscisse de la case

    //constructeur
    public Case(boolean v, int valx, int valy, boolean t) {
        vide = v;
        x = valx;
        y = valy;
        test = t;
    }

    //affiche une case en mode console
    public void afficher() {
        System.out.print("0 ");
    }

    //affiche une case dans un bouton
    public void afficher(JButton b) {
        java.awt.Image img;
        Toolkit t = Toolkit.getDefaultToolkit();
        URL chemin = getClass().getResource("/jeux/"+"blanc.png");
        img = t.getImage(chemin);
        img = img.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_DEFAULT);
        b.setIcon(new ImageIcon(img));
        
    }

    //permet de savoir si la case est un arbre
    public boolean estArbre() {
        return false;
    }

    //permet de savoir si la case est une tente
    public boolean estTente() {
        return false;
    }

    public boolean identique(Case c) {
        if (x == c.x && y == c.y) {
            return true;
        }
        return false;
    }

    //modifie la position d'une case
    public void setCoord(int vx, int vy) {
        x = vx;
        y = vy;
    }

    //acces a l'abscisse d'une case
    public int getX() {
        return x;
    }

    //acces a l'ordonne d'une case
    public int getY() {
        return y;
    }

    //acces a l'attribut test
    public boolean getTest() {
        return test;
    }

    //modifie l'attribut test
    public void setTest(boolean b) {
        test = b;
    }

    //acces a l'attribut vide
    public boolean getVide() {
        return vide;
    }
}
