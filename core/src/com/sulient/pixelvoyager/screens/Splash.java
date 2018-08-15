package com.sulient.pixelvoyager.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sulient.pixelvoyager.Main;
import com.sulient.pixelvoyager.tools.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import static com.sulient.pixelvoyager.Main.assetManager;

public class Splash implements Screen {

    private final Main main;
    public Splash(final Main main){
        this.main = main;
    }

    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private SpriteBatch spriteBatch;

    private ShapeRenderer shape;

    private TweenManager tweenManager;

    private Sprite splash, loadingBar;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        this.shape = new ShapeRenderer();

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        splash = new Sprite(assetManager.get("images/splash.png", Texture.class));
        splash.setSize(screenW, screenW/5);
        splash.setPosition(0, screenH/2);


        loadingBar = new Sprite(assetManager.get("images/loadingBar.png", Texture.class));
        loadingBar.setSize(screenW - 2*margin, screenW/12);
        loadingBar.setPosition(margin, screenH/2 - margin*4);

        Tween.set(loadingBar, SpriteAccessor.ALPHA).target(1).start(tweenManager);
        Tween.to(loadingBar, SpriteAccessor.ALPHA, 2).target(0).start(tweenManager);

        Tween.set(splash, SpriteAccessor.ALPHA).target(1).start(tweenManager);
        Tween.to(splash, SpriteAccessor.ALPHA, 2).target(0).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                main.setScreen(new Menu(main));
            }
        }).start(tweenManager);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        if(Gdx.input.justTouched()){
            main.setScreen(new Menu(main));}

        spriteBatch.begin();{
            splash.draw(spriteBatch);
            loadingBar.draw(spriteBatch);
        }spriteBatch.end();


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
        splash.getTexture().dispose();
    }
}
