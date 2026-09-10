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
    int matchingNodes = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchingNodes;
    }

  
    private long dfs(TreeNode node) {
        if (node == null) {
            return 0L;
        }

        long left = dfs(node.left);
        long right = dfs(node.right);

        int sum = node.val + (int)(left >>> 32) + (int)(right >>> 32);
        
        int count = 1 + (int)left + (int)right;

        if (node.val == sum / count) {
            matchingNodes++;
        }

       
        return ((long) sum << 32) | count;
    }
}