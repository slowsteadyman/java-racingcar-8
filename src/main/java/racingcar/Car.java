package racingcar;

public class Car {
    private String name;
    private int position;

    public Car(String name) {
        this.name = name;
        this.position = 0;
    }

    public void move(int whetherMove) {
        if (whetherMove >= 4) {
            this.position += 1;
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
