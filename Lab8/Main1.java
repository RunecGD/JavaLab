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

        String[] words = text.substring(0, text.length() - 1).split(",\\s*");
        System.out.println("\nНайденные слова:");

        // Используем регулярное выражение для поиска
        Pattern pattern = Pattern.compile("\\b\\w+\\b");

        for (int i = 0; i < words.length; i++) {
            Matcher matcher = pattern.matcher(words[i]);
            if (matcher.find()) {
                String currentWord = matcher.group();
                boolean allBeforeSmaller = true;
                boolean allAfterLarger = true;

                // Проверка слов перед текущим
                for (int j = 0; j < i; j++) {
                    if (words[j].compareTo(currentWord) >= 0) {
                        allBeforeSmaller = false;
                        break;
                    }
                }

                for (int j = i + 1; j < words.length; j++) {
                    if (words[j].compareTo(currentWord) <= 0) {
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
}