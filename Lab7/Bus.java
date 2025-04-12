package Labs.Lab7;

public class Bus extends PublicTransport {
    private boolean hasWiFi;

    public Bus(String routeNumber, int capacity, boolean isElectric, boolean hasWiFi) {
        super(routeNumber, capacity, isElectric);
        this.hasWiFi = hasWiFi;
    }

    public boolean hasWiFi() {
        return hasWiFi;
    }

    public void setHasWiFi(boolean hasWiFi) {
        this.hasWiFi = hasWiFi;
    }

    @Override
    public void move() {
        System.out.println("Автобус едет по маршруту " + getRouteNumber());
    }

    @Override
    public String toString() {
        return "Автобус — " + super.toString() + ", WiFi: " + hasWiFi;
    }
}
