import java.util.*;

class Node {
    int val;
    Node left, right;
    Node(int v) { val = v; }
}

public class ShortestPath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Read tree nodes (level order, -1 for null)
        String[] vals = sc.nextLine().trim().split("\\s+");
        if(vals.length == 0) return;
        Node root = buildTree(vals);
        
        // Read two server IDs
        int e1 = sc.nextInt();
        int e2 = sc.nextInt();
        
        Node lca = findLCA(root, e1, e2);
        int d1 = findDistance(lca, e1, 0);
        int d2 = findDistance(lca, e2, 0);
        System.out.println(d1 + d2);
    }
    
    static Node buildTree(String[] vals) {
        if(vals[0].equals("-1")) return null;
        Node root = new Node(Integer.parseInt(vals[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while(!queue.isEmpty() && i < vals.length) {
            Node curr = queue.poll();
            // left child
            if(i < vals.length && !vals[i].equals("-1")) {
                curr.left = new Node(Integer.parseInt(vals[i]));
                queue.offer(curr.left);
            }
            i++;
            // right child
            if(i < vals.length && !vals[i].equals("-1")) {
                curr.right = new Node(Integer.parseInt(vals[i]));
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }
    
    static Node findLCA(Node root, int n1, int n2) {
        if(root == null) return null;
        if(root.val == n1 || root.val == n2) return root;
        Node left = findLCA(root.left, n1, n2);
        Node right = findLCA(root.right, n1, n2);
        if(left != null && right != null) return root;
        return (left != null) ? left : right;
    }
    
    static int findDistance(Node root, int target, int dist) {
        if(root == null) return -1;
        if(root.val == target) return dist;
        int left = findDistance(root.left, target, dist + 1);
        if(left != -1) return left;
        return findDistance(root.right, target, dist + 1);
    }
}
