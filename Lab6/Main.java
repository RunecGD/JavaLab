package Labs.Lab6;

import java.util.Scanner;
public class Main {
    private static House house = new House();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить окно");
            System.out.println("2. Добавить дверь");
            System.out.println("3. Показать информацию о доме");
            System.out.println("4. Заблокировать все двери");
            System.out.println("5. Разблокировать все двери");
            System.out.println("6. Открыть двери");
            System.out.println("7. Закрыть двери");
            System.out.println("8. Закрыть окна");
            System.out.println("9. Открыть окна");
            System.out.println("10. Выход");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    createWindow();
                    break;
                case 2:
                    createDoor();
                    break;
                case 3:
                    house.displayInfo();
                    break;
                case 4:
                    house.lockAllDoors();
                    System.out.println("Все двери заблокированы.");
                    break;
                case 5:
                    house.unlockAllDoors();
                    System.out.println("Все двери разблокированы.");
                    break;
                case 6:
                    house.openAllDoors();
                    System.out.println("Все двери открыты");
                    break;
                case 7:
                    house.closeAllDoors();
                    System.out.println("Все двери закрыты");
                    break;
                case 8:
                    house.closeAllWindows();
                    System.out.println("Все окна закрыты.");
                    break;
                case 9:
                    house.openAllWindows();
                    System.out.println("Все окна открыты.");
                    break;
                case 10:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private static void createWindow() {
        Window window = new Window();
        house.addWindow(window);
        System.out.println("Добавлено новое окно.");
    }

    private static void createDoor() {
        Door door = new Door();
        house.addDoor(door);
        System.out.println("Добавлена новая дверь.");
    }
}