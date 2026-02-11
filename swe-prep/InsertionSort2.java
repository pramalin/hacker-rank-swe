import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class InsertionSort2 {

    /*
     * Complete the 'insertionSort2' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */

    public static void insertionSort2(int n, List<Integer> arr) {
        // Write your code here
        ArrayList<Integer> nums = new ArrayList<>(arr);
        int size = nums.size() - 1;
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < i; j++) {
                int curr = nums.get(i);
                if(nums.get(j) > curr) {
                    nums.set(i, nums.get(j));
                    nums.set(j, curr);
                }
            }
            nums.forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        List<Integer> arr = List.of(1, 4, 3, 5, 6, 2);
        insertionSort2(arr.size(), arr);
    }
}
