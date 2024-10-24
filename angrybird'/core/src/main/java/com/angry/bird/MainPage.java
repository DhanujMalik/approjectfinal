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
        backgroundTexture = new Texture(Gdx.files.internal("background.jpeg")); // MainPage background

        // Initialize the stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create the Skin for the UI
        Skin skin = new Skin(Gdx.files.internal("uiskin.json")); // Make sure you have a valid skin file

        // Create the buttons
        TextButton newGameButton = new TextButton("New Game", skin);
        TextButton previousGameButton = new TextButton("Previous Game", skin);
        TextButton exitGameButton = new TextButton("Exit Game", skin);

        // Add button listeners
        newGameButton.addListener(event -> {
            if (event.isHandled()) {
                startNewGame();  // Logic to start a new game
                return true;
            }
            return false;
        });

        previousGameButton.addListener(event -> {
            if (event.isHandled()) {
                loadPreviousGame();  // Logic to load a previous game
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

        // Create a table for layout
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // Add buttons to the table
        table.add(newGameButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0); // Add some padding between buttons
        table.add(previousGameButton).fillX().uniformX();
        table.row().pad(20, 0, 10, 0);
        table.add(exitGameButton).fillX().uniformX();

        // Add bottom padding to move the buttons downward
        table.padBottom(0); // Adjust this value to move buttons further down

        // Add the table to the stage
        stage.addActor(table);
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Update and draw the stage (buttons)
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Handle pause if needed
    }

    @Override
    public void resume() {
        // Handle resume if needed
    }

    @Override
    public void hide() {
        // Called when this screen is no longer the active screen
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        stage.dispose();
    }

    private void startNewGame() {
        // Implement your logic to start a new game
        game.setScreen(new NewGame(game));
        // Possibly switch to a NewGameScreen or reset game state here
    }

    private void loadPreviousGame() {
        // Implement your logic to load a previous game
        game.setScreen(new PreviousGame(game));
        // Handle loading saved game data or transition to the previous game state
    }
}
