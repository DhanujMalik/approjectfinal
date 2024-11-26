package com.angry.bird;

import com.badlogic.gdx.physics.box2d.World;

public class YellowBird extends Bird {
    private static final float WIDTH = 20.0f; // Width in pixels
    private static final float HEIGHT = 20.0f; // Height in pixels
    private static final String TEXTURE_PATH = "red_bird.png";

    public YellowBird(World world, float x, float y) {
        super(world, x, y, TEXTURE_PATH, WIDTH, HEIGHT);
    }

    @Override
    public float getWidth() {
        return WIDTH;
    }

    @Override
    public float getHeight() {
        return HEIGHT;
    }
}
