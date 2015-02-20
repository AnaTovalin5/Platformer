package com.tovalina.platformer.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicController {
    public static Music music;
    protected static Sound jump;

    public static void initializeController() {
        music = Gdx.audio.newMusic(Gdx.files.internal("music/music.wav"));
        jump = Gdx.audio.newSound(Gdx.files.internal("music/jump.mp3"));
    }

    public static void play(String soundName) {
        if (soundName.equalsIgnoreCase("music")) {
            music.play();
            music.setVolume(0.5f);
            music.setLooping(true);
        } else if (soundName.equalsIgnoreCase("jump")) {
            jump.play();
        }
    }
}
