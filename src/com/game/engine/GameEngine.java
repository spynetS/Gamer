package com.game.engine;

import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngine extends JFrame {
    public static int DELAY = 16;
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

    public static double deltaTime = 0;
    private static double prevTime = ((double)System.currentTimeMillis());
    private static double time = System.currentTimeMillis();
    /**
     * this is the amount of frames drawn every second
     */
    public static float fps = 5;
    private static float counter = 5;
    /**
     * This caps the amount of frames drawn in a second (0 = uncapped)
     */
    public static float fpsCap = 60;
    private void update(){


        double now = ((double) System.currentTimeMillis());
        //Increases counter every tick but when a 1/10 of a second we reset the counter
        //and sets the fps to the counter
        // To cap the fps we just increase the delay if our fps is too high
        // and decrease it when it is too low
        if (now - time >= 100) {
            fps = counter * 10;
            if (fpsCap > 0 && fps > fpsCap) DELAY++;
            if (fpsCap > 0 && fps < fpsCap && DELAY > 5) DELAY--;

            counter = 0;
            time = now;
        }
        counter++;

        // delta time is the time from previous frame (tick speed)
        deltaTime = (now - prevTime)/1000;
        prevTime = now;


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
