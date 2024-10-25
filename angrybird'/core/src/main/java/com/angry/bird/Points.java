public class Points {
    private int currPoints;

    public void addPoints(int points) {
        currPoints += points;
    }
    public void displayFinalPoints() {
    }
    public int pointsGainedInLevel() {
        return currPoints;
    }
    public void resetPoints() {
        currPoints = 0;
    }
}