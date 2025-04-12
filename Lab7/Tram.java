package Labs.Lab7;

public class Tram extends PublicTransport {
    private int numberOfWagons;

    public Tram(String routeNumber, int capacity, boolean isElectric, int numberOfWagons) {
        super(routeNumber, capacity, isElectric);
        setNumberOfWagons(numberOfWagons);
    }

    public int getNumberOfWagons() {
        return numberOfWagons;
    }

    public void setNumberOfWagons(int numberOfWagons) {
        this.numberOfWagons = numberOfWagons;
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

