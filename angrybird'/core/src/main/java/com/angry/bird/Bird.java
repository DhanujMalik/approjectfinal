package com.angry.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Bird {
    protected Vector2 position;
    protected Texture texture;
    protected Vector2 velocity;

    public Bird(float x, float y, String texturePath) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.texture = new Texture(texturePath);
    }

    public void render(SpriteBatch batch, float width, float height) {
        batch.draw(texture, position.x, position.y, width, height);
    }

    public boolean checkCollision(Pig pig) {
        return false;
    }


    public void update(float delta) {
        position.add(velocity.x * delta, velocity.y * delta);
        velocity.y -= 9.8f * delta; // Simulate gravity
    }

    public void fire(float angle, float power) {
        velocity.x = (float) (power * Math.cos(Math.toRadians(angle)));
        velocity.y = (float) (power * Math.sin(Math.toRadians(angle)));
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        texture.dispose();
    }

    public void render(SpriteBatch batch) {
    }
}
