package Labs.Lab9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookCollectionApp {
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBooks();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать содержимое коллекции");
            System.out.println("2. Добавить элемент в коллекцию");
            System.out.println("3. Изменить элемент в коллекции");
            System.out.println("4. Удалить элемент из коллекции");
            System.out.println("5. Показать книги А.С. Пушкина");
            System.out.println("6. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    showBooks();
                    break;
                case 2:
                    addBook(scanner);
                    break;
                case 3:
                    editBook(scanner);
                    break;
                case 4:
                    removeBook(scanner);
                    break;
                case 5:
                    showPushkinBooks();
                    break;
                case 6:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте еще раз.");
            }
        }
    }

    private static void initializeBooks() {
        books.add(new Book("Руслан и Людмила", "А.С. Пушкин", 100));
        books.add(new Book("Евгений Онегин", "А.С. Пушкин", 300));
        books.add(new Book("Капитанская дочка", "А.С. Пушкин", 150));
        books.add(new Book("1984", "Джордж Оруэлл", 328));
        books.add(new Book("Мастер и Маргарита", "Михаил Булгаков", 400));
        books.add(new Book("Война и мир", "Лев Толстой", 1225));
        books.add(new Book("Преступление и наказание", "Фёдор Достоевский", 430));
        books.add(new Book("Гарри Поттер и философский камень", "Дж.К. Роулинг", 223));
        books.add(new Book("Убить пересмешника", "Харпер Ли", 281));
        books.add(new Book("Старик и море", "Эрнест Хемингуэй", 128));
        books.add(new Book("451 градус по Фаренгейту", "Рэй Брэдбери", 158));
        books.add(new Book("Тихий Дон", "Михаил Шолохов", 640));
        books.add(new Book("На дне", "Максим Горький", 100));
        books.add(new Book("Маленький принц", "Антуан де Сент-Экзюпери", 96));
        books.add(new Book("Сияние", "Стивен Кинг", 447));
    }

    private static void showBooks() {
        System.out.println("Содержимое коллекции:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void addBook(Scanner scanner) {
        Book newBook = new Book();
       newBook.set(scanner);

        books.add(newBook);
        System.out.println("Книга добавлена.");
    }

    private static void editBook(Scanner scanner) {
        System.out.print("Введите индекс книги для изменения (0-" + (books.size() - 1) + "): ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        if (index < 0 || index >= books.size()) {
            System.out.println("Неверный индекс.");
            return;
        }
        // Изменение существующей книги
        Book book = books.get(index);
        book.set(scanner);
        System.out.println("Книга изменена.");
    }

    private static void removeBook(Scanner scanner) {
        System.out.print("Введите индекс книги для удаления (0-" + (books.size() - 1) + "): ");
        int index = scanner.nextInt();
        if (index < 0 || index >= books.size()) {
            System.out.println("Неверный индекс.");
            return;
        }
        books.remove(index);
        System.out.println("Книга удалена.");
    }

    private static void showPushkinBooks() {
        List<Book> pushkinBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals("А.С. Пушкин")) {
                pushkinBooks.add(book);
            }
        }

        pushkinBooks.sort(Comparator.comparing(Book::getTitle));

        System.out.println("Книги А.С. Пушкина в алфавитном порядке:");
        for (Book book : pushkinBooks) {
            System.out.println(book);
        }
    }
}