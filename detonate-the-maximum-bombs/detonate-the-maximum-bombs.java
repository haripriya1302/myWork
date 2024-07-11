class Solution {
    HashMap<Integer, List<Integer>> graph;
    public int maximumDetonation(int[][] bombs) {
        graph = new HashMap<>();
        int n = bombs.length;
        int max= Integer.MIN_VALUE;
        for(int i =0; i<n; i++) {
            graph.put(i, new ArrayList());
        }
        for(int key : graph.keySet()) {
            
            int x = bombs[key][0];
            int y = bombs[key][1];
            double rsquare = Math.pow(bombs[key][2], 2);
            for(int i = 0; i<bombs.length; i++) {
                double dissquare = Math.pow(x - bombs[i][0], 2) + Math.pow(y - bombs[i][1], 2); 
                
                if(key != i && rsquare >= dissquare) {
                    List<Integer> val = graph.get(key);
                    val.add(i);
                    graph.put(key, val);
                }
            }
        }
        for(int i=0; i<n; i++) {
            HashSet<Integer> seen = new HashSet<>();
            seen.add(i);
            max = Math.max(max, dfs(i, bombs, seen));
        }
        return max;
    }

    


    public int dfs(int i, int[][] bombs, HashSet<Integer> seen) {
        int count = 1;
        for(Integer neighbor : graph.get(i)) {
            if(!seen.contains(neighbor)) {
                seen.add(neighbor);
                count += dfs(neighbor, bombs, seen);
            }
            
        }
        return count;
    }
}

   