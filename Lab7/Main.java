package Labs.Lab7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PublicTransport[] transports = new PublicTransport[5];
        transports[0] = new Bus("3", 50, false, false);
        transports[1] = new Trolleybus("1", 60, true, 600);
        transports[2] = new Tram("12", 80, true, 3);
        transports[3] = new Bus("28", 55, false, false);
        transports[4] = new Tram("13", 90, true, 4);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nМеню:");
            System.out.println("1. Показать все транспортные средства");
            System.out.println("2. Проверить работу методов move()");
            System.out.println("3. Сравнить объекты");
            System.out.println("4. Кол-во созданных объектов");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    for (PublicTransport t : transports)
                        System.out.println(t);
                    break;
                case 2:
                    for (PublicTransport t : transports)
                        t.move();
                    break;
                case 3:
                    System.out.println("Сравниваются объекты 0 и 1:");
                    System.out.println(transports[0].equals(transports[1]) ? "Они равны" : "Они разные");
                    break;
                case 4:
                    System.out.println("Всего объектов: " + PublicTransport.getInstanceCount());
                    break;
            }
        } while (choice != 0);
    }
}

