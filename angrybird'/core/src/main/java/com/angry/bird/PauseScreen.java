package com.angry.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseScreen implements Screen {

    private final Main game;
    private final EasyLevel currentGameScreen;
    private final Stage stage;
    private final SpriteBatch batch;
    private Texture background;
    private boolean isSoundMuted;

    public PauseScreen(Main game, EasyLevel currentGameScreen) {
        this.game = game;
        this.currentGameScreen = currentGameScreen;
        this.stage = new Stage(new ScreenViewport());
        this.batch = new SpriteBatch();

        background = new Texture("blured.jpeg");

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Resume Button
        TextButton resumeButton = new TextButton("Resume", skin);
        resumeButton.setSize(200, 80);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f + 50);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(currentGameScreen); // Resume the existing game screen
            }
        });

        // Save and Exit Button
        TextButton saveExitButton = new TextButton("Save and Exit", skin);
        saveExitButton.setSize(200, 80);
        saveExitButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f - 100);
        saveExitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle saving game state if necessary
                game.setScreen(new MainPage(game)); // Return to MainPage
            }
        });

        // Sound Toggle Button
        TextButton soundToggleButton = new TextButton("Sound: ON", skin);
        soundToggleButton.setSize(200, 80);
        soundToggleButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f - 25);
        soundToggleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isSoundMuted = !isSoundMuted;
                soundToggleButton.setText("Sound: " + (isSoundMuted ? "OFF" : "ON"));
                // Add logic to mute/unmute game sounds
            }
        });

        // Add buttons to the stage
        stage.addActor(resumeButton);
        stage.addActor(saveExitButton);
        stage.addActor(soundToggleButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        batch.dispose();
    }
}
