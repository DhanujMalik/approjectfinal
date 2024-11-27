package com.angry.bird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class HardLevel implements Level {
    private List<Pig> pigs;

    public HardLevel() {
        this.pigs = new ArrayList<>();
    }

    @Override
    public void initialize() {
        // Add many pigs for Hard level
        pigs.add(new SmallPig(100, 50, 1, "small_pig.png"));
        pigs.add(new MediumPig(200, 120, 2, "medium_pig.png"));
        pigs.add(new MediumPig(300, 150, 2, "medium_pig.png"));
        pigs.add(new LargePig(400, 200, 3, "large_pig.png"));
        pigs.add(new LargePig(500, 250, 3, "large_pig.png"));
        pigs.add(new LargePig(600, 300, 3, "large_pig.png"));
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
