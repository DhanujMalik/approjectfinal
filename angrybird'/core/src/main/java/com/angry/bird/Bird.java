public abstract class Bird extends GameObject {
    private String color;
    private int damage;
    private float speed;
    protected SpecialAbility specialAbility;

    public void fire() {
    }

    public Trajectory calculateTrajectory() {
        return new Trajectory();
    }
    public abstract void activateSpecialAbility();
}

