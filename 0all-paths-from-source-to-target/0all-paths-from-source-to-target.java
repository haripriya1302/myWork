class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = graph.length;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        backtrack(0, ans, graph, list, n-1);
        return ans;
    }

    public void backtrack(int node, List<List<Integer>> ans, int[][] graph, List<Integer> curr, int endNode){

        if(curr.contains(endNode)) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for(int i : graph[node]){
            curr.add(i);
            backtrack(i, ans, graph, curr, endNode);
            curr.remove(curr.size()-1);
        }
    }
}