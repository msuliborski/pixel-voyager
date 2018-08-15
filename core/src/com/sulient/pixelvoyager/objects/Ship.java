package com.sulient.pixelvoyager.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sulient.pixelvoyager.Main;

public class Ship {

    private Main main;

    //Helping Variables
    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private Sprite shipSprite;

    private TextureAtlas        shipBoom, shipIdle;

    private Rectangle shipHitBox1, shipHitBox2;
    private Vector2 shipPosition;
    private float shipWidth, shipHeight;

    public Ship(Main main) {
        this.main = main;

        this.shipSprite = new Sprite(main.assetManager.get("images/ship/ship.png", Texture.class));
        this.shipSprite.flip(true, true);

        this.shipWidth  = screenW/6;
        this.shipHeight = screenW/6;
        if (!((screenW * 3/2 + shipHeight) > screenH))  this.shipPosition = new Vector2(screenW/2 - shipWidth/2, 3*screenW / 2); //@@@@@@@@     Y nie moze byc za duzy. Ogranicz, bo czasem ekran jest za krotki
        else                                            this.shipPosition = new Vector2(screenW/2 - shipWidth/2, screenH - shipHeight);

        this.shipHitBox1 = new Rectangle((float)(shipPosition.x + (0.416)*shipWidth), shipPosition.y, shipWidth/6, shipHeight);
        this.shipHitBox2 = new Rectangle(shipPosition.x, (float)(shipPosition.y + (0.6)*shipWidth), shipWidth, shipWidth/6);

        this.shipBoom = main.assetManager.get("images/ship/shipBoom.atlas", TextureAtlas.class);

        this.shipIdle = main.assetManager.get("images/ship/shipIdle.atlas", TextureAtlas.class);
    }



    public void resetPosition() {
        if (!((screenW * 3/2 + shipHeight) > screenH))  this.shipPosition = new Vector2(screenW/2 - shipWidth/2, 3*screenW / 2); //@@@@@@@@     Y nie moze byc za duzy. Ogranicz, bo czasem ekran jest za krotki
        else                                            this.shipPosition = new Vector2(screenW/2 - shipWidth/2, screenH - shipHeight);
        this.shipHitBox1.setPosition((float)(shipPosition.x + (0.416)*shipWidth), shipPosition.y);
        this.shipHitBox2.setPosition(shipPosition.x, (float)(shipPosition.y + (0.6)*shipWidth));
    }

    public Sprite getSprite() {
        return shipSprite;
    }
    public void setSprite(Sprite shipSprite) {
        this.shipSprite = shipSprite;
        this.shipSprite.flip(true, true);
    }

    public TextureAtlas getShipBoom() {
        return shipBoom;
    }
    public TextureAtlas getShipIdle() {
        return shipIdle;
    }


    public Rectangle getHitBox1() {
        return shipHitBox1;
    }
    public Rectangle getHitBox2() {
        return shipHitBox2;
    }


    public float getX() {
        return shipPosition.x;
    }
    public float getY() {return shipPosition.y;}

    public Vector2 getPosition() {return shipPosition;}
    public void setPosition(float x, float y) {
        this.shipPosition.set(x, y);
        this.shipHitBox1.setPosition((float)(x + (0.416)*shipWidth), y);
        this.shipHitBox2.setPosition(x, (float)(y + (0.6)*shipWidth));
    }
    public void setPosition(Vector2 pos) {
        this.shipPosition = pos;
        this.shipHitBox1.setPosition((float)(pos.x + (0.416)*shipWidth), pos.y);
        this.shipHitBox2.setPosition(pos.x, (float)(pos.y + (0.6)*shipWidth));
    }


    public float getWidth() {
        return shipWidth;
    }
    public void setWidth(float shipWidth) {
        this.shipWidth = shipWidth;
    }

    public float getHeight() {
        return shipHeight;
    }
    public void setHeight(float shipHeight) {
        this.shipHeight = shipHeight;
    }
}