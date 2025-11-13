class Container {
    public static void main(String[] args) {

        // 테스트 케이스 목록
        String[][] storageList = {
                {"AZWQY", "CAABX", "BBDDA", "ACACA"},
                {"HAH", "HBH", "HHH", "HAH", "HBH"}
        };

        String[][] requestsList = {
                {"A", "BB", "A"},
                {"C", "B", "B", "B", "B", "H"}
        };

        Container m = new Container(); // solution 호출을 위한 인스턴스 생성

        for (int i = 0; i < storageList.length; i++) {
            int r = m.solution(storageList[i], requestsList[i]);
            System.out.println("테스트 케이스 " + (i + 1) + " 결과: " + r);
        }
    }
    /**
     남은 컨테이너 수를 return하라.
     남은 컨테이너 수는 모든 요청을 순서대로 완료하고 남은 컨테이너 이다.
     요청 작업은 아래와 같다.
     컨테이너는 접근이 가능한 컨테이너만 꺼낼 수 있고
     접근 가능한 컨테이너는 적어도 1면이 외부 컨테이너와 연결되어 있어야 한다.
     크레인을 사용하면 모든 컨테이너를 꺼낼 수 있다.
     크레인을 사용하는 요청은 알파벳이 "BB"처럼 연속으로 요청이 들어와야 하고
     일반 요청은 외부와 연결된 컨테이너만 접근 가능하다.
     컨테이너 구조는 세로 n줄 가로 m줄 총 n*m 크기이다.

     접근방법
     1. 각 요소가 외부와 연결되어있는지 판단할 수 있어야 한다.
     2. 각 컨테이너의 4면의 상태를 저장할 수 있는 컬렉션이 필요할 듯
     3. 외부와 연결 되어있는지 확인 하는 방법
     - storage[i][j]의 4방
     동 : storage[i][j+1]
     서 : storage[i][j-1]
     남 : storage[i-1][j]
     북 : storage[i+1][j]
     -
     */
    public int solution(String[] storage, String[] requests) {
        int x = storage[0].length();
        int y = storage.length;
        int answer = x*y;
        char[][] space = new char[y][x];
        for(int i = 0; i < y; i ++){
            for(int j = 0; j < x; j ++){
                space[i][j] = storage[i].charAt(j);
            }
        }

        for(int q = 0 ; q < requests.length; q ++){
            String rq = requests[q];
            for(int i = 0; i < y; i ++){
                for(int j = 0; j < x; j ++){
                    if(space[i][j] == '0') continue;
                    if(rq.length() < 2){
                        char cur = space[i][j];
                        if(edge(i,j,x,y)){
                            boolean eq = rq.equals(String.valueOf(space[i][j]));
                            if(eq){
                                answer--;
                                space[i][j] = '0';
                            }
                            continue;
                        }

                        try {
                            char s = space[i-1][j];
                            char n = space[i+1][j];
                            char e = space[i][j+1];
                            char w = space[i][j-1];
                            // 아직 미해결..
                            if(canout(s,n,e,w)){
                                boolean eq = rq.equals(String.valueOf(space[i][j]));
                                if(eq){
                                    answer--;
                                    space[i][j] = '0';
                                }
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            System.out.println("Index out of bounds for i: " + i + ", j: " + j);
                        }
                    }else{
                        char elem = rq.charAt(0);
                        boolean eq = elem == space[i][j];
                        if(eq){
                            answer--;
                            space[i][j] = '0';
                        }
                    }
                }
            }
        }
        return answer;
    }
    public boolean canout(char s,char n,char e,char w){
        return (s == '0' || n == '0' || e == '0' || w == '0');
    }
    public boolean block(char cur,char i,char j,char e){
        return (cur == i || cur == j || cur == e);
    }
    public boolean edge(int i, int j,int x, int y){
        return (i == 0 || i == (y-1) || j == 0 || j == (x-1));
    }
}