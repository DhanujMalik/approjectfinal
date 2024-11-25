package com.angry.bird;

public class BlueBird extends Bird {
    public BlueBird(float x, float y) {
        super(x, y, "blue_bird.png"); // Path to Blue Bird texture
    }

    @Override
    public void fire(float angle, float power) {
        super.fire(angle, power);
        // Add any unique behavior for BlueBird here
    }
}
