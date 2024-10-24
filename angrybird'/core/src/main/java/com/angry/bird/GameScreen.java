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
    private ShapeRenderer shapeRenderer; // For drawing the circle

    public GameScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load the background image
        backgroundTexture = new Texture(Gdx.files.internal("game.jpeg"));
        batch = new SpriteBatch();

        // Initialize ShapeRenderer for the circular button
        shapeRenderer = new ShapeRenderer();

        // Create the pause button
        createPauseButton();
    }

    private void createPauseButton() {
        // Create a font for the button (can be empty text if needed)
        BitmapFont font = new BitmapFont(); // Default font

        // Define a button style programmatically
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font; // Use the font

        // Set button's background color (transparent for now, as we are manually drawing the circle)
        textButtonStyle.up = null;

        // Create the button with empty text (just for click detection)
        TextButton pauseButton = new TextButton("", textButtonStyle);

        // Set the size and position of the button (keeping in mind it will be circular)
        pauseButton.setWidth(30);  // Smaller width
        pauseButton.setHeight(30); // Smaller height
        pauseButton.setPosition(Gdx.graphics.getWidth() - pauseButton.getWidth() - 10,
            Gdx.graphics.getHeight() - pauseButton.getHeight() - 10);

        // Add a listener to the button for pause functionality
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Implement the logic to pause the game
                game.setScreen(new PauseScreen(game));
            }
        });

        // Add the button to the stage
        stage.addActor(pauseButton);
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for the game
    }

    @Override
    public void render(float delta) {
        // Clear the screen and set the background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Draw the smaller circular yellow pause button manually using ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(Gdx.graphics.getWidth() - 35, Gdx.graphics.getHeight() - 35, 30); // Smaller circle
        shapeRenderer.end();

        // Update and draw the stage (which includes the button)
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Called when the game is paused
    }

    @Override
    public void resume() {
        // Called when the game is resumed
    }

    @Override
    public void hide() {
        // Called when this screen is no longer the current screen
    }

    @Override
    public void dispose() {
        // Dispose assets when no longer needed
        stage.dispose();
        batch.dispose();
        backgroundTexture.dispose();
        shapeRenderer.dispose(); // Dispose ShapeRenderer
    }
}
