package stringcalculator;

import java.util.List;

public class StringCalculator {
    private final List<Double> numbers;
    private final List<String> operators;
    private double result;

    public StringCalculator(String input) {
        Parser parser = new Parser(input);
        this.numbers = parser.numbers();
        this.operators = parser.operators();
        this.result = numbers.get(0);
    }

    public Double calculate() {
        for (int i = 0; i < operators.size(); i++) {
            calculateEach(operators.get(i), numbers.get(i + 1));
        }
        return result;
    }

    private void calculateEach(String symbol, Double number) {
        Operation operation = Operation.find(symbol);
        result = operation.calculate(result, number);
    }

    public Double getResult() {
        return result;
    }
}
