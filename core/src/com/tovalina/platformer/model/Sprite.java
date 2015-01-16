package com.tovalina.platformer.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.tovalina.platformer.controller.LevelController;

import java.util.HashMap;

public class Sprite {
    public Body physicsBody;
    public Vector2 position;
    public SpriteSheet spriteSheet;
    public String currentAnimation;

    public float width;
    public float height;

    private float stateTime;
    protected HashMap<String, Animation> animations;

    public Sprite(Vector2 position, int width, int height, String sheetpath) {
        this.position = position; //sets the sprite to be placed on the origin
        animations = new HashMap<String, Animation>();

        this.width = width * LevelController.UNIT_SCALE;
        this.height = height * LevelController.UNIT_SCALE;

        spriteSheet = new SpriteSheet(sheetpath, width, height);

        stateTime = 0f;  //initializes statTime variable
    }

    public void draw(Batch spriteBatch) {
        spriteBatch.draw(animations.get(currentAnimation).getKeyFrame(stateTime, true), position.x, position.y, width, height);  //draws the character onto the screen and spcifies the height/width
    }

    public void update(float deltaTime) {
        stateTime += deltaTime; //sets gameTime as the game runs
    }
}
