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
}
