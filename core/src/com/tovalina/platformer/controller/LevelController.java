package com.tovalina.platformer.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tovalina.platformer.model.Level;
import com.tovalina.platformer.model.Sprite;

public class LevelController {
    public static final float UNIT_SCALE = 1/70f;

    public static Level level;
    public static OrthogonalTiledMapRenderer renderer;
    public static Batch spriteBatch;

    public static World gameWorld;
    private static Array<Body> worldBodies;
    private static Box2DDebugRenderer debugRenderer;

    public static void initializeController() {
        level = new Level("map/level01.tmx");
        renderer = new OrthogonalTiledMapRenderer(level.map, UNIT_SCALE); //renders map onto the screen and sets pixel length of tiles

        gameWorld = new World(new Vector2(0, 0), true);
        worldBodies = new Array<Body>();
        debugRenderer = new Box2DDebugRenderer();

        spriteBatch = renderer.getSpriteBatch(); //intializes spriteBatch
    }

    public static void draw() {
        spriteBatch.begin();  //starts the spriteBatch object
        PlayerController.player.draw(spriteBatch);  //draws the player onto the screen
        spriteBatch.end();  //ends the spriteBatch object

        debugRenderer.render(gameWorld, CameraController.camera.combined);  //builds the view
    }

    public static void update() {
        renderer.setView(CameraController.camera);  //sets camera to view rendered map
        renderer.render();
        updateWorldBodies();
        gameWorld.step(1 / 60f, 1, 1);  //updates gameWorld 60 frames per second
    }

    private static void updateWorldBodies() {
        worldBodies.clear();
        gameWorld.getBodies(worldBodies);

        for(Body body : worldBodies) {
            Sprite spriteBody = (Sprite)body.getUserData();
            spriteBody.position = body.getPosition();
        }
    }

}
