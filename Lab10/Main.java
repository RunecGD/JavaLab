package Labs.Lab10;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FilmManager filmManager = new FilmManager();
        Scanner scanner = new Scanner(System.in);

        filmManager.createInitialFilmFile();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать все элементы файла");
            System.out.println("2. Добавить элемент");
            System.out.println("3. Выход");
            System.out.println("4. Количество разных дней показа самого многосерийного фильма");
            System.out.println("5. За сколько календарных дней покажут самый многосерийный фильм (по расписанию)");

            System.out.print("Ваш выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    filmManager.showAllFilms();
                    break;
                case 2:
                    filmManager.addFilm(scanner);
                    break;
                case 3:
                    filmManager.showDaysToCompleteMaxEpisodesFilm();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}