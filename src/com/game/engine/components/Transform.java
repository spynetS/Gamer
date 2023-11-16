package com.game.engine.components;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import lombok.Getter;
import lombok.Setter;

/**
 * The transform class is the base of the GameObjects form.
 * It is the position rotation scale and parenting.
 */
public class Transform extends Component{

    @Setter private Vector2 globalPosition = new Vector2();

    @Getter @Setter private Vector2 localPosition = new Vector2();
    @Getter @Setter private float localRotation = 0f;
    @Getter @Setter private float rotation = 0f;
    private Vector2 rotationOffset = new Vector2();
    @Getter @Setter private Vector2 localScale = new Vector2(1,1);
    @Setter private Vector2 globalScale = new Vector2(1,1);
    @Getter @Setter private GameObject gameObject;
    @Getter @Setter private GameObject parent;
    private Vector2 scalePositionThing = new Vector2(0,0);

    public Transform(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Vector2 getGlobalPosition() {
        //rotationOffset.adds();
        return globalPosition.add(rotationOffset.multiply(scalePositionThing));
    }

    public Vector2 getGlobalScale() {
        if(localScale.equals(Vector2.zero)) return new Vector2(1,1);
        if(parent != null) return globalScale.add(localScale);
        return globalScale;
    }

    public Vector2 getScale(){
        return globalScale;
    }

    public void setScale(Vector2 scale){
        if(parent == null){
            setScaleFactor(scale.divide(globalScale));
        }
        else{
            if(localScale.containsZero()){
                localScale = scale;
            }
            setScaleFactor(scale.divide(localScale));
            localScale = scale;
        }
    }
    public void setScaleFactor(Vector2 factor){
        if(factor.getX() > 0 && factor.getY() > 0){
            Debug.log(globalScale);
            globalScale = globalScale.multiply(factor);
            Debug.log(globalScale);
            //scale children
            for(GameObject g : gameObject.gameObjects){
                g.transform.setScaleFactor(factor);
            }
        }
        else{
            Debug.log("factor is 0");
        }
    }

    /**
     * Translates (changes postion) with delta time (takes in to account framerate)
     * @param translation the amount to change
     */
    public void translate(Vector2 translation){
        float factor = 100;
        setPosition(getPosition().add(translation.multiply(GameEngine.deltaTime).multiply(factor)));
    }

    public Vector2 getPosition() {
        if(parent == null)
            return globalPosition;
        return localPosition;
    }

    public void setPosition(Vector2 position) {
        if(parent == null)
            globalPosition = position;
        else{
            localPosition = position.subtract(parent.transform.getPosition());
        }
    }

    public float getRotation(){
        return rotation + localRotation;
    }
    public void setRotation(float rotation){
        if(parent!=null){
            this.localRotation = rotation;
        }
        else
            this.rotation = rotation;
    }

    @Override
    public void update() {
        super.update();
        //update parent values
        if(parent != null){
            //update position based on the parent
            globalPosition = parent.transform.getGlobalPosition();
            //update scale based on parent
            globalScale = parent.transform.getScale().add(localScale);

            //this is for parent scaling so the object moves when parent scales
            if(!localScale.containsZero())
                scalePositionThing = (globalScale.divide(localScale)).divide(10);
            scalePositionThing = new Vector2(1,1);

            //calculate the rotation offset so the child rotates around the parent
            double rotation = Math.toRadians(parent.transform.getRotation());
            this.rotation = parent.transform.getRotation();

            float x = localPosition.getX();
            float y = localPosition.getY();

            //rotationOffset is the localpostion based on the rotation
            rotationOffset = new Vector2(
                    (float) (x * Math.cos(rotation) - y * Math.sin(rotation)),
                    (float) (x * Math.sin(rotation) + y * Math.cos(rotation))
            );

        }
    }


    @Override
    public void start() {
        // we have a parent
        if(parent != null){
            setGlobalScale(parent.transform.getGlobalScale().add(localScale));
            Debug.log(getScale());
        }
        super.start();
    }

    @Override
    public String toString() {
        return "Transform{" +
                "globalPosition=" + getGlobalPosition() +
                ", \nlocalPosition=" + localPosition +
                ", \nrotation=" + rotation +
                ", \nlocalScale=" + localScale +
                ", \nglobalScale=" + getGlobalScale() +
                ", \ngameObject=" + gameObject +
                ", \nparent=" + parent +
                ", \nscalePositionThing=" + scalePositionThing +
                '}';
    }
}
