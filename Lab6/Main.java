package Labs.Lab6;

public class Main {
    public static void main(String[] args) {
        Wheel frontLeft = new Wheel("Front Left");
        Wheel frontRight = new Wheel("Front Right");
        Wheel rearLeft = new Wheel("Rear Left");
        Wheel rearRight = new Wheel("Rear Right");
        Wheel[] wheels = {frontLeft, frontRight, rearLeft, rearRight};

        Engine engine = new Engine("V8");

        Car car = new Car("Toyota", engine, wheels);

        car.displayBrand();
        car.drive();
        car.refuel();
        car.changeWheel(0, new Wheel("New Front Left"));
        car.changeWheel(4, new Wheel("Invalid Wheel"));
    }
}