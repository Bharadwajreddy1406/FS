import java.util.*;

/*
For reference purpose only.

class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}

*/
public class Solution {
	public TreeNode upsideDownBinaryTree(TreeNode root) {
        //Write your logic here
        if(root == null) return root;
        if(root.left == null && root.right == null) return root;
        
        TreeNode flipped = upsideDownBinaryTree(root.left);
        
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        
        return flipped;
	}
    
    
}