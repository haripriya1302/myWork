class Solution {
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    boolean[][] seen;
    int m;
    int n;
    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;
        seen = new boolean[m][n];
        for(int i = 0; i<m;i++) {
            for(int j=0;j<n;j++) {
                if(!seen[i][j] && grid[i][j] == 1) {
                    ans = Math.max(ans, dfs(i, j, grid));
                }
            }
        }
        return ans;
    }   
    
    public boolean isValid(int i, int j) {
        return i>=0 && i<m && j>=0 && j<n;
    }

    public int dfs(int i, int j, int[][] grid) {
        seen[i][j] = true;
        int area = 1;
        for(int[] direction : directions) {
            int nexti = i + direction[0];
            int nextj = j + direction[1];
            if( isValid(nexti, nextj) && !seen[nexti][nextj] && grid[nexti][nextj] == 1) {
                area += dfs(nexti, nextj, grid);
            }
        }
        return area;
    }
}