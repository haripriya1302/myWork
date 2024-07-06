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
    public int closestValue(TreeNode root, double target) {
        Queue<TreeNode> queue = new LinkedList<>();
        double ans = Double.MAX_VALUE;
        int res = root.val;
        queue.add(root);
        while(!queue.isEmpty()) {
            int length = queue.size();
            for(int i=0; i<length; i++) {
                TreeNode node = queue.poll();
                double diff = Math.abs(target - (double) node.val);
                if(diff < ans|| (diff == ans && res > node.val)) {
                    ans = Math.min(ans, diff);
                    res = node.val;
                }
                
                if(node.left!=null) {queue.add(node.left);}
                if(node.right!=null) {queue.add(node.right);}
            }
        }
        return res;
    }
}