package Labs.Lab6;

class Window {
    private WindowState state;

    public Window() {
        this.state = WindowState.CLOSED;
    }

    public void open() throws Exception {
        if (state == WindowState.OPEN) {
            throw new Exception("Окно уже открыто.");
        }
        state = WindowState.OPEN;
    }

    public void close() throws Exception {
        if (state == WindowState.CLOSED) {
            throw new Exception("Окно уже закрыто.");
        }
        state = WindowState.CLOSED;
    }

    public WindowState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Окно " + state.toString().toLowerCase();
    }
}