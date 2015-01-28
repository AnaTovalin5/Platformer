package com.tovalina.platformer.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.tovalina.platformer.controller.CameraController;
import com.tovalina.platformer.controller.PlayerController;

import java.util.ArrayList;


public class InputController {
    private static SpriteSheet spriteSheet;

    private static ArrayList<InputControl> inputControls;
    private static InputControl left;
    private static InputControl right;
    private static InputControl jump;

    public static void initializeController() {
        spriteSheet = new SpriteSheet("img/touch-controls.png", 80, 80);
        inputControls = new ArrayList<InputControl>();

        InputControl left = new InputControl(new Vector2(0, 0), spriteSheet.spriteFrames[0], "left");
        InputControl right = new InputControl(new Vector2(2, 0), spriteSheet.spriteFrames[1], "right");
        InputControl jump = new InputControl(new Vector2(4, 0), spriteSheet.spriteFrames[2], "jump");
        InputControl down = new InputControl(new Vector2(6, 0), spriteSheet.spriteFrames[3], "down");
        inputControls.add(left);
        inputControls.add(right);
        inputControls.add(jump);
        inputControls.add(down);

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
                screenY = Gdx.graphics.getHeight() - screenY;
                for (InputControl inputControl : inputControls) {
                    if (inputControl.getBoundingBox().contains(screenX, screenY)) {
                        if (inputControl.action.equalsIgnoreCase("right")) {
                            PlayerController.movementAction = "right";
                        } else if (inputControl.action.equalsIgnoreCase("left")) {
                            PlayerController.movementAction = "left";
                        } else if (inputControl.action.equalsIgnoreCase("jump")) {
                            PlayerController.specialAction = "jump";
                        } else if (inputControl.action.equalsIgnoreCase("down")) {
                            PlayerController.specialAction = "down";
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                screenY = Gdx.graphics.getHeight() - screenY;
                for (InputControl inputControl : inputControls) {
                    if (inputControl.getBoundingBox().contains(screenX, screenY)) {
                        if (inputControl.action.equalsIgnoreCase("right")) {
                            PlayerController.movementAction = "";
                        } else if (inputControl.action.equalsIgnoreCase("left")) {
                            PlayerController.movementAction = "";
                        } else if (inputControl.action.equalsIgnoreCase("up")) {
                            PlayerController.specialAction = "";
                        } else if (inputControl.action.equalsIgnoreCase("down")) {
                            PlayerController.specialAction = "";
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
               if(keycode == Input.Keys.RIGHT) {
                    PlayerController.movementAction = "right";
               } else if (keycode == Input.Keys.LEFT) {
                   PlayerController.movementAction = "left";
               } else if (keycode == Input.Keys.UP) {
                   PlayerController.specialAction = "jump";
               } else if (keycode == Input.Keys.DOWN) {
                   PlayerController.specialAction = "down";
               }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.RIGHT) {
                    PlayerController.movementAction = "";
                } else if (keycode == Input.Keys.LEFT) {
                    PlayerController.movementAction = "";
                } else if (keycode == Input.Keys.UP) {
                    PlayerController.specialAction = "";
                } else if (keycode == Input.Keys.DOWN) {
                    PlayerController.specialAction = "";
                }
                return true;
            }
        };
    }
}
