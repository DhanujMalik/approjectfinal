package com.angry.bird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
    // Create a Music object
    private Music music;  // Remove the instantiation here

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Initialize the music object here
        music = Gdx.audio.newMusic(Gdx.files.internal("path/to/your/music.wav"));  // Load the music file

        // Play background music
        music.setLooping(true);  // Make it loop continuously
        music.play();

        // You can also play a sound effect here if needed (use another method for sound effects)
        // music.playSoundEffect("path/to/sound_effect.wav");

        this.setScreen(new Splash(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        music.dispose();  // Dispose of the music when you're done
    }
}

