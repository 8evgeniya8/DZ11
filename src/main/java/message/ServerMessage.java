package message;

public enum ServerMessage {
    WELCOME("Сервер прослуховує порт"),
    CLIENT_CONNECTED("Клієнт підключений."),

    // PalianytsiaQuestionCommand - ВКАЗАНО ЦИФРУ №2 (PALIANYTSIA_QUESTION)
    PALIANYTSIA_QUESTION("Что такое `паляниця` ? : " + " 1. Салат, " + " 2. Хлеб, " + " 3. Картошка,"),
    INCORRECT_ANSWER("Путін хуйло! УКРАЇНА ПЕРЕМОЖЕ "),
    CONFIRMATION_RECEIPT_NOTICE("Ваше повідомлення отримано."),
    DATE_TIME("Поточна дата і час: "),

    FAREWELL("До побачення!");

    private final String message;

    ServerMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
