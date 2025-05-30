package Labs.Lab8;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main1 {
    public static void main(String[] args) {
        String text = """
            apple, bat, cat, dog, egg, frog, ant, bee, cow, deer,
            fox, goat, hen, iguana, jay, kite, lion, mouse, newt,
            owl, pig, quail, rat, snake, tiger, uakari, viper,
            wolf, xerus, yak, zebra.
            """;

        System.out.println("Исходный текст:");
        System.out.println(text);

        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(text);

        System.out.println("\nНайденные слова:");

        List<String> words = new ArrayList<>();
        while (matcher.find()) {
            words.add(matcher.group());
        }


        for (int i = 0; i < words.size(); i++) {
            String currentWord = words.get(i);
            boolean allBeforeSmaller = true;
            boolean allAfterLarger = true;

            for (int j = 0; j < i; j++) {
                if (words.get(j).compareTo(currentWord) >= 0) {
                    allBeforeSmaller = false;
                    break;
                }
            }

            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(j).compareTo(currentWord) <= 0) {
                    allAfterLarger = false;
                    break;
                }
            }

            if (allBeforeSmaller && allAfterLarger) {
                System.out.print(currentWord + " ");
            }
        }
    }
}