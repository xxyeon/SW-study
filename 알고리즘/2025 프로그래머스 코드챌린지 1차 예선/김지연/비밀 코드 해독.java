import java.util.*;
class Solution {
    //5개의 정수 오름차순인 비밀코드 맞히기
    //m번시도. 각 시도마다 시도에 사용된 정수 몇개가 비밀코드에 포함되어 있는지 알려준다.
    //비밀코드로 가능한 정수 조합의 개수를 알고 싶음.
    //시스템이 알려준 정수의 개수를 기반으로 선택된 정수를 골라내야함.
    //오름차순으로 정렬되어 있음, 비밀키도 오름차순. 시스템 응답이 제일 큰게 범위가 제일 큼
    ArrayList<boolean[]> comb = new ArrayList<>(); //조합들
    Set<Integer> integers = new HashSet<>();
    ArrayList<Integer> arr = new ArrayList<>(5);
    boolean[] isSelected;
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        combination(arr, 0,n,0);
        isSelected = new boolean[n+1];
        for(boolean[] c : comb) {
            int a = 5; //비밀키 개수
            
            boolean isPossible = true;
            for(int i = 0; i<q.length; i++) {
                int check = 0;
                for(int j = 0; j<5; j++) {
                    //현재 조합이 입력한 정수 i번째(q[i])가 시스템 응답과 일치하는지 확인
                    if(c[q[i][j]]) {
                        if(!isSelected[q[i][j]]) {
                            a--;
                            isSelected[q[i][j]] = true;
                        }
                        check++;
                    }
                    if(a < 0) {
                        isPossible = false;
                        break;
                    }
                }
                
                if(check != ans[i]) {
                    isPossible = false;
                        break;
                }
            }
            if(isPossible) {
                answer++;
            }
        }
        return answer;
    }
    
    private void combination(ArrayList<Integer> arr, int depth, int n, int start) {
        if(depth == 5) {
            
            boolean[] temp = new boolean[n+1];
            for(int num : arr) {
                temp[num] = true;
            }
            comb.add(temp);
            return;
        }
        for(int i=start+1; i<=n;i++) {
            arr.add(i);
            combination(arr, depth+1, n, i);
            arr.remove(arr.size()-1);
        }
    }
    
}
