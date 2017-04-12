//Berlin Alexandre
//Weckner Jean
//2A-groupe AA
//CPO-Projet : Arbres et Tentes
//27/05/2013
package donnees;

public class Joueur {
    private String nom;//nom du joueur
    private long points;//score du joueur
    
    //constructeur prend en parametres le nom et le score
    public Joueur(String n,int p){
        nom=n;
        points=p;
    }
    
    public void afficher(){//affiche un joueur
        System.out.println(nom+" "+points);
    }
    
    //affiche le joueur dans un text area
    public void afficher(javax.swing.JTextArea t){
        t.append("   "+nom+" : "+points+"\n");
    }
    
    public String getNom(){//acces au nom
        return nom;
    }
    
    public long getPoints(){//acces au score
        return points;
    }
    
    public void setPoints(long p){//modifie le score
        points=p;
    }
}
