
package com.angry.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class NewGame implements Screen {
    private Stage stage;
    private Skin skin;
    private Main game; // Reference to the main game class
    private Texture backgroundTexture; // Texture for the background image
    private SpriteBatch batch; // Used to draw the background

    public NewGame(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load skin (UI elements and styles)
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Load the background image
        backgroundTexture = new Texture(Gdx.files.internal("birds.jpeg"));

        // Create a SpriteBatch to draw the background
        batch = new SpriteBatch();

        // Create the "Red Bird" button
        TextButton redBirdButton = new TextButton("Red Bird", skin);

        // Set button size and position
        redBirdButton.setSize(200, 50); // Button size: 200x50
        redBirdButton.setPosition((Gdx.graphics.getWidth() - redBirdButton.getWidth()) / 2, 50); // Centered at the bottom

        // Add listener to handle click event
        redBirdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Open the game screen when button is clicked
                game.setScreen(new LevelScreen(game));
            }
        });

        // Add button to the stage
        stage.addActor(redBirdButton);
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw background to fill the screen
        batch.end();

        // Update the stage and draw UI elements
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Handle screen resize
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
        // Dispose of resources when no longer needed
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose(); // Dispose of the background texture
        batch.dispose(); // Dispose of the sprite batch
    }
}
