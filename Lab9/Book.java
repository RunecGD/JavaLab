package Labs.Lab9;

import java.util.Scanner;

public class Book {
    private String title;
    private String author;
    private int pageCount;

    public Book(String title, String author, int pageCount) {
        setTitle(title);
        setAuthor(author);
        setPageCount(pageCount);
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void set(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Введите количество страниц: ");
        int pageCount = scanner.nextInt();
        scanner.nextLine();
        setTitle(title);
        setAuthor(author);
        setPageCount(pageCount);
    }



    @Override
    public String toString() {
        return getTitle() + " by " + getAuthor() + " (" + getPageCount() + " pages)";
    }
}