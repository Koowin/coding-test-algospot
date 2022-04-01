import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] arr = {0, 8, 12};

        for (int i = 0; i < 12; i++) {
            int sum = 0;
            for(int j : arr){
                int dif = j - i;
                sum += dif * dif;
            }
            System.out.println(i + ": " + sum);
        }
    }
}
