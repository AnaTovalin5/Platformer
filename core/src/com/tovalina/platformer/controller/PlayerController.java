package com.tovalina.platformer.controller;

import com.tovalina.platformer.model.Player;

public class PlayerController {
    public static Player player;

    public static void initializeController() {
        player = new Player(70, 100);  //initialzes player constructor
    }

    public static void update(float deltaTime) {
        player.update(deltaTime);  //updates the player's position
    }
}
