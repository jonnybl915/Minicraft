package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion down;
	TextureRegion up;
	TextureRegion right;
	TextureRegion left;
	TextureRegion runRight;


	float x, y, xv, yv;

	static final float MAX_VELOCITY = 100;
	static final float DECELERATOR = 0.8f;
	static final int WIDTH = 18;
	static final int HEIGHT = 22;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
		down = grid[6][0];
		up = grid[6][1];
		right = grid[6][3];
		left = new TextureRegion(right);
		left.flip(true, false);
		runRight = grid[7][3];

	}

	@Override
	public void render () {
		move();
		float movement = xv;
		Gdx.gl.glClearColor(.2f, 1, .7f, .5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(xv > 0){
			batch.draw(right, x, y, WIDTH*2, HEIGHT*2);
		}
		else if (xv < 0){
			batch.draw(left, x, y, WIDTH*2, HEIGHT*2);
		}
		else if(yv < 0){
			batch.draw(down, x, y, WIDTH*2, HEIGHT*2);
		}
		else if( yv > 0){
			batch.draw(up, x, y, WIDTH*2, HEIGHT*2);
		}
		else{
			batch.draw(right, x, y, WIDTH*2, HEIGHT*2);
		}
		batch.end();
	}
	public void move(){
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			yv = MAX_VELOCITY;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			yv = -MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			xv = MAX_VELOCITY;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			xv = -MAX_VELOCITY;
		}

		float delta = Gdx.graphics.getDeltaTime(); //amount of seconds which have passed since the last frame
		y+= yv * delta;
		x+= xv * delta;
		yv = decelerate(yv);
		xv = decelerate(xv);
	}
	public float decelerate(float velocity){
		velocity *= DECELERATOR;
		if (Math.abs(velocity) <1) {
			velocity = 0;
		}
		return velocity;
	}
}
