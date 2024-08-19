class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> answer = new ArrayList<>();
        for(int i=1; i<=9; i++){
            backtrack(answer, n, k, i);
        }
        
        int[] res = new int[answer.size()];
        for(int i=0; i< answer.size(); i++) {
            res[i] = answer.get(i);
        }
        return res;
    }

    public void backtrack(List<Integer> answer, int n, int k, int curr){
        if(String.valueOf(curr).length() == n) {
            answer.add(curr);
            return;
        }

        for(int i=0; i<=9; i++) {
            if(String.valueOf(curr).length()<n && Math.abs(curr%10 - i) == k) {
                curr = curr*10+i;
                backtrack(answer, n, k, curr);
                curr = (curr-i)/10;
            }
        }
    }
}