package Labs.Lab5;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Date[] dates = new Date[4];
        dates[0] = new Date();
        dates[1] = new Date();
        for (int i = 2; i < dates.length; i++) {
            dates[i] = inputDate(scanner);
        }

        System.out.println("Список дат:");
        for (int i = 0; i < dates.length; i++) {
            System.out.printf("Дата: %s, Високосный год: %b%n",
                    dates[i].toString(),
                    dates[i].isLeapYear());

            if (i < dates.length - 1) {
                if (dates[i].equals(dates[i + 1])) {
                    System.out.printf("Дата %d и дата %d равны%n", i, i + 1);
                } else {
                    System.out.printf("Дата %d и дата %d не равны%n", i, i + 1);
                }
            }
        }

        for (int i = 0; i < dates.length; i++) {
            dates[i].addDays(5);
            System.out.printf("Дата %d после увеличения на 5 дней: %s%n", i, dates[i].toString());
        }
    }

    private static Date inputDate(Scanner scanner) {
        Date date;
        while (true) {
            try {
                System.out.print("Введите день (1-31): ");
                int day = scanner.nextInt();

                System.out.print("Введите месяц (1-12): ");
                int month = scanner.nextInt();

                System.out.print("Введите год: ");
                int year = scanner.nextInt();
                date=new Date(day, month, year);
                return date;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Пожалуйста, попробуйте снова.");
                scanner.next();
            }
        }
    }
}
