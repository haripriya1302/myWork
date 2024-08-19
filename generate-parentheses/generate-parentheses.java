class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        backtrack(new StringBuilder(), 0, 0, n, answer);
        return answer;
    }

    public void backtrack(StringBuilder curr, int left, int right, int n, List<String> answer) {
        if(curr.length() == 2*n) {
            answer.add(curr.toString());
            return;
        }

        if(left < n) { 
            curr.append('(');
            backtrack(curr, left+1, right, n, answer);
            curr.deleteCharAt(curr.length()-1);
        }

        if(left>right) {
            curr.append(')');
            backtrack(curr, left, right+1, n, answer);
            curr.deleteCharAt(curr.length()-1);
        }
    }
}