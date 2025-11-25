package week2;

import java.util.*;

public class Box {
    public static void main(String[] args) {
        Box m = new Box();
        int r1 = m.solution(22, 6, 8);
        int r2 = m.solution(12, 3, 6);
        int r3 = m.solution(37, 8, 1);
        System.out.println("테스트 케이스 3 결과: " + r3); // 3

        List<String>ss = new ArrayList<>();
    }

    public int solution(int n, int w, int num) {
        int answer = 0;
        int ns = num/w + (num % w > 0 ? 1 : 0); // num의 층수

        int d = (w*ns) - num;
        int ni = (ns % 2 == 0) ? d+1 : w-d ; // 층의 인덱스

        int curNum = num; // 윗층 숫자
        int curS = ns; //현재 층

        while(curNum < n){
            int nextS = curS + 1; // 다음 층
            boolean odd = nextS % 2 > 0; // 다음층은 홀수? 짝수?
            int nextMax = nextS * w; // 다음 층수의 최대수
            int nextNum = odd ?
                    nextMax - w + ni
                    : nextMax - ni + 1;
            if(nextNum > n){
                break;
            }

            curNum = nextNum;
            curS ++;
            answer ++;
        }
        return answer + 1;
    }
}