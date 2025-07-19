Imagine you're the curator of an ancient manuscript archive. Each manuscript is
assigned a unique significance score, and the archive is arranged so that every 
manuscript on the left has a lower score and every manuscript on the right has a
higher score, forming a special ordered display. Now, for an upcoming exhibition,
scholars have decided that only manuscripts with significance scores between low 
and high (inclusive) are relevant. Your task is to update the archive by removing
any manuscripts whose scores fall outside the range [low, high]. Importantly, 
while you remove some manuscripts, you must preserve the relative order of the 
remaining ones—if a manuscript was originally displayed as a descendant of another, 
that relationship should remain intact. It can be proven that there is a unique 
way to update the archive.

Display the manuscript of the updated archive. Note that the main manuscript 
(the root) may change depending on the given score boundaries.

Input format:
Line 1: space separated scores to build the manuscript archive
Line 2: two space seperated integers, low and high.

Output format:
space separated scores of the updated archive

Example 1:
input=
1 0 2
1 2
output=
1 2

Explanation:
Initial archieve:
      1
     / \
    0   2


Updated archieve:
    1
     \
      2
After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
and its right manuscript 2 remain.

Example 2:
input=
3 0 4 2 1
1 3
output=
3 2 1

Explanation:
Initial archieve:
          3
         / \
        0   4
         \
          2
         /
        1

Updated archieve:
      3
     /
    2
 /
  1


## Solution 66.7

import java.util.*;

class BSTNode {
    int val;
    BSTNode left, right;

    public BSTNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class BST {
    BSTNode root;


    public BSTNode insert(BSTNode root, int val) {
        if (root == null) return new BSTNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }


    public BSTNode trimBST(BSTNode root, int low, int high) {
        if (root == null) return null;

        if (root.val < low) return trimBST(root.right, low, high);


        if (root.val > high) return trimBST(root.left, low, high);


        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }


    public void inorderTraversal(BSTNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        inorderTraversal(root.left);
        inorderTraversal(root.right);
    }

    public void buildAndTrimBST(int[] nums, int low, int high) {
        for (int num : nums) {
            root = insert(root, num);
        }


        root = trimBST(root, low, high);

        inorderTraversal(root);
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] input = sc.nextLine().split(" ");
        int[] nums = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

        int low = sc.nextInt();
        int high = sc.nextInt();

        BST bst = new BST();
        bst.buildAndTrimBST(nums, low, high);

    }
}

