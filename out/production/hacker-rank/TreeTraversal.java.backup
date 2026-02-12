import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class TreeTraversal {

/* you only have to complete the function given below.
Node is defined as

class Node {
    int data;
    Node left;
    Node right;
}

*/

    public static void preOrder(Node root) {
        if(root == null)
          return;

        // print root
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void postOrder(Node root) {
        if(root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        // print root
        System.out.print(root.data + " ");
    }

    public static void inOrder(Node root) {
        if(root == null)
            return;

        inOrder(root.left);
        // print root
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    static void levelOrder(Node root){
        //Write your code here
        Queue<Node> queue = new ArrayDeque<>();

        if(root != null)
            queue.add(root);

        while(!queue.isEmpty()) {
            Node n = queue.remove();
            System.out.print(n.data + " ");

            if(n.left != null)
                queue.add(n.left);
            if(n.right != null)
                queue.add(n.right);
        }
    }

    public static int height(Node root) {
        // Write your code here.
        if (root == null ||
            (root.left == null && root.right == null))
            return 0;
        else
            return 1 + Math.max(height(root.left), height(root.right));
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Node root = null;

        String[] nums = "3 5 4 7 2 1".split(" ");

        for (int i = 0; i < nums.length; i++) {
            root = insert(root, Integer.parseInt(nums[i]));
        }

        System.out.print("preorder: ");
        preOrder(root);
        System.out.println();
        System.out.print("postorder: ");
        postOrder(root);
        System.out.println();
        System.out.print("level order: ");
        levelOrder(root);
        System.out.println();

    }
}