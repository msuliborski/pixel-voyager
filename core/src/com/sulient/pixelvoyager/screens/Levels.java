package com.sulient.pixelvoyager.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sulient.pixelvoyager.Main;
import com.sulient.pixelvoyager.objects.Level;
import com.sulient.pixelvoyager.tools.InputManager;

import static com.sulient.pixelvoyager.Main.*;
import static com.sulient.pixelvoyager.screens.Game.*;


public class Levels implements Screen{

    private final Main main;
    public Levels(final Main main){
        this.main = main;
    }

    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private SpriteBatch spriteBatch;

    private Stage stageLevels;
    private Table tableLevels;
    private Level easyLevel, normalLevel, hardLevel, extremeLevel, insaneLevel, ascendLevel, extendLevel, collectLevel;

    private ScrollPane scrollPaneLevels;
    private Skin skin;

    private Sprite pvLogo;

    private Music levelsBackgroundMusic;

    private float scrollPaneLevelsX, scrollPaneLevelsY, scrollPaneLevelsW, scrollPaneLevelsH;





    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputManager());
        Gdx.input.setCatchBackKey(true);

        spriteBatch = new SpriteBatch();

        shipIsDead = false;
        ballsFalling = false;
        shipCollectedAllBalls = true;

        assetManager.update();

        if (!assetManager.isLoaded("sounds/levels.ogg", Music.class)) {levelsBackgroundMusic.dispose(); assetManager.load("sounds/levels.ogg", Music.class);}
        levelsBackgroundMusic = assetManager.get("sounds/levels.ogg", Music.class);
        levelsBackgroundMusic.setLooping(true);
        if (Menu.saves.getBoolean("volumeON", true)) levelsBackgroundMusic.play();

        stageLevels = new Stage();
        Gdx.input.setInputProcessor(stageLevels);

        tableLevels = new Table();
        tableLevels.setBounds(0, 0, screenW, screenH - screenW / 2);

        scrollPaneLevels = new ScrollPane(tableLevels);

        skin = assetManager.get("ui/uiskinBig.json", Skin.class);

        pvLogo = new Sprite(new Texture(Gdx.files.internal("images/pixelVoyagerLogo.png")));

        easyLevel = new Level("images/buttons/easy.png", skin, main);
        easyLevel.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                globalGameLevel = 1;
                globalGameLevelName = "easy";
                globalBallSpeed = 80;
                levelsBackgroundMusic.stop();
                main.setScreen(new Game(main));}});

        normalLevel = new Level("images/buttons/normal.png", skin, main);
        normalLevel.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                globalGameLevel = 2;
                globalGameLevelName = "normal";
                globalBallSpeed = 140;
                levelsBackgroundMusic.stop();
                main.setScreen(new Game(main));}});

        hardLevel = new Level("images/buttons/hard.png", skin, main);
        hardLevel.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                globalGameLevel = 3;
                globalGameLevelName = "hard";
                globalBallSpeed = 170;
                levelsBackgroundMusic.stop();
                main.setScreen(new Game(main));}});

        extremeLevel = new Level("images/buttons/extreme.png", skin, main);
        extremeLevel.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                globalGameLevel = 4;
                globalGameLevelName = "extreme";
                globalBallSpeed = 190;
                levelsBackgroundMusic.stop();
                main.setScreen(new Game(main));}});

        insaneLevel = new Level("images/buttons/insane.png", skin, main);
        insaneLevel.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                globalGameLevel = 5;
                globalGameLevelName = "insane";
                globalBallSpeed = 200;
                levelsBackgroundMusic.stop();
                main.setScreen(new Game(main));}});

        ascendLevel = new Level("images/buttons/ascend.png", skin, main);
        ascendLevel.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                globalGameLevel = 6;
                globalGameLevelName = "ascend";
                globalBallSpeed = 80;
                levelsBackgroundMusic.stop();
                main.setScreen(new Game(main));}});

        extendLevel = new Level("images/buttons/extend.png", skin, main);
        extendLevel.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                globalGameLevel = 7;
                globalGameLevelName = "extend";
                globalBallSpeed = 126;
                levelsBackgroundMusic.stop();
                main.setScreen(new Game(main));}});

        collectLevel = new Level("images/buttons/collect.png", skin, main);
        collectLevel.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                globalGameLevel = 8;
                globalGameLevelName = "collect";
                globalBallSpeed = 96;
                levelsBackgroundMusic.stop();
                main.setScreen(new Game(main));}});


        tableLevels.add(easyLevel.getImage()).size(3*screenW/4, 3*screenW/16).spaceLeft(margin / 2);   tableLevels.row();
        tableLevels.add(normalLevel.getImage()).size(3*screenW/4, 3*screenW/16).spaceLeft(margin / 2); tableLevels.row();
        tableLevels.add(hardLevel.getImage()).size(3*screenW/4, 3*screenW/16).spaceLeft(margin / 2);   tableLevels.row();
        tableLevels.add(extremeLevel.getImage()).size(3*screenW/4, 3*screenW/16).spaceLeft(margin / 2); tableLevels.row();
        tableLevels.add(insaneLevel.getImage()).size(3*screenW/4, 3*screenW/16).spaceLeft(margin / 2); tableLevels.row();
        tableLevels.add(ascendLevel.getImage()).size(3*screenW/4, 3*screenW/16).spaceLeft(margin / 2); tableLevels.row();
        tableLevels.add(extendLevel.getImage()).size(3*screenW/4, 3*screenW/16).spaceLeft(margin / 2); tableLevels.row();
        tableLevels.add(collectLevel.getImage()).size(3*screenW/4, 3*screenW/16).spaceLeft(margin / 2); tableLevels.row();

        scrollPaneLevels = new ScrollPane(tableLevels);
        scrollPaneLevelsW = screenW - margin;
        scrollPaneLevelsH = screenH - screenW / 2  - margin/2;
        scrollPaneLevelsX = margin/2;
        scrollPaneLevelsY = margin/2;
        scrollPaneLevels.setSize(scrollPaneLevelsW, scrollPaneLevelsH);
        scrollPaneLevels.setPosition(scrollPaneLevelsX, scrollPaneLevelsY);
        scrollPaneLevels.setScrollingDisabled(true, false);

        stageLevels.addActor(scrollPaneLevels);

        Gdx.input.setInputProcessor(stageLevels);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            levelsBackgroundMusic.stop();
            main.setScreen(new Menu(main));}

        spriteBatch.begin();
            spriteBatch.draw(pvLogo, 0, screenH - screenW / 2, screenW, screenW / 2);
        spriteBatch.end();


        stageLevels.act(delta);
        stageLevels.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stageLevels.dispose();

        pvLogo.getTexture().dispose();

        levelsBackgroundMusic.dispose();

        skin.dispose();
    }
}
