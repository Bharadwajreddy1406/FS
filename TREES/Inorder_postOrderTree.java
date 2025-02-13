// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7


import java.util.*;

class Node{
    
    Node left = null;
    Node right = null;
    int data;
    
    Node(int d){
        this.data = d;
    }
}


public class Inorder_postOrderTree{
    

    static Node buildTree(int inorder [], Stack<Integer> s,  int start, int end,HashMap<Integer,Integer> map ){
        if(start > end) return null;
        int curr = s.peek();
        s.pop();
        Node root = new Node(curr);
        if(start == end ) return root;
        int pos = map.get(curr);
        
        root.right = buildTree(inorder,s, pos+1,end,map);
        root.left = buildTree(inorder, s,start,pos-1,map);
        return root;

    }
        
    
    static List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.data);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            levels.add(level);
        }
        return levels;
    }
    
    static void Inorder(Node root){
        
        if(root == null){
            return;
        }
        
        Inorder(root.left);
        System.out.print(root.data +"  ");
        Inorder(root.right);
        
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // int ino[] = {4,2,5,1,6,3,7};
        // int post[] = {4,5,2,6,7,3,1};
        int ino[] = new int[n];
        // int post[] = new int[n];
        Stack<Integer> s = new Stack<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){ 
            ino[i] = sc.nextInt();
            map.put(ino[i],i);
        }
        for(int i=0;i<n;i++) s.push(sc.nextInt());
        
        Node root = buildTree(ino,s,0,n-1,map);
        
        int q=sc.nextInt();
        sc.close();
        List<List<Integer>> ans = new ArrayList<>();
        ans = levelOrder(root);
        
        List<List<Integer>> result = new ArrayList<>();
        
        for(int i=0;i<q;i++){
            int l=sc.nextInt();
            int r=sc.nextInt();
            List<Integer> res = new ArrayList<>();
            for(int j=l;j<=r;j++){
              res.addAll(ans.get(j-1));  
            }
        
            result.add(res);
            }
        
        for(int i=0;i<q;i++){
            System.out.println(result.get(i));
        }
    }
}


