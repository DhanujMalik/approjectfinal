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
    private Stage stage;
    private Main game;
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer; 

    public GameScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

       
        backgroundTexture = new Texture(Gdx.files.internal("game.jpeg"));
        batch = new SpriteBatch();

        
        shapeRenderer = new ShapeRenderer();

       
        createPauseButton();
    }

    private void createPauseButton() {
        
        BitmapFont font = new BitmapFont(); 

        
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font; 

        
        textButtonStyle.up = null;

        TextButton pauseButton = new TextButton("", textButtonStyle);

        pauseButton.setWidth(30);  
        pauseButton.setHeight(30);
        pauseButton.setPosition(Gdx.graphics.getWidth() - pauseButton.getWidth() - 10,
            Gdx.graphics.getHeight() - pauseButton.getHeight() - 10);

       
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(Gdx.graphics.getWidth() - 35, Gdx.graphics.getHeight() - 35, 30); // Smaller circle
        shapeRenderer.end();

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
