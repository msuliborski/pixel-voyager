package com.sulient.pixelvoyager.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sulient.pixelvoyager.Main;

import static com.sulient.pixelvoyager.Main.assetManager;


public class Loading implements Screen {

    private final Main main;

    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private SpriteBatch spriteBatch;

    private ShapeRenderer shape;

    private Sprite splash, loadingBar;

    private float progress;

    public Loading(final Main main){
        this.main = main;

        this.shape = new ShapeRenderer();

        this.progress = 0f;

        FreetypeFontLoader.FreeTypeFontLoaderParameter fontSmall = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontSmall.fontFileName = "fonts/fontSmall.ttf";
        fontSmall.fontParameters.size = (int) (screenW / 32);
        fontSmall.fontParameters.color = Color.WHITE;
        fontSmall.fontParameters.flip = true;
        assetManager.load("fonts/fontSmall.ttf", BitmapFont.class, fontSmall);

        FreetypeFontLoader.FreeTypeFontLoaderParameter fontMedium = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontMedium.fontFileName = "fonts/fontMedium.ttf";
        fontMedium.fontParameters.size = (int) (screenW / 15);
        fontMedium.fontParameters.color = Color.WHITE;
        fontMedium.fontParameters.flip = true;
        assetManager.load("fonts/fontMedium.ttf", BitmapFont.class, fontMedium);

        FreetypeFontLoader.FreeTypeFontLoaderParameter fontBig = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontBig.fontFileName = "fonts/fontBig.ttf";
        fontBig.fontParameters.size = (int) (screenW / 9);
        fontBig.fontParameters.color = Color.WHITE;
        fontBig.fontParameters.flip = true;
        assetManager.load("fonts/fontBig.ttf", BitmapFont.class, fontBig);


        assetManager.load("ui/uiskinBig.json", Skin.class);
        assetManager.load("ui/uiskinBig.png", Texture.class);
        assetManager.load("ui/uiskin.png", Texture.class);
        assetManager.load("ui/uiskinBig.atlas", TextureAtlas.class);


        assetManager.load("images/splash.png", Texture.class); //splash & loading
        assetManager.load("images/loadingBar.png", Texture.class); //splash & loading

        assetManager.load("images/pixelVoyagerLogo.png", Texture.class); //menu & levels
        assetManager.load("images/buttons/start.png", Texture.class);
        assetManager.load("images/buttons/credits.png", Texture.class);
        assetManager.load("images/buttons/stats.png", Texture.class);
        assetManager.load("images/buttons/exit.png", Texture.class);
        assetManager.load("images/music_off.png", Texture.class);
        assetManager.load("images/music_on.png", Texture.class);
        assetManager.load("images/playServices/achievements.png", Texture.class);
        assetManager.load("images/playServices/leaderboards.png", Texture.class);
        assetManager.load("images/playServices/text.png", Texture.class);


        assetManager.load("images/creditsText.png", Texture.class);


        assetManager.load("images/statsText.png", Texture.class);


        assetManager.load("images/buttons/easy.png", Texture.class);
        assetManager.load("images/buttons/normal.png", Texture.class);
        assetManager.load("images/buttons/hard.png", Texture.class);
        assetManager.load("images/buttons/extreme.png", Texture.class);
        assetManager.load("images/buttons/insane.png", Texture.class);
        assetManager.load("images/buttons/ascend.png", Texture.class);
        assetManager.load("images/buttons/extend.png", Texture.class);
        assetManager.load("images/buttons/collect.png", Texture.class);


        assetManager.load("images/bg.png", Texture.class);
        assetManager.load("images/ship/ship.png", Texture.class);
        assetManager.load("images/ship/shipIdle.atlas", TextureAtlas.class);
        assetManager.load("images/ship/shipIdle.png", Texture.class);
        assetManager.load("images/ship/shipBoom.atlas", TextureAtlas.class);
        assetManager.load("images/ship/shipBoom.png", Texture.class);
        assetManager.load("images/dodgeThoseBalls.png", Texture.class);
        assetManager.load("images/catchThoseBalls.png", Texture.class);
        assetManager.load("images/arrows2.png", Texture.class);
        assetManager.load("images/arrows4.png", Texture.class);
        assetManager.load("images/info_easy.png", Texture.class);
        assetManager.load("images/info_normal.png", Texture.class);
        assetManager.load("images/info_hard.png", Texture.class);
        assetManager.load("images/info_extreme.png", Texture.class);
        assetManager.load("images/info_insane.png", Texture.class);
        assetManager.load("images/info_ascend.png", Texture.class);
        assetManager.load("images/info_extend.png", Texture.class);
        assetManager.load("images/info_collect.png", Texture.class);
        assetManager.load("images/table.png", Texture.class);
        assetManager.load("images/table_new.png", Texture.class);


        assetManager.load("images/balls/empty.png", Texture.class);
        assetManager.load("images/balls/blue.png", Texture.class);
        assetManager.load("images/balls/green.png", Texture.class);
        assetManager.load("images/balls/red.png", Texture.class);
        assetManager.load("images/balls/violet.png", Texture.class);
        assetManager.load("images/balls/yellow.png", Texture.class);
        assetManager.load("images/balls/blue2.png", Texture.class);
        assetManager.load("images/balls/green2.png", Texture.class);
        assetManager.load("images/balls/red2.png", Texture.class);
        assetManager.load("images/balls/violet2.png", Texture.class);
        assetManager.load("images/balls/yellow2.png", Texture.class);


        assetManager.load("images/medals/bronze.png", Texture.class);
        assetManager.load("images/medals/silver.png", Texture.class);
        assetManager.load("images/medals/gold.png", Texture.class);
        assetManager.load("images/medals/empty.png", Texture.class);


        assetManager.load("sounds/ascend.ogg", Music.class);
        assetManager.load("sounds/extend.ogg", Music.class);
        assetManager.load("sounds/levels.ogg", Music.class);
        assetManager.load("sounds/easy.ogg", Music.class);
        assetManager.load("sounds/extreme.ogg", Music.class);
        assetManager.load("sounds/hard.ogg", Music.class);
        assetManager.load("sounds/insane.ogg", Music.class);
        assetManager.load("sounds/menu.ogg", Music.class);
        assetManager.load("sounds/normal.ogg", Music.class);
        assetManager.load("sounds/collect.ogg", Music.class);
        assetManager.load("sounds/explosion.ogg", Sound.class);
        }

        @Override
        public void show() {
        spriteBatch = new SpriteBatch();

        splash = new Sprite(new Texture("images/splash.png"));
        splash.setSize(screenW, screenW/5);
        splash.setPosition(0, screenH/2);

        loadingBar = new Sprite(new Texture("images/loadingBar.png"));

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (assetManager.update()) {
            main.setScreen(new Splash(main));}

        progress = assetManager.getProgress();

        spriteBatch.begin();{
            splash.draw(spriteBatch);
            spriteBatch.draw(loadingBar, margin, screenH/2 - margin*4, progress * (screenW - 2*margin), screenW/12);
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
        shape.dispose();

    }
}
