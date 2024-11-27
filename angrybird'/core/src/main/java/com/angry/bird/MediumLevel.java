package com.angry.bird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class MediumLevel implements Level {
    private List<Pig> pigs;

    public MediumLevel() {
        this.pigs = new ArrayList<>();
    }

    public MediumLevel(Main game, String selectedBirdType) {
    }

    @Override
    public void initialize() {
        // Add more pigs for Medium level
        pigs.add(new SmallPig(150, 100, 1, "small_pig.png"));
        pigs.add(new MediumPig(250, 120, 2, "medium_pig.png"));
        pigs.add(new MediumPig(350, 150, 2, "medium_pig.png"));
        pigs.add(new LargePig(450, 200, 3, "large_pig.png"));
    }

    @Override
    public void render(SpriteBatch batch) {
        for (Pig pig : pigs) {
            pig.render(batch);
        }
    }

    @Override
    public void update(float delta) {
        for (Pig pig : pigs) {
            pig.update(delta);
        }
    }

    @Override
    public void dispose() {
        for (Pig pig : pigs) {
            pig.dispose();
        }
    }
}
