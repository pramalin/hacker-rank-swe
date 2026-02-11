import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;

/**

 # Count Elements Greater Than Previous Average
 https://www.hackerrank.com/contests/software-engineer-prep-kit/challenges/count-elements-greater-than-previous-average/problem

 */
class CountElementsGreaterThanPreviousAverage {
    /*
     * Complete the 'countResponseTimeRegressions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY responseTimes as parameter.
     */

    public static int countResponseTimeRegressions(List<Integer> responseTimes) {
    // Write your code here
        int sum = 0;
        int prevAvg = 0;
        int index = 0;
        int count = 0;
        Iterator<Integer> iterator = responseTimes.iterator();

        while(iterator.hasNext()) {
            index++;
            int current = iterator.next();
            if(prevAvg < current && index > 1) {
                count++;
            }
            System.out.println("index: " + index + " current: " + current + " sum " + sum + " prevAvg: " + prevAvg + " count: " + count);
            sum = sum + current;
            prevAvg = sum / index;
        }

      return count;
    }

    public static void main(String[] args) throws IOException {
        List<Integer> responseTimes = List.of(100, 200, 150, 400);

        int result = countResponseTimeRegressions(responseTimes);

        System.out.println(result);
    }
}

