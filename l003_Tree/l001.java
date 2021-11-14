import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class l001 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) { // Constructor
            this.val = val;
        }
    }

    // basic functions
    public static int size(TreeNode root) {
        if (root == null)
            return 0;

        int left = size(root.left); // faith
        int right = size(root.right);

        return left + right + 1;
    }

    public static int height(TreeNode root) { // in term of edges
        if (root == null)
            return -1;
        int left = height(root.left);
        int right = height(root.right);

        return (Math.max(left, right) + 1);
    }

    public static int heightInTermsOfNode(TreeNode root) { // in term of nodes
        if (root == null)
            return 0;
        int left = heightInTermsOfNode(root.left);
        int right = heightInTermsOfNode(root.right);

        return (Math.max(left, right) + 1);
    }

    public static int sum(TreeNode root) {
        if (root == null)
            return 0;
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        return leftSum + root.val + rightSum;
    }

    public static boolean find(TreeNode root, int data) {
        if (root == null)
            return false;

        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    public static int Maximum(TreeNode root) {
        if (root == null)
            return -(int) 1e9;
        int left = Maximum(root.left);
        int right = Maximum(root.right);

        return Math.max(Math.max(left, right), root.val);
    }

    public static ArrayList<TreeNode> NodeToRootPath(TreeNode root, int data) {
        if (root == null) {
            return new ArrayList<>();
        }

        if (root.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }
        ArrayList<TreeNode> left = NodeToRootPath(root.left, data);
        if (left.size() != 0) {
            // means left se answer mila hai to right me call lgane ki need nahi h direct
            // return krege
            left.add(root);
            return left;
        }
        ArrayList<TreeNode> right = NodeToRootPath(root.right, data);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }
        return new ArrayList<>(); // means agar idr hm aare h means empty hai to aagye
    }

    public static boolean NodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {
        if (root == null)
            return false;

        if (root.val == data) {
            ans.add(root);
            return true;
        }

        // same find ki trah left or right managwaya
        boolean res = NodeToRootPath(root.left, data, ans) || NodeToRootPath(root.right, data, ans);
        if (res) {
            // agar true
            ans.add(root);
        }
        return res;
    }

    // do this with a helper function
    public static void rootToAllLeafPath(TreeNode root, ArrayList<Integer> smallAns,
            ArrayList<ArrayList<Integer>> ans) {

        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            ArrayList<Integer> base = new ArrayList<>(smallAns); // small ans ki deep copy bankr save kri
            base.add(root.val);
            ans.add(base);
            return;
        }

        // add when we are going down
        smallAns.add(root.val);

        rootToAllLeafPath(root.left, smallAns, ans);
        rootToAllLeafPath(root.right, smallAns, ans);

        // remove when we back track
        smallAns.add(root.val);
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>(); // imp-> it behaves like a bucket when we go upside it stores
                                                         // then backtract time it will become empty
        rootToAllLeafPath(root, smallAns, ans);
        return ans;
    }

    public static void singleChildNode(TreeNode node, ArrayList<Integer> ans) {
        if (node == null || (node.left == null && node.right == null)) // means it is a leaf
            return;

        if (node.left == null || node.right == null) { // it is single child so add it in answer
            ans.add(node.val);
        }

        singleChildNode(node.left, ans); // preorder
        singleChildNode(node.right, ans);
    }

    // do by static type A
    public static int count = 0;

    public static void countsingleChildNode_(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) // means it is a leaf
            return;

        countsingleChildNode_(node.left); // preorder
        countsingleChildNode_(node.right);

        if (node.left == null || node.right == null) { // it is single child so add it in answer
            count++;
        }
    }

    public static int countsingleChildNode(TreeNode node) {
        countsingleChildNode_(node);
        return count;
    }
    // ============================================================
    // b) with return type

    public static int countSingleChildNode(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        // faith -> left subtree wiill bring his own answer and similarly right
        int leftCount = countSingleChildNode(node.left);
        int rightCount = countSingleChildNode(node.right);

        int ans = leftCount + rightCount;

        if (node.left == null || node.right == null)
            ans++;
        return ans;
    }

    // =============================================================

    public static void printKlevelDown(TreeNode node, int k) {
        if (node == null || k < 0) {
            return;
        }
        if (k == 0) {
            System.out.println(node.val);
        }
        printKlevelDown(node.left, k - 1);
        printKlevelDown(node.right, k - 1);

    }

    // vvv imp leetcode 863 -> all node distance k in binary tree

    public static void kdown(TreeNode root, TreeNode blockNode, int k, List<Integer> ans) {
        if (root == null || root == blockNode || k < 0) {
            return;
        }
        if (k == 0) {
            ans.add(root.val);
            return;
        }
        kdown(root.left, blockNode, k - 1, ans); // sent to the below level by subtracting
        kdown(root.right, blockNode, k - 1, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> path = new ArrayList<>();
        NodeToRootPath(root, target.val, path); // we store the ans inhe path

        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;

        for (int i = 0; i < path.size(); i++) {
            if (k - i < 0) {
                break;
            }

            kdown(path.get(i), blockNode, k - i, ans); // call kdown
            blockNode = path.get(i); // update blockNode
        }
        return ans;
    }

    public static int distanceK_01(TreeNode root, TreeNode target, int k, ArrayList<Integer> ans) { // OPTIMIZE
        if (root == null)
            return -1;
        if (root == target) {
            kdown(root, null, k, ans);
            return 1;
        }
        int ld = distanceK_01(root.left, target, k, ans);
        if (ld != -1) { // means we come here at the first tym
            kdown(root, root.left, k - ld, ans);
            return ld + 1;
        }

        int rd = distanceK_01(root.right, target, k, ans);
        if (rd != -1) { // means we come here at the first tym
            kdown(root, root.right, k - ld, ans);
            return rd + 1;
        }
        return -1;
    }
    //Burning Tree=============================================================================================================

    public static void kDown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode)
            return;
        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        kDown(root.left, time + 1, blockNode, ans);
        kDown(root.right, time + 1, blockNode, ans);

    }

    public static int burningTree(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;

        if (root.val == target) {
            kDown(root, 0, null, ans); // kdown -> burns the whole tree
            return 1; // iske parent ko burn krne me 1 sec lgega
        }
        int ld = burningTree(root.left, target, ans); // when we eneter write faith from both side lhs rhs
        if (ld != -1) { // left ne btaya kitne time burn kra ab aage burn krege idr se
            kDown(root, ld, root.left, ans);
            return ld + 1;
        }
        int rd = burningTree(root.right, target, ans);
        if (rd != -1) {
            kDown(root, rd, root.right, ans); // idr time + 1nhi krege coz root.val me phle hi btare h ki
                                              // 1seclgega toh idr se kyo btaye
            return rd + 1;
        }
        return -1;
    }

    public static void kdownWithWater(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> water) {
        if (root == null || root == blockNode || water.contains(root.val))
            return;

        if (time == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(time).add(root.val);
        kdownWithWater(root.left, time + 1, blockNode, ans, water);
        kdownWithWater(root.right, time + 1, blockNode, ans, water);
    }

    // 1 : did we gett the target node, -2 : fire will not reach that node, t > 0 :
    // fire will reach with time t.
    public static int burningTreeWithWater(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> water) {
        // Hashset because-> coz hme find krna pdhega ki water kidr kidr hai
        // it follows like find function or NODETOROOTPATH

        if (root == null)
            return -1;

        if (root.val == target) { //1st follow we will get path from him
            if (!water.contains(root.val)) {
                kdownWithWater(root, 0, null, ans, water);
                return 1;
            } else {
                return -2;
            }
        }

        int ld = burningTreeWithWater(root.left, target, ans, water);
        if (ld > 0) {
            if (!water.contains(root.val)) {
                kdownWithWater(root, ld, root.left, ans, water);
                return ld + 1;
            } else
                return -2;
        }
        if (ld == -2)
            return -2;

        int rd = burningTreeWithWater(root.right, target, ans, water);
        if (rd > 0) {
            if (!water.contains(root.val)) {
                kdownWithWater(root, rd, root.right, ans, water);
                return rd + 1;
            }
            return -2;
        }
        if (rd == -2)
            return -2;

        return -1;

        // doubt -> hmne water store kidr hashset me mtlb isko kese pta ki hashset  m water h
    }

    public static void burningTree(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, target, ans);
    }

    // LCA=========================================================================================

    // public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {

    // }

    //VIEW QUES=====================================================================================

    public static void levelOrderTraversal(TreeNode root) {   //same BFS
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            System.out.println(level);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();
                smallAns.add(rnode.val);

                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
            ans.add(smallAns);
            level++;
        }
        for (var list : ans) {
            System.out.println(list);
        }
    }

    public static List<Integer> leftView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        List<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();
                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
        }
        return ans;
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();
                if (rnode.right != null)
                    que.addLast(rnode.right);
                if (rnode.left != null)
                    que.addLast(rnode.left);
            }
        }
        return ans;
    }
    
    public static class vPair {
        TreeNode node = null;
        int vl = 0;

        vPair(TreeNode node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }
}