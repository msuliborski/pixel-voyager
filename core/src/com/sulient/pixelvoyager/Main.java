package com.sulient.pixelvoyager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.sulient.pixelvoyager.screens.Loading;
import com.sulient.pixelvoyager.tools.PlayServices;

public class Main extends Game {

    public static PlayServices googleServices;

    public Main(PlayServices googleServices) {
        super();
        Main.googleServices = googleServices;
    }


    public static AssetManager assetManager;


    public static int       globalGameLevel = 0;
    public static String    globalGameLevelName = "";
    public static int       globalBallSpeed = 0;

    //stats
    public static double STAT_easy_time_playing;
    public static double STAT_normal_time_playing;
    public static double STAT_hard_time_playing;
    public static double STAT_extreme_time_playing;
    public static double STAT_insane_time_playing;
    public static double STAT_ascend_time_playing;
    public static double STAT_extend_time_playing;
    public static double STAT_collect_time_playing;
    public static double STAT_total_time_playing;

    public static double STAT_easy_best_score;
    public static double STAT_normal_best_score;
    public static double STAT_hard_best_score;
    public static double STAT_extreme_best_score;
    public static double STAT_insane_best_score;
    public static double STAT_ascend_best_score;
    public static double STAT_extend_best_score;
    public static double STAT_collect_best_score;

    public static double STAT_easy_games;
    public static double STAT_normal_games;
    public static double STAT_hard_games;
    public static double STAT_extreme_games;
    public static double STAT_insane_games;
    public static double STAT_ascend_games;
    public static double STAT_extend_games;
    public static double STAT_collect_games;
    public static double STAT_total_games;

    public static double STAT_bronze_total_medals;
    public static double STAT_silver_total_medals;
    public static double STAT_gold_total_medals;

    public static double STAT_max_idle_alive_time;

    public static double STAT_balls_avoided;
    public static double STAT_balls_collected;
    public static double STAT_total_deaths;

    public Main() {

    }

    @Override
    public void create() {

        assetManager = new AssetManager();

        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        setScreen(new Loading(this));
    }
    @Override
    public void dispose() {
        assetManager.dispose();
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize (int width, int height){
        super.resize(width, height);
    }

    @Override
    public void pause (){
        super.pause();
    }

    @Override
    public void resume (){
        super.resume();
    }

}
