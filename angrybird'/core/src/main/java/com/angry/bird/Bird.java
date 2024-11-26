package com.angry.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Bird {
    private Body body; // Box2D body for physics
    private Texture texture;
    private boolean isDestroyed;
    private float width; // Width of the bird
    private float height; // Height of the bird

    public Bird(World world, float x, float y, String texturePath, float width, float height) {
        this.width = width;
        this.height = height;
        this.texture = new Texture(texturePath);
        this.isDestroyed = false;

        // Create Box2D body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / Box2DConstants.PPM, y / Box2DConstants.PPM); // Convert to Box2D units
        this.body = world.createBody(bodyDef);

        // Define a circular shape for the bird
        CircleShape shape = new CircleShape();
        shape.setRadius((width / 2) / Box2DConstants.PPM); // Convert radius to Box2D units

        // Define fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f; // Adjust for realistic physics
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.6f; // Bounciness

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        if (!isDestroyed) {
            Vector2 position = body.getPosition();
            batch.draw(texture,
                position.x * Box2DConstants.PPM - width / 2,
                position.y * Box2DConstants.PPM - height / 2,
                width, height);
        }
    }
    public class Box2DConstants {
        public static final float PPM = 100.0f; // 100 pixels = 1 meter
    }

    public void fire(float angle, float power) {
        float forceX = (float) (power * Math.cos(Math.toRadians(angle)));
        float forceY = (float) (power * Math.sin(Math.toRadians(angle)));
        applyForce(new Vector2(forceX, forceY));
    }

    public void applyForce(Vector2 force) {
        if (body != null && !isDestroyed) {
            body.applyLinearImpulse(force, body.getWorldCenter(), true);
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
        if (destroyed && body != null) {
            body.setActive(false); // Deactivate the body in the physics world
        }
    }

    public void update(float delta) {
        if (isDestroyed) {
            body.setLinearVelocity(0, 0); // Stop movement when destroyed
        }
    }

    public void dispose() {
        texture.dispose();
    }

    public Vector2 getPosition() {
        return body.getPosition().scl(Box2DConstants.PPM); // Convert back to screen coordinates
    }

//    public Rectangle getBounds() {
//        Vector2 position = getPosition();
//        return new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
//    }

    public abstract float getWidth();

    public abstract float getHeight();
}
