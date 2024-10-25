import java.util.List;

public class Level {
    private int number;
    private List<GameObject> gameObjects;
    private List<Bird> birds;

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }
    public void removeGameObject(GameObject obj) {
        gameObjects.remove(obj);
    }
    public boolean checkCompletion() {
        return false;
    }
}