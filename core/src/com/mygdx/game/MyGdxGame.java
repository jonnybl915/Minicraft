package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;

	TextureRegion down;
	TextureRegion downFlip;
	TextureRegion up;
	TextureRegion upFlip;
	TextureRegion right;
	TextureRegion left;
	TextureRegion standingR;
	TextureRegion standingL;
	TextureRegion huStanding;
	TextureRegion huStandingL;
	TextureRegion huDown;
	TextureRegion huDownFlip;
	TextureRegion huRight;
	TextureRegion huLeft;
	TextureRegion huUp;
	TextureRegion huUpFlip;


	Animation walkRight;
	Animation walkLeft;
	Animation walkUp;
	Animation walkDown;
	Animation runDown;
	Animation runRight;
	Animation runLeft;
	Animation runUp;


	float time;
	int movementMem;
	float x, y, xv, yv;
	int runMem;

	static final float MAX_VELOCITY = 100;
	static final float DECELERATOR = 0.75f;
	static final int WIDTH = 18;
	static final int HEIGHT = 22;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);


		down = grid[6][0];
		downFlip = new TextureRegion(down);
		downFlip.flip(true, false);
		up = grid[6][1];
		upFlip = new TextureRegion(up);
		upFlip.flip(true, false);
		right = grid[6][3];
		left = new TextureRegion(right);
		left.flip(true, false);
		standingR = grid[6][2];
		standingL = new TextureRegion(standingR);
		standingL.flip(true, false);
		huStanding = grid [7][2];
		huStandingL = new TextureRegion(huStanding);
		huStandingL.flip(true, false);
		huRight = grid [7][3];
		huUp = grid[7][1];
		huUpFlip = new TextureRegion(huUp);
		huUpFlip.flip(true, false);
		huDown = grid[7][0];
		huDownFlip = new TextureRegion(huDown);
		huDownFlip.flip(true, false);
		Zombie z = new Zombie();

		walkRight = new Animation(0.2f, grid[6][3], grid[6][2]);
		walkLeft = new Animation(0.2f, left, standingL);
		walkUp = new Animation(0.2f, up, upFlip);
		walkDown = new Animation(0.2f, down, downFlip);
		runRight = new Animation(0.2f, huRight, huStanding);
		runLeft = new Animation(0.2f, huLeft, huStandingL);
		runUp = new Animation(0.2f, huUp, huUpFlip);
		runDown = new Animation(0.2f, huDown, huDownFlip);
	}

	@Override
	public void render () {
		move();
		time += Gdx.graphics.getDeltaTime();

		if(xv>0){
			right = walkRight.getKeyFrame(time, true);
			if(xv > MAX_VELOCITY){
				right = runRight.getKeyFrame(time, true);
			}
		}
		if(xv<0){
			left = (walkLeft.getKeyFrame(time, true));
			if(xv < -MAX_VELOCITY){							//**** needs some work.
				left = runLeft.getKeyFrame(time, false);	//******
			}
		}
		if(yv>0){
			up = (walkUp.getKeyFrame(time, true));
			if(yv > MAX_VELOCITY){
				up = runUp.getKeyFrame(time, true);
			}
		}
		if(yv<0){
			down = (walkDown.getKeyFrame(time, true));
			if(yv < -MAX_VELOCITY){
				down = runDown.getKeyFrame(time, true);
			}
		}


		Gdx.gl.glClearColor(.2f, 1, .7f, .5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		TextureRegion tempImg;

		if(movementMem ==3){

			tempImg = right;
		}
		else if (movementMem ==4){
			tempImg = left;
		}
		else if(movementMem == 2){
			tempImg = down;
		}
		else if(movementMem == 1){
			tempImg = up;
		}
		else{
			tempImg = left;
		}

		batch.draw(tempImg, x, y, WIDTH*2, HEIGHT*2);
		batch.end();
	}

	public void move(){

		float velocityBoost = MAX_VELOCITY + MAX_VELOCITY;

		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			yv = MAX_VELOCITY;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				yv = velocityBoost;
			}
			movementMem = 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			yv = -MAX_VELOCITY;
			if((Gdx.input.isKeyPressed(Input.Keys.SPACE))){
				yv = -velocityBoost;
			}
			movementMem = 2;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			xv = MAX_VELOCITY;
			if((Gdx.input.isKeyPressed(Input.Keys.SPACE))){
				xv = velocityBoost;
			}
			movementMem = 3;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			xv = -MAX_VELOCITY;
			if((Gdx.input.isKeyPressed(Input.Keys.SPACE))){
				xv = -velocityBoost;
			}
			movementMem = 4;
		}



		if(x>Gdx.graphics.getWidth()){
			x = -5;
		}
		if(x<-5){
			x = Gdx.graphics.getWidth();
		}
		if(y<-5){
			y = Gdx.graphics.getHeight();
		}
		if (y>Gdx.graphics.getHeight()){
			y=-5;
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
