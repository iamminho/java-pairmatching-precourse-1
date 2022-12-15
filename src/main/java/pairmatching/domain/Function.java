package pairmatching.domain;

public class Function {
    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 3;
    private final int QUIT_NUMBER = 4;
    private final String QUIT_COMMAND = "Q";
    private final String INVALID_QUIT_COMMAND = "[ERROR] 종료명령은 Q만 가능합니다.";
    private final String INVALID_NUMBER_BOUNDARY = "[ERROR] 명령숫자는 1과 3사이만 가능합니다.";
    private int number;

    public Function(String command) {
        this.number = toNumber(command);
    }

    private int toNumber(String command) {
        try {
            int number = Integer.parseInt(command);
            validateBoundary(number);
            return number;
        } catch (NumberFormatException e) {
            validateQuit(command);
            return QUIT_NUMBER;
        }
    }

    private void validateQuit(String command) {
        if (command.equals(QUIT_COMMAND)) {
            throw new IllegalArgumentException(INVALID_QUIT_COMMAND);
        }
    }

    private void validateBoundary(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_NUMBER_BOUNDARY);
        }
    }

    public int getNumber() {
        return this.number;
    }
}
