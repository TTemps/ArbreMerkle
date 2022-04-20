import java.util.ArrayList;

/**
 * TTH
 */
public class TTH {
    // M message binary
    private ArrayList<String> M = new ArrayList<String>();
    private String[][] matrix;

    public String[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public ArrayList<String> getM() {
        return this.M;
    }

    public void setM(ArrayList<String> M) {
        this.M = M;
    }

    public void init() {
        String[] message = { "00", "06", "08", "35", "17",
                "28", "24", "56", "62", "07",
                "12", "16", "20", "05", "33",
                "43", "35", "27", "12", "60",
                "25", "23", "18", "01", "45",
                "56", "12", "34", "21", "20",
                "02", "10", "22", "20", "17",
                "34", "01" };
        for (String string : message) {
            this.M.add(string);
        }
    }

    // method a
    // if M is not multiple of 25, add 32 in the end of M then pad with "00" till M
    // is multiple of 25
    public void padding() {
        if (this.M.size() % 25 != 0) {
            int pad = 25 - (this.M.size() % 25);
            this.M.add("32");
            for (int i = 0; i < pad - 1; i++) {
                this.M.add("00");
            }
        }
    }

    // method b
    // split M into 5*5 matrix and store them in a list of matrix
    public ArrayList<String[][]> split() {
        ArrayList<String[][]> matrixList = new ArrayList<String[][]>();
        int i = 0;
        while (i < this.M.size()) {
            String[][] matrix = new String[5][5];
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    matrix[j][k] = this.M.get(i);
                    i++;
                }
            }
            matrixList.add(matrix);
        }
        displayBlock(matrixList);
        return matrixList;
    }

    // method C
    // sums the elements of each column, then mod 64 the result and finally store
    // them in a 1D array
    public String[] sumColumn(String[][] matrix) {
        String[] sum = new String[5];
        for (int i = 0; i < 5; i++) {
            int sumCol = 0;
            for (int j = 0; j < 5; j++) {
                sumCol += Integer.parseInt(matrix[j][i]);
            }
            sum[i] = Integer.toString(sumCol % 64);
        }
        for (int i = 0; i < sum.length; i++) {
            switch (sum[i]) {
                case "0":
                    sum[i] = "00";
                    break;
                case "1":
                    sum[i] = "01";
                    break;
                case "2":
                    sum[i] = "02";
                    break;
                case "3":
                    sum[i] = "03";
                    break;
                case "4":
                    sum[i] = "04";
                    break;
                case "5":
                    sum[i] = "05";
                    break;
                case "6":
                    sum[i] = "06";
                    break;
                case "7":
                    sum[i] = "07";
                    break;
                case "8":
                    sum[i] = "08";
                    break;
                case "9":
                    sum[i] = "09";
                    break;

                default:
                    break;
            }
        }
        return sum;
    }

    // thanks to sumColumn sums the columns of each block
    public ArrayList<String[]> footprintBlock(ArrayList<String[][]> blocks) {
        String[] sum = new String[5];
        ArrayList<String[]> footprint = new ArrayList<String[]>();
        for (String[][] matrice : blocks) {
            sum = sumColumn(matrice);
            footprint.add(sum);
            sum = null;
        }
        return footprint;
    }

    public void display() {
        System.out.println("M: " + this.M);
    }

    public void displayMatrix(String[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // display block
    public void displayBlock(ArrayList<String[][]> blocks) {
        for (String[][] matrix : blocks) {
            System.out.println("Bloc :");
            displayMatrix(matrix);
        }
    }

    public void displayFootprint(ArrayList<String[]> footprint) {
        for (String[] string : footprint) {
            displaySum(string);
        }
    }

    public void displaySum(String[] sum) {
        for (int i = 0; i < 5; i++) {
            System.out.print(sum[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TTH tth = new TTH();
        tth.init();
        tth.padding();
        tth.display();
        tth.displayFootprint(tth.footprintBlock(tth.split()));
    }
}