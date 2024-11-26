package com.angry.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Pig extends GameObject implements Damageable {
    private Body body; // Box2D body for physics
    private Texture pigTexture;
    private int hitsToDestroy;
    private int currentHits;
    private boolean isDestroyed;
    private float width;
    private float height;
    public class Box2DConstants {
        public static final float PPM = 100.0f; // 100 pixels = 1 meter
    }
    public Pig(World world, float x, float y, int hitsToDestroy, String texturePath, float width, float height) {
        super(x, y); // Initialize position using GameObject's constructor
        this.hitsToDestroy = hitsToDestroy;
        this.currentHits = 0;
        this.isDestroyed = false;
        this.pigTexture = new Texture(texturePath);
        this.width = width;
        this.height = height;

        // Create Box2D body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x / Box2DConstants.PPM, y / Box2DConstants.PPM); // Convert to Box2D units
        this.body = world.createBody(bodyDef);

        // Define a circular shape for the pig
        CircleShape shape = new CircleShape();
        shape.setRadius((width / 2) / Box2DConstants.PPM); // Convert radius to Box2D units

        // Define fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f; // Slight bounciness

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    /**
     * Render the pig on the screen.
     */
    public void render(SpriteBatch batch) {
        if (!isDestroyed) {
            Vector2 position = body.getPosition();
            batch.draw(pigTexture,
                position.x * Box2DConstants.PPM - width / 2,
                position.y * Box2DConstants.PPM - height / 2,
                width, height);
        }
    }

    /**
     * Update the pig's state, such as checking if it's destroyed.
     */
    public void update(float delta) {
        if (currentHits >= hitsToDestroy && !isDestroyed) {
            destroy();
        }
    }

    /**
     * Inflict damage on the pig.
     */
    @Override
    public void takeDamage(int damage) {
        if (!isDestroyed) {
            currentHits += damage;
            if (currentHits >= hitsToDestroy) {
                destroy();
            }
        }
    }

    /**
     * Handle pig destruction.
     */
    public void destroy() {
        die(); // Call existing die logic for destruction
        if (body != null) {
            body.setActive(false); // Deactivate the body in the physics world
        }
    }

    /**
     * The core logic for when the pig is destroyed.
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

    /**
     * Get the position of the pig.
     */
    public Vector2 getPosition() {
        return body.getPosition().scl(Box2DConstants.PPM); // Convert back to screen coordinates
    }

    /**
     * Get the bounds for collision detection.
     */
    public Vector2 getBoundsCenter() {
        return body.getPosition();
    }
}
