package com.tovalina.platformer.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.tovalina.platformer.model.Player;

public class PlayerController {
    public static Player player;
    public static String movementAction;
    public static String specialAction;

    public static boolean grounded;

    private enum State {
        Idle, Walk, Run, Swim, Duck, Stand, Jump, Climb, Hurt
    }

    private static State playerState;

    private static final float VELOCITY = 1f;
    private static final float JUMP_VELOCITY = 9f;
    private static final float MAX_VELOCITY = 5f;

    public static void initializeController() {
        player = new Player(new Vector2(0,10), 70, 100, "img/aliens.png");  //initializes player constructor
        movementAction = "";
        specialAction = "";
        playerState = State.Idle;
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

        if (movementAction.equalsIgnoreCase("right")) {
            player.physicsBody.applyLinearImpulse(VELOCITY, 0f, position.x, position.y, true);
            player.direction = "right";
        }

        if (movementAction.equalsIgnoreCase("left")) {
            player.physicsBody.applyLinearImpulse(-VELOCITY, 0f, position.x, position.y, true);
            player.direction = "left";
        }

        if (specialAction.equalsIgnoreCase("jump")) {
            if (grounded == true) {
                player.physicsBody.applyLinearImpulse(0, JUMP_VELOCITY, position.x, position.y, true);  //moves character to the up when up key is pressed
                player.direction = "jump";
                grounded = false;
//                MusicController.play("jump");
            }
        }

        if (specialAction.equalsIgnoreCase("down")) {
            player.physicsBody.applyLinearImpulse(0, -VELOCITY, position.x, position.y, true);  //moves character to the up when up key is pressed
            player.direction = "down";
        }

        if (Math.abs(velocity.x) > 0) {
            playerState = State.Walk;
        } else {
            playerState = State.Idle;
        }

        setCurrentAnimation();
    }

    private static void setCurrentAnimation() {
        if (player.direction.equals("right")) {
            setRightAnimation();
        } else if (player.direction.equals("left")) {
            setLeftAnimation();
        }
    }

    private static void setRightAnimation() {
        if (playerState == State.Walk) {
            player.currentAnimation = "walkRight";
        } else if (playerState == State.Idle) {
            player.currentAnimation = "idleRight";
        }
    }

    private static void setLeftAnimation() {
        if (playerState == State.Walk) {
            player.currentAnimation = "walkLeft";
        } else if (playerState == State.Idle) {
            player.currentAnimation = "idleLeft";
        }
    }
}
