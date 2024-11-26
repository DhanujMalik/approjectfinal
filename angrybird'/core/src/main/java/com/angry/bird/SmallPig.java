package com.angry.bird;

import com.badlogic.gdx.physics.box2d.World;

public class SmallPig extends Pig {

    private static final int HITS_TO_DESTROY = 1;
    private static final String TEXTURE_PATH = "small_pig.png";
    private static final float WIDTH = 40; // Screen units
    private static final float HEIGHT = 40; // Screen units

    public SmallPig(World world, float x, float y) {
        super(world, x, y, HITS_TO_DESTROY, TEXTURE_PATH, WIDTH, HEIGHT);
    }
}
