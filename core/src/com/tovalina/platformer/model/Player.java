package com.tovalina.platformer.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Texture spriteSheet;

    public Player() {
        position = new Vector2(0, 0); //sets the sprite to be placed on the origin
        spriteSheet = new Texture(Gdx.files.internal("img/aliens.png"));  //makes aliens.png a texture and stores it into spriteSheet
    }
}
