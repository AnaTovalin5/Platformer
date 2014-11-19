package com.tovalina.platformer.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen {
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera;

    public GameScreen() {
        map = new TmxMapLoader().load("map/level01.tmx");      //load level map from my assets
        renderer = new OrthogonalTiledMapRenderer(map, 1/70f); //renders map onto the screen and sets pixel length of tiles
        camera = new OrthographicCamera(14f, 10f);             //sets view of camera to 14 tiles by 14 tiles
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);   //set camera position to the height and width divided by two to align to the bottom
    }

    @Override
    public void render(float delta) {
        camera.update();           //updates camera view
        renderer.setView(camera);  //sets camera to view rendered map
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

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
