package Labs.Lab5;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Date[] dates = new Date[4]; 
                for (int i = 0; i < dates.length; i++) {
            dates[i] = inputDate(scanner);         }

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
        Date date = new Date();         while (true) {
            try {
                System.out.print("Введите день (1-31): ");
                int day = scanner.nextInt();
                date.setDay(day);

                System.out.print("Введите месяц (1-12): ");
                int month = scanner.nextInt();
                date.setMonth(month);

                System.out.print("Введите год: ");
                int year = scanner.nextInt();
                date.setYear(year);

                return date;             } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Пожалуйста, попробуйте снова.");
                scanner.next();             }
        }
    }
}
