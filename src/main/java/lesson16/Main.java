package lesson16;

public class Main {

    public static void main(String[] args) {
        try {
            String longestWord = findLongest(new String[0]);
            System.out.println("самое длинное слово: " + longestWord);
        } catch (Exception e) {
            System.out.println("не могу найти самое длинное слово ");
        }
        System.out.println("работа продолжена ");
    }

    public static String findLongest(String[] words) {
        assertNotEmpty(words);
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    private static void assertNotEmpty(String[] words) {
        if (words == null || words.length == 0) {
            throw new RuntimeException("at least one word expected");
        }
    }
}