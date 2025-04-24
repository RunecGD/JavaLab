package Labs.Lab8;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        String poem = """
            Листья падают с деревьев,
            Тихо шепчет ветер в поле.
            Солнце светит, но уже
            Чувствуется холод в доле.
            """;

        // Убираем символы новой строки и разбиваем текст на слова
        String[] words = poem.replaceAll("[,.]", "").split("\\s+");
        List<String> vowelWords = new ArrayList<>();

        for (String word : words) {
            if (isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1))) {
                vowelWords.add(word);
            }
        }

        System.out.println("Найденные слова, начинающиеся и заканчивающиеся на гласную:");
        for (String word : vowelWords) {
            System.out.println(word);
        }
        System.out.println("Количество таких слов: " + vowelWords.size());
    }

    private static boolean isVowel(char c) {
        return "аеёиоуыэюяАЕЁИОУЫЭЮЯ".indexOf(c) != -1; // Проверка на гласные буквы
    }
}