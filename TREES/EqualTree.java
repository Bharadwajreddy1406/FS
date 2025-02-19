// Balbir Singh is working with networked systems, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
// a certain processing load (integer value).

// Balbir has been given a critical task: split the network into two balanced 
// sub-networks by removing only one connection (edge). The total processing 
// load in both resulting sub-networks must be equal after the split.

// Network Structure:
// - The network of servers follows a binary tree structure.
// - Each server is represented by an integer value, indicating its processing load.
// - If a server does not exist at a particular position, it is represented as '-1' (NULL).
	
// Help Balbir Singh determine if it is possible to split the network into two equal 
// processing load sub-networks by removing exactly one connection.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 7

// Sample Output-2:
// ----------------
// false

import java.util.*;

class Node{
    
    int data;
    Node left;
    Node right;
    
    Node(int data){
        this.data = data;
    }
    
}

public class EqualTree{
    
    
    static Node buildTree(int[] arr){
        
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int index =1;
        while(index < arr.length){
            Node node = q.poll();
            if(arr[index]!=-1){
                node.left = new Node(arr[index]);
                q.offer(node.left);
            }
            index++;
            if(arr[index]!=-1){
                node.right = new Node(arr[index]);
                q.offer(node.right);
            }
            index++;
        }
        
        return root;
    }
    
    static boolean func(Node root, int n){
        if(root == null) return false;
        
        int sumHere = findsum(root);
        
        if(sumHere == n- sumHere){
            return true;
        }
        
        return func(root.left,n) || func(root.right,n);
        
    }
    
    static int findsum(Node root){
        if(root == null) return 0;
        
        return findsum(root.left) + findsum(root.right) + root.data;
    }
    
    public static void main (String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String[] vals = sc.nextLine().split(" ");
        
        int [] arr = new int[vals.length];
        sc.close();
        for(int i=0;i<vals.length;i++) arr[i] = Integer.parseInt(vals[i]);
        
        Node root =  buildTree(arr);
        int treesum = findsum(root);
        System.out.println(func(root.left,treesum) || func(root.right,treesum));
        
    }
}

