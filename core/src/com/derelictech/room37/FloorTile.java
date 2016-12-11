package com.derelictech.room37;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FloorTile extends Image{
    public boolean l1_is_occupied = false;
    public boolean l2_is_occupied = false;
    public Coordinate coord;
    private boolean highlight;

    public FloorTile(Texture texture, int x, int y) {
        super(texture);
        this.coord = new Coordinate(x, y);

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                event.setRelatedActor(FloorTile.this);
                return false;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                highlight = true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                highlight = false;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(highlight) {
            Assets.skn().getDrawable("highlight").draw(getStage().getBatch(), getX(), getY(), getWidth(), getHeight());
        }
    }
}

