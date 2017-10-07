package me.ciocca.firstgame.states;

import me.ciocca.firstgame.Game;
import me.ciocca.firstgame.entities.Entity;
import me.ciocca.firstgame.entities.Player;
import me.ciocca.firstgame.rendering.textures.Sprite;
import me.ciocca.firstgame.rendering.textures.SpriteSheet;
import me.ciocca.firstgame.rendering.textures.Texture;
import me.ciocca.firstgame.world.Tile;
import me.ciocca.firstgame.world.World;

import java.awt.*;
import java.util.ArrayList;

public class GameState implements State {

    private ArrayList<Entity> entities;
    private ArrayList<Tile> tiles;
    private World world;


    @Override
    public void init() {
        entities = new ArrayList<Entity>();
        tiles = new ArrayList<Tile>();
        world = new World("level1", this);
    }

    @Override
    public void enter() {

    }

    @Override
    public void tick(StateManager stateManager) {
        for(Entity e: entities)
            e.tick();
    }

    @Override
    public void render(Graphics2D g) {
        for(Entity e: entities)
            e.render(g);
        for(Tile t: tiles)
            t.render(g);
    }

    @Override
    public void exit() {
        entities.clear();
    }

    @Override
    public String getName() {
        return "level1";
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addTile(Tile tile){
        tiles.add(tile);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

}
