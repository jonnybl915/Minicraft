package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by jonathandavidblack on 6/2/16.
 */
public class Zombie extends ApplicationAdapter {
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
}
