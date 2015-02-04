package com.tovalina.platformer.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tovalina.platformer.controller.CameraController;
import com.tovalina.platformer.controller.LevelController;

public class InputControl {
    public String action;
    public Vector2 position;

    private TextureRegion textureRegion;
    private float width;
    private float height;

    public InputControl(Vector2 position, TextureRegion textureRegion, String action) {
        this.textureRegion = textureRegion;
        this.position = position;
        this.action = action;
        this.textureRegion = textureRegion;

        width = textureRegion.getRegionWidth();
        height = textureRegion.getRegionHeight();
    }

    public void draw(Batch spriteBatch) {
        spriteBatch.draw(textureRegion, position.x, position.y, width * LevelController.UNIT_SCALE, height * LevelController.UNIT_SCALE);  //helps draw textureRegion onto the screen
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(position.x / LevelController.UNIT_SCALE * CameraController.widthScale, position.y / LevelController.UNIT_SCALE * CameraController.heightScale, width * CameraController.widthScale, height * CameraController.heightScale);
    }
}
