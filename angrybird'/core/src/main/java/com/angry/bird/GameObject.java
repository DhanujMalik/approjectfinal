public abstract class GameObject {
    protected Vector position;
    protected Vector size;

    public abstract void update();
    public abstract void render();
}