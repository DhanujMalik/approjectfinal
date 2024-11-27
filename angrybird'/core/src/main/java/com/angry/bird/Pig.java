package com.angry.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Pig extends GameObject implements Damageable {
    private Body body;
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
        super(x, y);
        this.hitsToDestroy = hitsToDestroy;
        this.currentHits = 0;
        this.isDestroyed = false;
        this.pigTexture = new Texture(texturePath);
        this.width = width;
        this.height = height;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x / Box2DConstants.PPM, y / Box2DConstants.PPM);
        this.body = world.createBody(bodyDef);

        //shape of the pig 
        CircleShape shape = new CircleShape();
        shape.setRadius((width / 2) / Box2DConstants.PPM); // Convert radius to Box2D units

        //fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f; // Slight bounciness

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        if (!isDestroyed) {
            Vector2 position = body.getPosition();
            batch.draw(pigTexture,
                position.x * Box2DConstants.PPM - width / 2,
                position.y * Box2DConstants.PPM - height / 2,
                width, height);
        }
    }

    public void update(float delta) {
        if (currentHits >= hitsToDestroy && !isDestroyed) {
            destroy();
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (!isDestroyed) {
            currentHits += damage;
            if (currentHits >= hitsToDestroy) {
                destroy();
            }
        }
    }

    public void destroy() {
        die(); // Call existing die logic for destruction
        if (body != null) {
            body.setActive(false); // Deactivate the body in the physics world
        }
    }

    public void die() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void dispose() {
        pigTexture.dispose();
    }

    public Vector2 getPosition() {
        return body.getPosition().scl(Box2DConstants.PPM);
    }

    public Vector2 getBoundsCenter() {
        return body.getPosition();
    }
}
