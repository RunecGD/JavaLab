package Labs.Lab7;

import java.util.Scanner;

public class Bus extends PublicTransport {
    private boolean hasCharge;

    public Bus() {
        super();
        this.hasCharge = false; // Значение по умолчанию
    }

    public boolean getHasCharge() {
        return hasCharge;
    }
    @Override
    public void set(Scanner scanner){
        System.out.print("Есть заряд? (true/false): ");
        boolean hasCharge = scanner.nextBoolean();
        setHasCharge(hasCharge);
        super.set(scanner);
    }
    public void setHasCharge(boolean hasCharge) {
        this.hasCharge = hasCharge;
    }

    @Override
    public void move() {
        System.out.println("Автобус едет по маршруту " + getRouteNumber());
    }

    @Override
    public String toString() {
        return "Автобус — " + super.toString() + ", Charge: " + getHasCharge();
    }
}
