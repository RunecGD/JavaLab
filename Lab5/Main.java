package Labs.Lab5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banknote[] banknotes = new Banknote[4]; // Массив для хранения 4 купюр

        // Заполнение массива объектами Banknote
        for (int i = 0; i < banknotes.length; i++) {
            banknotes[i] = inputBanknote(scanner); // Вводим купюру через отдельный метод
        }

        System.out.println("Список купюр:");
        for (int i = 0; i < banknotes.length; i++) {
            System.out.printf("Номинал: %d, Количество: %d, Сумма: %d%n",
                    banknotes[i].getDenomination(),
                    banknotes[i].getQuantity(),
                    banknotes[i].calculateTotal());

            if (i < banknotes.length - 1) {
                if (banknotes[i].equals(banknotes[i + 1])) {
                    System.out.printf("Купюра %d и купюра %d равны%n", i, i + 1);
                } else {
                    System.out.printf("Купюра %d и купюра %d не равны%n", i, i + 1);
                }
            }
        }
    }

    private static Banknote inputBanknote(Scanner scanner) {
        Banknote banknote = new Banknote(); // Создаем объект Banknote
        while (true) {
            try {
                System.out.print("Введите номинал купюры (1, 2, 5, 10, 20, 50, 100, 200, 500): ");
                int denomination = scanner.nextInt();
                banknote.setDenomination(denomination);

                System.out.print("Введите количество купюр (больше 0): ");
                int quantity = scanner.nextInt();
                banknote.setQuantity(quantity);

                return banknote; // Возвращаем созданную купюру
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Пожалуйста, попробуйте снова.");
                scanner.next(); // Сбрасываем некорректный ввод
            }
        }
    }
}
