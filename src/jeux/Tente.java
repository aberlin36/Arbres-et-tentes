package jeux;

import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Tente extends Case {
  
    //constructeur
    public Tente( int valx, int valy,boolean t) {
        super(false, valx, valy,t);
    }
    
    //affiche une tente en mode console
    public void afficher(){
       System.out.print("T "); 
    }
    
    //affiche une tente dans un bouton
    public void afficher(JButton b) {
        java.awt.Image img;
        Toolkit t = Toolkit.getDefaultToolkit();
        URL chemin = getClass().getResource("/jeux/"+"tente.jpg");
        img = t.getImage(chemin);
        img = img.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_DEFAULT);
        b.setIcon(new ImageIcon(img));
        
    }
    
    //permet de savoir si une case est une tente
    public boolean estTente(){
        return true;
    }
}
