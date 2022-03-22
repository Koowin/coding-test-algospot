import java.io.*;

public class Picnic {
    public void solution() throws IOException{
        FileInputStream fileInputStream = new FileInputStream("./input/picnic");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for(int i=0;i<caseCount;i++){
            String[] arr = br.readLine().split(" ");
            int studentNum = Integer.parseInt(arr[0]);

            PairFinder pairFinder = new PairFinder(studentNum);

            int friendPairCount = Integer.parseInt(arr[1]);
            arr = br.readLine().split(" ");
            for(int j=0;j<friendPairCount*2;j+=2){
                pairFinder.addFriend(Integer.parseInt(arr[j]), Integer.parseInt(arr[j+1]));
            }

            System.out.println(pairFinder.getAnswer());
        }
    }

    static class PairFinder{
        private final int studentNum;
        private boolean[][] isFriend;

        private PairFinder(int studentNum){
            this.studentNum = studentNum;
            isFriend = new boolean[studentNum][studentNum];
        }

        private void addFriend(int a, int b){
            isFriend[a][b] = true;
            isFriend[b][a] = true;
        }

        private int getAnswer(){
            boolean[] isMated = new boolean[studentNum];
            return dfs(0, isMated);
        }

        private int dfs(int matedCount, boolean[] isMated){
            if(matedCount == studentNum){
                return 1;
            }
            int count = 0;
            int i=0;
            for(;i<studentNum;i++){
                if(!isMated[i]){
                    break;
                }
            }
            isMated[i] = true;
            for (int j = i+1; j < studentNum; j++) {
                if(!isMated[j] && isFriend[i][j]){
                    isMated[j] = true;
                    count += dfs(matedCount+2, isMated);
                    isMated[j] = false;
                }
            }
            isMated[i] = false;

            return count;
        }

        private void print(){
            for(int i=0;i<studentNum;i++){
                for(int j=0;j<studentNum;j++){
                    if(isFriend[i][j]){
                        System.out.print("1 ");
                    }
                    else{
                        System.out.print("0 ");
                    }
                }
                System.out.println();
            }
        }
    }
}
