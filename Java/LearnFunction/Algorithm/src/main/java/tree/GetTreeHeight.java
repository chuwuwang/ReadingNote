package tree;

/**
 * time：O(n)
 * space：logN 或者 N
 */
public class GetTreeHeight {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(12);
        TreeNode node7 = new TreeNode(20);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        int height = new GetTreeHeight().getTreeHeight(node1);
        System.out.println("height:" + height);
    }

    public int getTreeHeight(TreeNode root) {
        // base case
        if (root == null) return 0;

        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);

        // logic
        int currentHeight = Math.max(leftHeight, rightHeight) + 1;

        return currentHeight;
    }


}
