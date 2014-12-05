package com.tovalina.platformer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.tovalina.platformer.model.Player;

public class GameScreen implements Screen {
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera;

    public Batch spriteBatch;
    public Player player;

    public GameScreen() {
        map = new TmxMapLoader().load("map/level01.tmx");      //load level map from my assets
        renderer = new OrthogonalTiledMapRenderer(map, 1/70f); //renders map onto the screen and sets pixel length of tiles

        //looks through graphics library and pulls window width and height
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(14f, 14f * (height / width));             //sets view of camera to the correct aspect ratio
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);   //set camera position to the height and width divided by two to align to the bottom

        spriteBatch = renderer.getSpriteBatch(); //intializes spriteBatch
        player = new Player();  //initialzes player constructor
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.32f, 0.33f, 0.83f, 1f); //sets background color to blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears game screen and allows us to use our background color

        camera.update();           //updates camera view
        renderer.setView(camera);  //sets camera to view rendered map
        renderer.render();


        player.update(delta);  //updates the player's position

        spriteBatch.begin();  //starts the spriteBatch object
        player.draw(spriteBatch);  //draws the player onto the screen
        spriteBatch.end();  //ends the spriteBatch object
    }

    @Override
    public void resize(int width, int height) {
        //sets window size to maintain aspect ratio
        camera.viewportWidth = 14f;
        camera.viewportHeight = 14f * height / width;
        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
