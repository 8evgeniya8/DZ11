package systemMessage;

public enum ServerMessage {
    CLIENT_UP("Клієнт підключився: "),
    MESSAGE_CLIENT("Повідомлення від клієнта: "),
    PALYANITSA_QUESTION("Що таке паляниця?"),
    HELLO("hello"),
    CHOICE_1("1. Хліб"),
    CHOICE_2("2. Український дрон"),
    CHOICE_3("3. Солодка булочка"),
    CORRECT_ANSWER("Вірно "),
    INCORRECT_ANSWER(" Путін хуйло! "),
    TRY_AGAIN("Спробуй іншим разом");

    private final String message;

    ServerMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}