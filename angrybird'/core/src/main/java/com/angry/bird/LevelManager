package com.angry.bird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelManager {
    private Level currentLevel;

    public void setLevel(Level level) {
        if (currentLevel != null) {
            currentLevel.dispose();
        }
        currentLevel = level;
        currentLevel.initialize();

    }

    public void render(SpriteBatch batch) {
        if (currentLevel != null) {
            currentLevel.render(batch);
        }
    }

    public void update(float delta) {
        if (currentLevel != null) {
            currentLevel.update(delta);
        }
    }

    public void dispose() {
        if (currentLevel != null) {
            currentLevel.dispose();
        }
    }
}
