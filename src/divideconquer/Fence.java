package divideconquer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Fence {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/divideconquer/fence");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            br.readLine();
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            MaxRectangleFinder mrf = new MaxRectangleFinder(input);
            System.out.println(mrf.getAnswer());
        }
    }

    static class MaxRectangleFinder {
        private final int[] fence;

        private MaxRectangleFinder(int[] fence) {
            this.fence = fence;
        }

        private int getAnswer(){
            return getMaxRectangleSize(0, fence.length);
        }

        private int getMaxRectangleSize(int lo, int hi){
            if((hi - lo) <= 1){
                if(hi == lo){
                    return 0;
                }
                return fence[lo];
            }

            int splitIndex = findMinIndex(lo, hi);
            int leftMax = getMaxRectangleSize(lo, splitIndex);
            int rightMax = getMaxRectangleSize(splitIndex+1, hi);
            int midMax = fence[splitIndex] * (hi - lo);
            int ret = Math.max(leftMax, rightMax);
            ret = Math.max(ret, midMax);

            return ret;
        }

        private int findMinIndex(int lo, int hi) {
            int minHeight = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = lo; i < hi; i++) {
                if(minHeight > fence[i]){
                    minHeight = fence[i];
                    minIndex = i;
                }
            }
            return minIndex;
        }
    }
}
