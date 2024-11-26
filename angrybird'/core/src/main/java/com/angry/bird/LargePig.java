package com.angry.bird;

import com.badlogic.gdx.physics.box2d.World;

public class LargePig extends Pig {

    private static final int HITS_TO_DESTROY = 3;
    private static final String TEXTURE_PATH = "large_pig.png";
    private static final float WIDTH = 60; // Screen units
    private static final float HEIGHT = 60; // Screen units

    public LargePig(World world, float x, float y) {
        super(world, x, y, HITS_TO_DESTROY, TEXTURE_PATH, WIDTH, HEIGHT);
    }
}
