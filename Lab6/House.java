package Labs.Lab6;

class House {
    private Window[] windows;
    private Door[] doors;
    private int windowCount;
    private int doorCount;

    public House() {
        this.windows = new Window[2]; // начальный размер
        this.doors = new Door[2];
        this.windowCount = 0;
        this.doorCount = 0;
    }

    public void addWindow(Window window) {
        if (windowCount >= windows.length) {
            expandWindowsArray();
        }
        windows[windowCount++] = window;
    }

    public void addDoor(Door door) {
        if (doorCount >= doors.length) {
            expandDoorsArray();
        }
        doors[doorCount++] = door;
    }

    private void expandWindowsArray() {
        Window[] newArray = new Window[windows.length * 2];
        System.arraycopy(windows, 0, newArray, 0, windows.length);
        windows = newArray;
    }

    private void expandDoorsArray() {
        Door[] newArray = new Door[doors.length * 2];
        System.arraycopy(doors, 0, newArray, 0, doors.length);
        doors = newArray;
    }

    public void lockAllDoors() {
        for (int i = 0; i < doorCount; i++) {
            doors[i].lock();
        }
    }

    public void unlockAllDoors() {
        for (int i = 0; i < doorCount; i++) {
            doors[i].unlock();
        }
    }

    public void openAllDoors() {
        for (int i = 0; i < doorCount; i++) {
            try {
                doors[i].open();
            } catch (Exception e) {
                System.out.println("Не удалось открыть дверь #" + (i + 1) + ": " + e.getMessage());
            }
        }
    }


    public void closeAllDoors() {
        for (int i = 0; i < doorCount; i++) {
            doors[i].close();
        }
    }

    public int getWindowCount() {
        return windowCount;
    }

    public int getDoorCount() {
        return doorCount;
    }

    public void displayInfo() {
        System.out.println("Количество окон: " + getWindowCount() + ", Количество дверей: " + getDoorCount());
        for (int i = 0; i < windowCount; i++) {
            System.out.println(" - " + windows[i]);
        }
        for (int i = 0; i < doorCount; i++) {
            System.out.println(" - " + doors[i]);
        }
    }
    public void openAllWindows() throws Exception {
        for (int i = 0; i < windowCount; i++) {
            windows[i].open();
        }
    }

    public void closeAllWindows() throws Exception {
        for (int i = 0; i < windowCount; i++) {
            windows[i].close();
        }
    }

}
