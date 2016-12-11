package com.derelictech.room37;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.badlogic.gdx.Gdx.input;

public class GameScreen extends ScreenAdapter {
    private Game game;

    private Stage game_stage;
    private Stage ui_stage;
    private Table game_area, ui_area;
    private List<FloorTile> floor_tiles;
    private Image walls;
    private Image door, window, painting;

    private Skin skin;
    private Furniture store_furniture;
    private TextButton buy_btn, pass_btn;
    private Label coins;

    public Random rand;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        rand = new Random(System.currentTimeMillis());
        skin = Assets.skn();
        init_game_stage();
        init_ui_stage();
        input.setInputProcessor(new InputMultiplexer(ui_stage, game_stage));
    }

    private void init_game_stage() {

        // Setup
        game_stage = new Stage(new FitViewport(30, 20));

        game_stage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Actor a = event.getRelatedActor();
                if(a instanceof FloorTile) {
                    System.out.println("Clicked Tile: x: " + ((FloorTile) a).coord.x + " y: " + ((FloorTile) a).coord.y);
                }
                return true;
            }

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

        walls = new Image(skin.getDrawable("walls"));
        walls.setPosition(0, 0);
        walls.setSize(20, 20);
        game_stage.addActor(walls);

        door = new Image(skin.getDrawable("door"));
        door.setPosition(8.5f, 0);
        door.setSize(2.7f, 2.6f);
        game_stage.addActor(door);

        window = new Image(skin.getDrawable("window"));
        window.setPosition(7.5f, 18);
        window.setSize(3, 1.5f);
        game_stage.addActor(window);

        painting = new Image(skin.getDrawable("painting"));
        painting.setPosition(0.5f, 8.5f);
        painting.setSize(1.5f, 3.5f);
        game_stage.addActor(painting);

        game_area = new Table();
        game_area.setSize(15, 15);
        game_area.setPosition(2.5f, 2.5f);
        game_stage.addActor(game_area);

        floor_tiles = new ArrayList<FloorTile>(CONST.NUM_TILES * CONST.NUM_TILES);
        for(int x = 0; x < CONST.NUM_TILES; x++) {
            for(int y = CONST.NUM_TILES; y > 0; y--) {
                FloorTile the_tile = new FloorTile(new Texture("floor_tile.png"), x, y);
                floor_tiles.add(the_tile);
                game_area.add(the_tile).prefSize(15.0f/(float)CONST.NUM_TILES);
            }
            game_area.row();
        }
    }

    private void init_ui_stage() {
        ui_stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Table layout = new Table();
        ui_stage.addActor(layout);
        layout.setFillParent(true);

        ui_area = new Table();
        Table blank = new Table();
        layout.add(blank).expand().left().width(380);
        layout.add(ui_area).right().top().expandY().width(280);

        Table store_box = new Table();
        store_furniture = Assets.furniture_list().get(rand.nextInt(Assets.furniture_list().size()));
        store_box.add(store_furniture).expand();
        ui_area.add(store_box).expand().fillX().size(200, 200);
        ui_area.row();

        Table btn_table = new Table();
        buy_btn = new TextButton("BUY!", skin, "green");
        pass_btn = new TextButton(" Pass... ", skin, "red");
        btn_table.add(buy_btn).expandX().fillX();
        btn_table.add(pass_btn).fillX();

        ui_area.add(btn_table).top().expandX().fillX();
        ui_area.row();

        Table coins_tbl = new Table();
        Image coin_img = new Image(skin.getDrawable("coin"));
        coins_tbl.add(coin_img).size(20, 20).left();
        coins = new Label("10000000 COINS!", skin, "default_lbl");
        coins.setFontScale(0.7f);
        coins.setColor(Color.YELLOW);
        coins_tbl.add(coins).left().padLeft(5);

        ui_area.add(coins_tbl).left().padTop(5);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.16f, 0.16f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Loop
        game_stage.act();
        game_stage.draw();
        ui_stage.act();
        ui_stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game_stage.getViewport().update(width, height);
        ui_stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        game_stage.dispose();
        ui_stage.dispose();
        Assets.dispose();
    }
}
