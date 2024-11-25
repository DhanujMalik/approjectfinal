package com.angry.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public abstract class Pig extends GameObject implements Damageable {
    private final Rectangle bounds;
    private int hitsToDestroy;
    private int currentHits;
    private Texture pigTexture;
    private boolean isDestroyed;

    public Pig(float x, float y, int hitsToDestroy, String texturePath) {
        super(x, y); // Initialize position using GameObject's constructor
        this.hitsToDestroy = hitsToDestroy;
        this.currentHits = 0;
        this.isDestroyed = false;
        this.pigTexture = new Texture(texturePath);
        this.bounds = new Rectangle(x, y, pigTexture.getWidth(), pigTexture.getHeight());
    }



    /**
     * Render the pig on the screen.
     */
    public void render(SpriteBatch batch) {
        if (!isDestroyed) {
            batch.draw(pigTexture, position.x, position.y);
        }
    }

    /**
     * Update the pig's state, such as checking if it's destroyed.
     */
    public void update(float delta) {
        if (currentHits >= hitsToDestroy && !isDestroyed) {
            die();
        }
    }

    /**
     * Inflict damage on the pig.
     */
//    @Override
    public void takeDamage(int damage) {
        if (!isDestroyed) {
            currentHits += damage;
            if (currentHits >= hitsToDestroy) {
                die();
            }
        }
    }

    /**
     * Handle pig destruction.
     */
    public void die() {
        isDestroyed = true;
    }

    /**
     * Check if the pig is destroyed.
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Dispose of resources when the pig is no longer needed.
     */
    @Override
    public void dispose() {
        pigTexture.dispose();
    }

    public Circle getBounds() {
        return new Circle();
    }
}
