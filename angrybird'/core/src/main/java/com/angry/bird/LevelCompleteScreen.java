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

public class LevelCompleteScreen implements Screen {
    private final Main game;
    private final Stage stage;
    private Texture background;

    public LevelCompleteScreen(final Main game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        background = new Texture(Gdx.files.internal("level_complete_bg.png")); // Background image for completion screen

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton nextLevelButton = new TextButton("Next Level", skin);
        TextButton mainMenuButton = new TextButton("Main Menu", skin);

        nextLevelButton.setSize(200, 50);
        mainMenuButton.setSize(200, 50);

        nextLevelButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f + 50);
        mainMenuButton.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f - 50);

        nextLevelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Transition to the next level (you can set this to any level you wish)
                game.setScreen(new LevelScreen(game, null)); // Adjust this based on your logic
            }
        });

        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Go back to the main menu screen
                game.setScreen(new MainPage(game)); // Adjust this to match your main menu class
            }
        });

        stage.addActor(nextLevelButton);
        stage.addActor(mainMenuButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {}

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
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
