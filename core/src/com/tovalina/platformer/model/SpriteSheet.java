package com.tovalina.platformer.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet {
    public Texture spriteSheet;
    public TextureRegion[] spriteFrames;

    public SpriteSheet(String pathToFile, int width, int height) {
        spriteSheet = new Texture(Gdx.files.internal(pathToFile));  //makes aliens.png a texture and stores it into spriteSheet

        TextureRegion[][] spriteSheetFrames = TextureRegion.split(spriteSheet, width, height);  //creates a 2d array and splits the sprite sheet by its width and height

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

    public Animation createAnimation(int startFrame, int lastFrame, float animationSpeed) {
        int counter = (lastFrame + 1) - startFrame;
        TextureRegion[] animationFrames = new TextureRegion[counter];  //creates new array to hold the number of images

        for (int index = lastFrame; index >= startFrame; index--) {  //allows counter to decrement and changes the animation
            animationFrames[--counter] = spriteFrames[index];
        }

        return new Animation(animationSpeed, animationFrames);  //sets animation to run for one frame
    }
}
