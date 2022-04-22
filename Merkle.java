import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Merkle {
    private OpenFile openFile;
    private ArrayList<Integer> message;

    public Merkle() {
        this.openFile = new OpenFile();
        this.message = openFile.readFile("texte.txt");
    }

    public void padding() {
        if (this.message.size() % 512 != 0) {
            int pad = 512 - (this.message.size() % 512);
            this.message.add(1);
            for (int i = 0; i < pad - 1; i++) {
                this.message.add(0);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> partionMessage() {
        ArrayList<ArrayList<Integer>> partionMessage = new ArrayList<ArrayList<Integer>>();
        int i = 0;
        while (i < message.size()) {
            ArrayList<Integer> subMessage = new ArrayList<Integer>();
            for (int j = 0; j < 512; j++) {
                subMessage.add(message.get(i));
                i++;
            }
            partionMessage.add(subMessage);
        }
        return partionMessage;
    }

    public ArrayList<ArrayList<Integer>> partionBlock() {
        ArrayList<ArrayList<Integer>> partionBlock = new ArrayList<ArrayList<Integer>>();
        int i = 0;
        while (i < message.size()) {
            ArrayList<Integer> subBlock = new ArrayList<Integer>();
            for (int j = 0; j < 32; j++) {
                subBlock.add(message.get(i));
                i++;
            }
            partionBlock.add(subBlock);
        }
        return partionBlock;
    }

    public ArrayList<ArrayList<Integer>> sommmeBlock(ArrayList<ArrayList<Integer>> partionBlock) {
        ArrayList<Integer> blockAddition = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> partionBlockAddition = new ArrayList<ArrayList<Integer>>();
        int somme = 0;
        int i = 1;
        for (ArrayList<Integer> liste32 : partionBlock) {
            for (Integer integer : liste32) {
                somme += integer;
            }
            somme = somme % 64;
            blockAddition.add(somme);
            if (i % 16 == 0) {
                partionBlockAddition.add(blockAddition);
                blockAddition = new ArrayList<Integer>();
            }
            i++;
        }
        return partionBlockAddition;
    }

    public ArrayList<Integer[][]> listToMatrix(ArrayList<ArrayList<Integer>> block) {
        ArrayList<Integer[][]> ListMatrix = new ArrayList<Integer[][]>();
        for (ArrayList<Integer> arrayList : block) {
            Integer[][] matrix = new Integer[4][4];
            int k = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = arrayList.get(k);
                    k++;
                }
            }
            ListMatrix.add(matrix);
        }
        return ListMatrix;
    }

    public ArrayList<Integer[]> matrixToEmpreinte(ArrayList<Integer[][]> ListMatrix) {
        ArrayList<Integer[]> empreinte = new ArrayList<Integer[]>();
        for (Integer[][] matrix : ListMatrix) {
            Integer[] somme = new Integer[4];
            for (int i = 0; i < matrix.length; i++) {
                int sommeColonne = 0;
                for (int j = 0; j < matrix.length; j++) {
                    sommeColonne += matrix[j][i];
                }
                somme[i] = sommeColonne % 64;
            }
            empreinte.add(somme);
        }
        return empreinte;
    }

    /**
     * Renvoie la liste de toute les matrices shiftés.
     * 
     * @param ListMatrix
     * @return ArrayList<Integer[][]>
     */
    public ArrayList<Integer[][]> shiftRow(ArrayList<Integer[][]> ListMatrix) {
        ArrayList<Integer[][]> shiftedMatrix = new ArrayList<Integer[][]>();
        for (Integer[][] integers : ListMatrix) {
            Integer[][] shifted = new Integer[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int shift = ((j - i) % 4);
                    if (shift < 0) {
                        shift += 4;
                    }
                    shifted[i][j] = integers[i][shift];
                }
            }
            shiftedMatrix.add(shifted);
        }
        return shiftedMatrix;
    }

    public ArrayList<Integer[]> shiftedMatrixToEmpreinte(ArrayList<Integer[][]> ListMatrix,
            ArrayList<Integer[]> empreinte) {
        ArrayList<Integer[]> newEmpreinte = new ArrayList<Integer[]>();
        int listnumber = 0;
        for (Integer[][] matrix : ListMatrix) {
            Integer[] somme = new Integer[4];
            for (int i = 0; i < matrix.length; i++) {
                int sommeColonne = 0;
                for (int j = 0; j < matrix.length; j++) {
                    sommeColonne += matrix[j][i];
                }
                somme[i] = (sommeColonne + empreinte.get(listnumber)[i]) % 64;

            }
            listnumber++;
            newEmpreinte.add(somme);
        }
        // displayAllEmpreinte(newEmpreinte);
        return newEmpreinte;
    }

    public ArrayList<String[]> normalizeEmpreinte(ArrayList<Integer[]> empreinte) {

        ArrayList<String[]> empreinteNormalize = new ArrayList<String[]>();
        for (Integer[] integer : empreinte) {
            String[] empreinteString = new String[4];
            for (int i = 0; i < integer.length; i++) {
                integer[i] = integer[i] % 15;
                switch (integer[i]) {
                    case 0:
                        empreinteString[i] = "0";
                        break;
                    case 1:
                        empreinteString[i] = "1";
                        break;
                    case 2:
                        empreinteString[i] = "2";
                        break;
                    case 3:
                        empreinteString[i] = "3";
                        break;
                    case 4:
                        empreinteString[i] = "4";
                        break;
                    case 5:
                        empreinteString[i] = "5";
                        break;
                    case 6:
                        empreinteString[i] = "6";
                        break;
                    case 7:
                        empreinteString[i] = "7";
                        break;
                    case 8:
                        empreinteString[i] = "8";
                        break;
                    case 9:
                        empreinteString[i] = "9";
                        break;
                    case 10:
                        empreinteString[i] = "A";
                        break;
                    case 11:
                        empreinteString[i] = "B";
                        break;
                    case 12:
                        empreinteString[i] = "C";
                        break;
                    case 13:
                        empreinteString[i] = "D";
                        break;
                    case 14:
                        empreinteString[i] = "E";
                        break;
                    case 15:
                        empreinteString[i] = "F";
                        break;
                }
            }
            empreinteNormalize.add(empreinteString);
        }
        // displayAllEmpreinteString(empreinteNormalize);
        return empreinteNormalize;
    }

    public void displayAllEmpreinteString(ArrayList<String[]> empreinte) {
        for (String[] integer : empreinte) {
            displayEmpreinteString(integer);
        }
    }

    public void displayAllEmpreinte(ArrayList<Integer[]> empreinte) {
        for (Integer[] integer : empreinte) {
            displayEmpreinte(integer);
        }
    }

    public void displayEmpreinte(Integer[] array) {
        for (Integer integer : array) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    public void displayEmpreinteString(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void displayAllMatrix(ArrayList<Integer[][]> ListMatrix) {
        for (Integer[][] matrix : ListMatrix) {
            displayMatrix(matrix);
        }
    }

    public void displayMatrix(Integer[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void displayArbre(Noeud n) {
        System.out.println(Arrays.toString(n.getValeur()));
        System.out.print("\t");
        if (n.getFilsGauche() != null) {
            displayArbre(n.getFilsGauche());
        }
        if (n.getFilsDroit() != null) {
            displayArbre(n.getFilsDroit());
        }
    }

    public String[] HexToInt(String[] s) {
        String[] s2 = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            s2[i] = Integer.toString(Integer.parseInt(s[i], 16));
        }
        return s2;
    }

    public String[] IntToHex(String[] s) {
        String[] s2 = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            switch (s[i]) {
                case "0":
                    s2[i] = "0";
                    break;
                case "1":
                    s2[i] = "1";
                    break;
                case "2":
                    s2[i] = "2";
                    break;
                case "3":
                    s2[i] = "3";
                    break;
                case "4":
                    s2[i] = "4";
                    break;
                case "5":
                    s2[i] = "5";
                    break;
                case "6":
                    s2[i] = "6";
                    break;
                case "7":
                    s2[i] = "7";
                    break;
                case "8":
                    s2[i] = "8";
                    break;
                case "9":
                    s2[i] = "9";
                    break;
                case "10":
                    s2[i] = "A";
                    break;
                case "11":
                    s2[i] = "B";
                    break;
                case "12":
                    s2[i] = "C";
                    break;
                case "13":
                    s2[i] = "D";
                    break;
                case "14":
                    s2[i] = "E";
                    break;
                case "15":
                    s2[i] = "F";
                    break;
                default:
                    s2[i] = "0";
            }
        }
        return s2;
    }

    public String[] addStringArray(String[] s1, String[] s2) {
        String[] s3 = new String[s1.length];
        String[] s1Int = HexToInt(s1);
        String[] s2Int = HexToInt(s2);

        for (int i = 0; i < s1.length; i++) {
            s3[i] = Integer.parseInt(s1Int[i]) + Integer.parseInt(s2Int[i]) + "";
        }
        return IntToHex(modulo16(s3));
    }

    public String[] modulo16(String[] s) {
        String[] s2 = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            s2[i] = Integer.parseInt(s[i]) % 15 + "";
        }
        return s2;
    }

    public String[] verifyIntegrity(String[] act, String[] frere, String[] oncle) {

        String[] s = addStringArray(act, frere);
        String[] s2 = addStringArray(s, oncle);

        return s2;
    }

    public boolean verifyBlockIntegrity(Noeud actuelle) {

        if (actuelle.getPere() == null) {
            System.out.println("Impossible de vérifier ce noeud");
            System.exit(2);
        }
        if (actuelle.getPere().getPere() == null) {
            System.out.println("Impossible de vérifier ce noeud");
            System.exit(2);
        }
        Noeud pere = actuelle.getPere(); // pere
        Noeud fils; // frere
        if (pere.getFilsDroit() == actuelle) {
            fils = pere.getFilsGauche();
        } else {
            fils = pere.getFilsDroit();
        }
        Noeud grandpere = pere.getPere();
        Noeud oncle;
        if (grandpere.getFilsDroit() == pere) {
            oncle = grandpere.getFilsGauche();
        } else {
            oncle = grandpere.getFilsDroit();
        }
        // on a tout les noeuds necessaire
        String[] integrity = verifyIntegrity(actuelle.getValeur(), fils.getValeur(), oncle.getValeur());
        if (Arrays.equals(integrity, grandpere.getValeur())) {
            return true;
        }
        return false;
    }

    public void arbre() {
        padding();
        ArrayList<ArrayList<Integer>> partion = partionMessage();
        // System.out.println(partion);affiche la liste de partion de 512 provenant
        // message
        ArrayList<ArrayList<Integer>> partionBlock = partionBlock();
        // System.out.println(partionBlock); //affiche la liste des blocks de 32
        ArrayList<ArrayList<Integer>> sommmeBlock = sommmeBlock(partionBlock);
        // System.out.println(sommmeBlock);
        ArrayList<Integer[][]> ListMatrix = listToMatrix(sommmeBlock);
        // System.out.println(ListMatrix);
        ArrayList<Integer[]> empreinte = matrixToEmpreinte(ListMatrix);
        ArrayList<Integer[][]> matrixShifted = shiftRow(ListMatrix);
        // System.out.println(matrixShifted);
        ArrayList<Integer[]> newempreinte = shiftedMatrixToEmpreinte(matrixShifted, empreinte);
        // System.out.println(newempreinte);
        ArrayList<String[]> empreinteNormal = normalizeEmpreinte(newempreinte);
        // System.out.println(empreinteNormal);
        ArrayList<Noeud> etage = new ArrayList<Noeud>();
        for (String[] strings : empreinteNormal) {
            Noeud noeud = new Noeud(strings);
            etage.add(noeud);
        }
        ArrayList<ArrayList<Noeud>> arbre = new ArrayList<ArrayList<Noeud>>();
        arbre.add(etage);
        int hauteur = (int) Math.floor((etage.size() + 1) / 2);
        for (int i = 0; i < hauteur; i++) {
            ArrayList<Noeud> etageCourant = new ArrayList<Noeud>();
            for (int j = 0; j < arbre.get(i).size(); j += 2) {
                if (j + 1 != arbre.get(i).size()) {
                    Noeud noeud = new Noeud(arbre.get(i).get(j), arbre.get(i).get(j + 1));
                    etageCourant.add(noeud);
                } else {
                    Noeud noeud = new Noeud(arbre.get(i).get(j));
                    etageCourant.add(noeud);
                }
            }
            arbre.add(etageCourant);

        }
        arbre.remove(arbre.size() - 1);

        // displayArbre(arbre.get(hauteur - 1).get(0));

        Noeud L2 = etage.get(1); // noeud à vérifier

        if (verifyBlockIntegrity(L2)) {
            System.out.println("Le block est valide");
            System.exit(0);
        } else {
            System.out.println("Le block n'est pas valide");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Merkle m = new Merkle();
        m.arbre();
    }
}
