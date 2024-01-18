package message;

public enum ClientMessage {
    CONNECTED_SERVER("Підключено до сервера. Введіть своє повідомлення:");


    private final String message;

    ClientMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}