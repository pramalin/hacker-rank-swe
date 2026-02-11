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

class InsertionSort1 {

    /*
     * Complete the 'insertionSort1' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */

    public static void insertionSort1(int n, List<Integer> arr) {
        // Write your code here
        ArrayList<Integer> nums = new ArrayList<>(arr);
        Integer term = nums.get(nums.size() - 1);
        // [1, 2, 4, 5, 3]
        // [2, 1]
        int i = n - 2;
        // shift the values > term to right to reach insertion point
        for (; i >=0 && nums.get(i) > term; i--) {
            nums.set(i + 1, nums.get(i));
            nums.forEach(c -> System.out.print(c + " "));
            System.out.println();
        }

        // put the term value at the insertion point.
        nums.set(i + 1, term);
        nums.forEach(c -> System.out.print(c + " "));
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        //List arr = List.of(1, 2, 4, 5, 3);
        List arr = List.of(2, 1);
        insertionSort1(arr.size(), arr);
    }
}