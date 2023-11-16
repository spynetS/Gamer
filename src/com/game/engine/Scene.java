package com.game.engine;

import com.game.engine.Input.Input;
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

    @Setter @Getter ArrayList<GameObject> gameObjects = new ArrayList<>();
    @Getter @Setter private boolean debug = true;
    @Getter @Setter GameObjectHandler gameObjectHandler = new GameObjectHandler();
    @Getter @Setter private boolean isEditing = false;
    @Getter @Setter private GameObject selectedGameObject = null;
    @Getter @Setter private GameObject mouseOverGameObject = null;
    @Getter @Setter private Vector2 cameraOffset = new Vector2();
    @Getter @Setter float scaleFactor = 0.001f;
    @Getter @Setter private boolean started = false;
    Vector2 prevScale = new Vector2();
    AffineTransform transform = new AffineTransform();
    private float time = 0;
    private int lastSec = 0;
    private int lastMili = 0;
    long elapsedTime = 0;
    public Scene() {
        setBackground(new Color(30,30,30));
    }

    public void start(){
        for(GameObject g : gameObjects){
            g.start();
        }
        started = true;
    }

    public GameObject add(GameObject gameObject){
        gameObjects.add(gameObject);
        return gameObject;
    }

    public void update(){

        gameObjects = gameObjectHandler.update(gameObjects);

        //time += GameEngine.deltaTime;
        //if ((int) time > lastSec) {
        //    lastSec = (int) (time);
        //    for (GameObject g : gameObjects) {
        //        g.updateSecond();
        //    }
        //}
        //if ( time * 1000 > lastMili) {
        //    lastMili = (int) (time);
        //    for (GameObject g : gameObjects) {
        //        g.updateMillisecond();
        //    }
        //}


        boolean entered = false;
        for(GameObject gameObject : gameObjects){
            if(!entered)
            {
                entered = checkMouseOverObject(gameObject);
                if(!entered){
                    for(GameObject child : gameObject.gameObjects){
                        entered = checkMouseOverObject(child);
                        if(child.isMouseInside() && Input.isMousePressed()){
                            setSelectedGameObject(child);
                        }
                    }
                }
            }

            gameObject.update();
            if(gameObject.isMouseInside() && Input.isMousePressed()){
                setSelectedGameObject(gameObject);
            }
        }
        //if no object had mouse over set over to null
        if(!entered) mouseOverGameObject = null;



        Toolkit.getDefaultToolkit().sync();
        validate();
        repaint();

        scaleFactor += Input.getScrollValue()*scaleFactor/10;

        Input.setScrollValue(0);
        Input.setMousePressed(1000);

    }
    private void drawDebugStats(Graphics2D g){

        GameObject read = mouseOverGameObject;

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Over: "+ mouseOverGameObject,100,70);
        g.drawString("Fps: "+GameEngine.fps,100,80);
        g.drawString("Delta time: "+GameEngine.deltaTime+"ms",100,90);
        g.drawString("Render time: "+String.valueOf(elapsedTime)+"ms",100,100);
        g.drawString("Window height: "+GameEngine.game.getHeight(),100,110);
        g.drawString("Window height: "+prevScale,100,120);
        g.drawString("Mouse pos: "+Input.getMousePosition(),100,130);
        try{
            g.drawString(read.transform.toString(), 100, 140);
            g.drawString(read.getComponent(Rigidbody.class).toString(), 100, 155);
            g.drawString(read.getChild(0).transform.toString(), 100, 170);
        }catch (Exception e){}
        g.setColor(c);
    }


    public boolean checkMouseOverObject(GameObject gameObject){
        Renderer renderer = gameObject.getComponent(com.game.engine.rendering.Renderer.class);
        if(renderer != null){
            Point p = new Point((int) Input.getMousePosition().getX(), (int) Input.getMousePosition().getY());
            if(renderer.getShape().contains(p)){

                if(mouseOverGameObject != null) mouseOverGameObject.onMouseLeft( );
                gameObject.onMouseOver();

                mouseOverGameObject = gameObject;

                return true;
            }else if(gameObject.isMouseInside()){
                gameObject.onMouseLeft();
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {

        long startTime = System.currentTimeMillis();

        super.paintComponent(g);

        // Your custom painting code goes here
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        drawDebugStats(graphics2D);


        graphics2D.translate(GameEngine.game.getWidth()/2, GameEngine.game.getHeight()/2);
        graphics2D.scale(1,-1);


        if(prevScale.getX() != GameEngine.game.getHeight() &&
                prevScale.getY() != GameEngine.game.getHeight()){

            graphics2D.scale(GameEngine.game.getHeight()*scaleFactor, GameEngine.game.getHeight()*scaleFactor);
        }
        graphics2D.translate(-cameraOffset.getX(), -cameraOffset.getY());

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
                    (int) gameObject.transform.getScale().multiply(100).getX(),
                    (int) gameObject.transform.getScale().multiply(100).getY()))){

                gameObject.render(graphics2D);

            }
        }


        long endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
    }
}
