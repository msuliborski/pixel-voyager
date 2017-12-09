package com.sulient.pixelvoyager.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sulient.pixelvoyager.Main;

public class Level {

    private final Main main;

    //Helping Variables
    private float screenH = Gdx.graphics.getHeight();
    private float screenW = Gdx.graphics.getWidth();
    private float margin = screenW / 40;

    private Image levelImage;

    public Level(String pathToTexture, Skin skin, Main main){
        this.main = main;

        skin.getFont("default-font").getData().setScale((float) 0.24* screenW/540, (float) 0.24*screenW/540);

        levelImage = new Image(new TextureRegionDrawable(new TextureRegion(main.assetManager.get(pathToTexture, Texture.class))));

    }

    public Image getImage() {
        return levelImage;
    }
}
