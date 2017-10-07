package me.ciocca.firstgame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FirstGame {
    public static void main(String[] args){
        Game game = new Game();
        JFrame frame = new JFrame(Game.TITLE);
        frame.add(game);
        frame.setSize(Game.WIDTH, Game.HEIGHT);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("Uscendo dal gioco");
                game.stop();
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }
}
