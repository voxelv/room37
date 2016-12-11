package com.derelictech.room37;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class FloorTile extends Image{
    public int gridX, gridY;
    public FloorTile(Texture texture, int x, int y) {
        super(texture);
        this.gridX = x;
        this.gridY = y;
    }
}
