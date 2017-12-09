package com.sulient.pixelvoyager.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.sulient.pixelvoyager.Main;
import com.sulient.pixelvoyager.objects.Background;
import com.sulient.pixelvoyager.objects.Ball;
import com.sulient.pixelvoyager.objects.Medal;
import com.sulient.pixelvoyager.objects.Ship;
import com.sulient.pixelvoyager.tools.InputManager;

import static  com.sulient.pixelvoyager.Main.*;
import static com.sulient.pixelvoyager.screens.Menu.saves;

public class Game implements Screen {

    private final Main main;
    public Game(final Main main){
        this.main = main;
    }

    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private OrthographicCamera  camera;

    private SpriteBatch         spriteBatch;

    private Background          background1, background2;

    private Ship                ship;
    private Vector2             targetShipPosition;

    private Medal               medalEmpty, medalBronze, medalSilver, medalGold;

    private Ball                ballGreen,  ballYellow,  ballRed,  ballBlue,  ballViolet;
    private Ball                ballGreen2, ballYellow2, ballRed2, ballBlue2, ballViolet2;

    private Animation<TextureRegion> shipBoomAnim, shipIdleAnim;
    private float               timeIdle, timeBoom;

    private Sprite              scoreTableSprite, infoGameLevel, doThoseBalls, arrows;
    private float               scoreTableWidth, scoreTableHeight, scoreTableX, scoreTableY;

    private float               currentScore = 0, bestScore;

    private Music               gameMusic;
    private Sound               shipBoomSound;

    private BitmapFont          fontSmall, fontMedium, fontBig;

    public static Boolean       shipIsDead = false;
    public static Boolean       ballsFalling = false;
    public static Boolean       shipCollectedAllBalls = true;

    private float               idleTimer = 0, waitBeforeStart = 0;


