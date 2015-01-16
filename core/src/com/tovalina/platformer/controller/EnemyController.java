package com.tovalina.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.tovalina.platformer.model.Enemy;
import com.tovalina.platformer.model.Player;

public class EnemyController {
    public static Enemy enemy;

    private static final float VELOCITY = 1f;
    private static final float MAX_VELOCITY = 5f;

    public static void initializeController() {
        enemy = new Enemy(new Vector2(4,1), 51, 58, "img/enemy-barnacle.png");  //initializes player constructor
    }

    public static void update(float deltaTime) {
        enemy.update(deltaTime);  //updates the player's position
    }

    private static void handleInput() {
        Vector2 velocity = enemy.physicsBody.getLinearVelocity();
        Vector2 position = enemy.physicsBody.getPosition();

        if (Math.abs(velocity.x) > MAX_VELOCITY) {
            velocity.x = Math.signum(velocity.x) * MAX_VELOCITY;  //sets maximum velocity so it doesn't fly off the screen
            enemy.physicsBody.setLinearVelocity(velocity.x, velocity.y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            enemy.physicsBody.applyLinearImpulse(VELOCITY, 0f, position.x, position.y, true);  //moves character to the right when right key is pressed
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            enemy.physicsBody.applyLinearImpulse(-VELOCITY, 0f, position.x, position.y, true);  //moves character to the left when right key is pressed
        }
    }
}
