package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 자동차가_두대_미만이면_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi", "3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 자동차가_두대_이상이면_정상_테스트() {
        assertSimpleTest(() -> {
            run("pobi,woni", "1");
            assertThat(output()).contains("최종 우승자");
        });
    }

    @Test
    void 자동차이름은_5글자_이하_아니면_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobipobi,woni", "3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 자동차이름은_앞뒤공백제거후_5글자_이하면_정상_테스트() {
        assertSimpleTest(() -> {
            run("    car1   , car2     ", "1");
            assertThat(output()).contains("car1", "car2", "최종 우승자");
        });
    }

    @Test
    void 자동차이름은_여러_단어를_허용_테스트() {
        assertSimpleTest(() -> {
            run("car 1,c a r", "1");
            assertThat(output()).contains("car 1", "c a r", "최종 우승자");
        });
    }

    @Test
    void 자동차이름이_공백만으로_구성되면_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi", "3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 시도할횟수가_1미만이면_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,woni", "0"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 시도할횟수가_숫자입력이_아니면_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,woni", "two"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 무작위값이_4이상이면_전진_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "2");
                String output = output();
                assertThat(output).contains("pobi : -", "woni : -");
                assertThat(output).contains("pobi : --", "woni : --");
            },
            MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    void 무작위값이_4미만이면_멈춤_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "2");
                String output = output();
                assertThat(output).contains("pobi : ", "woni : ");
                assertThat(output).contains("pobi : ", "woni : ");
            },
            STOP, STOP, STOP, STOP
        );
    }

    @Test
    void 단독우승자_출력_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 공동우승자_출력_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("최종 우승자 : pobi, woni");
            },
            MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
