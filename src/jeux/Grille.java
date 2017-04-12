//Berlin Alexandre
//Weckner Jean
//2A-groupe AA
//CPO-Projet : Arbres et Tentes
//27/05/2013
package jeux;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

public class Grille {

    private Case plateau[][];//la grille en elle meme
    private Arbre arbres[];//tous les arbres de la grille dans l'ordre
    private ArrayList tentes;//tentes solutions de la grille
    private int tentes_lignes[];//nombres de tentes par lignes
    private int tentes_colonnes[];//nombres de tentes par colonnes
    private int nlignes;//nombre de lignes de la grille
    private int ncolonnes;//nombre de colonnes de la grille
    private int narbres;//nombre d'arbres dans la grille
    private int ntentes;//nombre de tentes dans la grille
    private int difficulte;//difficulte de la grille
    private static final int nLignes0 = 4;//nombre de lignes minimal
    private static final int nColonnes0 = 4;//nombre de colonnes minimal
    private static final int difficulteMax = 3;//difficulte maximale

    //constructeur, prend en compte la difficulte
    public Grille(int d) {
        difficulte = d;
        Random rd = new Random();
        nlignes = nLignes0 + rd.nextInt(difficulte);
        ncolonnes = nColonnes0 + rd.nextInt(difficulte);
        narbres = (nlignes * ncolonnes) / (2 * difficulteMax - difficulte);

        arbres = new Arbre[narbres];
        tentes = new ArrayList<Tente>();
        ntentes = 0;
        tentes_lignes = new int[nlignes];
        for (int i = 0; i < nlignes; i++) {
            tentes_lignes[i] = 0;
        }
        tentes_colonnes = new int[ncolonnes];
        for (int i = 0; i < ncolonnes; i++) {
            tentes_colonnes[i] = 0;
        }
        plateau = new Case[nlignes][ncolonnes];
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                plateau[i][j] = new Case(true, i, j, false);
            }
        }
    }

    //second constructeur utilise pour la sauvegarde
    public Grille(int n, int d, int l, int c, Case tab[][]) {
        difficulte = d;
        nlignes = l;
        ncolonnes = c;
        plateau = tab;
        arbres = new Arbre[n];
        ordreArbres();
        tentes = new ArrayList<Tente>();
        tentes_lignes = new int[nlignes];
        for (int i = 0; i < nlignes; i++) {
            tentes_lignes[i] = 0;
        }
        tentes_colonnes = new int[ncolonnes];
        for (int i = 0; i < ncolonnes; i++) {
            tentes_colonnes[i] = 0;
        }
    }

    public void Test1() {
        plateau[2][2] = new Arbre(2, 2, true);
        plateau[3][0] = new Arbre(3, 0, true);
        plateau[3][1] = new Arbre(3, 1, true);
        ordreArbres();
    }

    public void Test2() {
        nlignes = 5;
        ncolonnes = 5;
        narbres = 8;
        arbres = new Arbre[narbres];
        plateau = new Case[nlignes][ncolonnes];
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                plateau[i][j] = new Case(true, i, j, false);
            }
        }
        tentes_lignes = new int[nlignes];
        for (int i = 0; i < nlignes; i++) {
            tentes_lignes[i] = 0;
        }
        tentes_colonnes = new int[ncolonnes];
        for (int i = 0; i < ncolonnes; i++) {
            tentes_colonnes[i] = 0;
        }

        plateau[0][1] = new Arbre(0, 1, true);
        plateau[1][2] = new Arbre(1, 2, true);
        plateau[1][3] = new Arbre(1, 3, true);
        plateau[2][2] = new Arbre(2, 2, true);
        plateau[2][4] = new Arbre(2, 4, true);
        plateau[3][0] = new Arbre(3, 0, true);
        plateau[3][2] = new Arbre(3, 2, true);
        plateau[4][1] = new Arbre(4, 1, true);
        ordreArbres();
    }

    public void Test3() {
        nlignes = 5;
        ncolonnes = 5;
        narbres = 5;
        arbres = new Arbre[narbres];
        plateau = new Case[nlignes][ncolonnes];
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                plateau[i][j] = new Case(true, i, j, false);
            }
        }
        tentes_lignes = new int[nlignes];
        for (int i = 0; i < nlignes; i++) {
            tentes_lignes[i] = 0;
        }
        tentes_colonnes = new int[ncolonnes];
        for (int i = 0; i < ncolonnes; i++) {
            tentes_colonnes[i] = 0;
        }

        plateau[1][1] = new Arbre(1, 1, true);
        plateau[1][3] = new Arbre(1, 3, true);
        plateau[2][1] = new Arbre(2, 1, true);
        plateau[3][3] = new Arbre(3, 3, true);
        plateau[4][3] = new Arbre(4, 3, true);

        ordreArbres();
    }

    public void Test4() {
        nlignes = 5;
        ncolonnes = 5;
        narbres = 8;
        arbres = new Arbre[narbres];
        plateau = new Case[nlignes][ncolonnes];
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                plateau[i][j] = new Case(true, i, j, false);
            }
        }
        tentes_lignes = new int[nlignes];
        for (int i = 0; i < nlignes; i++) {
            tentes_lignes[i] = 0;
        }
        tentes_colonnes = new int[ncolonnes];
        for (int i = 0; i < ncolonnes; i++) {
            tentes_colonnes[i] = 0;
        }

        plateau[0][0] = new Arbre(0, 0, true);
        plateau[0][3] = new Arbre(0, 3, true);
        plateau[0][4] = new Arbre(0, 4, true);
        plateau[1][2] = new Arbre(1, 2, true);
        plateau[2][0] = new Arbre(2, 0, true);
        plateau[3][3] = new Arbre(3, 3, true);
        plateau[4][1] = new Arbre(4, 1, true);
        plateau[4][3] = new Arbre(4, 3, true);
        plateau[4][1] = new Arbre(4, 1, true);
        ordreArbres();
    }

    public void generer() {//permet de generer une grille
        int n = narbres;
        int q = n / ncolonnes;
        int r = n % ncolonnes;

        for (int i = 0; i < q; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                Arbre a = new Arbre(i, j, true);
                plateau[i][j] = a;
            }
        }
        for (int j = 0; j < r; j++) {
            Arbre a = new Arbre(q, j, true);
            plateau[q][j] = a;
        }

        Random rd = new Random();
        Case temp;
        int permutations = (nlignes) * (ncolonnes);

        do {
            for (int k = 0; k < permutations; k++) {
                int x1 = rd.nextInt(nlignes);
                int y1 = rd.nextInt(ncolonnes);
                int x2 = rd.nextInt(nlignes);
                int y2 = rd.nextInt(ncolonnes);
                temp = plateau[x1][y1];
                plateau[x1][y1] = plateau[x2][y2];
                plateau[x2][y2] = temp;
                plateau[x1][y1].setCoord(x1, y1);
                plateau[x2][y2].setCoord(x2, y2);
            }
            ordreArbres();
        } while (!estPossible());
        //tant que la grille generer est incorrecte, on fait des permutations
    }

    public void reinitialisation() {//permet de vider la grille
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                plateau[i][j] = new Case(true, i, j, false);
            }
        }
    }

    //permet de savoir si un arbre possede un emplacement pour une tente
    public boolean placePourUneTente(Arbre a) {
        int places = 4;
        for (int i = 0; i < 4; i++) {
            Case c = Adjacent(a, i);
            if (c == null || c.estArbre()) {
                places--;
            }
        }
        if (places == 0) {
            return false;
        }
        return true;
    }

    //permet de savoir si une grille est correcte
    public boolean estPossible() {
        int i = 0;
        while (i < narbres && placePourUneTente(arbres[i])) {
            i++;
        }
        if (i < narbres) {
            return false;
        }
        return true;
    }

    //permet de localiser tous les arbres de la grille
    public void ordreArbres() {
        int k = 0;
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                if (plateau[i][j].estArbre()) {
                    arbres[k] = (Arbre) plateau[i][j];
                    k++;
                }
            }
        }
    }

    public void afficher(boolean b) {//affiche la grille en mode console
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                plateau[i][j].afficher();
            }
            if (b) {
                System.out.print(" " + tentes_lignes[i]);
            }
            System.out.println();
        }
        if (b) {
            System.out.println();
            for (int j = 0; j < ncolonnes; j++) {
                System.out.print(tentes_colonnes[j] + " ");
            }
            System.out.println("\n");
        }
    }

    public void afficher(JButton tab[][]) {
        int j, i, k;
        for (i = 0; i < nlignes; i++) {
            for (j = 0; j < ncolonnes; j++) {
                plateau[i][j].afficher(tab[i][j]);
            }
            tab[i][j].setText("" + tentes_lignes[i]);

        }
        for (k = 0; k < ncolonnes; k++) {
            tab[i][k].setText("" + tentes_colonnes[k]);
        }
    }

    //permet de savoir s'il y a une tente pres d'une case
    public boolean tenteProche(Case c) {
        int x = c.getX();
        int y = c.getY();
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (plateau[x - 1][y - 1].estTente()) {
                return true;
            }
        }
        if (x - 1 >= 0 && y + 1 < ncolonnes) {
            if (plateau[x - 1][y + 1].estTente()) {
                return true;
            }
        }
        if (x + 1 < nlignes && y - 1 >= 0) {
            if (plateau[x + 1][y - 1].estTente()) {
                return true;
            }
        }
        if (x + 1 < nlignes && y + 1 < ncolonnes) {
            if (plateau[x + 1][y + 1].estTente()) {
                return true;
            }
        }
        if (x - 1 >= 0) {
            if (plateau[x - 1][y].estTente()) {
                return true;
            }
        }
        if (x + 1 < nlignes) {
            if (plateau[c.getX() + 1][y].estTente()) {
                return true;
            }
        }
        if (y - 1 >= 0) {
            if (plateau[x][y - 1].estTente()) {
                return true;
            }
        }
        if (y + 1 < ncolonnes) {
            if (plateau[x][y + 1].estTente()) {
                return true;
            }
        }
        return false;
    }

    //renvoie une case en fonction de sa position
    public Case Adjacent(Case c, int i) {
        int x = c.getX();
        int y = c.getY();
        switch (i) {
            case 0:
                if (x - 1 >= 0) {
                    return plateau[x - 1][y];
                }
                return null;
            case 1:
                if (x + 1 < nlignes) {
                    return plateau[x + 1][y];
                }
                return null;
            case 2:
                if (y - 1 >= 0) {
                    return plateau[x][y - 1];
                }
                return null;
            case 3:
                if (y + 1 < ncolonnes) {
                    return plateau[x][y + 1];
                }
                return null;
            default:
                return null;
        }
    }

    //permet de savoir si une case peut accueillir une tente
    public boolean CaseValide(Case c, boolean b) {
        if (!(c == null) && !(c.getTest())) {
            if (!c.getVide()) {
                return false;
            } else if (tenteProche(c)) {
                return false;
            } else if (b && !placeVacante(c)) {
                return false;
            }
            for (int i = 0; i < 4; i++) {
                Case adj = Adjacent(c, i);
                if ((adj != null) && (adj.estArbre())) {
                    if (AtenteACote((Arbre) adj)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    //renvoie la position d'une place non testee autour d'un arbre
    public int placeNonTestee(Arbre a, boolean b) {
        int i = 0;
        while ((i < 4) && !CaseValide(Adjacent(a, i), b)) {
            i++;
        }
        if (i < 4) {
            return i;
        }
        return -1;
    }

    //permet de savoir si un arbre a une tente a cote de lui
    public boolean AtenteACote(Arbre a) {
        if (tenteACote(a) != null) {
            return true;
        }
        return false;
    }

    //renvoie la tente a cote de l'arbre
    public Tente tenteACote(Arbre a) {
        int i = 0;
        Case c = Adjacent(a, i);
        while (((i < 4) && !(c != null && c.estTente()))) {
            i++;
            c = Adjacent(a, i);
        }
        if (i < 4) {
            return (Tente) c;
        }
        return null;
    }

    //supprime une tente de la grille
    public void supprimerTente(Arbre a) {
        int xt = a.getTente().getX();
        int yt = a.getTente().getY();
        plateau[xt][yt] = new Case(true, xt, yt, true);
        a.setTente(null);
    }

    //place une tente dans la grille
    public void placerTente(Arbre a, int position) {
        int xt = Adjacent(a, position).getX();
        int yt = Adjacent(a, position).getY();
        plateau[xt][yt] = new Tente(xt, yt, true);
        a.setTente((Tente) plateau[xt][yt]);
    }

    //place une tente dans la grille
    public boolean placerTente(int x, int y) {
        if (x < nlignes && y < ncolonnes) {
            Case c = plateau[x][y];
            if (c.getVide()) {
                plateau[x][y] = new Tente(x, y, true);
                return true;
            } else if (c.estTente()) {
                plateau[x][y] = new Case(true, x, y, false);
                return false;
            }
        }
        return false;
    }

    //verifie si toutes les places d'un arbre sont testees
    public boolean placesToutesTest(Arbre a) {
        int n = 0;
        for (int i = 0; i < 4; i++) {
            if (Adjacent(a, i) != null) {
                n++;
            }
        }
        int t = 0;
        for (int i = 0; i < 4; i++) {
            if (Adjacent(a, i) != null && Adjacent(a, i).getTest()) {
                t++;
            }
        }
        if (t == n) {
            return true;
        }
        return false;
    }

    //les case adjacentes d'un arbre ne sont plus testees
    public void reinitArbreTest(Arbre a) {
        for (int i = 0; i < 4; i++) {
            if (Adjacent(a, i) != null && Adjacent(a, i).getVide()) {
                Adjacent(a, i).setTest(false);
            }
        }
    }

    //algorithme de resolution de la grille
    public boolean resolution() {
        int k = 0;
        Arbre courant;
        boolean retour = false;

        //tant que tous les arbres de la grille n'ont pas été examinés
        while (k < narbres) {
            courant = arbres[k];

            //si les place ont toutes ete testees
            if (placesToutesTest(courant)) {
                reinitArbreTest(courant);
            }

            //tant que les arbres possedent une tente
            while (tenteACote(courant) != courant.getTente()) {
                if (k == narbres - 1) {
                    //la grille a au moins une solution
                    return true;
                }
                if (!retour) {//s'il n'y a pas eu de retour
                    k++;//on va a l'arbre suivant
                } else {
                    k--;//sinon on va a l'arbre precedent
                }
                courant = arbres[k];
            }

            //si ce n'est pas la première fois que l'on place une tente à côté de cet arbre
            if (courant.dejaTente()) {
                supprimerTente(courant);//on supprime la tente
            }

            //on cherche une place non encore testée pour la tente
            int p = placeNonTestee(courant, false);
            if (p != -1) {//s'il en existe une
                placerTente(courant, p);//on place la tente 
                if (k == narbres - 1) {//si c'est le dernier arbre
                    return true;//la grille a au moins une solution
                } else {
                    k++;//sinon on va à l'arbre suivant
                    retour = false;
                }
            } else {//sinon si c'est le premier arbre de la grille
                if (k == 0) {
                    return false;//la grille n'a pas de solution
                } else {
                    k--;//sinon on retourne à l'arbre précédent
                    retour = true;
                }
            }
        }
        return true;//la grille a au moins une solution
    }

    //renvoie le nombre de tentes dans la grille
    public int conterTentes(boolean b) {
        int n = 0;
        if (b) {
            tentes.clear();
        }
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                if (plateau[i][j].estTente()) {
                    n++;
                    if (b) {
                        tentes_lignes[i]++;
                        tentes_colonnes[j]++;
                        ntentes = n;
                        tentes.add(plateau[i][j]);
                    }
                }
            }
        }
        return n;
    }

    //recupère les tentes dans la grille
    public void chercherTentes(ArrayList a) {
        a.clear();
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                if (plateau[i][j].estTente()) {
                    a.add(plateau[i][j]);
                }
            }
        }
    }

    //reinitialise les cases testees sauf s'il y a une tente dessus
    public void reinitTest() {
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                if (!plateau[i][j].estTente()) {
                    plateau[i][j].setTest(false);
                }
            }
        }
    }

    //permet de savoir si les nombres de tentes par lignes et par colonnes
    //permettent le placement d'une tente
    public boolean placeVacante(Case c) {
        int nligne = 0;
        int ncolonne = 0;
        int x = c.getX();
        int y = c.getY();
        for (int i = 0; i < nlignes; i++) {
            if (plateau[i][y].estTente()) {
                ncolonne++;
            }
        }
        for (int j = 0; j < ncolonnes; j++) {
            if (plateau[x][j].estTente()) {
                nligne++;
            }
        }
        if ((nligne < tentes_lignes[x]) && (ncolonne < tentes_colonnes[y])) {
            return true;
        }
        return false;

    }

    //vérifie le nombre de tentes dans la grille
    public boolean verificationTentes() {
        int n = conterTentes(false);
        if (n != ntentes) {
            return false;
        } else {
            return true;
        }
    }

    //permet de savoir si la solution trouvee est unique ou non
    public boolean autreSolution() {
        reinitTest();//on réinitialise la grille

        int k = narbres - 1;
        Arbre courant;

        //tant que tous les arbres de la grille n'ont pas été examinés
        while (k < narbres) {
            courant = arbres[k];
            //si l'arbre a une tente à coté de lui
            if (courant.dejaTente()) {
                supprimerTente(courant);//on supprime la tente
            }
            //on cherche une autre place possible pour la tente
            int p = placeNonTestee(courant, true);
            if (p != -1) {//s'il en existe une
                placerTente(courant, p);//on place la tente 
                if (k == narbres - 1) {//si c'est le dernier arbre
                    return verificationTentes();
                } else {
                    k++;//sinon on va à l'arbre suivant
                    if (k == narbres - 1) {//si l'arbre suivant est le dernier
                        courant = arbres[k];
                        if (AtenteACote(courant)) {//et qu'il a une tente
                            return verificationTentes();
                        }
                    }
                }
            } else {//sinon si c'est le premier arbre de la grille
                if (k == 0) {
                    return false;//la grille n'a pas d'autre solution
                } else {
                    k--;//sinon on retourne à l'arbre précédent
                }
            }
        }
        return false;//la grille n'a pas d'autre solution
    }

    

    //permet de retirer les tentes dans la grille
    public void nettoyer() {
        for (int i = 0; i < nlignes; i++) {
            for (int j = 0; j < ncolonnes; j++) {
                if (plateau[i][j].estTente()) {
                    plateau[i][j] = new Case(true, i, j, false);
                }
            }
        }
        for (int k = 0; k < narbres; k++) {
            arbres[k].setTente(null);
        }
    }
    
    public void VerifierGrille(){
        boolean resolue;
        do {//génération de la grille
            reinitialisation();//on reinitialise la grille
            generer();//on genere la grille
            System.out.println("La grille peut avoir une solution.");
            afficher(false);
            System.out.println();
            resolue = resolution();//on resoud la grille
            if (!resolue) {
                System.out.println("La grille n'a pas de solution.\n");
            } else {
                afficher(false);
                System.out.println();
                conterTentes(true);
                afficher(true);
                System.out.println();
            }
        } while (!resolue | autreSolution());
    }

    public int getNlignes() {//accesseur au nombre de ligne
        return nlignes;
    }

    public int getNcolonnes() {//accesseur au nombre de colonne
        return ncolonnes;
    }

    public Case[][] getPlateau() {//accesseur au plateau de jeu
        return plateau;
    }

    public int getNtentes() {//accesseur au nombre de tentes
        return ntentes;
    }

    public ArrayList getTentes() {//accesseur a la liste de tentes
        return tentes;
    }

    public int getDifficulte() {//accesseur a la difficulte de la grille
        return difficulte;
    }

    public int getNArbres() {//accesseur au nombre d'arbres dans la grille
        return narbres;
    }
}
