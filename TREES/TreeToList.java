import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class Linked{
    Node head;
    Node tail;
    Linked(int data){
        this.head = new Node(data);
        this.tail = this.head;    
    }

    void add(int data){
        Node temp = new Node(data);
        this.tail.right = temp;
        this.tail = temp;
    }

    void print(){
        Node temp = this.head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.right;
        }
    }
}
// Linked l = new Linked(10);

public class TreeToList {
    

    static Node buildTree (int arr[]){
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i=1;
        while(i<arr.length){
            Node temp = q.poll();
            if(i<arr.length){
                temp.left = new Node(arr[i]);
                q.add(temp.left);
                i++;
            }
            if(i<arr.length){
                temp.right = new Node(arr[i]);
                q.add(temp.right);
                i++;
            }
        }
        return root;
    }

    static void convert(Node root, Linked l){
        if(root == null){
            return;
        }
        l.add(root.data);
        convert(root.left, l);
        convert(root.right, l);
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String[] elems = sc.nextLine().split(" ");
        sc.close();
        int arr[] = new int[elems.length];
        for(int i=0; i<elems.length; i++){
            arr[i] = Integer.parseInt(elems[i]);
        }

        Node root = buildTree(arr);
        Linked l = new Linked(-1);
        l.head = l.head.right;
        convert(root, l);
        l.print();
    }
}
