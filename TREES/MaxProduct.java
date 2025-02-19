// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100




  
import java.util.*;

class Node{
    
    int data;
    Node left;
    Node right;
    
    Node(int data){
        this.data = data;
    }
}


public class MaxProduct{
    
    
    static Node buildTree(int [] arr) {
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        int index =1;
        q.offer(root);
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
    
    static int findsum(Node root){
        if(root == null) return 0;
        return root.data + findsum(root.left) + findsum(root.right);
    }
    
    static void func(Node root,HashSet<Integer> set ){
        
        if(root == null) return;
        
        set.add(findsum(root));
        
        func(root.left,set);
        func(root.right,set);
    }
    
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int arr[] = new int[strs.length];
        for(int i=0;i<strs.length;i++) arr[i] = Integer.parseInt(strs[i]);
        
        Node root = buildTree(arr);
        
        int sum = findsum(root);
        
        HashSet<Integer> set = new HashSet<>();
        func(root,set);
        sc.close();
        int max=Integer.MIN_VALUE;
        for (int x : set){
            max = Math.max(max , x * (sum -x));
        }
        System.out.println(max);
    }
}