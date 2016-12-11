package com.derelictech.room37;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Furniture extends Image {
    public Coordinate coord;
    private List<Coordinate> occupied_tiles;
    private String level;
    private int cost;
    private int income;

    public Furniture(Drawable texture) {
        super(texture);

        occupied_tiles = new ArrayList<Coordinate>();
    }

    public boolean is_in(Coordinate c) {
        return occupied_tiles.contains(c);
    }

    public boolean is_in(int x, int y) {

        return is_in(new Coordinate(x, y));
    }

    public void occupy(Coordinate c) {
        occupied_tiles.add(c);
    }

    public void clear_occupied() {
        occupied_tiles.clear();
    }

    public void set_stats(String level, int cost, int income) {
        this.level = level;
        this.cost = cost;
        this.income = income;
    }
}
