package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Numb3rs {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/numb3rs");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            int[] cityInfos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            CityMap cityMap = new CityMap(cityInfos[0], cityInfos[1], cityInfos[2]);

            for (int j = 0; j < cityInfos[0]; j++) {
                int[] roads = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                cityMap.addRoad(j, roads);
            }
            br.readLine();
            int[] villages = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int v : villages) {
                cityMap.printPercentage(v);
            }
            System.out.println("\b");
        }
    }

    private static class CityMap {
        private final int villageCount;
        private final int daysAfterEscape;
        private List<Integer>[] roads;
        private double[][] cache;

        public CityMap(int villageCount, int daysAfterEscape, int prisonIndex) {
            this.villageCount = villageCount;
            this.daysAfterEscape = daysAfterEscape;
            roads = new List[villageCount];
            for (int i = 0; i < villageCount; i++) {
                roads[i] = new ArrayList<Integer>(villageCount);
            }

            cache = new double[villageCount][daysAfterEscape + 1];
            for (double[] arr : cache) {
                Arrays.fill(arr, -0.5);
            }

            initPrisonLocation(prisonIndex);
        }

        private void initPrisonLocation(int prisonIndex) {
            for (int i = 0; i < villageCount; i++) {
                cache[i][0] = 0;
            }
            cache[prisonIndex][0] = 1;
        }

        private void addRoad(int n, int[] roads) {
            for (int i = 0; i < villageCount; i++) {
                if (roads[i] == 1) {
                    this.roads[n].add(i);
                }
            }
        }

        private void printPercentage(int v) {
            System.out.print(getPercentage(v, daysAfterEscape));
            System.out.print(" ");
        }

        private double getPercentage(int n, int day) {
            if (cache[n][day] > -0.5) {
                return cache[n][day];
            }

            double ret = 0;

            for (int i : roads[n]) {
                ret += getPercentage(i, day - 1) / roads[i].size();
            }
            cache[n][day] = ret;
            return ret;
        }
    }
}
