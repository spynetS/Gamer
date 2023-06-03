package com.game.engine.components;

import com.game.engine.GameEngine;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.PhysicsWorld;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Rigidbody extends Component{


    @Getter
    @Setter
    private Vector2 velocity = new Vector2();

    @Getter
    @Setter
    private float angularVelocity = 36f;

    @Getter
    @Setter
    private float inertia = 0f;

    @Getter
    @Setter
    private float linerDrag = 0.01f;

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
