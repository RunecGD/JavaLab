package Labs.Lab6;
class Door {
    private DoorState state;

    public Door() {
        this.state = DoorState.CLOSED;
    }

    public void close() {
        state = DoorState.CLOSED;
    }

    public void open() throws Exception {
        if (state == DoorState.CLOSED) {
            state = DoorState.OPEN;
        } else if (state == DoorState.LOCKED) {
            throw new Exception("Дверь заблокирована.");
        } else if (state == DoorState.UNLOCKED) {
            state = DoorState.OPEN;
        }
    }

    public void lock() {
        state = DoorState.LOCKED;
    }

    public void unlock() {
        if (state == DoorState.LOCKED) {
            state = DoorState.UNLOCKED;
        }
    }

    public DoorState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Дверь " + getState().toString().toLowerCase();
    }
}