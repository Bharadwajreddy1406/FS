// Construct Tree from the given Level-order and In-order.
// Compute the Depth and Sum of the Deepest nodes in the Binary tree

// Input Format:
// -------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the level-order traversal.

// Output Format:
// --------------
// Print two values:
// ->The maximum number of levels.
// ->The sum of all node values at the deepest level.

// Example:
// -------------
// Input:
// 11
// 7 8 4 2 5 9 11 10 1 6 3
// 1 2 3 4 5 6 7 9 8 10 11

// Output:
// 6 11

// Explanation:
// The binary tree structure:
//            1
//          /   \
//        2       3
//       / \     /
//      4   5   6
//     /     \   
//    7       9
//     \       \
//      8      10
//             /
//           11
// Maximum Depth: 6
// nodes at the Deepest Level (6): 11
// Sum of nodes at Level 6: 11

import java.util.*;

public class LevelOrder_inOrder {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static TreeNode buildTree(int[] inOrder, int[] levelOrder) {
        if (inOrder.length == 0)
            return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        int index = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == levelOrder[0]) {
                index = i;
                break;
            }
        }
        int[] leftInOrder = Arrays.copyOfRange(inOrder, 0, index);
        int[] rightInOrder = Arrays.copyOfRange(inOrder, index + 1, inOrder.length);

        ArrayList<Integer> leftLevelOrder = new ArrayList<>();
        ArrayList<Integer> rightLevelOrder = new ArrayList<>();
        for (int i = 1; i < levelOrder.length; i++) {
            for (int j = 0; j < leftInOrder.length; j++) {
                if (levelOrder[i] == leftInOrder[j]) {
                    leftLevelOrder.add(levelOrder[i]);
                    break;
                }
            }
            for (int j = 0; j < rightInOrder.length; j++) {
                if (levelOrder[i] == rightInOrder[j]) {
                    rightLevelOrder.add(levelOrder[i]);
                    break;
                }
            }
        }

        root.left = buildTree(leftInOrder, leftLevelOrder.stream().mapToInt(i -> i).toArray());
        root.right = buildTree(rightInOrder, rightLevelOrder.stream().mapToInt(i -> i).toArray());

        return root;
    }

    public static int[] findDepthAndSum(TreeNode root) {
        if (root == null)
            return new int[] { 0, 0 };

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0, sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            depth++;
        }

        return new int[] { depth, sum };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inOrder = new int[n];
        int[] levelOrder = new int[n];
        for (int i = 0; i < n; i++) {
            inOrder[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            levelOrder[i] = sc.nextInt();
        }
        sc.close();
        TreeNode root = buildTree(inOrder, levelOrder);
        int[] res = findDepthAndSum(root);
        System.out.println(res[0] + " " + res[1]);
    }
}
