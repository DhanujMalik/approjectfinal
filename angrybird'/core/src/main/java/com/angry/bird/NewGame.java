package com.angry.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class NewGame implements Screen {
    private final Main game;
    private final Stage stage;
    private final Texture backgroundTexture;

    private String selectedBirdType = "Red"; // Default bird type (Red)

    public NewGame(Main game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.backgroundTexture = new Texture("newgame_background.jpeg"); // Background image
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Create bird selection buttons
        TextButton redBirdButton = new TextButton("Red Bird", skin);
        TextButton blueBirdButton = new TextButton("Blue Bird", skin);
        TextButton yellowBirdButton = new TextButton("Yellow Bird", skin);

        // Set button sizes and positions
        redBirdButton.setSize(150, 50);
        blueBirdButton.setSize(150, 50);
        yellowBirdButton.setSize(150, 50);

        redBirdButton.setPosition(Gdx.graphics.getWidth() / 4f - 75, Gdx.graphics.getHeight() / 2f);
        blueBirdButton.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f);
        yellowBirdButton.setPosition(3 * Gdx.graphics.getWidth() / 4f - 75, Gdx.graphics.getHeight() / 2f);

        // Add listeners to buttons to select the bird type
        redBirdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedBirdType = "Red";
            }
        });

        blueBirdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedBirdType = "Blue";
            }
        });

        yellowBirdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedBirdType = "Yellow";
            }
        });

        // Create a "Play" button to proceed to the game screen
        TextButton playButton = new TextButton("Play", skin);
        playButton.setSize(150, 50);
        playButton.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 4f);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelScreen(game, selectedBirdType));
            }
        });

        // Add actors to the stage
        stage.addActor(redBirdButton);
        stage.addActor(blueBirdButton);
        stage.addActor(yellowBirdButton);
        stage.addActor(playButton);
    }

    @Override
    public void render(float delta) {
        // Render the background
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

        // Render the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
