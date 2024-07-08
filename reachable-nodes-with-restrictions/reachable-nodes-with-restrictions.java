class Solution {
    HashMap<Integer, List<Integer>> graph;
    HashSet<Integer> seen;
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        graph = new HashMap<>();
        for(int i=0; i<n;i++){
            graph.put(i, new ArrayList<>());
        }
        HashSet<Integer> res = new HashSet<>();
        for(int i : restricted) {
            res.add(i);
        }
        seen = new HashSet<>();

        for(int[] edge: edges) {
            int x = edge[0];
            int y = edge[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        seen.add(0);
        return dfs(0, res);
    }

    public int dfs(int node, HashSet<Integer> restricted) {
        int ans = 1;
            for(int neighbor : graph.get(node)) {
                if(!restricted.contains(neighbor) && !seen.contains(neighbor)) {
                    seen.add(neighbor);
                    ans += dfs(neighbor, restricted);     
                }  
            }
        
        return ans;
    }
}