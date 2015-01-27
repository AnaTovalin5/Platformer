package com.tovalina.platformer.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.tovalina.platformer.controller.CameraController;
import com.tovalina.platformer.controller.PlayerController;

import java.util.ArrayList;

import jdk.internal.util.xml.impl.Input;

public class InputController {
    private static SpriteSheet spriteSheet;
    private static ArrayList<InputControl> inputControls;
    private static InputControl left;
    private static InputControl right;

    public static void initializeController() {
        spriteSheet = new SpriteSheet("img/touch-controls.png", 80, 80);
        inputControls = new ArrayList<InputControl>();

        left = new InputControl(new Vector2(0, 0), spriteSheet.spriteFrames[0], "left");
        right = new InputControl(new Vector2(10, 0), spriteSheet.spriteFrames[1], "right");
        inputControls.add(left);
        inputControls.add(right);

        Gdx.input.setInputProcessor(createInputAdapter());
    }

    public static void draw(Batch spriteBatch) {
        spriteBatch.begin();
        for (InputControl inputControl : inputControls) {
            inputControl.draw(spriteBatch);
        }
        spriteBatch.end();
    }

    private static InputAdapter createInputAdapter() {
        return new InputAdapter() {  //listens to touch, keyboard, and mouse events

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (left.getBoundingBox().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                    PlayerController.movementAction = "left";
                } else if (right.getBoundingBox().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                    PlayerController.movementAction = "right";
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                PlayerController.movementAction = "";
                return true;
            }
        };
    }
}
