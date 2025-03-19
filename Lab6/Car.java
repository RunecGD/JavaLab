package Labs.Lab6;

class Car {
    private String brand;
    private Engine engine;
    private Wheel[] wheels;

    public Car(String brand, Engine engine, Wheel[] wheels) {
        this.brand = brand;
        this.engine = engine;
        this.wheels = wheels;
    }

    public void drive() {
        System.out.println("The car is driving.");
    }

    public void refuel() {
        System.out.println("The car is refueling.");
    }

    public void changeWheel(int index, Wheel newWheel) {
        if (index >= 0 && index < wheels.length) {
            wheels[index] = newWheel;
            System.out.println("Changed wheel at position " + index + " to " + newWheel.getType());
        } else {
            System.out.println("Invalid wheel index.");
        }
    }

    public void displayBrand() {
        System.out.println("Car brand: " + brand);
    }
}
