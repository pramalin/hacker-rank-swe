import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;

class Node {
    Integer data;
    Node left;
    Node right;
    Node(Integer data) {
        this.data = data;
    }
}

class TreeHeight {
    static int treeHeight(Node n) {
        if (n == null || (n.left == null && n.right == null))
            return 0;
        else {
            int lh = treeHeight(n.left);
            int rh = treeHeight(n.right);
            return Math.max(lh, rh) + 1;
        }
    }

    static Node insert(Node n, Integer v) {
        if(n == null) {
            n = new Node(v);
        } else {
            if (v <= n.data) {
                Node curr = insert(n.left, v);
                n.left = curr;
            } else {
                Node curr = insert(n.right, v);
                n.right = curr;
            }
        }
        return n;
    }

    /*
     * Complete the 'getBinarySearchTreeHeight' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY values
     *  2. INTEGER_ARRAY leftChild
     *  3. INTEGER_ARRAY rightChild
     */

    public static int getBinarySearchTreeHeight(List<Integer> values, List<Integer> leftChild, List<Integer> rightChild) {
        // Write your code here
        int n = values.size();
        if (n == 0) return 0;

        // 1. Create all nodes first
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(values.get(i));
        }

        // 2. Link them based on the provided children indices
        for (int i = 0; i < n; i++) {
            int leftIdx = leftChild.get(i);
            int rightIdx = rightChild.get(i);

            if (leftIdx != -1) nodes[i].left = nodes[leftIdx];
            if (rightIdx != -1) nodes[i].right = nodes[rightIdx];
        }

        // 3. Standard height calculation (edges)
        return treeHeight(nodes[0]);
    }

    public static void main(String[] args) throws IOException {
        // 2 5 3 2 1 -1 2 -1 -1

        List<Integer> values = List.of(4, 2, 6, 1, 3, 5, 7);
        List<Integer> leftChild = List.of(1, 3, 5, -1, -1, -1, -1);
        List<Integer> rightChild = List.of(2, 4, 6, -1, -1, -1, -1);

       // List<Integer> values = List.of(5, 3);
       // List<Integer> leftChild = List.of(1, -1);
       // List<Integer> rightChild = List.of(-1, -1);

       // List<Integer> values = List.of(10);
       // List<Integer> leftChild = List.of( -1);
       // List<Integer> rightChild = List.of(-1);

        int result = getBinarySearchTreeHeight(values, leftChild, rightChild);

        System.out.println(result);
    }
}
