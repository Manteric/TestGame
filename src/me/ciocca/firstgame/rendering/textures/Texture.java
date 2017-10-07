package me.ciocca.firstgame.rendering.textures;

import me.ciocca.firstgame.utils.managers.TextureManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Texture {
    private final static Map<String, TextureManager> texMap = new HashMap<String, TextureManager>();
    private TextureManager manager;
    private String fileName;

    public Texture(String fileName){
        this.fileName = fileName;
        TextureManager oldTexture = texMap.get(fileName);
        if(oldTexture != null){
            manager = oldTexture;
            manager.addReference();
        }
        else{
            try {
                System.out.println("Carico la texture " + fileName);
                manager = new TextureManager(ImageIO.read(new File("./resources/textures/" + fileName + ".png")));
                texMap.put(fileName, manager);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if(manager.removeReference() && !fileName.isEmpty()){
            texMap.remove(fileName);
            System.out.println("Rimuovo la teture " + fileName + "dalla memoria");
        }
        super.finalize();
    }

    public void render(Graphics g, double x, double y){
        g.drawImage(manager.getImage(), (int) x, (int) y, null);
    }

    public BufferedImage getImage(){
        return manager.getImage();
    }
}
