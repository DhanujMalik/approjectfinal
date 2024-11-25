package com.angry.bird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Level {
    void initialize();       // Set up pigs, birds, and structures for the level
    void render(SpriteBatch batch);  // Render the level
    void update(float delta);        // Update the state of the level
    void dispose();          // Dispose of resources
}
