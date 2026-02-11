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

class BigSortFunction {

    /*
     * Complete the 'bigSorting' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY unsorted as parameter.
     */

    public static List<String> bigSorting(List<String> unsorted) {
        // Write your code here
        unsorted.sort((a, b) -> {
            if (a.length() != b.length()) {
                return Integer.compare(a.length(), b.length());
            } else {
                return a.compareTo(b);
            }
        });
        return unsorted;
    }

    public static void main(String[] args) throws IOException {
        List<String> unsorted = List.of(
                "6",
                "31415926535897932384626433832795",
                "1",
                "3",
                "10",
                "3",
                "5");

        List<String> result = bigSorting(new ArrayList<>(unsorted));
        result.forEach(l -> System.out.println(l));
   }
}
