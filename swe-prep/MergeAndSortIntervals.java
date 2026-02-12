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


class MergeAndSortIntervals {
    /*
     * Complete the 'mergeHighDefinitionIntervals' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY intervals as parameter.
     */

    public static List<List<Integer>> mergeHighDefinitionIntervals(List<List<Integer>> intervals) {
        // Write your code here
        ArrayList<List<Integer>> result = new ArrayList<>();
        Collections.sort(intervals, (a, b) -> a.get(0) - b.get(0));

        //System.out.println("intervals: " + intervals);

        if(intervals.size() < 1) return result;
        result.add(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            List<Integer> lastInterval = result.get(result.size() -1 );

            if(lastInterval.get(1) > intervals.get(i).get(0)) {
                List<Integer> mergedInterval = List.of(lastInterval.get(0), intervals.get(i).get(1));
                result.set(result.size() - 1, mergedInterval);
            } else {
                result.add(intervals.get(i));
            }
        }

        //System.out.println("result: " + result);

        return result;
    }

    public static void main(String[] args) throws IOException {
        //[1, 3], [2, 6], [8, 10], [15, 18]
        List<List<Integer>> intervals = List.of(List.of(2, 6), List.of(1, 3), List.of(5, 10), List.of(15, 18));
        List<List<Integer>> result = mergeHighDefinitionIntervals(new ArrayList<>(intervals));
        System.out.println("result: " + result);
    }
}
