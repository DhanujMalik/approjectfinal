package com.angry.bird;

import com.badlogic.gdx.physics.box2d.World;

public class MediumPig extends Pig {

    private static final int HITS_TO_DESTROY = 2;
    private static final String TEXTURE_PATH = "medium_pig.png";
    private static final float WIDTH = 50; // Screen units
    private static final float HEIGHT = 50; // Screen units

    public MediumPig(World world, float x, float y) {
        super(world, x, y, HITS_TO_DESTROY, TEXTURE_PATH, WIDTH, HEIGHT);
    }
}
