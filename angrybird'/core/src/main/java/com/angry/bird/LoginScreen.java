package com.angry.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoginScreen implements Screen {

    private Main game;
    private Stage stage;
    private TextField usernameField;
    private TextField passwordField;
    private SpriteBatch batch;
    private Texture backgroundTexture;

    public LoginScreen(Main game) {
        this.game = game;

        // Initialize the SpriteBatch and background texture
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("background.jpeg")); // Load your background image

        // Initialize the stage and set the input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create a Skin for UI elements
        Skin skin = new Skin(Gdx.files.internal("uiskin.json")); // Ensure you have a skin file

        // Create text fields for username and password
        usernameField = new TextField("", skin);
        usernameField.setMessageText("Username");

        passwordField = new TextField("", skin);
        passwordField.setMessageText("Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        // Create a login button
        TextButton loginButton = new TextButton("Login", skin);
        loginButton.addListener(event -> {
            if (event.isHandled()) {
                // Handle login logic here
                handleLogin();
                return true;
            }
            return false;
        });

        // Create a table for layout
        Table table = new Table();
        table.setFillParent(true);

        // Move the elements to the lower part of the screen by adding top padding
        table.padTop(Gdx.graphics.getHeight() / 3f); // Adjust this value for more or less padding

        // Add UI elements to the table
        table.add(usernameField).fillX().uniformX();
        table.row().pad(10, 0, 10, 0); // Adjust padding between rows
        table.add(passwordField).fillX().uniformX();
        table.row().pad(20, 0, 10, 0); // Adjust padding before the login button
        table.add(loginButton).fillX().uniformX();

        stage.addActor(table);
    }

    private void handleLogin() {
        game.setScreen(new MainPage(game)); // Assuming MainPage is the next screen
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Draw the stage (UI elements)
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
        // Dispose resources when no longer needed
        batch.dispose();
        backgroundTexture.dispose();
        stage.dispose();
    }
}
