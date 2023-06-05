package com.game.engine;

import com.game.engine.Input.Input;
import com.game.engine.collision.CollisionDetector;
import com.game.engine.components.GameObjectHandler;
import com.game.engine.physics.Rigidbody;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.Renderer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.ListIterator;

public class Scene extends JPanel {

    @Setter
    @Getter
    ArrayList<GameObject> gameObjects = new ArrayList<>();

    @Getter
    @Setter
    private boolean debug = true;

    @Getter
    @Setter
    CollisionDetector detector = new CollisionDetector();

    @Getter
    @Setter
    GameObjectHandler gameObjectHandler = new GameObjectHandler();

    @Getter @Setter private boolean isEditing = true;
    @Getter @Setter private GameObject selectedGameObject = null;

    private float time = 0;
    private int lastSec = 0;
    private int lastMili = 0;

    public void start(){
        for(GameObject g : gameObjects){
            g.start();
        }
    }


    public void add(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public void update(){

        time += GameEngine.deltaTime;
        if ((int) time > lastSec) {
            lastSec = (int) (time);
            for (GameObject g : gameObjects) {
                g.updateSecond();
            }
        }
        if ( time * 1000 > lastMili) {
            lastMili = (int) (time);
            for (GameObject g : gameObjects) {
                g.updateMillisecond();
            }
        }

        gameObjects = gameObjectHandler.update(gameObjects);

        boolean entered = false;
        for(GameObject gameObject : gameObjects){
            if(!entered)
                entered = checkMouseOverObject(gameObject);

            gameObject.update();
        }
        //if no object had mouse over set over to null
        if(!entered) mouseOverGameObject = null;

        Toolkit.getDefaultToolkit().sync();
        validate();
        repaint();

        //detector.checkCollision(gameObjects);
    }
    long elapsedTime = 0;
    private void drawDebugStats(Graphics2D g){
        g.drawString("Over: "+ mouseOverGameObject,100,70);
        g.drawString("Fps: "+GameEngine.fps,100,80);
        g.drawString("Delta time: "+GameEngine.deltaTime+"ms",100,90);
        g.drawString("Render time: "+String.valueOf(elapsedTime)+"ms",100,100);
        g.drawString("Window height: "+GameEngine.game.getHeight(),100,110);
        g.drawString("Window height: "+prevScale,100,120);
        g.drawString("Mouse pos: "+Input.getMousePosition(),100,130);
        try{
            g.drawString(gameObjects.get(0).transform.toString(), 100, 140);
            g.drawString(gameObjects.get(0).getComponent(Rigidbody.class).toString(), 100, 155);
            g.drawString(gameObjects.get(0).getChild(0).transform.toString(), 100, 170);
        }catch (Exception e){}
    }

    Vector2 prevScale = new Vector2();
    AffineTransform transform = new AffineTransform();

    float scaleFactor = 0.001f;
    @Getter @Setter private GameObject mouseOverGameObject = null;
    public boolean checkMouseOverObject(GameObject gameObject){
        Renderer renderer = gameObject.getComponent(com.game.engine.rendering.Renderer.class);
        if(renderer != null){
            Point p = new Point((int) Input.getMousePosition().getX(), (int) Input.getMousePosition().getY());
            if(renderer.getShape().contains(p)){

                if(mouseOverGameObject != null) mouseOverGameObject.onMouseLeft( );
                gameObject.onMouseOver();

                mouseOverGameObject =gameObject;

                return true;
            }else if(gameObject.isMouseInside()){
                gameObject.onMouseLeft();
            }
        }
        return false;
    }

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

        //graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        drawDebugStats(graphics2D);


        graphics2D.translate(GameEngine.game.getWidth()/2, GameEngine.game.getHeight()/2);

        if(prevScale.getX() != GameEngine.game.getHeight() &&
                prevScale.getY() != GameEngine.game.getHeight()){

            graphics2D.scale(GameEngine.game.getHeight()*scaleFactor, GameEngine.game.getHeight()*scaleFactor);
        }

        int x = (int) ((int) (Input.getMousePositionOnCanvas().getX() / (GameEngine.game.getHeight()*scaleFactor) + graphics2D.getClip().getBounds().getX()));
        int y = (int) ((int) (Input.getMousePositionOnCanvas().getY() / (GameEngine.game.getHeight()*scaleFactor) + graphics2D.getClip().getBounds().getY() ));

        Input.setMousePosition(new Vector2(x,y));


        //render gameobjects if they are inside the view
        boolean entered = false;
        ListIterator li = gameObjects.listIterator(gameObjects.size());

        while (li.hasPrevious()){
        GameObject gameObject = (GameObject) li.previous();
        //for(GameObject gameObject : gameObjects){
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
