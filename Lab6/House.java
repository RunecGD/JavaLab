package Labs.Lab6;

import java.util.ArrayList;
import java.util.List;

class House {
    private List<Window> windows;
    private List<Door> doors;

    public House() {
        this.windows = new ArrayList<>();
        this.doors = new ArrayList<>();
    }

    public void addWindow(Window window) {
        windows.add(window);
    }

    public void addDoor(Door door) {
        doors.add(door);
    }

    public void lockAllDoors() {
        for (Door door : doors) {
            door.lock();
        }
    }

    public int getWindowCount() {
        return windows.size();
    }

    public int getDoorCount() {
        return doors.size();
    }

    public void displayInfo() {
        System.out.println("Количество окон: " + getWindowCount() + ", Количество дверей: " + getDoorCount());
        for (int i = 0; i < windows.size(); i++) {
            System.out.println(" - " + windows.get(i));
        }
        for (int i = 0; i < doors.size(); i++) {
            System.out.println(" - " + doors.get(i));
        }
    }

    public void unlockallDoors() {
        for (Door door : doors) {
            door.unlock();
        }
    }
    public void openAllDoors() throws Exception {
        for (Door door : doors) {
            door.open();
        }
    }
    public void closeAllDoors() throws Exception {
        for (Door door : doors) {
            door.close();
        }
    }

}
