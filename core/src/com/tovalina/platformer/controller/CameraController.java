package com.tovalina.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController {
    public static OrthographicCamera camera;

    public static void intializeController() {
        //looks through graphics library and pulls window width and height
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(14f, 14f * (height / width));             //sets view of camera to the correct aspect ratio
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);   //set camera position to the height and width divided by two to align to the bottom
    }

    public static void update() {
        camera.update();           //updates camera view
    }

    public static void resize(int width, int height) {
        //sets window size to maintain aspect ratio
        camera.viewportWidth = 14f;
        camera.viewportHeight = 14f * height / width;
        camera.update();
    }
}
