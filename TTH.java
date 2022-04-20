import java.util.ArrayList;

/**
 * TTH
 */
public class TTH {
    // M message binary
    private ArrayList<String> M = new ArrayList<String>();

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

    public void display() {
        System.out.println("M: " + this.M);
    }

    public static void main(String[] args) {
        TTH tth = new TTH();
        tth.init();
        tth.padding();
        tth.display();
    }
}