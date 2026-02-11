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

class BalancedBrackets {

    /*
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isBalanced(String s) {
        // Write your code here
        //      System.out.println("s:" + s);

        Stack<String> stack = new Stack<>();
        String openChars = "{[(";
        String []chars = s.split("");
        for (int i = 0; i < chars.length; i++) {
            String c = chars[i];
            if(openChars.contains(c)) {
                stack.push(c);
            } else if("}".equals(c)) {
                if (stack.size() > 0 && stack.peek().equals("{"))
                    stack.pop();
                else return "NO";
            } else if("]".equals(c)) {
                if (stack.size() > 0 && stack.peek().equals("["))
                    stack.pop();
                else return "NO";
            } else if(")".equals(c)) {
                if (stack.size() > 0 && stack.peek().equals("("))
                    stack.pop();
                else return "NO";
            }

        }
        if (stack.size() == 0)
            return "YES";
        else
            return "NO";
    }

    public static void main(String[] args) throws IOException {
        List<String> terms = List.of(
        "}][}}(}][))]",
        "[](){()}",
        "()",
        "({}([][]))[]()",
        "{)[](}]}]}))}(())(",
        "([[)"
        );
        terms.forEach (s -> {
            String result = isBalanced(s);
            System.out.println(s + " " + result);
        });
    }
}