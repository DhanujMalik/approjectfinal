package com.angry.bird;

import com.badlogic.gdx.math.Vector2;

public class Trajectory {
    public static final float GRAVITY = -9.8f; // Gravitational constant

    private Vector2 startPosition;
    private Vector2 velocity;

    public Trajectory(Vector2 startPosition, Vector2 velocity) {
        this.startPosition = startPosition;
        this.velocity = velocity;
    }

    public Vector2 predictPosition(float time) {
        float x = startPosition.x + velocity.x * time;
        float y = startPosition.y + velocity.y * time + 0.5f * GRAVITY * time * time;
        return new Vector2(x, y);
    }
}
