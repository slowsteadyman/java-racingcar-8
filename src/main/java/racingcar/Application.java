package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> carNames = readCarNames();
        List<String> validCarNames = validateCarNames(carNames);
        String tryNum = readTryNum();
        int validTryNum = validateTryNum(tryNum);

        List<Car> cars = createCars(validCarNames);

        progressRace(cars, validTryNum);
        List<String> winnerNames = findWinnerNames(cars);
        winnerAnnouncement(winnerNames);
    }

    private static List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carNames = Console.readLine();
        return List.of(carNames.split(",", -1));
    }

    private static List<String> validateCarNames(List<String> carNames) {
        List<String> validCarNames = new ArrayList<>();

        for (String carName : carNames) {
            carName =  carName.trim();
            if (carName.isEmpty() || carName.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 공백으로 구성되어서는 안 되고 5글자 이하여야 합니다.");
            }
            validCarNames.add(carName);
        }

        if (validCarNames.size() < 2) {
            throw new IllegalArgumentException("자동차 이름을 2대 이상 입력해야 합니다.");
        }

        return validCarNames;
    }

    private static String readTryNum() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        return Console.readLine();
    }

    private static int validateTryNum(String tryNum) {
        int validTryNum;

        try {
            validTryNum = Integer.parseInt(tryNum);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자로 입력되어야 합니다.");
        }

        if (validTryNum < 1) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }

        return validTryNum;
    }

    private static List<Car> createCars(List<String> carNames) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < carNames.size(); i++) {
            cars.add(new Car(carNames.get(i)));
        }
        return cars;
    }

    private static void progressRace(List<Car> cars, int tryNum) {
        System.out.println("실행 결과");
        for (int i = 0; i < tryNum; i++) {
            raceOneRound(cars);
        }
    }

    private static void raceOneRound(List<Car> cars) {
        for (Car car : cars) {
            car.tryMove();
            car.display();
        }
        System.out.println();
    }

    private static List<String> findWinnerNames(List<Car> cars) {
        cars.sort(Comparator.comparing(Car::getPosition).reversed());

        List<String> winnerNames = new ArrayList<>();
        int winnerPosition = cars.get(0).getPosition();
        for (Car car : cars) {
            if (car.getPosition() == winnerPosition) {
                winnerNames.add(car.getName());
            }
        }

        return winnerNames;
    }

    private static void winnerAnnouncement(List<String> winnerNames) {
        System.out.print("최종 우승자 : ");
        System.out.println(String.join(", ", winnerNames));
    }
}
