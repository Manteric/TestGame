package me.ciocca.firstgame.world;

import me.ciocca.firstgame.entities.Player;
import me.ciocca.firstgame.rendering.textures.Sprite;
import me.ciocca.firstgame.states.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class World {

    private String name;
    private int width;
    private int height;
    private int[] pixels;

    public World(String name, GameState state){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./resources/levels/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.name = name;
        this.width = image.getWidth();
        this.height = image.getHeight();
        pixels = image.getRGB(0,0,width, height, null, 0, width);
        render(image, state);

    }

    public void render(BufferedImage image, GameState state){
        this.width = image.getWidth();
        this.height = image.getHeight();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int id = pixels[x + y * width];
                if(id == 0xFF0000FF)
                    new Player(new Sprite("player"), x*32, y*32, state);
                else if(Tile.getFromID(id) != null)
                    state.addTile(new Tile(id, x, y));
            }
        }
    }
}
