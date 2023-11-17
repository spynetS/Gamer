package com.game.engine.physics;

import com.game.engine.GameEngine;
import com.game.engine.components.Component;
import com.game.engine.components.Transform;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.Renderer;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Rigidbody extends Component {

    @Getter @Setter private boolean isFreeze           = false;
    @Getter @Setter private Vector2 velocity           = new Vector2();
    @Getter @Setter private double   angularVelocity    = 0f;
    @Getter @Setter private double   inertia            = 0f;
    @Getter @Setter private double   linerDrag          = 0.001f;
    @Getter @Setter private double   angularDrag        = 0f;
    @Getter @Setter private Vector2 centerOfMass       = new Vector2(); //point where the center of mass is
    @Getter @Setter private double   mass               = 1f; // 1 is one kg
    @Getter @Setter private boolean useGravity         = false;
    @Getter @Setter private double   gravitationalScale = 1f;

    public Rigidbody(boolean useGravity) {
        this.useGravity = useGravity;
    }

    public void addForce(Vector2 force){
        double mass = this.mass;
        if(mass == 0){
            mass = 1;
        }
        Vector2 a = force.divide(mass);
        velocity.adds(a.multiply(GameEngine.deltaTime).multiply(100));
    }
    private void updateInertia(){
        double i = (double) ((1/12) *
                        mass *
                        (Math.pow(transform.getScale().getX(),2) +
                         Math.pow(transform.getPosition().getY(),2)));

        setInertia(i);
    }

    private void move(Rigidbody other){
        Collider c1 = getComponent(Collider.class);
        Collider c2 = other.getComponent(Collider.class);

        int max = 10000;
        while(c1.collides(c2) && max > 0){
            //transform.translate(velocity.getNormalized().multiply(-0.01));
            //Debug.log(velocity.getNormalized().multiply(-0.01));
            transform.translate(new Vector2(0,0.01f));

            getComponent(Renderer.class).update();
            c2.getComponent(Renderer.class).update();
            max--;
        }
    }

    public void resolveCollision(Rigidbody other) {
        Rigidbody me = this;
        double cor = 0f; // Adjust the coefficient of restitution as needed (e.g., 0.8 for 80% bounce)

        move(other);

        double m1 = mass;
        double m2 = other.mass;
        Vector2 v1i = velocity;
        Vector2 v2i = other.velocity;

        Vector2 idk = (v1i.multiply(m1).add(v2i.multiply(m2))).divide(m1+m2).multiply(1-cor);

        Vector2 meNewVelocity    = v1i.multiply(m1-m2).add(v2i.multiply(2*m2)).divide(m1+m2).multiply(cor).add(idk);
        Vector2 otherNewVelocity = v1i.multiply(2*m1).add(v2i.multiply(m2-m1)).divide(m1+m2).multiply(cor).add(idk);

        //me.velocity = velocity.multiply(new Vector2(1,0));
        // Update velocities with bounce effect
        me.velocity = (!me.isFreeze()) ? meNewVelocity : me.getVelocity();
        other.velocity = (!other.isFreeze()) ? otherNewVelocity : other.getVelocity();

        //transform.setPosition(transform.getPosition().add(velocity.multiply(GameEngine.deltaTime)));
    }

    Vector2 prev = Vector2.zero;
    @Override
    public void update() {
        //update transforms position based on velocity
        transform.setPosition(transform.getPosition().add(velocity.multiply(GameEngine.deltaTime)));
        updateInertia();

        //rotate by angular velocity
        transform.setRotation((double) (transform.getRotation() + (angularVelocity *GameEngine.deltaTime)));

        //add linerdrag force
        addForce(velocity.multiply(-linerDrag));

        if(useGravity){
            velocity = velocity.add(PhysicsWorld.g.divide(gravitationalScale).multiply(GameEngine.deltaTime));
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



}
