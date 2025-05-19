package Labs.Lab10;

import java.io.*;
import java.util.Scanner;

public class FilmManager {
    private static final String FILE_NAME = "films.dat";

    public void createInitialFilmFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
            oos.writeObject(new Film("Сериал A", 10, 30, "Пн,Ср,Пт"));
            oos.writeObject(new Film("Сериал B", 20, 45, "Вт,Чт,Сб"));
            oos.writeObject(new Film("Сериал C", 15, 50, "Вс,Пн"));
            oos.writeObject(new Film("Сериал D", 8, 25, "Вт,Чт"));
            oos.writeObject(new Film("Сериал E", 12, 40, "Ср,Пт,Вс"));
            oos.writeObject(new Film("Сериал F", 6, 60, "Пн,Ср"));
            oos.writeObject(new Film("Сериал G", 14, 35, "Сб,Вс"));
            oos.writeObject(new Film("Сериал H", 5, 20, "Пн"));
            oos.writeObject(new Film("Сериал I", 11, 55, "Вт,Чт"));
            oos.writeObject(new Film("Сериал J", 9, 30, "Ср,Пт"));
            oos.writeObject(new Film("Сериал K", 13, 45, "Пн,Сб"));
            oos.writeObject(new Film("Сериал L", 7, 50, "Вт,Чт,Вс"));
            oos.writeObject(new Film("Сериал M", 10, 40, "Пн,Ср,Чт"));
            oos.writeObject(new Film("Сериал N", 4, 25, "Сб"));
            oos.writeObject(new Film("Сериал O", 3, 30, "Вс"));

            System.out.println("Бинарный файл создан и заполнен.");
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

    public void addFilm(Scanner scanner) {
        System.out.println("Введите название фильма:");
        String title = scanner.nextLine();

        System.out.println("Введите количество серий:");
        int episodes = scanner.nextInt();

        System.out.println("Введите длительность одной серии (в минутах):");
        int duration = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Введите дни показа (через запятую, например: Пн,Ср,Пт):");
        String days = scanner.nextLine().replaceAll("\\s+", ""); // убрать лишние пробелы

        Film newFilm = new Film(title, episodes, duration, days);
        addFilmToFile(newFilm);
    }

    private void addFilmToFile(Film film) {
        File file = new File(FILE_NAME);
        boolean append = file.exists() && file.length() > 0;

        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME, true);
                ObjectOutputStream oos = append
                        ? new AppendingObjectOutputStream(new BufferedOutputStream(fos))
                        : new ObjectOutputStream(new BufferedOutputStream(fos))
        ) {
            oos.writeObject(film);
            System.out.println("Фильм добавлен.");
        } catch (IOException e) {
            System.out.println("Ошибка при добавлении фильма: " + e.getMessage());
        }
    }

    public void showAllFilms() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
            while (true) {
                Film film = (Film) ois.readObject();
                System.out.println(film);
            }
        } catch (EOFException e) {
            // Конец файла
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }


    // Сколько календарных дней потребуется на показ всего сериала
    public void showDaysToCompleteMaxEpisodesFilm() {
        int maxEpisodes = -1;
        Film maxFilm = null;

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
            while (true) {
                Film film = (Film) ois.readObject();
                if (film.getEpisodes() > maxEpisodes) {
                    maxEpisodes = film.getEpisodes();
                    maxFilm = film;
                }
            }
        } catch (EOFException e) {
            // конец файла
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        if (maxFilm == null) {
            System.out.println("Нет данных о фильмах.");
            return;
        }

        String[] schedule = maxFilm.getDays().split(",");
        int episodes = maxFilm.getEpisodes();

        String[] weekDays = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
        int episodesLeft = episodes;
        int totalDays = 0;
        int weekIdx = 0;

        while (episodesLeft > 0) {
            String currentDay = weekDays[weekIdx % 7];
            for (String day : schedule) {
                if (day.equals(currentDay)) {
                    episodesLeft--;
                    break;
                }
            }
            totalDays++;
            weekIdx++;
        }

        System.out.println("Самый многосерийный фильм: " + maxFilm.getTitle());
        System.out.println("Количество серий: " + maxFilm.getEpisodes());
        System.out.println("Дни показа: [" + maxFilm.getDays() + "]");
        System.out.println("Чтобы показать весь сериал, потребуется " + totalDays + " дней (календарных, начиная с Пн).");
    }
}