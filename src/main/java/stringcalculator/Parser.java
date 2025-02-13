package stringcalculator;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final String NUMBER_REGEX = "^[0-9]*$";
    private final String OPERATION_REGEX = "^[+\\-*/]+$";
    private final List<Double> numbers = new ArrayList<>();
    private final List<Operation> operators = new ArrayList<>();

    public Parser(String input) {
        validateNotBlank(input);
        String[] strings = splitInput(input);
        for (int i = 0; i < strings.length; i++) {
            separateInput(strings, i);
        }
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }
    }

    private String[] splitInput(String input) {
        return input.split(" ");
    }

    private void separateInput(String[] strings, int i) {
        if (i % 2 == 1) {
            validateSymbol(strings[i]);
            operators.add(Operation.find(strings[i]));
            return;
        }
        validateNumber(strings[i]);
        numbers.add(Double.valueOf(strings[i]));
    }

    private void validateSymbol(String symbol) {
        if (!symbol.matches(OPERATION_REGEX)) {
            throw new IllegalArgumentException("사칙연산 기호 외의 값을 입력하였습니다.");
        }
    }

    private void validateNumber(String number) {
        if (!number.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("숫자 외의 값을 입력하였습니다.");
        }
    }

    public List<Double> numbers() {
        return numbers;
    }

    public List<Operation> operators() {
        return operators;
    }
}
