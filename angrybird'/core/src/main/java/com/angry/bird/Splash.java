package com.angry.bird;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash extends ScreenAdapter {

    private Main game;  // Reference to the main game
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private float elapsedTime = 0f;  // To track the time passed
    private float loadingTime = 3f;

    public Splash(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Load the splash screen assets
        backgroundTexture = new Texture(Gdx.files.internal("background.jpeg"));
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        elapsedTime += delta;
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the splash screen
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        if (elapsedTime >= loadingTime) {
            game.setScreen(new LoginScreen(game)); // Transition to MainPage after loading
        }
        // Simulate loading and switch to the main game screen

    }



    @Override
    public void hide() {
        // Dispose splash screen assets
        batch.dispose();
        backgroundTexture.dispose();
    }
}
