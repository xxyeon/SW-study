class Solution {
    /**
     startday일때 토요일,일요일이 되는 index를 찾아야함.
     희망시각이 i시 50~59분일때 인정시각 다시 계산해야함.
     */
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length; // 직원 수
        int answer = n; //
        startday--;
        for(int i = 0; i < n; i ++){
            int t = schedules[i];// 출근 희망 시간
            int[] logs = timelogs[i]; // 주간 출근기록
            int limit = schedules[i] % 100 + 10 >= 60 ? schedules[i] + 50 : schedules[i] + 10;
            for(int j = 0; j < 7 ; j ++){
                if((startday + j) % 7 == 5 || (startday + j) % 7 == 6) continue; // 토요일,일요일이면 생략
                if(limit < timelogs[i][j]){
                    answer--;
                    break;
                }

            }
        }
        return answer;
    }
}