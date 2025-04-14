package Labs.Lab7;

public abstract class PublicTransport {
    private static int instanceCount = 0;

    private String routeNumber;
    private int capacity;
    private boolean isElectric;

    public PublicTransport() {
        this.routeNumber = "Не задан";
        this.capacity = 0;
        this.isElectric = false;
        instanceCount++;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public String getRouteNumber() {
        return this.routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean getIsElectric() {
        return this.isElectric;
    }

    public void setElectric(boolean electric) {
        this.isElectric = electric;
    }

    public abstract void move();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PublicTransport other)) return false;
        return this.routeNumber.equals(other.routeNumber) &&
                this.capacity == other.capacity &&
                this.isElectric == other.isElectric;
    }

    @Override
    public String toString() {
        return "Маршрут: " + getRouteNumber() + ", Вместимость: " + getCapacity() + ", Электро: " + getIsElectric();
    }
}
