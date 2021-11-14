import java.util.ArrayList;
import java.util.LinkedList;

public class l002_BST {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val; // CONSTRUCTOR
        }
    }

    public static int size(TreeNode root) { // same as in binary tree
        if (root == null)
            return 0;
        int left = size(root.left);
        int right = size(root.right);
        return left + right + 1;
    }

    public static int height(TreeNode root) {
        if (root == null) // in term of edges
            return -1;
        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;
    }

    // DIFFERENT FUNCTION FROM BT (BST DO WITHOUT RECURSIONS)
    public static int Maximum(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            curr = curr.right; // rightMost
        }
        return curr.val;
    }

    public static int Minimum(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            curr = curr.left; // leftMost
        }
        return curr.val;
    }

    public static boolean find(TreeNode root, int data) {
        // IT is Like Binary Search
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == data) {
                return true;
            } else if (curr.val < data) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return false;
    }

    // NOTE BST INORDER IS ALWAYS SORTED

    public static ArrayList<TreeNode> rootToNodePath(TreeNode root, int data) {
        ArrayList<TreeNode> ans = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            ans.add(curr);
            boolean flag = false;
            if (curr.val == data) {
                flag = true;
                break;
            } else if (curr.val < data) {
                curr = curr.right;

            } else
                curr = curr.left;

            if (!flag) 
                ans.clear();      
        }
        return ans;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        // same find function  && note: height of bst is log n
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val < p && curr.val < q) {
                curr = curr.right;
            } else if (curr.val > p && curr.val > q) {
                curr = curr.left;
            } else
                return curr; //koi 1 data nhi mila
        }
        return null;  //dono hi nhi mile
    }
    //NOTE: root to node path in Binary Tree usme hme recursion thi coze hme yeh nhi pta tha ki data kidr milega
    // BST ITERATOR=========================================================================================================================

    class BSTIterator {

        private LinkedList<TreeNode> st = new LinkedList<>(); //we make stack : addfirst,removefirst
        
        public BSTIterator(TreeNode root) {
            addAllLeft(root); // it add right and add all left
        }
        
        private void addAllLeft(TreeNode node) {
            while (node != null) {
                this.st.addFirst(node);
                node = node.left;
            }
        }
        public int next() {
            TreeNode removeNode = st.removeFirst();
            addAllLeft(removeNode.right);
            return removeNode.val;
        }
        
        public boolean hasNext() {
            return st.size() != 0;
        }
    }
}