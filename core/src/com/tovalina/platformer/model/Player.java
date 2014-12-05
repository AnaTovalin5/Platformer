package com.tovalina.platformer.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Texture spriteSheet;
    public TextureRegion[] spriteFrames;

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

    }

    public void draw(Batch spriteBatch) {
        spriteBatch.draw(spriteFrames[22], position.x, position.y, 70 * (1/70f), 100 * (1/70f));  //draws the character onto the screen and spcifies the height/width
    }

    public void update(float deltaTime) {
        position.x += deltaTime; //updates x position
    }
}
