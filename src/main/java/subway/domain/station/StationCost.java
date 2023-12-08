package subway.domain.station;

public class StationCost {
    private final int distance;
    private final int arrivalTime;

    public StationCost(int distance, int arrivalTime) {
        this.distance = distance;
        this.arrivalTime = arrivalTime;
    }

    public int getDistance() {
        return distance;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}
