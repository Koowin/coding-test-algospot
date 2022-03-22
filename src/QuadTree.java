import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadTree {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/quadTree");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            String input = br.readLine();
            if (input.length() == 1) {
                System.out.println(input);
            } else {
                Flipper flipper = new Flipper(input);
                System.out.println(flipper.getFlippedString());
            }
        }
    }

    static class Flipper {
        private StringBuilder sb;
        private int index = 1;

        private Flipper(String str) {
            sb = new StringBuilder(str);
        }

        private String getFlippedString() {
            searchFlipLocation();
            return sb.toString();
        }

        private void searchFlipLocation(){
            int lo = index;
            int mid = 0;
            for(int i=0;i<4;i++){
                if(sb.charAt(index++) == 'x'){
                    searchFlipLocation();
                }
                if(i == 1){
                    mid = index;
                }
            }
            int hi = index;
            flip(lo, mid, hi);
        }

        private void flip(int lo, int mid, int hi) {
            String replacer = sb.substring(mid, hi);
            replacer += sb.substring(lo, mid);
            sb.replace(lo, hi, replacer);
        }
    }
}
