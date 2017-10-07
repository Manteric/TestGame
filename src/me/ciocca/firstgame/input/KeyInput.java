package me.ciocca.firstgame.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

    private static final int NUM_KEYS = 256;
    private static final boolean[] keys = new boolean[NUM_KEYS];
    private static final boolean[] lastKeys = new boolean[NUM_KEYS];

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public static void update(){
        for(int i=0; i < NUM_KEYS; i++)
            lastKeys[i] = keys[i];
    }
    public static boolean isDown(int keyCode){
        return keys[keyCode];
    }

    public static boolean wasPressed(int keyCode){
        // Ti dice se il tasto "keyCode" è stato premuto
        // Se il tasto è giù e prima NON era stato premuto allora è stato premuto ora = true
        // Se il tasto è giù e prima era stato premuto allora non è stato premuto = false
        // Se il tasto è su = false
        return isDown(keyCode) && !lastKeys[keyCode];
    }

    public static boolean wasReleased(int keyCode){
        return !isDown(keyCode) && lastKeys[keyCode];
    }
}
