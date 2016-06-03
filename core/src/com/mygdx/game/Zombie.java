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
    Zombie bob = new Zombie();
    @Override
    public void create () {
        batch = new SpriteBatch();
        Texture tiles = new Texture("tiles.png");
        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);

        down = grid[6][4];
        downFlip = new TextureRegion(down);
        downFlip.flip(true, false);
        up = grid[6][5];
        upFlip = new TextureRegion(up);
        upFlip.flip(true, false);
        right = grid[6][7];
        left = new TextureRegion(right);
        left.flip(true, false);
        standingR = grid[6][6];
        standingL = new TextureRegion(standingR);
        standingL.flip(true, false);

        walkRight = new Animation(0.2f, right, standingR);
        walkLeft = new Animation(0.2f, left, standingL);
        walkUp = new Animation(0.2f, up, upFlip);
        walkDown = new Animation(0.2f, down, downFlip);
        runRight = new Animation(0.2f, huRight, huStanding);
        runLeft = new Animation(0.2f, huLeft, huStandingL);
        runUp = new Animation(0.2f, huUp, huUpFlip);
        runDown = new Animation(0.2f, huDown, huDownFlip);
    }

}
