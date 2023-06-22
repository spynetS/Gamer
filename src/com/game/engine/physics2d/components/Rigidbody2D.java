package com.game.engine.physics2d.components;

import com.game.engine.GameEngine;
import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.PhysicsWorld;
import lombok.*;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class Rigidbody2D extends Component {

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

    private BodyType bodyType;

    @Getter @Setter private Body rawBody = null;

    public Rigidbody2D(boolean useGravity) {
        this.useGravity = useGravity;
        bodyType = BodyType.DYNAMIC;
    }
    public Rigidbody2D() {
        bodyType = BodyType.DYNAMIC;
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
    public void resolveCollision(Rigidbody2D other){
        Rigidbody2D me = this;
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
        if(rawBody != null){
            this.transform.setPosition(new Vector2(rawBody.getPosition().x, rawBody.getPosition().y));
            this.transform.setRotation((float) Math.toDegrees(rawBody.getAngle()));

        }
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

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }
}
