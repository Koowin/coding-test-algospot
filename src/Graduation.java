import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Graduation {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/graduation");
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
            if (semester == openCourse.length) {
                return count;
            }
            int ret = Integer.MAX_VALUE;
            List<Integer> canEnrolList = new ArrayList<>();
            for (int i = 0; i < totalCourseCount; i++) {
                if ((clearedCourse & 1 << i) == 0 && canEnrol(clearedCourse, i)) {
                    canEnrolList.add(i);
                }
            }
            if (canEnrolList.isEmpty()) {
                ret = enrollSemester(semester + 1, clearedCourse, count);
            } else if (canEnrolList.size() <= courseLimit) {
                for (Integer i : canEnrolList) {
                    clearedCourse |= 1 << i;
                }
                ret = enrollSemester(semester + 1, clearedCourse, count + 1);
            } else {

            }

            return ret;
        }

        private List<Integer> makeClearedCourse(List<Integer> candidateCourse, int clearedCourse) {
            return null;
        }

        private boolean canEnrol(int clearedCourse, int index) {
            return (preCourse[index] & clearedCourse) == preCourse[index];
        }
    }
}