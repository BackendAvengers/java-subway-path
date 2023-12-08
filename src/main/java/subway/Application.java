package subway;

import java.util.Scanner;
import subway.controller.MainController;
import subway.domain.station.StationRepository;
import subway.init.StationDataLoader;
import subway.io.ConsoleReader;
import subway.io.ConsoleWriter;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        init();

        MainController mainController = getMainController();
        mainController.run();
    }

    private static void init() {
        StationDataLoader.saveAllStations();
        StationDataLoader.saveAllLines();
    }

    private static MainController getMainController() {
        final Scanner scanner = new Scanner(System.in);

        ConsoleReader consoleReader = new ConsoleReader(scanner);
        ConsoleWriter consoleWriter = new ConsoleWriter();

        InputView inputView = new InputView(consoleReader, consoleWriter);
        OutputView outputView = new OutputView(consoleWriter);

        StationRepository stationRepository = new StationRepository();

        return new MainController(inputView, outputView, stationRepository);
    }
}
