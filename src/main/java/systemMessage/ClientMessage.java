package systemMessage;

public enum ClientMessage {
    MESSAGE_FROM_SERVER("Повідомлення від сервера: "),
    MESSAGE_CLIENT("Введіть своє повідомлення: "),
    CONNECTED_CLOSE("Connection closed.");
    private final String message;

    ClientMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
