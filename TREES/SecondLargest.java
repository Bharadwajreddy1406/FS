
// In an Intelligence Agency, each senior officer supervises either two junior officers 
// or none. The senior officer is assigned a clearance level equal to the higher clearance 
// level of the two junior officers they supervise.

// The clearance levels are represented as integer values in the range [1, 50], and multiple 
// officers may have the same clearance level.

// At the end, all officers (senior and junior) are collectively referred to as agents in the system.

// You are provided with a hierarchical clearance level tree where each node represents 
// an officer's clearance level. The tree structure follows these rules:
// 	- If a node has two children, its clearance level is the maximum of the two children's
// 	  clearance levels.
// 	- If a node has no children, it's clearance level is same as exists.
// 	- The value -1 indicates an empty (null) position.
// Your task is to find the second highest clearance level among all agents in the agency. 
// If no such level exists, return -2.

// Input Format:
// -------------
// A single line of space separated integers, clearance levels of each individual.

// Output Format:
// --------------
// Print an integer, second top agent based on rank.


// Sample Input-1:
// ---------------
// 2 5 2 -1 -1 2 4

// Sample Output-1:
// ----------------
// 4


// Sample Input-2:
// ---------------
// 3 3 3 3 3

// Sample Output-2:
// ----------------
// -2



import java.util.*;

public class SecondLargest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i=1;
        while(i<s.length){
            TreeNode curr = q.poll();
            if(!s[i].equals("-1")){
                curr.left = new TreeNode(Integer.parseInt(s[i]));
                q.add(curr.left);
            }
            i++;
            if(i<s.length && !s[i].equals("-1")){
                curr.right = new TreeNode(Integer.parseInt(s[i]));
                q.add(curr.right);
            }
            i++;
        }
        Solution obj = new Solution();
        System.out.println(obj.secondHighest(root));
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



class Solution
{
    
    void helper(TreeNode root, int ele[]){
        
        if(root == null) return;
        
        if(ele[0] < root.val){
            ele[1] = ele[0];
            ele[0] = root.val;
        }
        
        if(root.val < ele[0] && root.val > ele[1]){
            ele[1] = root.val;
        }
        
        helper(root.left,ele);
        helper(root.right,ele);
    }
    
    
    public int secondHighest(TreeNode root) 
    {
        // Write your code and return the second top value. 
        // (return -2 if no second highest.)
        int[] ele = new int[]{-1,-2};
        helper(root,ele);
        
        return ele[1]>0 ? ele[1]:-2;
    }
}