package Labs.Lab7;

import java.util.Scanner;

public class Tram extends PublicTransport {
    private int numberOfWagons;

    public Tram() {
        super();
        this.numberOfWagons = 0; // Значение по умолчанию
    }

    public int getNumberOfWagons() {
        return numberOfWagons;
    }

    public void setNumberOfWagons(int numberOfWagons) {
        this.numberOfWagons = numberOfWagons;
    }
    public void set(Scanner scanner) {
        System.out.print("Введите количество вагонов: ");
        int numberOfWagons = scanner.nextInt();
        setNumberOfWagons(numberOfWagons);
        super.set(scanner);

    }
    @Override
    public void move() {
        System.out.println("Трамвай едет по маршруту " + getRouteNumber());
    }

    @Override
    public String toString() {
        return "Трамвай — " + super.toString() + ", Вагонов: " + getNumberOfWagons();
    }
}

