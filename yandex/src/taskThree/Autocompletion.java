package taskThree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Autocompletion {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Autocompletion autocompletion = new Autocompletion();
        autocompletion.start();
    }

    public void start() {
        int n = Integer.parseInt(getUserInputLine());
        String[] words = getUserInputLine().split("\s");
        int count = count(words);
        System.out.println(count);
    }

    public String getUserInputLine() {
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public int count(String[] words) {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add(words[0]);
        int count = words[0].length();
        for (int i = 1; i < words.length; i++) {
            if (treeSet.add(words[i])) {
                count += words[i].length();
            } else {
                String similarWord = treeSet.lower(words[i]);
                if (similarWord == null) {
                    similarWord = treeSet.higher(words[i]);
                }
                count += lengthOfSimilarity(words[i], similarWord) + 1;
            }
        }
        return count;
    }

    public int lengthOfSimilarity(String word1, String word2) {
        int length = Math.min(word1.length(), word2.length());
        for (int i = 0; i < length; i++) {
            if (word1.charAt(i) != word2.charAt(i)) return i;
        }
        return length;
    }
}
