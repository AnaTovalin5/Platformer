package com.tovalina.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController {
    public static OrthographicCamera camera;
    public static OrthographicCamera inputCamera;

    public static float widthScale;
    public static float heightScale;

    public static void intializeController() {
        //looks through graphics library and pulls window width and height
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(14f, 14f * (height / width));             //sets view of camera to the correct aspect ratio
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);   //set camera position to the height and width divided by two to align to the bottom

        inputCamera = new OrthographicCamera(14f, 14f * (height/width));
        inputCamera.position.set(inputCamera.viewportWidth / 2, inputCamera.viewportHeight / 2, 0);
        inputCamera.update();
    }

    public static void update() {
        camera.position.set(PlayerController.player.position.x, PlayerController.player.position.y, 0);
        camera.update();           //updates camera view
    }

    public static void resize(int width, int height) {
        //sets window size to maintain aspect ratio
        camera.viewportWidth = 14f;
        camera.viewportHeight = 14f * height / width;
        camera.update();

        inputCamera.viewportWidth = 14f;
        inputCamera.viewportHeight = 14f * height / width;
        inputCamera.position.set(inputCamera.viewportWidth / 2, inputCamera.viewportHeight / 2, 0);
        inputCamera.update();

        widthScale = width / inputCamera.viewportWidth * LevelController.UNIT_SCALE;
        heightScale = height / inputCamera.viewportHeight * LevelController.UNIT_SCALE;
    }
}
