package com.angry.bird;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PreviousGame implements Screen {

    private final Main game;
    private final Stage stage;
    private Texture background;

    public PreviousGame(final Main game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        // Set up the background (optional)
        background = new Texture("game.jpeg"); // Your background image

        // Load skin for buttons (assumes you have a skin available)
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Create a button for resuming the previous game
        TextButton resumeButton = new TextButton("Resume Previous Game", skin);
        resumeButton.setSize(300, 80); // Button size
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2f - 150, Gdx.graphics.getHeight() / 2f); // Center position

        // Add listener for button click
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Switch to the GameScreen (resume previous game)
                game.setScreen(new GameScreen(game));
            }
        });

        // Add the button to the stage
        stage.addActor(resumeButton);

        // Set input processor
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // No additional setup needed when showing
    }

    @Override
    public void render(float delta) {
        // Draw background
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

        // Draw the stage with the button
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // No need to implement
    }

    @Override
    public void resume() {
        // No need to implement
    }

    @Override
    public void hide() {
        // No need to implement
    }

    @Override
    public void dispose() {
        // Clean up resources
        stage.dispose();
        background.dispose();
    }
}
