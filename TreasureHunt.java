import java.util.LinkedList;
import java.util.Queue;

class TreasureHunt {

    // Definition for a binary tree node.
    class TreeNode {
        int value;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int value) {
            this.value = value;
        }
    }

    // Helper class to store information about each subtree
    class SubtreeInfo {
        int minValue;        // Minimum value in the subtree
        int maxValue;        // Maximum value in the subtree
        int subtreeSum;     // Sum of all nodes in the subtree
        int maxBSTSum;      // Maximum sum of all BST subtrees encountered so far
        boolean isBST;      // Whether the subtree is a BST

        SubtreeInfo(int minValue, int maxValue, int subtreeSum, int maxBSTSum, boolean isBST) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.subtreeSum = subtreeSum;
            this.maxBSTSum = maxBSTSum;
            this.isBST = isBST;
        }
    }

    // Main function to find the largest BST subtree sum
    public int findLargestBSTSum(TreeNode root) {
        return analyzeSubtree(root).maxBSTSum;
    }

    // Depth-first search function to compute SubtreeInfo for each subtree
    private SubtreeInfo analyzeSubtree(TreeNode node) {
        // Base case: if the node is null, return a default SubtreeInfo
        if (node == null) {
            return new SubtreeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0, true);
        }

        // Recursively analyze the left and right subtrees
        SubtreeInfo leftInfo = analyzeSubtree(node.leftChild);
        SubtreeInfo rightInfo = analyzeSubtree(node.rightChild);

        // Check if the current subtree is a BST
        if (leftInfo.isBST && rightInfo.isBST && leftInfo.maxValue < node.value && node.value < rightInfo.minValue) {
            // Current subtree is a BST
            int currentSubtreeSum = leftInfo.subtreeSum + rightInfo.subtreeSum + node.value;
            int currentMinValue = Math.min(node.value, leftInfo.minValue);
            int currentMaxValue = Math.max(node.value, rightInfo.maxValue);
            int largestBSTSum = Math.max(currentSubtreeSum, Math.max(leftInfo.maxBSTSum, rightInfo.maxBSTSum));
            return new SubtreeInfo(currentMinValue, currentMaxValue, currentSubtreeSum, largestBSTSum, true);
        } else {
            // Current subtree is not a BST
            int largestBSTSum = Math.max(leftInfo.maxBSTSum, rightInfo.maxBSTSum);
            return new SubtreeInfo(0, 0, 0, largestBSTSum, false);
        }
    }

    // Helper function to build a binary tree from an array
    public TreeNode constructTree(Integer[] nodeValues) {
        if (nodeValues.length == 0 || nodeValues[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(nodeValues[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;

        while (!queue.isEmpty() && index < nodeValues.length) {
            TreeNode currentNode = queue.poll();

            if (nodeValues[index] != null) {
                currentNode.leftChild = new TreeNode(nodeValues[index]);
                queue.add(currentNode.leftChild);
            }
            index++;

            if (index < nodeValues.length && nodeValues[index] != null) {
                currentNode.rightChild = new TreeNode(nodeValues[index]);
                queue.add(currentNode.rightChild);
            }
            index++;
        }

        return root;
    }

    public static void main(String[] args) {
        TreasureHunt treasureHunt = new TreasureHunt();

        // Input: Forest [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
        Integer[] nodeValues = {1, 4, 3, 2, 4, 2, 5, null, null, null, null, null, null, 4, 6};
        TreeNode root = treasureHunt.constructTree(nodeValues);

        // Find and print the largest BST subtree sum
        int largestBSTSum = treasureHunt.findLargestBSTSum(root);
        System.out.println("The largest BST subtree sum is: " + largestBSTSum);
    }
}
