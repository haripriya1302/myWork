class State {
    int row;
    int col;
    int steps;
    public State(int row, int col, int steps) {
        this.row = row;
        this.col = col;
        this.steps = steps;
    }
}
class Solution {
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    Queue<State> queue;
    int m;
    int n;
    boolean[][] seen;
    public int nearestExit(char[][] maze, int[] entrance) {
        queue = new LinkedList<>();
        m = maze.length;
        n = maze[0].length;
        seen = new boolean[m][n];
        int startx = entrance[0];
        int starty = entrance[1];
        seen[startx][starty] = true;
        queue.add(new State(startx, starty, 0));

        while(!queue.isEmpty()){
            State state = queue.poll();
            int row = state.row;
            int col = state.col;
            int steps = state.steps;
            if(((row == 0  || row == m-1) && ( row !=startx || col != starty) )|| ((col == 0  || col == n-1) && ( row !=startx || col != starty) )) {
                return steps;
            }
            for(int[] direction: directions) {
                int nextrow = row + direction[0];
                int nextcol = col + direction[1];
                if(valid(nextrow, nextcol, maze) && !seen[nextrow][nextcol]) {
                    seen[nextrow][nextcol] = true;
                    queue.add(new State(nextrow, nextcol, steps+1));
                } 
            }
        }
        return -1;
    }

    public boolean valid(int x, int y, char[][] maze) {
        return x>=0 && y>=0 && x<m && y<n && maze[x][y] == '.';
    }
}