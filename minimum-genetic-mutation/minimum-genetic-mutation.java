class State {
    String node;
    int mutations;
    public State(String node, int mutations) {
        this.node = node;
        this.mutations = mutations;
    }
}
class Solution {
    char[] mutations = {'A', 'C', 'G', 'T'};
    HashSet<String> seen;
    public int minMutation(String startGene, String endGene, String[] bank) {
        seen = new HashSet<>();
        HashSet<String> bankStrings = new HashSet<>();
        Queue<State> queue = new LinkedList<>();
        if(startGene.length() != 8 || endGene.length() != 8) {
            return -1;
        }
        for(String s : bank) {
            bankStrings.add(s);
        }
        queue.add(new State(startGene, 0));
        seen.add(startGene);

        while(!queue.isEmpty()) {
            State state = queue.poll();
            String node = state.node;
            int mutations = state.mutations;
            if(node.equals(endGene)) return mutations;
            for(String neighbor: getNeighbors(node)) {
                if(!seen.contains(neighbor) && bankStrings.contains(neighbor)) {
                    seen.add(neighbor);
                    queue.add(new State(neighbor, mutations+1));
                }
            }
        }
        return -1;
    }

    public List<String> getNeighbors(String node) {
        List<String> ans = new ArrayList<>();
        for(int i=0; i<node.length();i++) {
            for(char m : mutations) {
                ans.add(node.substring(0,i)+m+node.substring(i+1));
            }
        }
        return ans;
    }

}