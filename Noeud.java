import java.util.Arrays;

public class Noeud {
    private Noeud filsGauche;
    private Noeud filsDroit;
    private Noeud pere;
    private String[] valeur;

    public Noeud getFilsGauche() {
        return this.filsGauche;
    }

    public void setFilsGauche(Noeud filsGauche) {
        this.filsGauche = filsGauche;
    }

    public Noeud getFilsDroit() {
        return this.filsDroit;
    }

    public void setFilsDroit(Noeud filsDroit) {
        this.filsDroit = filsDroit;
    }

    public Noeud getPere() {
        return this.pere;
    }

    public void setPere(Noeud pere) {
        this.pere = pere;
    }

    public String[] getValeur() {
        return this.valeur;
    }

    public void setValeur(String[] valeur) {
        this.valeur = valeur;
    }

    public Noeud(String[] valeur) { // le premier fils
        this.valeur = valeur;
    }

    public Noeud(Noeud filsGauche) {
        this.filsGauche = filsGauche;
        filsGauche.setPere(this);
        this.valeur = filsGauche.valeur;
    }

    public Noeud(Noeud filsGauche, Noeud filsDroit) {

        this.filsGauche = filsGauche;
        this.filsDroit = filsDroit;
        filsGauche.setPere(this);
        filsDroit.setPere(this);
        this.valeur = empreinteAddition(filsGauche.valeur, filsDroit.valeur);
    }

    public String[] empreinteAddition(String[] empreinte, String[] empreinte2) {
        String[] empreinteAddition = new String[4];
        for (int i = 0; i < empreinte.length; i++) {
            empreinteAddition[i] = convert(empreinte[i], empreinte2[i]);

        }
        empreinteAddition = reconvert(empreinteAddition);
        return empreinteAddition;
    }
    public int deconvert(String s) {
        switch (s) {
            case "0":
                return 0;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "A":
                return 10;
            case "B":
                return 11;
            case "C":
                return 12;
            case "D":
                return 13;
            case "E":
                return 14;
            case "F":
                return 15;
        }
        return 0;
    }

    public String convert(String s, String s2) {
        int result = deconvert(s) + deconvert(s2);
        result = result % 15;
        return result + "";
    }

    public String[] reconvert(String[] s) {
        String[] result = new String[4];
        for (int i = 0; i < s.length; i++) {
            switch (s[i]) {
                case "0":
                    result[i] = "0";
                    break;
                case "1":
                    result[i] = "1";
                    break;
                case "2":
                    result[i] = "2";
                    break;
                case "3":
                    result[i] = "3";
                    break;
                case "4":
                    result[i] = "4";
                    break;
                case "5":
                    result[i] = "5";
                    break;
                case "6":
                    result[i] = "6";
                    break;
                case "7":
                    result[i] = "7";
                    break;
                case "8":
                    result[i] = "8";
                    break;
                case "9":
                    result[i] = "9";
                    break;
                case "10":
                    result[i] = "A";
                    break;
                case "11":
                    result[i] = "B";
                    break;
                case "12":
                    result[i] = "C";
                    break;
                case "13":
                    result[i] = "D";
                    break;
                case "14":
                    result[i] = "E";
                    break;
                case "15":
                    result[i] = "F";
                    break;
                default:
                    result[i] = "0";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "\nNoeud [fg = " + filsGauche + "\nfd = " + filsDroit + " \nvaleur=" + valeur[0]
                + valeur[1]
                + valeur[2]
                + valeur[3] + "]";
    }
}
