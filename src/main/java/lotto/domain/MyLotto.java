package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyLotto {
    private final List<Integer> numbers;
    private int matchingCount;

    public MyLotto(List<Integer> numbers) {
        this.numbers = numbers;
        this.matchingCount = 0;
    }

    public static MyLotto auto() {
        List<Integer> autoNumbers = new ArrayList<>(LottoRule.NUMBER_RANGE);
        Collections.shuffle(autoNumbers);
        autoNumbers = autoNumbers.subList(0, LottoRule.CHOICE_COUNT);
        Collections.sort(autoNumbers);
        return new MyLotto(autoNumbers);
    }

    public static MyLotto manual(String inputString) {
        String[] pickedNumbers = inputString.split(LottoRule.SPLIT_DELIMITER);
        List<Integer> manualNumbers = new ArrayList<>();
        for (String pickNumber : pickedNumbers) {
            //TODO : 입력된 숫자 유효성 검사 로직 필요 (숫자 타입, 숫자 개수, 숫자 범위)
            manualNumbers.add(Integer.valueOf(pickNumber));
        }
        Collections.sort(manualNumbers);
        return new MyLotto(manualNumbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public List<Integer> numbers() {
        return numbers;
    }

    public void checkMatchingNumbers(WinLotto winLotto) {
        for (int i = 0; i < LottoRule.CHOICE_COUNT; i++) {
            if (numbers.contains(winLotto.number(i))) {
                matchingCount++;
            }
        }
    }

    public int matchingCount() {
        return matchingCount;
    }
}
