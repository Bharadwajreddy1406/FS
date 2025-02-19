// VishnuVardan is working with Decision Trees for AI-based predictions.
// To analyze alternative outcomes, Kishore has planned to flip the decision 
// tree horizontally to simulate a reverse processing approach.

// Rules for Flipping the Decision Tree:
// 	- The original root node becomes the new rightmost node.
// 	- The original left child becomes the new root node.
// 	- The original right child becomes the new left child.
// This transformation is applied level by level recursively.

// Note:
// ------
// - Each node in the given tree has either 0 or 2 children.
// - Every right node in the tree has a left sibling sharing the same parent.
// - Every right node has no further children (i.e., they are leaf nodes).

// Your task is to help VishnuVardan flip the Decision Tree while following 
// the given transformation rules.

// Input Format:
// -------------
// Space separated integers, nodes of the tree.

// Output Format:
// --------------
// Print the list of nodes of flipped tree as described below.


// Sample Input-1:
// ---------------
// 4 2 3 5 1

// Sample Output-1:
// ----------------
// 5 1 2 3 4


// Sample Input-2:
// ---------------
// 4 2 5

// Sample Output-2:
// ----------------
// 2 5 4



import java.util.*;


public class UpsideDownFlipTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] vals = sc.nextLine().split(" ");
        int arr[] = new int[vals.length];
        for(int i=0;i<vals.length;i++){
            arr[i] = Integer.parseInt(vals[i]);
        }
        
        TreeNode root = buildTree(arr);
        TreeNode answer = new Solution().upsideDownBinaryTree(root);
        printlevelOrder(answer);
    }
    
    static TreeNode buildTree(int[] arr){
        if(arr.length == 0) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i=1;
        while(i<arr.length){
            TreeNode node = q.poll();
            if(i<arr.length){
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
                i++;
            }
            if(i<arr.length){
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
                i++;
            }
        }
        return root;
    }
    
    static void printlevelOrder(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){

            TreeNode node = q.poll();
            System.out.print(node.val+" ");
            if(node.left !=null){
                q.offer(node.left);
            }
            if(node.right !=null){
                q.offer(node.right);
            }
        }
    }

}



class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}


class Solution {
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
    
    static void func(TreeNode root){
        if(root == null) return;
        func(root.left);
        func(root.right);
        System.out.print(root.val+" ");
    }
        
        
    static TreeNode flip(TreeNode root){
        if(root == null) return root;
        if(root.left == null && root.right == null) return root;
        
        TreeNode flipped = flip(root.left);
        
        root.left.left = root.right;
        root.right = root;
        root.left = null;
        root.right = null;
        
        return flipped;
    }
    
    static void printlevelOrder(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){

            TreeNode node = q.poll();
            System.out.print(node.val+" ");
            if(node.left !=null){
                q.offer(node.left);
            }
            if(node.right !=null){
                q.offer(node.right);
            }
        }
    }
    
    // public static void main (String[] args) {


    //     Scanner sc = new Scanner(System.in);
    //     String[] vals = sc.nextLine().split(" ");
    //     int arr[] = new int[vals.length];
    //     for(int i=0;i<vals.length;i++){
    //         arr[i] = Integer.parseInt(vals[i]);
    //     }
        
    //     TreeNode root = buildTree(arr);
    //     func(root);
    //     TreeNode answer = flip(root);
    //     printlevelOrder(root);
    // }      
}