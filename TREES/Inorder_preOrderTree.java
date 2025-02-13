// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)




import java.util.*;

class Node{
    Node left = null;
    Node right = null;
    int data;
    
    Node(int d){
        this.data = d;
    }
}


public class Inorder_preOrderTree{
    static Node buildTree(int inorder [], Queue<Integer> s,  int start, int end,HashMap<Integer,Integer> map ){
        if(start > end) return null;
        int curr = s.poll();
        Node root = new Node(curr);
        if(start == end ) return root;
        int pos = map.get(curr);
        
        root.left = buildTree(inorder, s,start,pos-1,map);
        root.right = buildTree(inorder,s, pos+1,end,map);
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
        Queue<Integer> s = new LinkedList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            ino[i] = sc.nextInt();
            map.put(ino[i],i);
        }
        for(int i=0;i<n;i++) s.offer(sc.nextInt());
        
        Node root = buildTree(ino,s,0,n-1,map);
        
        // Inorder(root);
        
        int l=sc.nextInt();
        int r=sc.nextInt();
        

        sc.close();

        
        List<List<Integer>> ans = new ArrayList<>();
        ans = levelOrder(root);
        
        List<List<Integer>> result = new ArrayList<>();
        
        List<Integer> res = new ArrayList<>();
        for(int j=l;j<=r;j++){
            if(j%2==1){
                res.addAll(ans.get(j-1));  
            }else{
            for(int x =ans.get(j-1).size()-1;x>=0;x--){
                res.add(ans.get(j-1).get(x));
                }
                
            }
        }
    
        result.add(res);
        
        
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }
}