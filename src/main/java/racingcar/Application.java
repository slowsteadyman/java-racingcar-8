package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carNames = Console.readLine();
        List<String> participants = validateCarNames(carNames);

        System.out.println("시도할 횟수는 몇 회인가요?");
        String tryNum = Console.readLine();
        validateTryNum(tryNum);
    }

    private static List<String> validateCarNames(String carNames) {
        String[] participateCandidates = carNames.split(",", -1);
        List<String> participants = new ArrayList<>();

        for  (String participateCandidate : participateCandidates) {
            participateCandidate = participateCandidate.trim();
            if (participateCandidate.isEmpty() || participateCandidate.length() > 5) {
                throw new IllegalArgumentException();
            }
            participants.add(participateCandidate);
        }

        if (participants.size() < 2) {
            throw new IllegalArgumentException();
        }

        return participants;
    }

    private static void validateTryNum(String tryNum) {
        if (!tryNum.matches("[0-9]+")) {
            throw new IllegalArgumentException();
        }
    }
}
