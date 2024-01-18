package message;

public enum ErrorMessage {
    CONNECTION_ERROR("Під час спроби підключитися до "),
    ERROR_ATTEMPTING_CONNECT_PORTCONNECTION_ERROR("Під час спроби прослухати через порт виявлено виняток "),
    FAILED_LISTEN_PORT("Не вдалося прослухати порт ");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
