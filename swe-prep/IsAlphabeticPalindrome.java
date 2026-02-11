public class IsAlphabeticPalindrome {

    public static boolean isAlphabeticPalindrome(String code) {
        // Write your code here
        boolean result = true;
        String cleanCode = code.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String reversedString = new StringBuilder(cleanCode).reverse().toString();
        return (cleanCode.equals(reversedString));
    }

    public static void main(String[] args) {

        String[] codes = {"A1b2B!a", "", "abc123cba"};
        //String code = "A1b2B!a";

        for (int i = 0; i < codes.length; i++) {
            boolean result = isAlphabeticPalindrome(codes[i]);
            System.out.println(codes[i] + " is " + (result ? "" : "not") + " a polindrome");
        };
    }
}