package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrianglePath {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/trianglePath");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
    }
}
