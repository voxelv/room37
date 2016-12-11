package com.derelictech.room37;

import com.badlogic.gdx.Game;

public class Room37 extends Game {

	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}
}
