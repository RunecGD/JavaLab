package Labs.Lab7;

public class Bus extends PublicTransport {
    private boolean hasCharge;

    public Bus() {
        super();
        this.hasCharge = false; // Значение по умолчанию
    }

    public boolean getHasCharge() {
        return hasCharge;
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
