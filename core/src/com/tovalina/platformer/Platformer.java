package com.tovalina.platformer;

import com.badlogic.gdx.Game;
import com.tovalina.platformer.view.GameScreen;

public class Platformer extends Game {
    @java.lang.Override
    public void create() {
        setScreen(new GameScreen());  //runs GameScreen
    }
}
