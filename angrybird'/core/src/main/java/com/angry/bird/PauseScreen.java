
package com.angry.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseScreen implements Screen {

    private final Main game;
    private final Stage stage;
    private Texture background;
    private SpriteBatch batch;

    public PauseScreen(final Main game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.batch = new SpriteBatch();

        // Set up the blurred background
        background = new Texture("blured.jpeg");

        // Load the skin for buttons (assuming you have a skin or use a built-in skin)
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Create the "Resume" button
        TextButton resumeButton = new TextButton("Resume", skin);
        resumeButton.setSize(200, 80); // Set button size
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f + 50); // Center position
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Go back to the game screen (resume the game)
                game.setScreen(new GameScreen(game));
            }
        });

        // Create the "Save and Exit" button
        TextButton saveExitButton = new TextButton("Save and Exit", skin);
        saveExitButton.setSize(200, 80); // Set button size
        saveExitButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f - 100); // Center position below the Resume button
        saveExitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Implement the saving logic here and exit to the main menu
                // For now, let's just exit to the main menu
                game.setScreen(new MainPage(game)); // Redirects to the main menu or main page
            }
        });

        // Add buttons to the stage
        stage.addActor(resumeButton);
        stage.addActor(saveExitButton);

        // Set the input processor to handle the buttons
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // Not much to do here for now
    }

    @Override
    public void render(float delta) {
        // Draw the background image
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Draw the stage (buttons)
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Not needed
    }

    @Override
    public void resume() {
        // Not needed
    }

    @Override
    public void hide() {
        // Not needed
    }

    @Override
    public void dispose() {
        // Dispose resources
        stage.dispose();
        background.dispose();
        batch.dispose();
    }
}
