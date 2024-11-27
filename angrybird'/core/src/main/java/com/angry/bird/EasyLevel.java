package com.angry.bird;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.*;

public class EasyLevel implements Screen {
    private final Main game;
    private final Stage stage;
    private final SpriteBatch batch;
    private final Texture backgroundSkyTexture;
    private final Texture groundTexture;
    private final Texture woodenBoxTexture;
    private final Texture catapultTexture;

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Body groundBody;
    private Queue<Bird> birds;
    private Bird currentBird;
    private List<Pig> pigs;
    private List<Vector2> woodenBoxPositions;
    private boolean isBirdLaunched = false;

    private Skin skin;
    private Label birdsRemainingLabel;
    private Label pigsRemainingLabel;
    private boolean isPaused = false;

    private Texture smallPigTexture;
    private Texture mediumPigTexture;

    private final Vector2 catapultPosition = new Vector2(100, 100);

    // Constructor
    public EasyLevel(Main game, String selectedBirdType) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.batch = new SpriteBatch();
        this.backgroundSkyTexture = new Texture(Gdx.files.internal("sky.png"));
        this.groundTexture = new Texture(Gdx.files.internal("ground.png"));
        this.woodenBoxTexture = new Texture(Gdx.files.internal("woodenbox.png"));
        this.catapultTexture = new Texture(Gdx.files.internal("catapult.png"));

        this.smallPigTexture = new Texture(Gdx.files.internal("smallpig.png"));
        this.mediumPigTexture = new Texture(Gdx.files.internal("mediumpig.png"));

        this.skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Initialize Box2D world
        this.world = new World(new Vector2(0, -9.8f), true);
        this.debugRenderer = new Box2DDebugRenderer();

        // Create ground body
        createGround();

        // Initialize birds
        this.birds = new LinkedList<>();
        initBirds(selectedBirdType);

        // Initialize pigs and wooden structures
        this.pigs = new ArrayList<>();
        initPigs();
        initWoodenStructure();

        // Add HUD
        addHUD();

        // Add pause button
        addPauseButton();

        // Load the first bird
        loadNextBird();

        // Set the input processor
        Gdx.input.setInputProcessor(stage);
    }

    private void addHUD() {
        birdsRemainingLabel = new Label("Birds Remaining: " + birds.size(), skin);
        birdsRemainingLabel.setPosition(10, Gdx.graphics.getHeight() - 30);

        pigsRemainingLabel = new Label("Pigs Remaining: " + pigs.size(), skin);
        pigsRemainingLabel.setPosition(10, Gdx.graphics.getHeight() - 60);

        stage.addActor(birdsRemainingLabel);
        stage.addActor(pigsRemainingLabel);
    }

    private void addPauseButton() {
        TextButton pauseButton = new TextButton("Pause", skin);
        pauseButton.setSize(100, 40);
        pauseButton.setPosition(Gdx.graphics.getWidth() - 110, Gdx.graphics.getHeight() - 50);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game, EasyLevel.this));
            }
        });

        stage.addActor(pauseButton);
    }

    private void initBirds(String selectedBirdType) {
        for (int i = 0; i < 8; i++) {
            Bird bird;
            switch (selectedBirdType) {
                case "Red":
                    bird = new RedBird(world, catapultPosition.x, catapultPosition.y);
                    break;
                case "Blue":
                    bird = new BlueBird(world, catapultPosition.x, catapultPosition.y);
                    break;
                case "Yellow":
                    bird = new YellowBird(world, catapultPosition.x, catapultPosition.y);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown bird type: " + selectedBirdType);
            }
            birds.add(bird);
        }
    }

    private void createGround() {
        BodyDef groundDef = new BodyDef();
        groundDef.position.set(0, 0);
        groundBody = world.createBody(groundDef);

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(Gdx.graphics.getWidth(), 10);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 0.5f;
        groundBody.createFixture(fixtureDef);

        groundShape.dispose();
    }

    private void initPigs() {
        pigs.add(new SmallPig(world, 200, 20)); // On ground
        pigs.add(new SmallPig(world, 300, 120)); // On structure
        pigs.add(new MediumPig(world, 400, 120)); // On structure
    }

    private void initWoodenStructure() {
        woodenBoxPositions = Arrays.asList(
                new Vector2(250, 50),
                new Vector2(300, 50),
                new Vector2(350, 50)
        );
    }

    private void loadNextBird() {
        if (!birds.isEmpty()) {
            currentBird = birds.poll();
            updateHUD();
        } else {
            currentBird = null;
            checkGameOver();
        }
    }

    private void updateHUD() {
        birdsRemainingLabel.setText("Birds Remaining: " + birds.size());
        pigsRemainingLabel.setText("Pigs Remaining: " + pigs.size());
    }

    private void checkGameOver() {
        if (pigs.isEmpty()) {
            game.setScreen(new LevelCompleteScreen(game));
        } else if (birds.isEmpty()) {
            game.setScreen(new GameOverScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        if (isPaused) return;

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update physics
        world.step(1 / 60f, 6, 2);

        batch.begin();
        batch.draw(backgroundSkyTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(groundTexture, 0, 0, Gdx.graphics.getWidth(), 100);
        batch.draw(catapultTexture, catapultPosition.x - 50, catapultPosition.y - 50, 100, 100);

        // Draw wooden boxes
        for (Vector2 position : woodenBoxPositions) {
            batch.draw(woodenBoxTexture, position.x - 25, position.y - 25, 50, 50);
        }

        // Draw pigs
        for (Pig pig : pigs) {
            pig.render(batch);
        }

        // Draw current bird
        if (currentBird != null) {
            currentBird.render(batch);
        }

        batch.end();

        debugRenderer.render(world, stage.getCamera().combined);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        backgroundSkyTexture.dispose();
        groundTexture.dispose();
        woodenBoxTexture.dispose();
        catapultTexture.dispose();
        smallPigTexture.dispose();
        mediumPigTexture.dispose();
        world.dispose();
        debugRenderer.dispose();
        stage.dispose();
    }
}
