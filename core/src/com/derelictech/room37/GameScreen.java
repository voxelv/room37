package com.derelictech.room37;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Gdx.input;

public class GameScreen extends ScreenAdapter {
    private Game game;
    boolean first = true;

    private Stage stage;
    private Table layout, game_area, ui_area;
    private List<FloorTile> floor_tiles;
    private Image walls;
    private Image door, window, painting;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Setup
        stage = new Stage(new FitViewport(27, 20));
        input.setInputProcessor(stage);

        stage.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch(keycode) {
                    case Input.Keys.ESCAPE:
                        Gdx.app.exit();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        layout = new Table();
        layout.setFillParent(true);
        stage.addActor(layout);

        walls = new Image(new Texture("walls.png"));
        walls.setPosition(0, 0);
        walls.setSize(20, 20);
        stage.addActor(walls);

        door = new Image(new Texture("door.png"));
        door.setPosition(8.5f, 0);
        door.setSize(2.7f, 2.6f);
        stage.addActor(door);

        window = new Image(new Texture("window.png"));
        window.setPosition(7.5f, 18);
        window.setSize(3, 1.5f);
        stage.addActor(window);

        painting = new Image(new Texture("painting.png"));
        painting.setPosition(0.5f, 8.5f);
        painting.setSize(1.5f, 3.5f);
        stage.addActor(painting);


        game_area = new Table();
        game_area.setSize(15, 15);
        game_area.debugAll();
        game_area.setPosition(2.5f, 2.5f);
        stage.addActor(game_area);

        floor_tiles = new ArrayList<FloorTile>(CONST.NUM_TILES * CONST.NUM_TILES);
        for(int x = 0; x < CONST.NUM_TILES; x++) {
            for(int y = CONST.NUM_TILES; y > 0; y--) {
                FloorTile the_tile = new FloorTile(new Texture("floor_tile.png"), x, y);
//                the_tile.setSize(5f/(float)CONST.NUM_TILES, 5f/(float)CONST.NUM_TILES);
                floor_tiles.add(the_tile);
                game_area.add(the_tile).left().bottom();
            }
            game_area.row();
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.16f, 0.16f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(first){
            System.out.println("RENDERING");
            first = false;
        }

        // Loop
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
