// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3



import java.util.*;

class Node{
    
    int data;
    Node left;
    Node right;
    
    Node(int d){
        this.data = d;
    }
}


public class PreOrder_PostOrderTree{
    
    
    static List<List<Integer>> levelOrder(Node root){
        
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        List<List<Integer>> answer = new ArrayList<>();
        while(!q.isEmpty()){
            
            int n = q.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<n;i++){
                Node node = q.poll();
                list.add(node.data);
                
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
            }
            answer.add(list);
        }
        
        return answer;
    }
    
    
    
    static Node buildTree(int [] pre, int[] post, int start, int end, int size, int [] index){
        
        if(start > end) return null;
        
        Node root = new Node(pre[index[0]]);
        index[0]++;
        
        if(start == end) return root;
        int pos=-1;
        for(int i=start;i<=end;i++){
            if(pre[index[0]] == post[i]){
                pos =i;
                break;
            }
        }
        if(pos==-1) return null;
        root.left = buildTree(pre,post,start,pos,size,index);
        root.right = buildTree(pre,post,pos+1,end-1,size,index);
        
        return root;
        
        
    }
    
    static void Inorder(Node root){
        
        if(root == null) return;
        
        Inorder(root.left);
        System.out.print(root.data +" ");
        Inorder(root.right);
        
    }
    public static void main (String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        String[] preo = sc.nextLine().split(" ");
        String[] posto =  sc.nextLine().split(" ");
        int n = preo.length;
        int pre[] = new int[n];
        int post[] = new int[n];
        sc.close();
        for(int i=0;i<n;i++) {
            pre[i] = Integer.parseInt(preo[i]);
        }
        for(int i=0;i<n;i++) {
            post[i] = Integer.parseInt(posto[i]);
            
        }
        
        // System.out.println(Arrays.toString(pre));
        int[] index = new int[]{0};
        Node root = buildTree(pre,post,0,pre.length-1,pre.length,index);
        
        List<List<Integer>> list = levelOrder(root);
        System.out.print(list);
        
        
    }
}