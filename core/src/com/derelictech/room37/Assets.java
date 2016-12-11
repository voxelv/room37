package com.derelictech.room37;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;
import java.util.List;

public class Assets {
    private static Skin skin;
    private static List<Furniture> furnitures;
    private static String[] furniture_names = {
            "bed_frame", "bed_blankets", "bed_pillow", "desk", "desk_lamp", "floor_lamp"
    };

    public static Skin skn() {
        return load_assets();
    }

    private static Skin load_assets() {
        if(skin == null) {
            Skin s = new Skin();

            s.add("walls", new Texture("walls.png"));
            s.add("door", new Texture("door.png"));
            s.add("window", new Texture("window.png"));
            s.add("painting", new Texture("painting.png"));
            s.add("coin", new Texture("coin.png"));
            s.add("highlight", new Texture("highlight.png"));

            for (String name: furniture_names) {
                s.add(name, new Texture(name + ".png"));
            }

            s.add("btn_bg", new Texture("btn_bg.png"));
            s.add("btn_bg_dn", new Texture("btn_bg_dn.png"));
            s.add("btn_bg_red", new Texture("btn_bg_red.png"));
            s.add("btn_bg_red_dn", new Texture("btn_bg_red_dn.png"));
            BitmapFont font = new BitmapFont(Gdx.files.internal("r37.fnt"));
            font.getData().scale(0.01f);
            s.add("font", font);
            TextButton.TextButtonStyle tbs_green = new TextButton.TextButtonStyle(
                    s.getDrawable("btn_bg"),
                    s.getDrawable("btn_bg_dn"),
                    s.getDrawable("btn_bg"),
                    s.get("font", BitmapFont.class)
            );
            TextButton.TextButtonStyle tbs_red = new TextButton.TextButtonStyle(
                    s.getDrawable("btn_bg_red"),
                    s.getDrawable("btn_bg_red_dn"),
                    s.getDrawable("btn_bg_red"),
                    s.get("font", BitmapFont.class)
            );
            Label.LabelStyle lbl_style = new Label.LabelStyle(
                    s.get("font", BitmapFont.class),
                    Color.WHITE
            );
            s.add("green", tbs_green);
            s.add("red", tbs_red);
            s.add("default_lbl", lbl_style);

            skin = s;
        }
        return skin;
    }

    public static List<Furniture> furniture_list() {
        if(furnitures == null) {
            furnitures = new ArrayList<Furniture>(furniture_names.length);
            for (String name : furniture_names) {
                furnitures.add(new Furniture(skn().getDrawable(name)));
            }
        }
        return furnitures;
    }

    public static void dispose() {
        skin.dispose();
    }
}


