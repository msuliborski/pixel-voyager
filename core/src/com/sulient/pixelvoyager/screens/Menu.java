package com.sulient.pixelvoyager.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sulient.pixelvoyager.Main;
import com.sulient.pixelvoyager.tools.InputManager;

import static com.sulient.pixelvoyager.Main.assetManager;

public class Menu implements Screen{

    private final Main main;
    public Menu(final Main main){
        this.main = main;
    }

    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private SpriteBatch spriteBatch;

    private Stage stageMenu;
    private Table tableMenu;

    private Sprite pvLogo;

    private Image playButton, creditsButton, statsButton, exitButton;
    private Image achievementsButton, textButton, leaderboardsButton, musicButton;

    private Music menuBackgroundMusic;

    public static Preferences saves = Gdx.app.getPreferences("pixelpoyager2");

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputManager());
        Gdx.input.setCatchBackKey(true);

        if (!Main.googleServices.getSignedInGPGS())
            Main.googleServices.loginGPGS();


        spriteBatch = new SpriteBatch();


        if (!assetManager.isLoaded("sounds/menu.ogg", Music.class)) {menuBackgroundMusic.dispose(); assetManager.load("sounds/menu.ogg", Music.class);}
        menuBackgroundMusic = assetManager.get("sounds/menu.ogg", Music.class);
        menuBackgroundMusic.setLooping(true);
        if (saves.getBoolean("volumeON", true)) menuBackgroundMusic.play();


        pvLogo = new Sprite(assetManager.get("images/pixelVoyagerLogo.png", Texture.class));


        stageMenu = new Stage();
        Gdx.input.setInputProcessor(stageMenu);

        tableMenu = new Table();
        tableMenu.setBounds(0, screenW/5, screenW, screenH - screenW / 2);


        playButton = new Image(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/buttons/start.png", Texture.class))));
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y){
                menuBackgroundMusic.stop();
                main.setScreen(new Levels(main));}});

        creditsButton = new Image(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/buttons/credits.png", Texture.class))));
        creditsButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y){
                menuBackgroundMusic.stop();
                main.setScreen(new Credits(main));}});

        statsButton = new Image(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/buttons/stats.png", Texture.class))));
        statsButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y){
                menuBackgroundMusic.stop();
                main.setScreen(new Statistics(main));}});

        exitButton = new Image(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/buttons/exit.png", Texture.class))));
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y){
                menuBackgroundMusic.stop();
                Gdx.app.exit();}});

        tableMenu.add(playButton).size(3*screenW/4, 3*screenW/16);
        tableMenu.row();
        tableMenu.add(creditsButton).size(3*screenW/4, 3*screenW/16);
        tableMenu.row();
        tableMenu.add(statsButton).size(3*screenW/4, 3*screenW/16);
        tableMenu.row();
        tableMenu.add(exitButton).size(3*screenW/4, 3*screenW/16);



        achievementsButton = new Image(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/playServices/achievements.png", Texture.class))));
        achievementsButton.setSize(screenW/5, screenW/5);
        achievementsButton.setX(0);
        achievementsButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y){
                if (Main.googleServices.getSignedInGPGS()) Main.googleServices.getAchievementsGPGS();
                else Main.googleServices.loginGPGS();}});

        textButton = new Image(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/playServices/text.png", Texture.class))));
        textButton.setSize(3*screenW/5, 3*screenW/105);
        textButton.setX(screenW/5);
        textButton.setY(10);

        leaderboardsButton = new Image(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/playServices/leaderboards.png", Texture.class))));
        leaderboardsButton.setSize(screenW/5, screenW/5);
        leaderboardsButton.setX(screenW - screenW/5);
        leaderboardsButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y){
                if (Main.googleServices.getSignedInGPGS()) Main.googleServices.getLeaderboardsGPGS();
                else Main.googleServices.loginGPGS();}});

        if (saves.getBoolean("volumeON", true))
            musicButton = new Image(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/music_on.png", Texture.class))));
        else
            musicButton = new Image(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/music_off.png", Texture.class))));
        musicButton.setSize(screenW/10, screenW/10);
        musicButton.setX(screenW/2 - screenW/20);
        musicButton.setY(screenW/10);
        musicButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y){
                if (saves.getBoolean("volumeON", true)){
                    saves.putBoolean("volumeON", false);
                    saves.flush();
                    menuBackgroundMusic.pause();
                    musicButton.setDrawable(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/music_off.png", Texture.class))));}
                else {
                    saves.putBoolean("volumeON", true);
                    saves.flush();
                    menuBackgroundMusic.play();
                    musicButton.setDrawable(new TextureRegionDrawable(new TextureRegion(assetManager.get("images/music_on.png", Texture.class))));}}});

        stageMenu.addActor(tableMenu);
        stageMenu.addActor(achievementsButton);
        stageMenu.addActor(textButton);
        stageMenu.addActor(leaderboardsButton);
        stageMenu.addActor(musicButton);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            menuBackgroundMusic.stop();
            Gdx.app.exit();}

        spriteBatch.begin();
            spriteBatch.draw(pvLogo, 0, screenH - screenW / 2, screenW, screenW / 2);
        spriteBatch.end();

        stageMenu.act(delta);
        stageMenu.draw();


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
        stageMenu.dispose();

        pvLogo.getTexture().dispose();

        menuBackgroundMusic.dispose();
    }
}
