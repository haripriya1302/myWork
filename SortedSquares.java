class Solution {
    public int[] sortedSquares(int[] nums) {
        int N = nums.length;
        int i= 0;
        int j= N-1;
        int r = N-1;
        int[] squares = new int[N];
        while(i<=j) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])){
               squares[r] = nums[i] * nums[i];
               i++;
            } else {
                squares[r] = nums[j] * nums[j];
                j--;
            } 
            r--;
        }
       return squares;
    }
}
