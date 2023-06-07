package com.game.engine;

import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngine extends JFrame {
    public static int DELAY = 2;
    private static Scene selectedScene;

    @Getter @Setter private static SceneHolder sceneHolder = new SceneHolder();

    @Getter @Setter private static JPanel canvas = new JPanel();

    public static Scene getSelectedScene() {
        return selectedScene;
    }

    public static void setSelectedScene(Scene selectedScene) {
        GameEngine.selectedScene = selectedScene;
        try{
            selectedScene.setSize(game.getWidth(), game.getHeight());
        }catch (Exception e){
            selectedScene.setSize(500,400);
        }
        selectedScene.start();
        sceneHolder.add(selectedScene);
        sceneHolder.setLayer(selectedScene,0);
        sceneHolder.setLayer(canvas, 1);

    }
    public static GameEngine game;

    public static double deltaTime = 0; // time each second
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


        if(getSelectedScene().getSize() != game.getSize()){
            selectedScene.setSize(game.getWidth(), game.getHeight());
        }
        if(canvas.getSize() != game.getSize()){
            // minus otherwise it will not render the scene
            canvas.setSize(game.getWidth()-1, game.getHeight()-1);
        }

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


        sceneHolder.add(canvas);


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
