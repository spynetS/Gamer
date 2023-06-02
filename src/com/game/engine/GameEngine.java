package com.game.engine;

import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngine extends JFrame {
    public static int DELAY = 2;
    private static Scene selectedScene;
    private static SceneHolder sceneHolder = new SceneHolder();

    public static Scene getSelectedScene() {
        return selectedScene;
    }

    public static void setSelectedScene(Scene selectedScene) {
        GameEngine.selectedScene = selectedScene;
        selectedScene.start();
        sceneHolder.add(selectedScene);
    }
    public static GameEngine game;

    private void update(){
        getSelectedScene().update();
    }


    public void start(){
        System.setProperty("sun.java2d.opengl", "true");
        game = this;
        game.add(sceneHolder);


        game.setSize(1920/2,1080/2);
        game.setVisible(true);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);

        javax.swing.Timer timer = new javax.swing.Timer(DELAY, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                update();
            }

        });
        timer.setCoalesce(true);
        timer.setRepeats(true);
        timer.start();
    }

}
