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

import java.util.List;

public class LevelScreen implements Screen {

    private final Main game;
    private final Stage stage;
    private Texture background;
    private final String selectedBirdType; // Bird type selected in the NewGame screen

    public LevelScreen(final Main game, String selectedBirdType) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.selectedBirdType = selectedBirdType;

        background = new Texture("background.jpeg");

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Create buttons for level selection
        TextButton easyButton = new TextButton("EASY", skin);
        TextButton mediumButton = new TextButton("MEDIUM", skin);
        TextButton hardButton = new TextButton("HARD", skin);

        easyButton.setSize(100, 40);
        mediumButton.setSize(100, 40);
        hardButton.setSize(100, 40);

        easyButton.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 50);
        mediumButton.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 100);
        hardButton.setPosition(Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 150);

        // Set up listeners for the level buttons
        easyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new EasyLevel(game, selectedBirdType)); // Pass the selected bird type
            }
        });

        mediumButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // game.setScreen(new MediumLevel(game,selectedBirdType)); // Pass MediumLevel game (implement MediumLevel)
            }
        });

        hardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new HardLevel(game,selectedBirdType)); // Pass HardLevel game (implement HardLevel)
            }
        });

        // Add buttons to the stage
        stage.addActor(easyButton);
        stage.addActor(mediumButton);
        stage.addActor(hardButton);

        // Set input processor for the stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

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
        background.dispose();
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
