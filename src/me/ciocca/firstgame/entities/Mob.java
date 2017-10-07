package me.ciocca.firstgame.entities;

import me.ciocca.firstgame.rendering.textures.Sprite;
import me.ciocca.firstgame.states.GameState;
import me.ciocca.firstgame.world.Tile;

public abstract class Mob extends Entity {

    protected double dx, dy;
    protected double maxDY;
    protected double gravity;
    protected boolean falling = true;
    protected boolean canjump;


    public Mob(Sprite sprite, double x, double y, GameState state) {

        super(sprite, x, y, state);
        gravity = 0.5;
        maxDY = 7;
    }

    @Override
    public void tick() {
        move();
        fall();
    }

    public void move(){
        if(!hasHorizontalCollision()) x += dx;
        if(!hasVerticalCollision()) y += dy;
    }

    protected boolean hasVerticalCollision(){
        for(int i = 0; i < state.getTiles().size(); i++){
            Tile t = state.getTiles().get(i);
            if(getBottom().intersects(t.getTop()) && dy > 0){
                canjump = true;
                falling = false;
                dy = 0;
                return true;
            } else falling = true;
            if(getBounds().intersects(t.getBottom()) && dy < 0){
                dy = 0;
                return true;
            }
        }
        return false;
    }

    protected boolean hasHorizontalCollision(){
        for(int i = 0; i < state.getTiles().size(); i++){
            Tile t = state.getTiles().get(i);
            if(getBounds().intersects(t.getRight()) && dx < 0){
                dx = 0;
                return true;
            }
            if(getBounds().intersects(t.getLeft()) && dx > 0){
                dx = 0;
                return true;
            }
        }

        return false;

    }

    protected void fall(){
        if(falling){
            dy += gravity;
            if(dy> maxDY) dy = maxDY;
        }
    }

    protected void jump(double jumpHeight){
        if(canjump){
            dy -= jumpHeight;
            canjump = false;
        }
    }
}
