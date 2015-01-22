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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.tovalina.platformer.controller.CameraController;
import com.tovalina.platformer.controller.EnemyController;
import com.tovalina.platformer.controller.LevelController;
import com.tovalina.platformer.controller.PlayerController;
import com.tovalina.platformer.model.Enemy;
import com.tovalina.platformer.model.InputController;
import com.tovalina.platformer.model.Player;

import javafx.scene.Camera;

public class GameScreen implements Screen {

    public GameScreen() {
        LevelController.initializeController();
        CameraController.intializeController();
        PlayerController.initializeController();
        EnemyController.initializeController();
        InputController.initializeController();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.32f, 0.33f, 0.83f, 1f); //sets background color to blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears game screen and allows us to use our background color

        CameraController.update();
        LevelController.update();
        PlayerController.update(delta);
        EnemyController.update(delta);
        LevelController.draw();
    }

    @Override
    public void resize(int width, int height) {
        CameraController.resize(width, height);
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
