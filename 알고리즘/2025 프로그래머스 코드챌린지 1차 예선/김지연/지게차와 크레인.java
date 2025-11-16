import java.util.*;

class Solution {
    boolean[][] isAccessible;
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    int n, m;
    char[][] copyStorage;

    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        n = storage.length;
        m = storage[0].length();
        isAccessible = new boolean[n][m];
        
        copyStorage = new char[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                copyStorage[i][j] = storage[i].charAt(j);
            }
            
        }
        //초기 접근 가능한 컨테이너
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(i == 0 || j == 0|| i == n-1 || j == m-1) {
                    isAccessible[i][j] = true;  
                }                
            }
        }
    
        //requests 수 만큼 dfs 실행
        for(int i=0;i<requests.length;i++) {
            
            String order = requests[i];
            char target = order.charAt(0);
            Queue<int[]> q = new LinkedList<>();
            if(order.length() == 1) {
                //지게차
                for(int x=0;x<n;x++) {
                    for(int y=0;y<m;y++) {
                        if(target == copyStorage[x][y] && isAccessible[x][y]) {
                            //접근 가능한 컨테이너위치라면 제거 표시
                            copyStorage[x][y] = '.';
                            // dfs(x, y); //제거 후 접근 가능한 컨테이너 업데이트
                        }
                    }
                }

               
            } else {
                //크레인
                //삭제할 위치
                for(int x=0;x<n;x++) {
                    for(int y=0;y<m;y++) {
                        if(target == copyStorage[x][y]) {
                            //창고에서 일치하는 컨테이너 모두 제거
                            copyStorage[x][y] = '.';
                            
                        }
                    }
                }
                
            }
            //컨테이너 제거 후 접근 가능한 컨테이너 탐색
            dfs();
        }
        
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(copyStorage[i][j] != '.') {
                    answer++;
                }
            }
        }
  
        return answer;
    }
    private void dfs() {

        Queue<int[]> q = new LinkedList<>();
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(copyStorage[i][j] == '.' && isAccessible[i][j]) {
                    //접근 가능한 컨테이너가 삭제 되었다면
                    q.offer(new int[]{i, j});
                }
            }

        }

        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int d=0; d<4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(copyStorage[nx][ny] == '.' && !isAccessible[nx][ny]) {
                    // 빈 공간 확장
                    isAccessible[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                } else if(copyStorage[nx][ny] != '.') {
                    // 컨테이너는 인접한 빈 공간이 있으면 접근 가능 처리
                    isAccessible[nx][ny] = true;
                }
            }
        }
    }


}