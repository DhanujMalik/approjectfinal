package com.angry.bird;

public class YellowBird extends Bird {
    public YellowBird(float x, float y) {
        super(x, y, "yellow_bird.png"); // Path to Yellow Bird texture
    }

    @Override
    public void fire(float angle, float power) {
        super.fire(angle, power);
        // Add any unique behavior for YellowBird here
    }
}