    private void unlockAchievements() {
        switch (globalGameLevel) {
            case 1: {
                if (bestScore >= 5)  Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQEw");
                if (bestScore >= 15) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQGw");
                if (bestScore >= 25) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQIw");break;}
            case 2: {
                if (bestScore >= 5)  Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQFA");
                if (bestScore >= 15) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQHA");
                if (bestScore >= 25) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQJA");break;}
            case 3: {
                if (bestScore >= 5)  Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQFQ");
                if (bestScore >= 15) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQHQ");
                if (bestScore >= 25) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQJQ");break;}
            case 4: {
                if (bestScore >= 5)  Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQFg");
                if (bestScore >= 15) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQHg");
                if (bestScore >= 25) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQJg");break;}
            case 5: {
                if (bestScore >= 5)  Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQFw");
                if (bestScore >= 15) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQHw");
                if (bestScore >= 25) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQJw");break;}
            case 6: {
                if (bestScore >= 5)  Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQGA");
                if (bestScore >= 15) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQIA");
                if (bestScore >= 25) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQKA");break;}
            case 7: {
                if (bestScore >= 5)  Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQGQ");
                if (bestScore >= 15) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQIQ");
                if (bestScore >= 25) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQKQ");break;}
            case 8: {
                if (bestScore >= 5)  Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQGg");
                if (bestScore >= 15) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQIg");
                if (bestScore >= 25) Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQKg");break;}
        }

        if (    (saves.getFloat("easyBestScore", 0) >= 31.0) &&
                (saves.getFloat("normalBestScore", 0) >= 26.0) &&
                (saves.getFloat("hardBestScore", 0) >= 48.0) &&
                (saves.getFloat("extremeBestScore", 0) >= 26.0) &&
                (saves.getFloat("insaneBestScore", 0) >= 19.0))
                {Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQCQ");}  //31s EASY; 26s NORMAL; 48s HARD; 26s EXTREME; 19s INSANE;
    }

    private void incrementAchievements() {
        switch (globalGameLevel) {
            case 1: {Main.googleServices.incrementAchievementGPGS("CgkI_KiYto0CEAIQCw"); break;}
            case 2: {Main.googleServices.incrementAchievementGPGS("CgkI_KiYto0CEAIQDA"); break;}
            case 3: {Main.googleServices.incrementAchievementGPGS("CgkI_KiYto0CEAIQDQ"); break;}
            case 4: {Main.googleServices.incrementAchievementGPGS("CgkI_KiYto0CEAIQDg"); break;}
            case 5: {Main.googleServices.incrementAchievementGPGS("CgkI_KiYto0CEAIQDw"); break;}
            case 6: {Main.googleServices.incrementAchievementGPGS("CgkI_KiYto0CEAIQEA"); break;}
            case 7: {Main.googleServices.incrementAchievementGPGS("CgkI_KiYto0CEAIQEQ"); break;}
            case 8: {Main.googleServices.incrementAchievementGPGS("CgkI_KiYto0CEAIQEg"); break;}
        }
    }


    private void updateLeaderboards() {
        switch (globalGameLevel) {
            case 1: {Main.googleServices.submitScoreGPGS(bestScore, "CgkI_KiYto0CEAIQAQ"); break;}
            case 2: {Main.googleServices.submitScoreGPGS(bestScore, "CgkI_KiYto0CEAIQAg"); break;}
            case 3: {Main.googleServices.submitScoreGPGS(bestScore, "CgkI_KiYto0CEAIQAw"); break;}
            case 4: {Main.googleServices.submitScoreGPGS(bestScore, "CgkI_KiYto0CEAIQBA"); break;}
            case 5: {Main.googleServices.submitScoreGPGS(bestScore, "CgkI_KiYto0CEAIQBQ"); break;}
            case 6: {Main.googleServices.submitScoreGPGS(bestScore, "CgkI_KiYto0CEAIQBg"); break;}
            case 7: {Main.googleServices.submitScoreGPGS(bestScore, "CgkI_KiYto0CEAIQBw"); break;}
            case 8: {Main.googleServices.submitScoreGPGS(bestScore, "CgkI_KiYto0CEAIQCA"); break;}
        }
    }

    private void saveAllData() {
        saves.putFloat(globalGameLevelName + "BestScore", bestScore);

        saves.putString("STAT_easy_time_playing", String.valueOf(STAT_easy_time_playing));
        saves.putString("STAT_normal_time_playing", String.valueOf(STAT_normal_time_playing));
        saves.putString("STAT_hard_time_playing", String.valueOf(STAT_hard_time_playing));
        saves.putString("STAT_extreme_time_playing", String.valueOf(STAT_extreme_time_playing));
        saves.putString("STAT_insane_time_playing", String.valueOf(STAT_insane_time_playing));
        saves.putString("STAT_ascend_time_playing", String.valueOf(STAT_ascend_time_playing));
        saves.putString("STAT_extend_time_playing", String.valueOf(STAT_extend_time_playing));
        saves.putString("STAT_collect_time_playing", String.valueOf(STAT_collect_time_playing));
        saves.putString("STAT_total_time_playing", String.valueOf(STAT_total_time_playing));

        saves.putString("STAT_easy_best_score", String.valueOf(STAT_easy_best_score));
        saves.putString("STAT_normal_best_score", String.valueOf(STAT_normal_best_score));
        saves.putString("STAT_hard_best_score", String.valueOf(STAT_hard_best_score));
        saves.putString("STAT_extreme_best_score", String.valueOf(STAT_extreme_best_score));
        saves.putString("STAT_insane_best_score", String.valueOf(STAT_insane_best_score));
        saves.putString("STAT_ascend_best_score", String.valueOf(STAT_ascend_best_score));
        saves.putString("STAT_extend_best_score", String.valueOf(STAT_extend_best_score));
        saves.putString("STAT_collect_best_score", String.valueOf(STAT_collect_best_score));

        saves.putString("STAT_easy_games", String.valueOf(STAT_easy_games));
        saves.putString("STAT_normal_games", String.valueOf(STAT_normal_games));
        saves.putString("STAT_hard_games", String.valueOf(STAT_hard_games));
        saves.putString("STAT_extreme_games", String.valueOf(STAT_extreme_games));
        saves.putString("STAT_insane_games", String.valueOf(STAT_insane_games));
        saves.putString("STAT_ascend_games", String.valueOf(STAT_ascend_games));
        saves.putString("STAT_extend_games", String.valueOf(STAT_extend_games));
        saves.putString("STAT_collect_games", String.valueOf(STAT_collect_games));
        saves.putString("STAT_total_games", String.valueOf(STAT_total_games));

        saves.putString("STAT_bronze_total_medals", String.valueOf(STAT_bronze_total_medals));
        saves.putString("STAT_silver_total_medals", String.valueOf(STAT_silver_total_medals));
        saves.putString("STAT_gold_total_medals", String.valueOf(STAT_gold_total_medals));

        saves.putString("STAT_max_idle_alive_time", String.valueOf(STAT_max_idle_alive_time));

        saves.putString("STAT_balls_avoided", String.valueOf(STAT_balls_avoided));
        saves.putString("STAT_balls_collected", String.valueOf(STAT_balls_collected));
        saves.putString("STAT_total_deaths", String.valueOf(STAT_total_deaths));

        saves.flush();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputManager());
        Gdx.input.setCatchBackKey(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(true, screenW, screenH);

        Main.googleServices.loginGPGS();

        spriteBatch = new SpriteBatch();

        STAT_easy_time_playing     = Double.parseDouble(saves.getString("STAT_easy_time_playing", "0"));
        STAT_normal_time_playing   = Double.parseDouble(saves.getString("STAT_normal_time_playing", "0"));
        STAT_hard_time_playing     = Double.parseDouble(saves.getString("STAT_hard_time_playing", "0"));
        STAT_extreme_time_playing  = Double.parseDouble(saves.getString("STAT_extreme_time_playing", "0"));
        STAT_insane_time_playing   = Double.parseDouble(saves.getString("STAT_insane_time_playing", "0"));
        STAT_ascend_time_playing   = Double.parseDouble(saves.getString("STAT_ascend_time_playing", "0"));
        STAT_extend_time_playing   = Double.parseDouble(saves.getString("STAT_extend_time_playing", "0"));
        STAT_collect_time_playing  = Double.parseDouble(saves.getString("STAT_collect_time_playing", "0"));
        STAT_total_time_playing    = Double.parseDouble(saves.getString("STAT_total_time_playing", "0"));

        STAT_easy_best_score       = Double.parseDouble(saves.getString("STAT_easy_best_score", "0"));
        STAT_normal_best_score     = Double.parseDouble(saves.getString("STAT_normal_best_score", "0"));
        STAT_hard_best_score       = Double.parseDouble(saves.getString("STAT_hard_best_score", "0"));
        STAT_extreme_best_score    = Double.parseDouble(saves.getString("STAT_extreme_best_score", "0"));
        STAT_insane_best_score     = Double.parseDouble(saves.getString("STAT_insane_best_score", "0"));
        STAT_ascend_best_score     = Double.parseDouble(saves.getString("STAT_ascend_best_score", "0"));
        STAT_extend_best_score     = Double.parseDouble(saves.getString("STAT_extend_best_score", "0"));
        STAT_collect_best_score    = Double.parseDouble(saves.getString("STAT_collect_best_score", "0"));

        STAT_easy_games            = Double.parseDouble(saves.getString("STAT_easy_games", "0"));
        STAT_normal_games          = Double.parseDouble(saves.getString("STAT_normal_games", "0"));
        STAT_hard_games            = Double.parseDouble(saves.getString("STAT_hard_games", "0"));
        STAT_extreme_games         = Double.parseDouble(saves.getString("STAT_extreme_games", "0"));
        STAT_insane_games          = Double.parseDouble(saves.getString("STAT_insane_games", "0"));
        STAT_ascend_games          = Double.parseDouble(saves.getString("STAT_ascend_games", "0"));
        STAT_extend_games          = Double.parseDouble(saves.getString("STAT_extend_games", "0"));
        STAT_collect_games         = Double.parseDouble(saves.getString("STAT_collect_games", "0"));
        STAT_total_games           = Double.parseDouble(saves.getString("STAT_total_games", "0"));

        STAT_bronze_total_medals   = Double.parseDouble(saves.getString("STAT_bronze_total_medals", "0"));
        STAT_silver_total_medals   = Double.parseDouble(saves.getString("STAT_silver_total_medals", "0"));
        STAT_gold_total_medals     = Double.parseDouble(saves.getString("STAT_gold_total_medals", "0"));

        STAT_max_idle_alive_time   = Double.parseDouble(saves.getString("STAT_max_idle_alive_time", "0"));

        STAT_balls_avoided         = Double.parseDouble(saves.getString("STAT_balls_avoided", "0"));
        STAT_balls_collected       = Double.parseDouble(saves.getString("STAT_balls_collected", "0"));
        STAT_total_deaths          = Double.parseDouble(saves.getString("STAT_total_deaths", "0"));



        bestScore = saves.getFloat(globalGameLevelName + "BestScore", 0);
        if (!assetManager.isLoaded("sounds/" + globalGameLevelName + ".ogg", Music.class)) {gameMusic.dispose(); assetManager.load("sounds/" + globalGameLevelName + ".ogg", Music.class);}
        gameMusic = assetManager.get("sounds/" + globalGameLevelName + ".ogg", Music.class);


        gameMusic.setLooping(true);

        unlockAchievements();
        updateLeaderboards();

        ship = new Ship(main);
        ship.resetPosition();
        targetShipPosition = new Vector2(ship.getPosition());
        if (!assetManager.isLoaded("sounds/explosion.ogg", Sound.class)) {gameMusic.dispose(); assetManager.load("sounds/explosion.ogg", Sound.class);}
        shipBoomSound = assetManager.get("sounds/explosion.ogg", Sound.class);
        shipBoomSound.setLooping(1, false);
        shipBoomAnim = new Animation<TextureRegion>(0.2f, ship.getShipBoom().getRegions());
        shipIdleAnim = new Animation<TextureRegion>(0.2f, ship.getShipIdle().getRegions());

        shipIsDead = false;
        ballsFalling = false;
        shipCollectedAllBalls = true;

        background1 = new Background(0f, -2*screenW + screenW, screenW*1, 2*screenW, main);
        background2 = new Background(0f, screenW, screenW*1, 2*screenW, main);

        fontSmall  = assetManager.get("fonts/fontSmall.ttf", BitmapFont.class);
        fontMedium  = assetManager.get("fonts/fontMedium.ttf", BitmapFont.class);
        fontBig  = assetManager.get("fonts/fontBig.ttf", BitmapFont.class);

        ballGreen = new Ball("green", main);
        ballYellow = new Ball("yellow", main);
        ballRed = new Ball("red", main);
        ballBlue = new Ball("blue", main);
        ballViolet = new Ball("violet", main);
        ballGreen2 = new Ball("green2", main);
        ballYellow2 = new Ball("yellow2", main);
        ballRed2 = new Ball("red2", main);
        ballBlue2 = new Ball("blue2", main);
        ballViolet2 = new Ball("violet2", main);

        medalEmpty      = new Medal ("empty" , main);
        medalBronze     = new Medal ("bronze" , main);
        medalSilver     = new Medal ("silver" , main);
        medalGold       = new Medal ("gold" , main);

        infoGameLevel   = new Sprite(assetManager.get("images/info_" + globalGameLevelName + ".png", Texture.class));
        infoGameLevel.flip(false, true);

        scoreTableSprite = new Sprite(assetManager.get("images/table.png", Texture.class));
        scoreTableWidth = screenW*3/4;
        scoreTableHeight = 2*scoreTableWidth/3;
        scoreTableX = screenW/8;
        scoreTableY = screenH/2 - scoreTableHeight/2;

        if (globalGameLevel != 8) doThoseBalls = new Sprite(assetManager.get("images/dodgeThoseBalls.png", Texture.class));
        else doThoseBalls = new Sprite(assetManager.get("images/catchThoseBalls.png", Texture.class));
        doThoseBalls.flip(false, true);

        if (globalGameLevel != 8 && globalGameLevel != 7) arrows = new Sprite(assetManager.get("images/arrows2.png", Texture.class));
        else arrows = new Sprite(assetManager.get("images/arrows4.png", Texture.class));
        arrows.flip(false, true);



    }



    private void collectCollision(Ball ball, Ship ship) {
        if ((   Intersector.overlaps(ball.getShape(), ship.getHitBox1()) || Intersector.overlaps(ball.getShape(), ship.getHitBox2())) && !ball.isCollected()) {
            ball.setSprite(new Sprite(assetManager.get("images/balls/empty.png", Texture.class)));
            ball.setCollected(true);
            STAT_balls_collected++;

        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

////////////////////////////////////////////////////////////////////////////////
        //BACK PRESSED
////////////////////////////////////////////////////////////////////////////////
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            gameMusic.stop();
            shipBoomSound.stop();

            timeBoom = 0f;

            ballsFalling = false;
            shipIsDead = false;
            shipCollectedAllBalls = true;

            main.setScreen(new Levels(main));
        }

////////////////////////////////////////////////////////////////////////////////
        //SHIP CONTROLLING
////////////////////////////////////////////////////////////////////////////////
        if (!shipIsDead && Gdx.input.isTouched() && ballsFalling) {
            targetShipPosition.x = Gdx.input.getX() - ship.getWidth()/2; targetShipPosition.y = ship.getY();}
        if (!shipIsDead && Gdx.input.isTouched() && ballsFalling && ((globalGameLevel == 7) || (globalGameLevel == 8))) {
            targetShipPosition.y = Gdx.input.getY() - ship.getWidth()*4/3;}

        if (ballsFalling && !shipIsDead) {
            if (Math.sqrt(Math.pow(ship.getX() - targetShipPosition.x, 2) + Math.pow(ship.getY() - targetShipPosition.y, 2)) <= screenW/4) {
                ship.setPosition(targetShipPosition.x, targetShipPosition.y);}
            else {
                if (Gdx.input.justTouched()) {
                    ship.setPosition(ship.getPosition().add((targetShipPosition.x -  ship.getX())/3, (targetShipPosition.y - ship.getY())/3));}
                else {
                    ship.setPosition(targetShipPosition.x, targetShipPosition.y);}}

            if (ship.getX() < 0)
                ship.setPosition(0, ship.getY());
            if (ship.getX() > screenW - ship.getWidth())
                ship.setPosition(screenW - ship.getWidth(), ship.getY());
            if (ship.getY() < 0)
                ship.setPosition(ship.getX(), 0);
            if (ship.getY() > (3*screenW / 2))
                ship.setPosition(ship.getX(), 3*screenW / 2);

            targetShipPosition.set(ship.getX(), ship.getY());

            if (!Gdx.input.isTouched()) idleTimer += delta;
            else idleTimer = 0;
            if (idleTimer >= 5) { Main.googleServices.unlockAchievementGPGS("CgkI_KiYto0CEAIQCg");}
            if (idleTimer > STAT_max_idle_alive_time) STAT_max_idle_alive_time = idleTimer;
        }

////////////////////////////////////////////////////////////////////////////////
        //BACKGROUND SCROLLING
////////////////////////////////////////////////////////////////////////////////
        if (ballsFalling) {
            background1.scroll(ballGreen.getFallingSpeed(), delta);
            background2.scroll(ballGreen.getFallingSpeed(), delta);}

        if (background1.getY() >= background1.getHeight())
            background1.setOnTop();
        if (background2.getY() >= background2.getHeight())
            background2.setOnTop();

////////////////////////////////////////////////////////////////////////////////
        //BALLS HANDLING
////////////////////////////////////////////////////////////////////////////////
        if (ballsFalling) {
            ballGreen.updatePosition(delta);
            ballYellow.updatePosition(delta);
            ballRed.updatePosition(delta);
            ballBlue.updatePosition(delta);
            ballViolet.updatePosition(delta);

            if ((globalGameLevel == 7) || (globalGameLevel == 8)) {
                ballGreen2.updatePosition(delta);
                ballYellow2.updatePosition(delta);
                ballRed2.updatePosition(delta);
                ballBlue2.updatePosition(delta);
                ballViolet2.updatePosition(delta);}
        }

////////////////////////////////////////////////////////////////////////////////
        //TOUCH TO PLAY
////////////////////////////////////////////////////////////////////////////////
        waitBeforeStart += delta;
        if (!ballsFalling && Gdx.input.justTouched() && waitBeforeStart>=0.25) {
            ballsFalling = true;
            ship.resetPosition();
            if (saves.getBoolean("volumeON", true)) gameMusic.play();
        }

        if (!shipIsDead && ballsFalling) {
            currentScore += delta;}

////////////////////////////////////////////////////////////////////////////////
        //COLLISIONS
////////////////////////////////////////////////////////////////////////////////

        if (    (Intersector.overlaps(ballBlue.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballBlue.getShape(), ship.getHitBox2()) ||
                Intersector.overlaps(ballGreen.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballGreen.getShape(), ship.getHitBox2()) ||
                Intersector.overlaps(ballRed.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballRed.getShape(), ship.getHitBox2()) ||
                Intersector.overlaps(ballViolet.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballViolet.getShape(), ship.getHitBox2()) ||
                Intersector.overlaps(ballYellow.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballYellow.getShape(), ship.getHitBox2()) ||
                Intersector.overlaps(ballBlue2.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballBlue2.getShape(), ship.getHitBox2()) ||
                Intersector.overlaps(ballGreen2.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballGreen2.getShape(), ship.getHitBox2()) ||
                Intersector.overlaps(ballRed2.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballRed2.getShape(), ship.getHitBox2()) ||
                Intersector.overlaps(ballViolet2.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballViolet2.getShape(), ship.getHitBox2()) ||
                Intersector.overlaps(ballYellow2.getShape(), ship.getHitBox1()) || Intersector.overlaps(ballYellow2.getShape(), ship.getHitBox2()))
                && (!shipIsDead) &&  (globalGameLevel != 8)) {
            waitBeforeStart = 0f;
            shipIsDead = true;
            gameMusic.stop();
            timeBoom = 0f;
            if (saves.getBoolean("volumeON", true)) shipBoomSound.play();

            if (currentScore > bestScore) {
                scoreTableSprite = new Sprite(assetManager.get("images/table_new.png", Texture.class)); scoreTableSprite.flip(false, true);
                bestScore = currentScore;}
            else {
                scoreTableSprite = new Sprite(assetManager.get("images/table.png", Texture.class)); scoreTableSprite.flip(false, true);}


            switch (globalGameLevel) {
                case 1: {   STAT_easy_time_playing += currentScore;
                            STAT_easy_best_score = bestScore;
                            STAT_easy_games++; break;}
                case 2: {   STAT_normal_time_playing += currentScore;
                            STAT_normal_best_score = bestScore;
                            STAT_normal_games++; break;}
                case 3: {   STAT_hard_time_playing += currentScore;
                            STAT_hard_best_score = bestScore;
                            STAT_hard_games++; break;}
                case 4: {   STAT_extreme_time_playing += currentScore;
                            STAT_extreme_best_score = bestScore;
                            STAT_extreme_games++; break;}
                case 5: {   STAT_insane_time_playing += currentScore;
                            STAT_insane_best_score = bestScore;
                            STAT_insane_games++; break;}
                case 6: {   STAT_ascend_time_playing += currentScore;
                            STAT_ascend_best_score = bestScore;
                            STAT_ascend_games++; break;}
                case 7: {   STAT_extend_time_playing += currentScore;
                            STAT_extend_best_score = bestScore;
                            STAT_extend_games++; break;}}
            STAT_total_time_playing += currentScore;
            STAT_total_games++;
            STAT_total_deaths++;

            if(currentScore >= 5) STAT_bronze_total_medals++;
            if(currentScore >= 15) STAT_silver_total_medals++;
            if(currentScore >= 25) STAT_gold_total_medals++;

            incrementAchievements();
            unlockAchievements();
            saveAllData();
            updateLeaderboards();
        }

        if (globalGameLevel == 8){
                if (!shipIsDead) {
                collectCollision(ballGreen, ship);
                collectCollision(ballBlue, ship);
                collectCollision(ballYellow, ship);
                collectCollision(ballRed, ship);
                collectCollision(ballViolet, ship);
                collectCollision(ballGreen2, ship);
                collectCollision(ballBlue2, ship);
                collectCollision(ballYellow2, ship);
                collectCollision(ballRed2, ship);
                collectCollision(ballViolet2, ship);}

            if (!shipCollectedAllBalls && !shipIsDead && ballsFalling) {
                waitBeforeStart = 0f;
                shipIsDead = true;
                gameMusic.stop();
                timeBoom = 0f;
                if (saves.getBoolean("volumeON", true)) shipBoomSound.play();
                if (currentScore > bestScore) {
                    scoreTableSprite = new Sprite(assetManager.get("images/table_new.png", Texture.class)); scoreTableSprite.flip(false, true);
                    bestScore = currentScore;}
                else {scoreTableSprite = new Sprite(assetManager.get("images/table.png", Texture.class)); scoreTableSprite.flip(false, true);}

                STAT_collect_time_playing += currentScore;
                STAT_collect_best_score = bestScore;
                STAT_collect_games++;
                STAT_total_time_playing += currentScore;
                STAT_total_games++;
                STAT_total_deaths++;

                if(currentScore >= 5) STAT_bronze_total_medals++;
                if(currentScore >= 15) STAT_silver_total_medals++;
                if(currentScore >= 25) STAT_gold_total_medals++;

                incrementAchievements();
                unlockAchievements();
                saveAllData();
                updateLeaderboards();
            }

        }

////////////////////////////////////////////////////////////////////////////////
        //ANIMATIONS
////////////////////////////////////////////////////////////////////////////////
        if (!shipIsDead) timeIdle += delta;
        else timeBoom += delta;

        if (!shipIsDead && ballsFalling) ship.setSprite(new Sprite(shipIdleAnim.getKeyFrame(timeIdle, true)));

        if (shipIsDead){
            if (ballsFalling) ship.setSprite(new Sprite(shipBoomAnim.getKeyFrame(timeBoom, false)));
            else ship.setSprite(new Sprite(assetManager.get("images/ship/ship.png", Texture.class)));}


////////////////////////////////////////////////////////////////////////////////
        //RESET
////////////////////////////////////////////////////////////////////////////////
        if(shipIsDead && Gdx.input.justTouched() && waitBeforeStart>=0.25)
        {
            shipIsDead = false;
            shipCollectedAllBalls = true;
            shipBoomSound.stop();
            timeBoom = 0;
            ship.setSprite(new Sprite(assetManager.get("images/ship/ship.png", Texture.class)));
            ship.resetPosition();
            targetShipPosition.x = screenW/2 - ship.getWidth()/2;

            background1.setY((-2)*screenW);
            background2.setY(0);

            ballGreen.resetPosition();
            ballYellow.resetPosition();
            ballRed.resetPosition();
            ballBlue.resetPosition();
            ballViolet.resetPosition();
            ballGreen2.resetPosition();
            ballYellow2.resetPosition();
            ballRed2.resetPosition();
            ballBlue2.resetPosition();
            ballViolet2.resetPosition();
            ballsFalling = false;

            currentScore = 0;
        }


////////////////////////////////////////////////////////////////////////////////
        //DRAWING EVERYTHING
////////////////////////////////////////////////////////////////////////////////

        spriteBatch.begin();{
            spriteBatch.draw(background1.getSprite(), background1.getX(), background1.getY(), background1.getWidth(), background1.getHeight());
            spriteBatch.draw(background2.getSprite(), background2.getX(), background2.getY(), background2.getWidth(), background2.getHeight());

            spriteBatch.draw(ship.getSprite(), ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());

            spriteBatch.draw(ballGreen.getSprite(), ballGreen.getX(), ballGreen.getY(), ballGreen.getWidth(), ballGreen.getHeight());
            spriteBatch.draw(ballYellow.getSprite(), ballYellow.getX(), ballYellow.getY(), ballYellow.getWidth(), ballYellow.getHeight());
            spriteBatch.draw(ballRed.getSprite(), ballRed.getX(), ballRed.getY(), ballRed.getWidth(), ballRed.getHeight());
            spriteBatch.draw(ballBlue.getSprite(), ballBlue.getX(), ballBlue.getY(), ballBlue.getWidth(), ballBlue.getHeight());
            spriteBatch.draw(ballViolet.getSprite(), ballViolet.getX(), ballViolet.getY(), ballViolet.getWidth(), ballViolet.getHeight());
            spriteBatch.draw(ballGreen2.getSprite(), ballGreen2.getX(), ballGreen2.getY(), ballGreen2.getWidth(), ballGreen2.getHeight());
            spriteBatch.draw(ballYellow2.getSprite(), ballYellow2.getX(), ballYellow2.getY(), ballYellow2.getWidth(), ballYellow2.getHeight());
            spriteBatch.draw(ballRed2.getSprite(), ballRed2.getX(), ballRed2.getY(), ballRed2.getWidth(), ballRed2.getHeight());
            spriteBatch.draw(ballBlue2.getSprite(), ballBlue2.getX(), ballBlue2.getY(), ballBlue2.getWidth(), ballBlue2.getHeight());
            spriteBatch.draw(ballViolet2.getSprite(), ballViolet2.getX(), ballViolet2.getY(), ballViolet2.getWidth(), ballViolet2.getHeight());

            fontMedium.draw(spriteBatch, "TIME:", margin/2, margin);
            fontBig.draw(spriteBatch, ((float) (Math.floor(currentScore * 1000) / 1000) + ""), margin/2, margin + fontMedium.getCapHeight() + margin);

            if ((currentScore >= 0) && (currentScore < 5))
                spriteBatch.draw(medalEmpty.getSprite(), medalEmpty.getX(), medalEmpty.getY(), medalEmpty.getWidth(), medalEmpty.getHeight());
            else if ((currentScore >= 5) && (currentScore < 15))
                spriteBatch.draw(medalBronze.getSprite(), medalBronze.getX(), medalBronze.getY(), medalBronze.getWidth(), medalBronze.getHeight());
            else if ((currentScore >= 15) && (currentScore < 25))
                spriteBatch.draw(medalSilver.getSprite(), medalSilver.getX(), medalSilver.getY(), medalSilver.getWidth(), medalSilver.getHeight());
            else if (currentScore >= 25)
                spriteBatch.draw(medalGold.getSprite(), medalGold.getX(), medalGold.getY(), medalGold.getWidth(), medalGold.getHeight());

            float bestScoreWidth = ((float) (Math.floor(bestScore * 1000) / 1000) + "").length() * fontMedium.getSpaceWidth();
            if (shipIsDead) {
                spriteBatch.draw(scoreTableSprite, scoreTableX, scoreTableY, scoreTableWidth, scoreTableHeight);
                spriteBatch.draw(infoGameLevel, screenW / 2 - scoreTableWidth / 2, scoreTableY - scoreTableWidth / 12, scoreTableWidth, scoreTableWidth / 12);
                fontMedium.draw(spriteBatch, ((float) (Math.floor(currentScore * 1000) / 1000) + ""), scoreTableX + margin, scoreTableY + screenW / 8);
                fontMedium.draw(spriteBatch, ((float) (Math.floor(bestScore * 1000) / 1000) + ""), scoreTableX + scoreTableWidth - bestScoreWidth - 5*margin/2 , scoreTableY + screenW / 8);
            }

            if (!ballsFalling){
                spriteBatch.draw(doThoseBalls, margin, screenH / 3, screenW - 2*margin, (screenW - 2*margin) / 2);
                spriteBatch.draw(arrows, screenW/2 - (ship.getWidth() * 3) / 2, ship. getY() + ship.getHeight()/2 - (ship.getHeight() * 3) / 2, ship.getWidth() * 3, ship.getHeight() * 3);
            }
        }spriteBatch.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(true, width, height);
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

        fontSmall.dispose();
        fontMedium.dispose();
        fontBig.dispose();

        background1.getSprite().getTexture().dispose();
        background2.getSprite().getTexture().dispose();

        ship.getSprite().getTexture().dispose();

        ballGreen.getSprite().getTexture().dispose();
        ballYellow.getSprite().getTexture().dispose();
        ballRed.getSprite().getTexture().dispose();
        ballBlue.getSprite().getTexture().dispose();
        ballViolet.getSprite().getTexture().dispose();
        ballGreen2.getSprite().getTexture().dispose();
        ballYellow2.getSprite().getTexture().dispose();
        ballRed2.getSprite().getTexture().dispose();
        ballBlue2.getSprite().getTexture().dispose();
        ballViolet2.getSprite().getTexture().dispose();

        medalEmpty.getSprite().getTexture().dispose();
        medalBronze.getSprite().getTexture().dispose();
        medalSilver.getSprite().getTexture().dispose();
        medalGold.getSprite().getTexture().dispose();

        gameMusic.dispose();
        shipBoomSound.dispose();

        scoreTableSprite.getTexture().dispose();
        infoGameLevel.getTexture().dispose();

        doThoseBalls.getTexture().dispose();
    }
}
