package com.angry.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainPage implements Screen {

    private Main game;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Stage stage;

    public MainPage(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("background.jpeg"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json")); // Make sure you have a valid skin file

        TextButton newGameButton = new TextButton("New Game", skin);
        TextButton previousGameButton = new TextButton("Previous Game", skin);
        TextButton exitGameButton = new TextButton("Exit Game", skin);

        newGameButton.addListener(event -> {
            if (event.isHandled()) {
                startNewGame(); 
                return true;
            }
            return false;
        });

        previousGameButton.addListener(event -> {
            if (event.isHandled()) {
                loadPreviousGame();
                return true;
            }
            return false;
        });

        exitGameButton.addListener(event -> {
            if (event.isHandled()) {
                Gdx.app.exit();  // Exit the game
                return true;
            }
            return false;
        });

        Table table = new Table();
        table.setFillParent(true);
        table.center()
        table.add(newGameButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0); // Add some padding between buttons
        table.add(previousGameButton).fillX().uniformX();
        table.row().pad(20, 0, 10, 0);
        table.add(exitGameButton).fillX().uniformX();
        table.padBottom(0); 
        stage.addActor(table);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        stage.dispose();
    }

    private void startNewGame() {
        game.setScreen(new NewGame(game));
    }

    private void loadPreviousGame() {
        game.setScreen(new PreviousGame(game));
    }
}
