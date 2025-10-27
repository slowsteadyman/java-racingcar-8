package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final String name;
    private int position;

    public Car(String name) {
        this.name = name;
        this.position = 0;
    }

    public void tryMove() {
        int whetherMove = Randoms.pickNumberInRange(0, 9);
        if (whetherMove >= 4) {
            this.position++;
        }
    }

    public void display() {
        String positionBar = "-".repeat(this.position);
        System.out.printf("%s : %s%n", this.name, positionBar);
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
