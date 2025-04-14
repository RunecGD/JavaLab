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
        System.out.print("Введите тип транспорта (Bus, Trolleybus, Tram): ");
        String type = scanner.next();
        PublicTransport transport = null;

        if (type.equalsIgnoreCase("Bus")) {
            transport = new Bus();
            System.out.print("Введите номер маршрута: ");
            String routeNumber = scanner.next();
            System.out.print("Введите вместимость: ");
            int capacity = scanner.nextInt();
            System.out.print("Электрический? (true/false): ");
            boolean isElectric = scanner.nextBoolean();
            System.out.print("Есть заряд? (true/false): ");
            boolean hasCharge = scanner.nextBoolean();
            transport.setRouteNumber(routeNumber);
            transport.setCapacity(capacity);
            transport.setElectric(isElectric);
            ((Bus) transport).setHasCharge(hasCharge);

        } else if (type.equalsIgnoreCase("Trolleybus")) {
            transport = new Trolleybus();
            System.out.print("Введите номер маршрута: ");
            String routeNumber = scanner.next();
            System.out.print("Введите вместимость: ");
            int capacity = scanner.nextInt();
            System.out.print("Электрический? (true/false): ");
            boolean isElectric = scanner.nextBoolean();
            System.out.print("Введите напряжение: ");
            int voltage = scanner.nextInt();
            transport.setRouteNumber(routeNumber);
            transport.setCapacity(capacity);
            transport.setElectric(isElectric);
            ((Trolleybus) transport).setVoltage(voltage);

        } else if (type.equalsIgnoreCase("Tram")) {
            transport = new Tram();
            System.out.print("Введите номер маршрута: ");
            String routeNumber = scanner.next();
            System.out.print("Введите вместимость: ");
            int capacity = scanner.nextInt();
            System.out.print("Электрический? (true/false): ");
            boolean isElectric = scanner.nextBoolean();
            System.out.print("Введите количество вагонов: ");
            int numberOfWagons = scanner.nextInt();
            transport.setRouteNumber(routeNumber);
            transport.setCapacity(capacity);
            transport.setElectric(isElectric);
            ((Tram) transport).setNumberOfWagons(numberOfWagons);
        }

        if (transport != null) {
            addTransport(transport);
            System.out.println("Транспортное средство добавлено.");
        }
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
            System.out.print("Введите новый номер маршрута: ");
            String routeNumber = scanner.next();
            System.out.print("Введите новую вместимость: ");
            int capacity = scanner.nextInt();
            System.out.print("Электрический? (true/false): ");
            boolean isElectric = scanner.nextBoolean();

            if (transport instanceof Bus) {
                System.out.print("Есть зарядка? (true/false): ");
                boolean hasCharge = scanner.nextBoolean();
                transports[index] = new Bus();
                transports[index].setRouteNumber(routeNumber);
                transports[index].setCapacity(capacity);
                transports[index].setElectric(isElectric);
                ((Bus) transports[index]).setHasCharge(hasCharge);

            } else if (transport instanceof Trolleybus) {
                System.out.print("Введите новое напряжение: ");
                int voltage = scanner.nextInt();
                transports[index] = new Trolleybus();
                transports[index].setRouteNumber(routeNumber);
                transports[index].setCapacity(capacity);
                transports[index].setElectric(isElectric);
                ((Trolleybus) transports[index]).setVoltage(voltage);

            } else if (transport instanceof Tram) {
                System.out.print("Введите новое количество вагонов: ");
                int numberOfWagons = scanner.nextInt();
                transports[index] = new Tram();
                transports[index].setRouteNumber(routeNumber);
                transports[index].setCapacity(capacity);
                transports[index].setElectric(isElectric);
                ((Tram) transports[index]).setNumberOfWagons(numberOfWagons);
            }

            System.out.println("Транспортное средство изменено.");
        } else {
            System.out.println("Некорректный индекс.");
        }
    }
}