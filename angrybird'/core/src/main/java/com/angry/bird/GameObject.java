package com.angry.bird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    protected Vector2 position;

    public GameObject(float x, float y) {
        this.position = new Vector2(x, y);
    }

    public float getX() {
        return position.x;
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public float getY() {
        return position.y;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();
}
