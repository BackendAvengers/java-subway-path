package subway;

import java.util.Scanner;
import subway.init.InitialSet;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        InitialSet.saveAllStations();
        InitialSet.saveAllLines();

    }
}
