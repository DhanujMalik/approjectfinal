package com.angry.bird;

public class RedBird extends Bird {
    public RedBird(float x, float y) {
        super(x, y, "red_bird.png"); // Path to Red Bird texture
    }

    @Override
    public void fire(float angle, float power) {
        super.fire(angle, power);
        // Add any unique behavior for RedBird here
    }
}
