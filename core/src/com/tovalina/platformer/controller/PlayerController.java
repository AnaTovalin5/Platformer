package com.tovalina.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.tovalina.platformer.model.Player;

public class PlayerController {
    public static Player player;
    public static String movementAction;
    public static String specialAction;

    private static final float VELOCITY = 1f;
    private static final float MAX_VELOCITY = 5f;

    public static void initializeController() {
        player = new Player(new Vector2(0,1), 70, 100, "img/aliens.png");  //initialzes player constructor
        movementAction = "";
        specialAction = "";
    }

    public static void update(float deltaTime) {
        handleInput();
        player.update(deltaTime);  //updates the player's position
    }

    private static void handleInput() {
        Vector2 velocity = player.physicsBody.getLinearVelocity();
        Vector2 position = player.physicsBody.getPosition();

        if (Math.abs(velocity.x) > MAX_VELOCITY) {
            velocity.x = Math.signum(velocity.x) * MAX_VELOCITY;  //sets maximum velocity so it doesn't fly off the screen
            player.physicsBody.setLinearVelocity(velocity.x, velocity.y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.physicsBody.applyLinearImpulse(VELOCITY, 0f, position.x, position.y, true);  //moves character to the right when right key is pressed
        }else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.physicsBody.applyLinearImpulse(-VELOCITY, 0f, position.x, position.y, true);  //moves character to the left when left key is pressed
        }else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.physicsBody.applyLinearImpulse(0, VELOCITY, position.x, position.y, true);  //moves character to the up when up key is pressed
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.physicsBody.applyLinearImpulse(0, -VELOCITY, position.x, position.y, true);  //moves character to the down when down key is pressed
        }

        if(movementAction.equalsIgnoreCase("right")) {
            player.physicsBody.applyLinearImpulse(VELOCITY, 0f, position.x, position.y, true);
        } else if (movementAction.equalsIgnoreCase("left")) {
            player.physicsBody.applyLinearImpulse(-VELOCITY, 0f, position.x, position.y, true);
        }
    }
}
