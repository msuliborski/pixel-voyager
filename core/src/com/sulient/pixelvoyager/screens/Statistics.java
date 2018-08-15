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
import com.sulient.pixelvoyager.functions.Functions;
import com.sulient.pixelvoyager.tools.InputManager;

import static com.sulient.pixelvoyager.Main.*;


public class Statistics implements Screen {

    private final Main main;
    public Statistics(final Main main){
        this.main = main;
    }

    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;

    private BitmapFont fontBig, fontMedium, fontSmall;

    private Sprite statsSprite;
    private float statisticsContentStringX, statisticsContentStringY, statisticsContentW, statisticsContentH;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(new InputManager());
        Gdx.input.setCatchBackKey(true);

        spriteBatch = new SpriteBatch();

        fontSmall  = assetManager.get("fonts/fontSmall.ttf", BitmapFont.class);
        fontMedium  = assetManager.get("fonts/fontBig.ttf", BitmapFont.class);
        fontBig  = assetManager.get("fonts/fontBig.ttf", BitmapFont.class);

        statsSprite = new Sprite(assetManager.get("images/statsText.png", Texture.class));
        statsSprite.flip(false, true);

        STAT_easy_time_playing     = Double.parseDouble(Menu.saves.getString("STAT_easy_time_playing", "0"));
        STAT_normal_time_playing   = Double.parseDouble(Menu.saves.getString("STAT_normal_time_playing", "0"));
        STAT_hard_time_playing     = Double.parseDouble(Menu.saves.getString("STAT_hard_time_playing", "0"));
        STAT_extreme_time_playing  = Double.parseDouble(Menu.saves.getString("STAT_extreme_time_playing", "0"));
        STAT_insane_time_playing   = Double.parseDouble(Menu.saves.getString("STAT_insane_time_playing", "0"));
        STAT_ascend_time_playing   = Double.parseDouble(Menu.saves.getString("STAT_ascend_time_playing", "0"));
        STAT_extend_time_playing   = Double.parseDouble(Menu.saves.getString("STAT_extend_time_playing", "0"));
        STAT_collect_time_playing   = Double.parseDouble(Menu.saves.getString("STAT_collect_time_playing", "0"));
        STAT_total_time_playing    = Double.parseDouble(Menu.saves.getString("STAT_total_time_playing", "0"));

        STAT_easy_best_score       = Double.parseDouble(Menu.saves.getString("STAT_easy_best_score", "0"));
        STAT_normal_best_score     = Double.parseDouble(Menu.saves.getString("STAT_normal_best_score", "0"));
        STAT_hard_best_score       = Double.parseDouble(Menu.saves.getString("STAT_hard_best_score", "0"));
        STAT_extreme_best_score    = Double.parseDouble(Menu.saves.getString("STAT_extreme_best_score", "0"));
        STAT_insane_best_score     = Double.parseDouble(Menu.saves.getString("STAT_insane_best_score", "0"));
        STAT_ascend_best_score     = Double.parseDouble(Menu.saves.getString("STAT_ascend_best_score", "0"));
        STAT_extend_best_score     = Double.parseDouble(Menu.saves.getString("STAT_extend_best_score", "0"));
        STAT_collect_best_score     = Double.parseDouble(Menu.saves.getString("STAT_collect_best_score", "0"));

        STAT_easy_games            = Double.parseDouble(Menu.saves.getString("STAT_easy_games", "0"));
        STAT_normal_games          = Double.parseDouble(Menu.saves.getString("STAT_normal_games", "0"));
        STAT_hard_games            = Double.parseDouble(Menu.saves.getString("STAT_hard_games", "0"));
        STAT_extreme_games         = Double.parseDouble(Menu.saves.getString("STAT_extreme_games", "0"));
        STAT_insane_games          = Double.parseDouble(Menu.saves.getString("STAT_insane_games", "0"));
        STAT_ascend_games          = Double.parseDouble(Menu.saves.getString("STAT_ascend_games", "0"));
        STAT_extend_games          = Double.parseDouble(Menu.saves.getString("STAT_extend_games", "0"));
        STAT_collect_games          = Double.parseDouble(Menu.saves.getString("STAT_collect_games", "0"));
        STAT_total_games           = Double.parseDouble(Menu.saves.getString("STAT_total_games", "0"));

        STAT_bronze_total_medals   = Double.parseDouble(Menu.saves.getString("STAT_bronze_total_medals", "0"));
        STAT_silver_total_medals   = Double.parseDouble(Menu.saves.getString("STAT_silver_total_medals", "0"));
        STAT_gold_total_medals     = Double.parseDouble(Menu.saves.getString("STAT_gold_total_medals", "0"));

        STAT_max_idle_alive_time   = Double.parseDouble(Menu.saves.getString("STAT_max_idle_alive_time", "0"));

