package Labs.Lab7;

public class Trolleybus extends PublicTransport {
    private int voltage;

    public Trolleybus() {
        super();
        this.voltage = 0; // Значение по умолчанию
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public void move() {
        System.out.println("Троллейбус едет по маршруту " + getRouteNumber());
    }

    @Override
    public String toString() {
        return "Троллейбус — " + super.toString() + ", Напряжение: " + getVoltage() + "В";
    }
}

