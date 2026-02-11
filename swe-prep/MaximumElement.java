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

class MaximumElement {
    /*
     * Complete the 'getMax' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY operations as parameter.
     */

    public static List<Integer> getMax(List<String> operations) {
        // Write your code here
        List<Integer> stack = new ArrayList<>();
        LinkedList<Integer> result = new LinkedList<>();

        for (int i = 0; i < operations.size(); i++) {
            String[] ops = operations.get(i).split(" ");
            Integer op = Integer.parseInt(ops[0]);
            if(1 == op) {
                Integer value = Integer.parseInt(ops[1]);
                stack.add(value);
            } else if (2 == op) {
                Integer removed = stack.remove(stack.size() - 1);
            } else if (3 == op) {
                result.add(Collections.max(stack));
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        List<String> ops = List.of("1 97", "2", "1 20", "2", "1 26", "1 20", "2", "3", "1 91", "3");
        List<Integer> res = getMax(ops);
        System.out.println("result: " + res);
    }
}
