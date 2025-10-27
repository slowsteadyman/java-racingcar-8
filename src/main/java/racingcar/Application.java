package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        List<String> carNames = validateCarNames(Console.readLine());

        System.out.println("시도할 횟수는 몇 회인가요?");
        int tryNum = validateTryNum(Console.readLine());

        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < carNames.size(); i++) {
            cars.add(new Car(carNames.get(i)));
        }

        System.out.println("실행 결과");
        for (int i = 0; i < tryNum; i++) {
            raceOneRound(cars);
        }

        winnerAnnouncement(cars);
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

    private static Integer validateTryNum(String tryNum) {
        int validTryNum;

        try {
            validTryNum = Integer.parseInt(tryNum);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        if (validTryNum < 1) {
            throw new IllegalArgumentException();
        }

        return validTryNum;
    }

    private static void raceOneRound(List<Car> cars) {
        for (Car car : cars) {
            car.move(Randoms.pickNumberInRange(0, 9));
            car.display();
        }
        System.out.println();
    }

    private static void winnerAnnouncement(List<Car> cars) {
        cars.sort(Comparator.comparing(Car::getPosition).reversed());

        List<String> winnerNames = new ArrayList<>();
        int winnerPosition = cars.get(0).getPosition();
        for (Car car : cars) {
            if (car.getPosition() == winnerPosition) {
                winnerNames.add(car.getName());
            }
        }

        System.out.print("최종 우승자 : ");
        System.out.println(String.join(", ", winnerNames));
    }
}
