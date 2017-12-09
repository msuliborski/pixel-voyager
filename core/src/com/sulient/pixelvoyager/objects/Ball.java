package com.sulient.pixelvoyager.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.sulient.pixelvoyager.Main;
import com.sulient.pixelvoyager.screens.Game;

import static com.sulient.pixelvoyager.screens.Game.*;
import static com.sulient.pixelvoyager.Main.*;

public class Ball {

    private Main main;

    //Helping Variables
    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private Sprite ballSprite;
    private Circle ballShape;
    private float ballPositionX, ballPositionY;
    private float ballWidth, ballHeight, ballSpacing;
    private float ballFallingSpeed;

    private boolean collected;


    private String color;

    public Ball(String color, Main main) {
        this.main = main;
        this.color = color;

        this.ballWidth = screenW / 10;
        this.ballHeight = screenW / 10;

        this.collected = false;

        switch (globalGameLevel) {
            case 1: {this.ballSpacing = screenW * 20/40; break;}
            case 2: {this.ballSpacing = screenW * 21/40; break;}
            case 3: {this.ballSpacing = screenW * 22/40; break;}
            case 4: {this.ballSpacing = screenW * 23/40; break;}
            case 5: {this.ballSpacing = screenW * 24/40; break;}
            case 6: {this.ballSpacing = screenW * 25/40; break;}
            case 7: {this.ballSpacing = screenW/6 + ballHeight * 3/2; break;}
            case 8: {this.ballSpacing = screenW/6 + ballHeight * 3/2; break;}
        }


        this.ballFallingSpeed = globalBallSpeed * screenW / 56;

        this.ballSprite = new Sprite(assetManager.get("images/balls/" + color + ".png", Texture.class));

        if      (color.equals("green"))     {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight - 0;}
        else if (color.equals("yellow"))    {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -1 * ballSpacing;}
        else if (color.equals("red"))       {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -2 * ballSpacing;}
        else if (color.equals("blue"))      {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -3 * ballSpacing;}
        else if (color.equals("violet"))    {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -4 * ballSpacing;}
        else if (color.equals("green2"))    {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -5 * ballSpacing;}
        else if (color.equals("yellow2"))   {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -6 * ballSpacing;}
        else if (color.equals("red2"))      {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -7 * ballSpacing;}
        else if (color.equals("blue2"))     {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -8 * ballSpacing;}
        else if (color.equals("violet2"))   {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -9 * ballSpacing;}

        this.ballShape = new Circle(ballPositionX + ballWidth / 2, ballPositionY + ballHeight / 2, ballWidth * 9 / 20);
    }


    public void resetPosition() {
        if      (color.equals("green"))     {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight - 0;}
        else if (color.equals("yellow"))    {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -1 * ballSpacing;}
        else if (color.equals("red"))       {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -2 * ballSpacing;}
        else if (color.equals("blue"))      {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -3 * ballSpacing;}
        else if (color.equals("violet"))    {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -4 * ballSpacing;}
        else if (color.equals("green2"))    {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -5 * ballSpacing;}
        else if (color.equals("yellow2"))   {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -6 * ballSpacing;}
        else if (color.equals("red2"))      {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -7 * ballSpacing;}
        else if (color.equals("blue2"))     {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -8 * ballSpacing;}
        else if (color.equals("violet2"))   {this.ballPositionX = (float) (Math.random() * (screenW - ballWidth)); ballPositionY = -ballHeight -9 * ballSpacing;}

        ballShape = new Circle(ballPositionX + ballWidth / 2, ballPositionY + ballHeight / 2, ballWidth * 9 / 20);

        ballFallingSpeed = globalBallSpeed * screenW / 56;

        this.resetSprite();
    }

    public void updatePosition(float delta) {
        ballPositionY += delta * ballFallingSpeed;
        ballShape.setPosition(ballPositionX + ballWidth / 2, ballPositionY + ballHeight / 2);

        if (globalGameLevel == 6 && !shipIsDead && ballsFalling) this.ballFallingSpeed += (float)(Math.pow((1 + 1/ballFallingSpeed), ballFallingSpeed)/1.5);

        if (ballPositionY >= (screenH + ballHeight)) {setOnTop();}
    }

    public void setOnTop() {
        ballPositionX = (float) (Math.random() * (screenW - ballWidth));
        if ((globalGameLevel == 7) || (globalGameLevel == 8)) ballPositionY -= 10 * ballSpacing;
        else ballPositionY -= 5 * ballSpacing;
        if(!shipIsDead && (globalGameLevel != 8)) STAT_balls_avoided++;

        if (globalGameLevel == 8) {
            if (this.collected && Game.shipCollectedAllBalls) Game.shipCollectedAllBalls = true;
            else Game.shipCollectedAllBalls = false;
            this.resetSprite();
        }

    }

    public void resetSprite () {
        this.ballSprite = new Sprite(assetManager.get("images/balls/" + color + ".png", Texture.class));
        this.collected = false;
    }

    public Sprite getSprite() {return ballSprite;}
    public void setSprite(Sprite ballSprite) {this.ballSprite = ballSprite;}

    public float getX() {return ballPositionX;}
    public void setX(float ballX) {this.ballPositionX = ballX;}

    public float getY() {return ballPositionY;}
    public void setY(float ballY) {this.ballPositionY = ballY;}

    public Circle getShape() {return ballShape;}

    public float getWidth() {return ballWidth;}
    public float getHeight() {return ballHeight;}

    public float getFallingSpeed() {return ballFallingSpeed;}

    public boolean isCollected() {return collected;}
    public void setCollected(boolean collected) {this.collected = collected;}
}