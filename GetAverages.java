class Solution {
    public int[] getAverages(int[] nums, int k) {
        int N = nums.length;
        int[] result = new int[N];
        int r = 2*k+1;
        for(int i=0; i< N; i++) {
            int iterator = k;
            if (i+k > N-1 || i-k < 0) {
                result[i] = -1;
            } else {
                int sum = nums[i];
                while (iterator>0) {
                    sum += nums[i+iterator]+nums[i-iterator];
                    iterator--;
                }
                result[i] = sum/r;
            }            
        }
        return result;
    }
}
