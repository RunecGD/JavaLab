package Labs.Lab10;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "films.dat";

    public static void main(String[] args) {
        createInitialFilmFile();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Показать все элементы файла");
            System.out.println("2. Добавить элемент");
            System.out.println("3. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    showAllFilms();
                    break;
                case 2:
                    addFilm();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void createInitialFilmFile() {
        List<Film> films = new ArrayList<>();
        films.add(new Film("Сериал A", 10, 30, Arrays.asList("Пн", "Ср", "Пт")));
        films.add(new Film("Сериал B", 20, 45, Arrays.asList("Вт", "Чт", "Сб")));
        films.add(new Film("Сериал C", 15, 50, Arrays.asList("Вс", "Пн")));
        films.add(new Film("Сериал D", 8, 25, Arrays.asList("Вт", "Чт")));
        films.add(new Film("Сериал E", 12, 40, Arrays.asList("Ср", "Пт", "Вс")));
        films.add(new Film("Сериал F", 6, 60, Arrays.asList("Пн", "Ср")));
        films.add(new Film("Сериал G", 14, 35, Arrays.asList("Сб", "Вс")));
        films.add(new Film("Сериал H", 5, 20, Arrays.asList("Пн")));
        films.add(new Film("Сериал I", 11, 55, Arrays.asList("Вт", "Чт")));
        films.add(new Film("Сериал J", 9, 30, Arrays.asList("Ср", "Пт")));
        films.add(new Film("Сериал K", 13, 45, Arrays.asList("Пн", "Сб")));
        films.add(new Film("Сериал L", 7, 50, Arrays.asList("Вт", "Чт", "Вс")));
        films.add(new Film("Сериал M", 10, 40, Arrays.asList("Пн", "Ср", "Чт")));
        films.add(new Film("Сериал N", 4, 25, Arrays.asList("Сб")));
        films.add(new Film("Сериал O", 3, 30, Arrays.asList("Вс")));

        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
            oos.writeObject(films);
            System.out.println("Бинарный файл создан и заполнен.");
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

    private static void showAllFilms() {
        List<Film> films = readFilmFile();
        if (films.isEmpty()) {
            System.out.println("Нет данных о фильмах.");
            return;
        }

        System.out.println("Список фильмов:");
        for (Film film : films) {
            System.out.println(film);
        }
    }

    private static void addFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название фильма:");
        String title = scanner.nextLine();

        System.out.println("Введите количество серий:");
        int episodes = scanner.nextInt();

        System.out.println("Введите длительность одной серии (в минутах):");
        int duration = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Введите дни показа (через запятую):");
        String daysInput = scanner.nextLine();
        List<String> days = Arrays.asList(daysInput.split(","));

        Film newFilm = new Film(title, episodes, duration, days);
        List<Film> films = readFilmFile();
        films.add(newFilm);

        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
            oos.writeObject(films); // перезапись файла
            System.out.println("Фильм добавлен.");
        } catch (IOException e) {
            System.out.println("Ошибка при добавлении фильма: " + e.getMessage());
        }
    }

    private static List<Film> readFilmFile() {
        List<Film> films = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
            films = (List<Film>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return films;
    }
}