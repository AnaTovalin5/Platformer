package com.tovalina.platformer;

import com.badlogic.gdx.Game;
import com.tovalina.platformer.view.GameScreen;
import com.tovalina.platformer.view.TitleScreen;

public class Platformer extends Game {
    @java.lang.Override
    public void create() {
        setScreen(new TitleScreen());
    }
}
