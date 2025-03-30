package Labs.Lab5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banknote[] banknotes = new Banknote[4];

        // Первые два элемента создаются с помощью конструктора по умолчанию
        banknotes[0] = new Banknote(); // Конструктор по умолчанию
        banknotes[1] = new Banknote(); // Конструктор по умолчанию

        // Запрашиваем данные для последних двух элементов
        for (int i = 2; i < banknotes.length; i++) {
            banknotes[i] = inputBanknote(scanner); // Конструктор с параметрами
        }

        System.out.println("Список купюр:");
        for (int i = 0; i < banknotes.length; i++) {
            System.out.println(banknotes[i]);
            System.out.println("Общая сумма: " + banknotes[i].calculateTotal());

            if (i < banknotes.length - 1) {
                if (banknotes[i].equals(banknotes[i + 1])) {
                    System.out.printf("Купюра %d и купюра %d равны%n", i, i + 1);
                } else {
                    System.out.printf("Купюра %d и купюра %d не равны%n", i, i + 1);
                }
            }
        }

        scanner.close(); // Закрыть сканер в конце
    }

    private static Banknote inputBanknote(Scanner scanner) {
        Banknote banknote;
        while (true) {
            try {
                System.out.print("Введите номинал купюры (1, 2, 5, 10, 20, 50, 100, 200, 500): ");
                int denomination = scanner.nextInt();

                System.out.print("Введите количество купюр (больше 0): ");
                int quantity = scanner.nextInt();

                banknote = new Banknote(denomination, quantity); // Создаем объект с параметрами
                return banknote; // Возвращаем объект Banknote
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Пожалуйста, попробуйте снова.");
                scanner.next(); // Сбросить некорректный ввод
            }
        }
    }
}