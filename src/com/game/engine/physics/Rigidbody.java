package com.game.engine.physics;

import com.game.engine.GameEngine;
import com.game.engine.components.Component;
import com.game.engine.msc.Vector2;
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
        float mass = this.mass;
        if(mass == 0){
            mass = 1;
        }
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
        float cor = 1;

        Vector2 newb = ((me.getVelocity().multiply(me.mass).add
                ( other.getVelocity().multiply(other.mass) ).add
                ( (other.getVelocity().subtract(me.getVelocity()) ).multiply(other.mass))).divide
                (me.mass+other.mass));

        Vector2 newCb =((me.getVelocity().multiply(me.mass)).add
                (other.getVelocity().multiply(other.mass).add
                        ((me.getVelocity().subtract(other.getVelocity()).multiply(me.mass)))).divide
                (me.mass+other.mass));

        me.velocity    = (!me.isFreeze())    ? newb:me.getVelocity();
        other.velocity = (!other.isFreeze()) ? newCb:other.getVelocity();
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
