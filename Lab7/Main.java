package Labs.Lab7;

import java.util.Scanner;

public class Main {
    private static PublicTransport[] transports = new PublicTransport[5];
    private static int count = 0;

    public static void main(String[] args) {
        addTransport(new Bus());
        addTransport(new Trolleybus());
        addTransport(new Tram());
        addTransport(new Tram());
        addTransport(new Trolleybus());

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nМеню:");
            System.out.println("1. Показать все транспортные средства");
            System.out.println("2. Проверить работу методов move()");
            System.out.println("3. Сравнить объекты");
            System.out.println("4. Кол-во созданных объектов");
            System.out.println("5. Добавить транспортное средство");
            System.out.println("6. Удалить транспортное средство");
            System.out.println("7. Изменить транспортное средство");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTransports();
                    break;
                case 2:
                    for (int i = 0; i < count; i++) {
                        if (transports[i] != null) {
                            transports[i].move();
                        }
                    }
                    break;
                case 3:
                    compareTransports();
                    break;
                case 4:
                    System.out.println("Всего объектов: " + PublicTransport.getInstanceCount());
                    break;
                case 5:
                    addNewTransport(scanner);
                    break;
                case 6:
                    removeTransport(scanner);
                    break;
                case 7:
                    updateTransport(scanner);
                    break;
            }
        } while (choice != 0);
    }

    private static void addTransport(PublicTransport transport) {
        if (count == transports.length) {
            expandArray();
        }
        transports[count++] = transport;
    }

    private static void expandArray() {
        PublicTransport[] newArray = new PublicTransport[transports.length * 2];
        System.arraycopy(transports, 0, newArray, 0, transports.length);
        transports = newArray;
    }

    private static void showTransports() {
        for (int i = 0; i < count; i++) {
            System.out.println(transports[i]);
        }
    }

    private static void compareTransports() {
        if (count < 2) {
            System.out.println("Недостаточно объектов для сравнения.");
            return;
        }
        System.out.println("Сравниваются объекты 0 и 1:");
        for (int i = 0; i < count - 1; i++) {
            System.out.println(transports[i].equals(transports[i + 1]) ? "Они равны" : "Они разные");
        }
    }

    private static void addNewTransport(Scanner scanner) {
        System.out.print("Введите тип транспорта (1. Bus, 2. Trolleybus, 3. Tram): ");
        int type = scanner.nextInt();
        PublicTransport transport = switch (type) {
            case 1 -> new Bus();
            case 2 -> new Trolleybus();
            case 3 -> new Tram();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        transport.set(scanner);
        addTransport(transport);
        System.out.println("Транспортное средство добавлено.");
    }

    private static void removeTransport(Scanner scanner) {
        System.out.print("Введите индекс для удаления (0-" + (count - 1) + "): ");
        int index = scanner.nextInt();
        if (index >= 0 && index < count) {
            for (int i = index; i < count - 1; i++) {
                transports[i] = transports[i + 1];
            }
            transports[--count] = null; // Уменьшаем количество и очищаем последний элемент
            System.out.println("Транспортное средство удалено.");
        } else {
            System.out.println("Некорректный индекс.");
        }
    }

    private static void updateTransport(Scanner scanner) {
        System.out.print("Введите индекс для изменения (0-" + (count - 1) + "): ");
        int index = scanner.nextInt();
        if (index >= 0 && index < count) {
            PublicTransport transport = transports[index];
                transport.set(scanner);

            System.out.println("Транспортное средство изменено.");
        } else {
            System.out.println("Некорректный индекс.");
        }
    }

}