package com.sulient.pixelvoyager.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sulient.pixelvoyager.Main;
import com.sulient.pixelvoyager.tools.InputManager;

import static com.sulient.pixelvoyager.Main.assetManager;

public class Credits implements Screen {

    private final Main main;
    public Credits(final Main main){
        this.main = main;
    }

    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;

    private BitmapFont fontBig, fontMedium, fontSmall;

    private Sprite creditsSprite;
    private float creditsContentStringX, creditsContentStringY, creditsContentW, creditsContentH;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(new InputManager());
        Gdx.input.setCatchBackKey(true);

        spriteBatch = new SpriteBatch();

        fontSmall  = assetManager.get("fonts/fontSmall.ttf", BitmapFont.class);
        fontMedium  = assetManager.get("fonts/fontMedium.ttf", BitmapFont.class);
        fontBig  = assetManager.get("fonts/fontBig.ttf", BitmapFont.class);

        creditsSprite = new Sprite(assetManager.get("images/creditsText.png", Texture.class));
        creditsSprite.flip(false, true);
    }

    @Override
    public void render(float delta) {
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            main.setScreen(new Menu(main));}

        if (Gdx.input.isTouched()){
            main.setScreen(new Menu(main));}

        creditsContentW = screenW - 2*margin;
        creditsContentH = margin + fontBig.getCapHeight() + 2*margin + margin;
        creditsContentStringX = margin;
        creditsContentStringY = 3*margin + fontBig.getCapHeight() + 5*margin;

        spriteBatch.begin();
            spriteBatch.draw(creditsSprite, 0, margin, screenW, screenW/5);

            fontSmall.draw(spriteBatch,
                "Game coding: \n" +
                "Art & visuals: \n" +
                "Music & sounds: \n" +
                "\n" +
                "\n" +
                "                 Achieved with: \n" +
                "                     libGDX  ", creditsContentStringX, creditsContentStringY);

            fontSmall.draw(spriteBatch,
                "Michal Suliborski \n" +
                "Michal Suliborski \n" +
                "Gabriel Rapacz \n", creditsContentStringX + screenW /2, creditsContentStringY);
        spriteBatch.end();
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
        spriteBatch.dispose();
        creditsSprite.getTexture().dispose();

        fontSmall.dispose();
        fontMedium.dispose();
        fontBig.dispose();
    }
}















