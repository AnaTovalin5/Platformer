package com.tovalina.platformer.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class LevelController {
    public static TiledMap map;
    public static OrthogonalTiledMapRenderer renderer;
    public static Batch spriteBatch;

    public static World gameWorld;
    private static Box2DDebugRenderer debugRenderer;

    public static void initializeController() {
        map = new TmxMapLoader().load("map/level01.tmx");      //load level map from my assets
        renderer = new OrthogonalTiledMapRenderer(map, 1/70f); //renders map onto the screen and sets pixel length of tiles
        gameWorld = new World(new Vector2(0, -9.8f), true);
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
    }

}
