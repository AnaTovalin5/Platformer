package com.tovalina.platformer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tovalina.platformer.controller.CameraController;
import com.tovalina.platformer.model.InputControl;
import com.tovalina.platformer.model.SpriteSheet;

public class TitleScreen implements Screen {
    //global variables go here
    public static SpriteBatch spriteBatch;
    public static Texture texture;
    public static TextureRegion textureRegion;

    private static SpriteSheet spriteSheet;
    private static InputControl next;

    public TitleScreen() {
        texture = new Texture(Gdx.files.internal("img/titleScreen.PNG"));
        textureRegion = new TextureRegion(texture);
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        TitleScreen.spriteBatch.draw(textureRegion, 0, 0);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
