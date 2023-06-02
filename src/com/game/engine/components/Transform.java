package com.game.engine.components;

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

    @Setter
    private Vector2 globalPosition = new Vector2();

    @Getter
    @Setter
    private Vector2 localPosition = new Vector2();

    @Getter
    @Setter
    private Vector2 rotation = new Vector2();

    @Getter
    @Setter
    private Vector2 localScale = new Vector2(1,1);

    @Setter
    private Vector2 globalScale = new Vector2(10,10);

    @Getter
    @Setter
    private GameObject gameObject;

    @Getter
    @Setter
    private GameObject parent;

    private Vector2 scalePositionThing = new Vector2(0,0);

    public Transform(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Vector2 getGlobalPosition() {
        return globalPosition.add(localPosition.multiply(scalePositionThing));
    }

    public Vector2 getGlobalScale() {
        if(localScale.equals(Vector2.zero)) return Vector2.zero;
        return globalScale.add(localScale);
    }

    public Vector2 getScale(){
        if(parent == null)
            return globalScale;
        return localScale;
    }
    public void setScale(Vector2 scale){
        if(parent == null){

            setScaleFactor(scale.divide(globalScale));
        }
        else{
            if(localScale.getX() == 0 && localScale.getY() == 0){
                Debug.log("ues");
                localScale = scale;
                Debug.log(localScale);

            }
            setScaleFactor(scale.divide(localScale));
            localScale = scale;
        }
    }
    public void setScaleFactor(Vector2 factor){
        if(parent == null){
            globalScale = globalScale.multiply(factor);
            for(GameObject g : gameObject.gameObjects){
                g.transform.setScaleFactor(factor);
            }
        }
        else{
            if(factor.getX() != 0)
                globalScale = globalScale.multiply(factor);
        }
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
            localPosition = position;
        }
    }

    @Override
    public void update() {
        super.update();
        //update parent values
        if(parent != null){
            globalPosition = parent.transform.getPosition();

            if(localScale.getX() != 0 && localScale.getY() != 0)
                scalePositionThing = (globalScale.divide(localScale)).divide(10);

        }
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
