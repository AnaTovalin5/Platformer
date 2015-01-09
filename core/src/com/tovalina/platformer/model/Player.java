package com.tovalina.platformer.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.tovalina.platformer.controller.LevelController;
import com.tovalina.platformer.view.GameScreen;

import java.util.HashMap;

public class Player {
    public Vector2 position;
    public SpriteSheet spriteSheet;

    public float width;
    public float height;

    private float stateTime;
    private HashMap<String, Animation> animations;

    public Player(int width, int height) {
        position = new Vector2(0, 1); //sets the sprite to be placed on the origin
        animations = new HashMap<String, Animation>();

        this.width = width * (1/70f);
        this.height = height * (1/70f);

        spriteSheet = new SpriteSheet("img/aliens.png", width, height);
        animations.put("walk", spriteSheet.createAnimation(9, 10, 0.1f));

        stateTime = 0f;  //initializes statTime variable

        BodyDef bodydefinition = new BodyDef();
        bodydefinition.type = BodyDef.BodyType.DynamicBody;
        bodydefinition.position.set(position);

        Body playerBody = LevelController.gameWorld.createBody(bodydefinition); //assign the body defs to the player
        playerBody.setUserData(this);//attach this class to the playerBody

        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(this.width / 2f, this.height / 2f, new Vector2(this.width / 2f, this.height / 2f), 0f);  //defines rectangle

        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = rectangleShape;  //creates the shape

        playerBody.createFixture(fixtureDefinition);
        rectangleShape.dispose(); //deletes the shape
    }

    public void draw(Batch spriteBatch) {
        spriteBatch.draw(animations.get("walk").getKeyFrame(stateTime, true), position.x, position.y, width, height);  //draws the character onto the screen and spcifies the height/width
    }

    public void update(float deltaTime) {
        stateTime += deltaTime; //sets gameTime as the game runs
    }


}
