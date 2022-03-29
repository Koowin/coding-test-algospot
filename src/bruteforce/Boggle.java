package bruteforce;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boggle {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/bruteforce/boggle");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");
            int m = Integer.parseInt(arr[0]);
            for(int j=0;j<m;j++){
                System.out.println(br.readLine());
            }
        }
    }
}
