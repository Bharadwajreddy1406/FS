// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and row 
// number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Sample Input:
// -------------
// 3 9 20 -1 -1 15 7

// Sample Output:
// --------------
// [[9],[3,15],[20],[7]]

// Explanation:
// ------------
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Sample Input-2:
// ---------------
// 3 9 8 4 0 1 7

// Sample Output-2:
// ----------------
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
// ------------

//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]


import java.util.*;
class Node{
    int data;
    Node left, right;
    Node(int data){
        this.data = data;
        this.left = this.right = null;
    }
}

public class VerticalTreeTraversal {
    
    public static Node buildTree(String[] nodes){
        Node root = new Node(Integer.parseInt(nodes[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(i < nodes.length){
            Node curr = queue.poll();
            if(!nodes[i].equals("-1")){
                curr.left = new Node(Integer.parseInt(nodes[i]));
                queue.add(curr.left);
            }
            i++;
            if(i < nodes.length && !nodes[i].equals("-1")){
                curr.right = new Node(Integer.parseInt(nodes[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static List<List<Integer>> verticalTraversal(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        
        Map<Integer, List<int[]>> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        queue.add(root);
        cols.add(0);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            Map<Integer, List<Integer>> temp = new TreeMap<>();
            for(int i=0; i<size; i++){
                Node curr = queue.poll();
                int col = cols.poll();
                if(!temp.containsKey(col)){
                    temp.put(col, new ArrayList<>());
                }
                temp.get(col).add(curr.data);
                
                if(curr.left != null){
                    queue.add(curr.left);
                    cols.add(col-1);
                }
                if(curr.right != null){
                    queue.add(curr.right);
                    cols.add(col+1);
                }
            }
            for(int key: temp.keySet()){
                if(!map.containsKey(key)){
                    map.put(key, new ArrayList<>());
                }
                List<Integer> list = temp.get(key);
                Collections.sort(list);
                for(int val: list){
                    map.get(key).add(new int[]{val, key});
                }
            }
        }
        
        for(int key: map.keySet()){
            List<int[]> list = map.get(key);
            List<Integer> res = new ArrayList<>();
            for(int[] val: list){
                res.add(val[0]);
            }
            result.add(res);
        }
        return result;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String[] nodes = sc.nextLine().split(" ");
        Node root = buildTree(nodes);
        
        List<List<Integer>> result = verticalTraversal(root);
        System.out.println(result);
    }

}
