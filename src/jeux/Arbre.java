package jeux;

import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Arbre extends Case {

    private Tente tente;//tente a cote de l'arbre 

    //constructeur
    public Arbre( int valx, int valy, boolean t) {
        super(false, valx, valy, t);
    }
    
    //permet de savoir si l'arbre possede une tente
    public boolean dejaTente (){
        if (getTente()!=null){
            return true;
        }
        else return false;
    }

    //affiche un arbre en mode console
    public void afficher() {
        System.out.print("A ");
    }
    
    //affiche un arbre dans un bouton
    public void afficher(JButton b) {
        java.awt.Image img;
        Toolkit t = Toolkit.getDefaultToolkit();
        URL chemin = getClass().getResource("/jeux/"+"arbre.jpg");
        img = t.getImage(chemin);
        img = img.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_DEFAULT);
        b.setIcon(new ImageIcon(img));
    }

    //permet de savoir si une case est un arbre
    public boolean estArbre() {
        return true;
    }

    //acces a l'attribut tente
    public Tente getTente() {
        return tente;
    }
    
    //modifie l'attribut tente
    public void setTente(Tente t){
        tente=t;
    }   
}
