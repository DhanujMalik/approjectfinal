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

        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("background.jpeg")); // Load your background image

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json")); // Ensure you have a skin file

        usernameField = new TextField("", skin);
        usernameField.setMessageText("Username");

        passwordField = new TextField("", skin);
        passwordField.setMessageText("Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        // Creating a login button
        TextButton loginButton = new TextButton("Login", skin);
        loginButton.addListener(event -> {
            if (event.isHandled()) {
                // Handle login logic here
                loginin();
                return true;
            }
            return false;
        });

        Table table = new Table();
        table.setFillParent(true);
        table.padTop(Gdx.graphics.getHeight() / 3f);
        table.add(usernameField).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(passwordField).fillX().uniformX();
        table.row().pad(20, 0, 10, 0);
        table.add(loginButton).fillX().uniformX();

        stage.addActor(table);
    }

    private void loginin() {
        game.setScreen(new MainPage(game));
    }

    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
}
