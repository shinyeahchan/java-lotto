package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MyPurchaseTest {

    @ParameterizedTest(name = "[{index}] {0}원 투입 (정상금액 투입 확인)")
    @ValueSource(ints = {1000, 5000, 18000, 330000})
    void 구입금액입력(int inputMoney) {
        MyPurchase myPurchase = new MyPurchase(inputMoney);
        assertThat(myPurchase.money()).isEqualTo(inputMoney);
    }

    @ParameterizedTest(name = "[{index}] {0}원 투입 (구입금액이 음수이므로 예외처리)")
    @ValueSource(ints = {-1, -500, -3000, -15000})
    void 구입금액0미만입력(int inputMoney) {
        assertThatThrownBy(() -> new MyPurchase(inputMoney)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "구입 금액 {0}원 => 로또 {1}개 구매")
    @CsvSource(value = {"0:0", "1000:1", "15000:15", "21000:21"}, delimiter = ':')
    void 로또구매개수(int inputMoney, int expectedCount) {
        MyPurchase myPurchase = new MyPurchase(inputMoney);
        assertThat(myPurchase.count()).isEqualTo(expectedCount);
    }

    @ParameterizedTest(name = "구입 금액 {0}원 / 수동 로또 {1}장 구매 => 총 {2}개 구매 (자동 {3} / 수동 {1})")
    @CsvSource(value = {"0:0:0:0", "1000:0:1:1", "15000:5:15:10"}, delimiter = ':')
    void 로또수동구매포함(int inputMoney, int manualCount, int allCount, int autoCount) {
        MyPurchase myPurchase = new MyPurchase(inputMoney, manualCount);
        assertAll(
                () -> assertThat(myPurchase.count()).isEqualTo(allCount),
                () -> assertThat(myPurchase.autoCount()).isEqualTo(autoCount),
                () -> assertThat(myPurchase.manualCount()).isEqualTo(manualCount)
        );
    }
}
