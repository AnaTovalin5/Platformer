package com.tovalina.platformer.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tovalina.platformer.model.Bodies;
import com.tovalina.platformer.model.CollisionListener;
import com.tovalina.platformer.model.InputController;
import com.tovalina.platformer.model.Level;
import com.tovalina.platformer.model.Sprite;

import javafx.scene.Camera;

public class LevelController {
    public static final float UNIT_SCALE = 1/70f;

    public static Level level;
    public static OrthogonalTiledMapRenderer renderer;
    public static Batch spriteBatch;

    public static World gameWorld;
    private static Array<Body> worldBodies;
    private static Box2DDebugRenderer debugRenderer;

    public static Array<Sprite> worldSprites;

    public static void initializeController() {
        level = new Level("map/level01.tmx");
        renderer = new OrthogonalTiledMapRenderer(level.map, UNIT_SCALE); //renders map onto the screen and sets pixel length of tiles

        gameWorld = new World(new Vector2(0, -9.8f), true);
        gameWorld.setContactListener(new CollisionListener());
        worldBodies = new Array<Body>();
        worldSprites = new Array<Sprite>();
        debugRenderer = new Box2DDebugRenderer();

        spriteBatch = renderer.getSpriteBatch(); //intializes spriteBatch
        createLevelBodies();
//        MusicController.play("music");
    }

    public static void draw() {
        spriteBatch.setProjectionMatrix(CameraController.camera.combined);
        spriteBatch.begin();  //starts the spriteBatch object
        PlayerController.player.draw(spriteBatch);  //draws the player onto the screen
        EnemyController.enemy.draw(spriteBatch);
        for (Sprite sprite : worldSprites) {
            sprite.draw(spriteBatch);
        }
        spriteBatch.end();  //ends the spriteBatch object

        spriteBatch.setProjectionMatrix(CameraController.inputCamera.combined);
        InputController.draw(spriteBatch);

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

            if (spriteBody != null) {
                spriteBody.position = body.getPosition();
            }
        }
    }

    private static void createLevelBodies() {
        MapObjects mapObjects = level.getMapObjects(level.getMapLayer("collisions"));

        for (MapObject mapObject : mapObjects) {
            Bodies.createBody(mapObject);
        }

        MapObjects blockObjects = level.getMapObjects(level.getMapLayer("blocks"));

        for (MapObject mapObject : blockObjects) {
            Bodies.createBody(mapObject);
        }
    }

}
