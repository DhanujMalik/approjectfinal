package com.angry.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Catapult {
    private float x, y;
    private Texture texture;

    public Catapult(float x, float y) {
        this.x = x;
        this.y = y;
        this.texture = new Texture("catapult.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, 100, 100); // Example size
    }

    public void dispose() {
        texture.dispose();
    }
}
