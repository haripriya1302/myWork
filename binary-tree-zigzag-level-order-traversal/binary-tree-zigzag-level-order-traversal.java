/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        queue.add(root);
        int level = 1;
        while(!queue.isEmpty()) {
            int length = queue.size();
            List<Integer> ans = new ArrayList<>();
            Stack<TreeNode> stack = new Stack();
            for(int i=0; i<length; i++){
                TreeNode node = queue.poll();
                if(level%2 == 0) {
                    stack.add(node);
                } else {
                    ans.add(node.val);
                }
                
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
            
            while(!stack.isEmpty()){
                ans.add(stack.pop().val);    
            }
            level++;
            res.add(ans);
        }
        return res;
    }
}