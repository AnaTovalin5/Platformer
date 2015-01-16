package com.tovalina.platformer.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.tovalina.platformer.controller.LevelController;

public class Enemy extends Sprite {

    public Enemy(Vector2 position, int width, int height, String sheetpath) {
        super(position, width, height, sheetpath);

        BodyDef bodydefinition = new BodyDef();
        bodydefinition.type = BodyDef.BodyType.DynamicBody;
        bodydefinition.position.set(position);

        physicsBody = LevelController.gameWorld.createBody(bodydefinition); //assign the body defs to the player
        physicsBody.setUserData(this);//attach this class to the playerBody

        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(this.width / 2f, this.height / 2f, new Vector2(this.width / 2f, this.height / 2f), 0f);  //defines rectangle

        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = rectangleShape;  //creates the shape

        physicsBody.createFixture(fixtureDefinition);
        rectangleShape.dispose(); //deletes the shape
        animations.put("walk", spriteSheet.createAnimation(0,1, 0.3f));
    }

    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
    }


}
