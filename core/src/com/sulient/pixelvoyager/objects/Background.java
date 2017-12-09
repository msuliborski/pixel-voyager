package com.sulient.pixelvoyager.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sulient.pixelvoyager.Main;

public class Background {

    private Main main;

    //Helping Variables
    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private Sprite backgroundSprite;
    private float backgroundPositionX, backgroundPositionY;
    private float backgroundWidth, backgroundHeight;
    private float backgroundScrollingSpeed;

    public Background(float x, float y, float width, float height, Main main) {

        this.main = main;

        this.backgroundSprite = new Sprite(main.assetManager.get("images/bg.png", Texture.class));

        this.backgroundPositionX = x;
        this.backgroundPositionY = y;

        this.backgroundWidth = width;
        this.backgroundHeight = height;

    }

    public void setOnTop() {
        backgroundPositionY -= 2*backgroundHeight;
    }

    public void scroll (float ballsSpeed, float delta){backgroundPositionY += ballsSpeed * delta / 12;}

    public Sprite getSprite() {return backgroundSprite;}

    public float getX() {return backgroundPositionX;}
    public void setX(float backgroundPositionX) {this.backgroundPositionX = backgroundPositionX;}

    public float getY() {return backgroundPositionY;}
    public void setY(float backgroundPositionY) {this.backgroundPositionY = backgroundPositionY;}

    public float getWidth() {return backgroundWidth;}
    public float getHeight() {return backgroundHeight;}

    public float getScrollingSpeed() {return backgroundScrollingSpeed;}
    public void setScrollingSpeed(float backgroundScrollingSpeed) {this.backgroundScrollingSpeed = backgroundScrollingSpeed;}
}