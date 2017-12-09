package com.sulient.pixelvoyager.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sulient.pixelvoyager.Main;

public class Medal {

private Main main;

//Helping Variables
private float screenH = Gdx.graphics.getHeight();
private float screenW = Gdx.graphics.getWidth();
private float margin = screenW / 40;

private Sprite medalSprite;
private float medalPositionX, medalPositionY;
private float medalWidth, medalHeight;

public Medal(String division, Main main) {


    this.medalWidth = screenW / 6;
    this.medalHeight = screenW / 6;
    this.medalPositionX = screenW - medalWidth - margin/2;
    this.medalPositionY = margin/2;

    this.medalSprite = new Sprite(main.assetManager.get("images/medals/" + division + ".png", Texture.class));
    this.medalSprite.flip(true, true);
}

    public Sprite getSprite() {
        return medalSprite;
    }

    public void setSprite(Sprite medal) {
        this.medalSprite = medal;
        this.medalSprite.flip(true, true);
    }

    public float getX() {
        return medalPositionX;
    }
    public void setX(float medalPositionX) {
        this.medalPositionX = medalPositionX;
    }

    public float getY() {
        return medalPositionY;
    }
    public void setY(float medalPositionY) {
        this.medalPositionY = medalPositionY;
    }

    public float getWidth() {
        return medalWidth;
    }
    public void setWidth(float medalWidth) {
        this.medalWidth = medalWidth;
    }

    public float getHeight() {
        return medalHeight;
    }
    public void setHeight(float medalHeight) {
        this.medalHeight = medalHeight;
    }
}
