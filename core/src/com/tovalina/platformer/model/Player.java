package com.tovalina.platformer.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Texture spriteSheet;
    public TextureRegion[] spriteFrames;

    public Animation animation;
    private float stateTime;

    public Player() {
        position = new Vector2(0, 1); //sets the sprite to be placed on the origin
        spriteSheet = new Texture(Gdx.files.internal("img/aliens.png"));  //makes aliens.png a texture and stores it into spriteSheet

        TextureRegion[][] spriteSheetFrames = TextureRegion.split(spriteSheet, 70, 100);  //creates a 2d array and splits the sprite sheet by its width and height

        int counter = 0;
        for (int row = 0; row < spriteSheetFrames.length; row++) {  //counts the amount of aliens in the spritesheet
            for(int column = 0; column < spriteSheetFrames[row].length; column++) {
                counter++;
            }
        }

        spriteFrames = new TextureRegion[counter]; //saves the amount of rows into spriteFrames

        counter = 0;
        for (TextureRegion[] row : spriteSheetFrames) {  //stores each row into the variable row
            for (TextureRegion sprite : row) {  //stores each column(alien) into the variable sprite
                spriteFrames[counter++] = sprite;  //stores each alien in spriteFrames and assigns it an index
            }
        }

        TextureRegion[] animationFrames = new TextureRegion[2];  //creates new array to hold the number of images
        animationFrames[0] = spriteFrames[23];  //saves sprite 31 into slot 0 of the animationFrame
        animationFrames[1] = spriteFrames[24];  //saves sprite 32 into slot 1 of the animationFrame
        animation = new Animation(0.2f, animationFrames);  //sets animation to run for one frame

        stateTime = 0f;  //initializes statTime variable
    }

    public void draw(Batch spriteBatch) {
        spriteBatch.draw(animation.getKeyFrame(stateTime, true), position.x, position.y, 70 * (1/70f), 100 * (1/70f));  //draws the character onto the screen and spcifies the height/width
    }

    public void update(float deltaTime) {
        stateTime += deltaTime; //sets gameTime as the game runs
        position.y += deltaTime; //updates x position
    }
}
