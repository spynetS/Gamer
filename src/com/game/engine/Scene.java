package com.game.engine;

import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.components.GameObjectHandler;
import com.game.engine.components.ShapeRender;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Scene extends JPanel {

    ArrayList<GameObject> gameObjects = new ArrayList<>();
    public boolean debug = true;

    @Getter
    @Setter
    GameObjectHandler gameObjectHandler = new GameObjectHandler();

    public void start(){
        for(GameObject g : gameObjects){
            g.start();
        }
    }


    public void add(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public void update(){

        gameObjects = gameObjectHandler.update(gameObjects);

        for(GameObject gameObject : gameObjects)
            gameObject.update();

        Toolkit.getDefaultToolkit().sync();
        validate();
        repaint();
    }
    long elapsedTime = 0;
    private void drawDebugStats(Graphics2D g){
        g.drawString("Render time: "+String.valueOf(elapsedTime)+"ms",100,100);
        g.drawString("Window height: "+GameEngine.game.getHeight(),100,110);
        g.drawString("Window height: "+prevScale,100,120);
        g.drawString("Mouse pos: "+Input.getMousePosition(),100,130);
        //g.drawString("Mouse pos: "+gameObjects.get(0).gameObjects.get(0).transform,100,140);
    }

    Vector2 prevScale = new Vector2();
    AffineTransform transform = new AffineTransform();

    float scaleFactor = 0.005f;


    @Override
    protected void paintComponent(Graphics g) {
        /*
        if(Input.isKeyDown(Keys.DOWNARROW)) {
            scaleFactor -= 0.0001f;
        }
        if(Input.isKeyDown(Keys.UPARROW)) {
            scaleFactor += 0.0001f;
        }
        */

        long startTime = System.currentTimeMillis();

        super.paintComponent(g);

        // Your custom painting code goes here
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        drawDebugStats(graphics2D);


        graphics2D.translate(GameEngine.game.getWidth()/2, GameEngine.game.getHeight()/2);

        if(prevScale.getX() != GameEngine.game.getHeight() &&
                prevScale.getY() != GameEngine.game.getHeight()){

            graphics2D.scale(GameEngine.game.getHeight()*scaleFactor, GameEngine.game.getHeight()*scaleFactor);
        }

        int x = (int) ((int) (Input.getMousePositionOnCanvas().getX() /*camera scale was here*/) + graphics2D.getClip().getBounds().getX());
        int y = (int) ((int) (Input.getMousePositionOnCanvas().getY() /*camera scale was here*/) + graphics2D.getClip().getBounds().getY() );
        Input.setMousePosition(new Vector2(x,y));


        //render gameobjects if they are inside the view
        for(GameObject gameObject : gameObjects) {

            if(graphics2D.getClip().intersects(new Rectangle(
                    (int) gameObject.transform.getPosition().getX(),
                    (int) gameObject.transform.getPosition().getY(),
                    (int) gameObject.transform.getScale().getX(),
                    (int) gameObject.transform.getScale().getY()))){

                gameObject.render(graphics2D);
            }
        }

        long endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
    }
}
