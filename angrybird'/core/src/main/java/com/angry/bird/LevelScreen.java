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

public class LevelScreen implements Screen {

    private final Main game;  // This is the reference to Main, which has the batch
    private final Stage stage;
    private Texture background;

    public LevelScreen(final Main game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        // Set up the background
        background = new Texture("background.jpeg");

        // Load skin for buttons (assumes you have a skin available)
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Create buttons for each level
        TextButton level1Button = new TextButton("Level 1", skin);
        TextButton level2Button = new TextButton("Level 2", skin);
        TextButton level3Button = new TextButton("Level 3", skin);

        // Set button sizes (smaller than before)
        level1Button.setSize(100, 40);
        level2Button.setSize(100, 40);
        level3Button.setSize(100, 40);

        // Set button positions (moved downwards)
        level1Button.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 50);
        level2Button.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 100);
        level3Button.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 150);

        // Add listeners to buttons
        level1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));  // Start GameScreen (Level 1)
            }
        });

        level2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));  // Start GameScreen (Level 2)
            }
        });

        level3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));  // Start GameScreen (Level 3)
            }
        });

        // Add buttons to the stage
        stage.addActor(level1Button);
        stage.addActor(level2Button);
        stage.addActor(level3Button);

        // Set input processor for the stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // No additional setup needed
    }

    @Override
    public void render(float delta) {
        // Clear the screen and draw background
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

        // Draw the stage and the buttons
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
        stage.dispose();
        background.dispose();
    }
}
