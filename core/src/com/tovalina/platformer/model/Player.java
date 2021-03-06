package com.tovalina.platformer.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.tovalina.platformer.controller.LevelController;

public class Player extends Sprite {

    public Player(Vector2 position, int width, int height, String sheetpath) {
        super(position, width, height, sheetpath);

        animations.put("walkRight", spriteSheet.createAnimation(9, 10, 0.1f));
        animations.put("idleRight", spriteSheet.createAnimation(6, 6, 6f));
        animations.put("walkLeft", spriteSheet.flipAnimation(animations.get("walkRight"), true, false));
        animations.put("idleLeft", spriteSheet.flipAnimation(animations.get("idleRight"), true, false));
        currentAnimation = "idleRight";

        BodyDef bodydefinition = new BodyDef();
        bodydefinition.type = BodyDef.BodyType.DynamicBody;
        bodydefinition.position.set(position);

        physicsBody = LevelController.gameWorld.createBody(bodydefinition); //assign the body defs to the player
        physicsBody.setUserData(this);//attach this class to the playerBody

        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(this.width / 2f, this.height / 2f, new Vector2(this.width / 2f, this.height / 2f), 0f);  //defines rectangle

        PolygonShape sensorShape = new PolygonShape();
        sensorShape.setAsBox(this.width / 2.2f, this.height / 32, new Vector2(this.width / 2, 0), 0f);

        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = rectangleShape;  //creates the shape

        FixtureDef fixtureDefinitionSensor = new FixtureDef();
        fixtureDefinitionSensor.shape = sensorShape;
        fixtureDefinitionSensor.isSensor = true;

        physicsBody.createFixture(fixtureDefinition);
        physicsBody.createFixture(fixtureDefinitionSensor);
        rectangleShape.dispose(); //deletes the shape
        sensorShape.dispose();
    }

    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
    }


}
