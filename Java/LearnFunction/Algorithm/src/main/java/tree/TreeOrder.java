package tree;

/**
 * 二叉树遍历
 */
public class TreeOrder {

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

        TreeOrder treeOrder = new TreeOrder();
        treeOrder.preOrder(node1);

        System.out.println();

        treeOrder.inOrder(node1);

        System.out.println();

        treeOrder.postOrder(node1);

    }

    public void preOrder(TreeNode root) {
        // base case
        if (root == null) return;

        // sub problem -- print current node
        System.out.print(root.value + " ");

        // recursion left call
        preOrder(root.left);

        // recursion right call
        preOrder(root.right);
    }

    public void inOrder(TreeNode root) {
        // base case
        if (root == null) return;

        // recursion left call
        inOrder(root.left);

        // sub problem -- print current node
        System.out.print(root.value + " ");

        // recursion right call
        inOrder(root.right);
    }

    public void postOrder(TreeNode root) {
        // base case
        if (root == null) return;

        // recursion left call
        postOrder(root.left);

        // recursion right call
        postOrder(root.right);

        // sub problem -- print current node
        System.out.print(root.value + " ");
    }


}
