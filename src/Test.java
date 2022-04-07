import java.util.*;

public class Test {
    public static void main(String[] args) {
        double[][] test = new double[3][3];

        for (double[] t : test) {
            Arrays.fill(t, -1);
        }

        for (double[] t : test) {
            System.out.println(Arrays.toString(t));
        }
    }
}
