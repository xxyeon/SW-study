package week2;

import java.util.*;

public class ServerInstall {
    class Server{
        int time;
        int etime;

        public boolean end(int s){
            return (etime <= s); //현재 종료시간보다 빠른지 여부 판단
        }

        public Server(int t,int k){
            this.time = t;
            this.etime = t+k;
        }
    }

    public int solution(int[] players, int m, int k) {
        int answer = 0;

        List<Server>sl = new ArrayList<>();
        for(int i = 0; i < players.length; i ++){
            int stime = i; // 시작시간
            int p = players[i]; // 이용자수
            int needs = p/m;// 필요한 서버 증설 수
            if(needs == 0) continue;
            int leftServer = leftServers(sl,stime); // 현재 시간 기준으로 남을 서버 대수
            int avail = p / leftServer;
            if(needs > 0) { // 증설이 필요한 경우
                answer += needs; // 누적 증설 대수
                for(int e = 0; e < needs; e ++){
                    sl.add(new Server(stime,k));
                }
            }
        }
        return answer;
    }

    public int leftServers(List<Server> sl, int stime) {
        Iterator<Server> it = sl.iterator();
        while (it.hasNext()) {
            Server s = it.next();
            if (s.end(stime)) {
                it.remove();
            }
        }
        return sl.size();
    }
}
