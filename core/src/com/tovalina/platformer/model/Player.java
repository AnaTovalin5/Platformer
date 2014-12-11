package com.tovalina.platformer.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Animation animation;
    public SpriteSheet spriteSheet;

    public int width;
    public int height;

    private float stateTime;

    public Player() {
        position = new Vector2(0, 1); //sets the sprite to be placed on the origin
        width = 70;
        height = 100;
        spriteSheet = new SpriteSheet("img/aliens.png", width, height);
        animation = spriteSheet.createAnimation(9, 10, 0.1f);
        stateTime = 0f;  //initializes statTime variable
    }

    public void draw(Batch spriteBatch) {
        spriteBatch.draw(animation.getKeyFrame(stateTime, true), position.x, position.y, 70 * (1/70f), 100 * (1/70f));  //draws the character onto the screen and spcifies the height/width
    }

    public void update(float deltaTime) {
        stateTime += deltaTime; //sets gameTime as the game runs
        position.x += deltaTime; //updates x position
    }
}
