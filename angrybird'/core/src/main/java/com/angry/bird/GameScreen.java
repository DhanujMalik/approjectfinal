package com.angry.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    private final Stage stage;
    private final Main game;
    private final Texture backgroundTexture;
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;

    public GameScreen(Main game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        // Set input processor and initialize background
        Gdx.input.setInputProcessor(stage);
        this.backgroundTexture = new Texture(Gdx.files.internal("game.jpeg"));

        pausebutton();
    }

    private void pausebutton() {
        BitmapFont font = new BitmapFont();
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = null; // Customize as needed

        TextButton pauseButton = new TextButton("", textButtonStyle);
        pauseButton.setSize(30, 30);
        pauseButton.setPosition(Gdx.graphics.getWidth() - 40, Gdx.graphics.getHeight() - 40);

        // Add listener to handle pause functionality
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game));
            }
        });

        stage.addActor(pauseButton);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background image
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Draw the pause button indicator
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(Gdx.graphics.getWidth() - 35, Gdx.graphics.getHeight() - 35, 30);
        shapeRenderer.end();

        // Update and draw stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
        stage.dispose();
        batch.dispose();
        backgroundTexture.dispose();
        shapeRenderer.dispose();
    }
}
