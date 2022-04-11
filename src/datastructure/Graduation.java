package datastructure;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Graduation {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/datastructure/graduation");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            GraduationSimulation simulation = new GraduationSimulation(info[0], info[1], info[2], info[3]);

            for (int j = 0; j < info[0]; j++) {
                int[] preCourseInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                simulation.addPreCourse(j, preCourseInfo);
            }

            for (int j = 0; j < info[2]; j++) {
                int[] openCourseInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                simulation.addOpenCourse(j, openCourseInfo);
            }


        }
    }

    static class GraduationSimulation {
        private final int totalCourseCount;
        private int remainCourseCount;
        private int[] preCourse;
        private int[] openCourse;
        private final int courseLimit;

        private GraduationSimulation(int N, int K, int M, int L) {
            totalCourseCount = N;
            preCourse = new int[N];
            remainCourseCount = K;
            openCourse = new int[M];
            courseLimit = L;
        }

        private void addPreCourse(int index, int[] preCourseInfo) {
            for (int i = 1; i < preCourseInfo.length; i++) {
                preCourse[index] |= 1 << preCourseInfo[i];
            }
        }

        private void addOpenCourse(int index, int[] openCourseInfo) {
            for (int i = 0; i < openCourseInfo.length; i++) {
                openCourse[index] |= 1 << openCourseInfo[i];
            }
        }

        private int enrollSemester(int semester, int clearedCourse, int count) {
            int c = Integer.bitCount(clearedCourse);
            if (c >= remainCourseCount) {
                return c;
            }
            if (count == openCourse.length) {
                return Integer.MAX_VALUE;
            }

            List<Integer> candidateCourse = getCandidateCourse(semester, clearedCourse);
            int ret = Integer.MAX_VALUE;
            for (int i : candidateCourse) {
                ret = Math.min(ret, enrollSemester(semester + 1, clearedCourse | i, count + 1));
            }
            return ret;
        }

        private List<Integer> getCandidateCourse(int semester, int clearedCourse) {
            List<Integer> ret = new ArrayList<>();

            return null;
        }

        private boolean canEnrol(int clearedCourse, int index) {
            return (preCourse[index] & clearedCourse) == preCourse[index];
        }
    }
}