package Labs.Lab8;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) {
        String poem = """
            Листья падают с деревьев,
            Тихо шепчет ветер в поле.
            Солнце светит, но уже
            Чувствуется холод в доле.
            """;

        Pattern pattern = Pattern.compile("\\b[аеёиоуыэюяАЕЁИОУЫЭЮЯ]\\w*[аеёиоуыэюяАЕЁИОУЫЭЮЯ]\\b");
        Matcher matcher = pattern.matcher(poem);

        List<String> vowelWords = new ArrayList<>();

        while (matcher.find()) {
            vowelWords.add(matcher.group());
        }

        System.out.println("Найденные слова, начинающиеся и заканчивающиеся на гласную:");
        for (String word : vowelWords) {
            System.out.println(word);
        }
        System.out.println("Количество таких слов: " + vowelWords.size());
    }
}