        STAT_balls_avoided         = Double.parseDouble(Menu.saves.getString("STAT_balls_avoided", "0"));
        STAT_balls_collected         = Double.parseDouble(Menu.saves.getString("STAT_balls_collected", "0"));
        STAT_total_deaths          = Double.parseDouble(Menu.saves.getString("STAT_total_deaths", "0"));
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

        statisticsContentW = screenW - 2*margin;
        statisticsContentH = margin + fontBig.getCapHeight() + 2*margin + margin;
        statisticsContentStringX = margin;
        statisticsContentStringY = 3*margin + fontBig.getCapHeight() + 5*margin;

        spriteBatch.begin();
            spriteBatch.draw(statsSprite, 0, margin, screenW, screenW/5);
            fontSmall.draw(spriteBatch,
                "Time playing on EASY: \n" +
                "Time playing on NORMAL: \n" +
                "Time playing on HARD: \n" +
                "Time playing on EXTREME: \n" +
                "Time playing on INSANE: \n" +
                "Time playing on ASCEND: \n" +
                "Time playing on EXTEND: \n" +
                "Time playing on COLLECT: \n" +
                "Total time playing: \n\n\n" +

                "Best score on EASY: \n" +
                "Best score on NORMAL: \n" +
                "Best score on HARD: \n" +
                "Best score on EXTREME: \n" +
                "Best score on INSANE: \n" +
                "Best score on ASCEND: \n" +
                "Best score on EXTEND: \n" +
                "Best score on COLLECT: \n\n\n" +

                "Games on EASY: \n" +
                "Games on NORMAL: \n" +
                "Games on HARD: \n" +
                "Games on EXTREME: \n" +
                "Games on INSANE: \n" +
                "Games on ASCEND: \n" +
                "Games on EXTEND: \n" +
                "Games on COLLECT: \n" +
                "Total games: \n\n\n" +

                "Total bronze medals: \n" +
                "Total silver medals: \n" +
                "Total gold medals: \n\n\n" +

                "Max idle time alive: \n\n\n" +

                "Total balls avoided: \n" +
                "Total balls collected: \n" +
                "Total deaths: \n" +
                        "          ", statisticsContentStringX, statisticsContentStringY);

            fontSmall.draw(spriteBatch,
                Functions.timeFormatOf(STAT_easy_time_playing) + "\n" +
                Functions.timeFormatOf(STAT_normal_time_playing) + "\n" +
                Functions.timeFormatOf(STAT_hard_time_playing) + "\n" +
                Functions.timeFormatOf(STAT_extreme_time_playing) + "\n" +
                Functions.timeFormatOf(STAT_insane_time_playing) + "\n" +
                Functions.timeFormatOf(STAT_ascend_time_playing) + "\n" +
                Functions.timeFormatOf(STAT_extend_time_playing) + "\n" +
                Functions.timeFormatOf(STAT_collect_time_playing) + "\n" +
                Functions.timeFormatOf(STAT_total_time_playing) + "\n\n\n" +

                Math.floor(STAT_easy_best_score*1000)/1000 + "\n" +
                Math.floor(STAT_normal_best_score*1000)/1000 + "\n" +
                Math.floor(STAT_hard_best_score*1000)/1000 + "\n" +
                Math.floor(STAT_extreme_best_score*1000)/1000 + "\n" +
                Math.floor(STAT_insane_best_score*1000)/1000 + "\n" +
                Math.floor(STAT_ascend_best_score*1000)/1000 + "\n" +
                Math.floor(STAT_extend_best_score*1000)/1000 + "\n" +
                Math.floor(STAT_collect_best_score*1000)/1000 + "\n\n\n" +

                Functions.numberFormatOf(STAT_easy_games) + "\n" +
                Functions.numberFormatOf(STAT_normal_games) + "\n" +
                Functions.numberFormatOf(STAT_hard_games) + "\n" +
                Functions.numberFormatOf(STAT_extreme_games) + "\n" +
                Functions.numberFormatOf(STAT_insane_games) + "\n" +
                Functions.numberFormatOf(STAT_ascend_games) + "\n" +
                Functions.numberFormatOf(STAT_extend_games) + "\n" +
                Functions.numberFormatOf(STAT_collect_games) + "\n" +
                Functions.numberFormatOf(STAT_total_games) + "\n\n\n" +

                Functions.numberFormatOf(STAT_bronze_total_medals) + "\n" +
                Functions.numberFormatOf(STAT_silver_total_medals) + "\n" +
                Functions.numberFormatOf(STAT_gold_total_medals) + "\n\n\n" +

                Math.floor(STAT_max_idle_alive_time*1000)/1000 + "\n\n\n" +

                Functions.numberFormatOf(STAT_balls_avoided) + "\n" +
                Functions.numberFormatOf(STAT_balls_collected) + "\n" +
                Functions.numberFormatOf(STAT_total_deaths) + "\n" +
                            "          ", screenW * 3/5, statisticsContentStringY);
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
        statsSprite.getTexture().dispose();

        fontSmall.dispose();
        fontMedium.dispose();
        fontBig.dispose();
    }
}
