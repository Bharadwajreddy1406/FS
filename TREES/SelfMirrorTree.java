import java.util.*;

class Node {
    int val;
    Node left, right;
    Node(int v) { val = v; }
}

public class SelfMirrorTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Read tree as level order (space separated, -1 for null)
        String[] vals = sc.nextLine().trim().split("\\s+");
        Node root = buildTree(vals);
        System.out.println(isMirror(root, root));
    }
    
    static Node buildTree(String[] vals) {
        if(vals.length == 0 || vals[0].equals("-1")) return null;
        Node root = new Node(Integer.parseInt(vals[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while(!queue.isEmpty() && i < vals.length) {
            Node curr = queue.poll();
            if(i < vals.length && !vals[i].equals("-1")) {
                curr.left = new Node(Integer.parseInt(vals[i]));
                queue.offer(curr.left);
            }
            i++;
            if(i < vals.length && !vals[i].equals("-1")) {
                curr.right = new Node(Integer.parseInt(vals[i]));
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }
    
    static boolean isMirror(Node n1, Node n2) {
        if(n1 == null && n2 == null) return true;
        if(n1 == null || n2 == null) return false;
        return (n1.val == n2.val) &&
               isMirror(n1.left, n2.right) &&
               isMirror(n1.right, n2.left);
    }
}
