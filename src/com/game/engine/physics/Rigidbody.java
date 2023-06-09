package com.game.engine.physics;

import com.game.engine.GameEngine;
import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.PhysicsWorld;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Rigidbody extends Component {

    @Getter @Setter private boolean isFreeze           = false;
    @Getter @Setter private Vector2 velocity           = new Vector2();
    @Getter @Setter private float   angularVelocity    = 0f;
    @Getter @Setter private float   inertia            = 0f;
    @Getter @Setter private float   linerDrag          = 0.001f;
    @Getter @Setter private float   angularDrag        = 0f;
    @Getter @Setter private Vector2 centerOfMass       = new Vector2(); //point where the center of mass is
    @Getter @Setter private float   mass               = 1f; // 1 is one kg
    @Getter @Setter private boolean useGravity         = false;
    @Getter @Setter private float   gravitationalScale = 1f;

    public Rigidbody(boolean useGravity) {
        this.useGravity = useGravity;
    }

    public void addForce(Vector2 force){
        Vector2 a = force.divide(mass);
        velocity.adds(a.multiply(GameEngine.deltaTime).multiply(100));
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

        }
    }
    @Override
    public void update() {
        updateInertia();

        //rotate by angular velocity
        transform.setRotation((float) (transform.getRotation() + (angularVelocity *GameEngine.deltaTime)));

        //add linerdrag force
        addForce(velocity.multiply(-linerDrag));

        if(useGravity){
            velocity = velocity.add(PhysicsWorld.g.divide(gravitationalScale).multiply(GameEngine.deltaTime));
        }

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
