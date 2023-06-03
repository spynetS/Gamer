package com.game.engine.physics;

import com.game.engine.GameEngine;
import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.PhysicsWorld;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Rigidbody extends Component {


    @Getter
    @Setter
    private boolean isFreeze = false;

    @Getter
    @Setter
    private Vector2 velocity = new Vector2();

    @Getter
    @Setter
    private float angularVelocity = 0f;

    @Getter
    @Setter
    private float inertia = 0f;

    @Getter
    @Setter
    private float linerDrag = 0.001f;

    @Getter
    @Setter
    private float angularDrag = 0f;

    @Getter
    @Setter
    private Vector2 centerOfMass  = new Vector2(); //point where the center of mass is

    @Getter
    @Setter
    private float mass = 1f; // 1 is one kg

    @Getter
    @Setter
    private float gravitationalScale = 10f;

    @Getter
    @Setter
    private boolean useGravity = false;


    public void addForce(Vector2 force){
        Vector2 a = force.divide(mass);
        velocity.adds(a);
    }

    private void updateInertia(){
        float i = (float) ((1/12) *
                        mass *
                        (Math.pow(transform.getScale().getX(),2) +
                         Math.pow(transform.getPosition().getY(),2)));

        setInertia(i);
    }

    public void resolveCollision(Rigidbody other){
        Rigidbody me = this;

        if(other == null){

        }
        else{

            float m1 = getMass();
            float m2 = other.getMass();

            Vector2 v1i = getVelocity();
            Vector2 v2i = other.getVelocity();

            Vector2 p_initial = v1i.multiply(m1).add(v2i.multiply(m2));

            Vector2 v1f = (p_initial.subtract (v2i.subtract(v1i).multiply(m2)).divide(m1 + m2));
            Vector2 v2f = v1f.add(v2i).subtract(v1i);

            //me.velocity    = (!me.isFreeze())    ? v1f:me.getVelocity();
            //other.velocity = (!other.isFreeze()) ? v2f:other.getVelocity();
            me.addForce(Vector2.left.multiply(10));

        }
    }

    @Override
    public void update() {

        updateInertia();

        //rotate by angular velocity
        transform.setRotation((float) (transform.getRotation() + (angularVelocity *GameEngine.deltaTime)));

        //add linerdrag force
        addForce(velocity.multiply(-linerDrag));

        if(useGravity)
            velocity = velocity.add(PhysicsWorld.g.divide(gravitationalScale));

        //update transforms position based on velocity
        transform.setPosition(transform.getPosition().add(velocity.multiply(GameEngine.deltaTime)));
    }


    @Override
    public String toString() {
        return "Rigidbody{" +
                "velocity=" + velocity +
                ", angularVelocity=" + angularVelocity +
                ", inertia=" + inertia +
                ", linerDrag=" + linerDrag +
                ", angularDrag=" + angularDrag +
                ", centerOfMass=" + centerOfMass +
                ", mass=" + mass +
                ", gravitationalScale=" + gravitationalScale +
                '}';
    }
}
