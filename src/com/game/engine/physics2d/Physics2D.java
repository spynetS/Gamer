package com.game.engine.physics2d;

import com.game.engine.components.Transform;
import com.game.engine.msc.Debug;
import com.game.engine.physics2d.components.Rigidbody2D;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

public class Physics2D {

    private Vec2 gravity = new Vec2(0, -9.82f);
    private World world = new World(gravity);
    private float physicsTime = 0f;
    private float physicsStep = 1/120;
    private int velocityIterations = 8;
    private int positionIterations = 3;

    public Physics2D() {
        float lengthUnitRatio = 1.0f; // Conversion factor: 1 unit in jbox2d = 1 meter in the game world
    }

    public void add(Rigidbody2D rb){
        if(rb != null && rb.getRawBody() == null){

            Transform transform = rb.transform;
            BodyDef bodyDef = new BodyDef();
            bodyDef.angle = (float) Math.toRadians(transform.getRotation());
            bodyDef.angularDamping = rb.getAngularDamping();
            bodyDef.linearDamping = rb.getLinearDamping();
            bodyDef.fixedRotation = rb.isFreeze();
            bodyDef.gravityScale = rb.getGravitationalScale();
            bodyDef.bullet = world.isContinuousPhysics();
            bodyDef.type = rb.getBodyType();

            Debug.log(bodyDef.type);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(transform.getScale().getX()/2,transform.getScale().getY()/2);

            bodyDef.position.set(transform.getPosition().getX(), transform.getPosition().getY());

            Body body = world.createBody(bodyDef);
            rb.setRawBody(body);
            body.createFixture(shape, rb.getMass());
            Debug.log(body);

        }
    }

    public void update(float dt){

        world.step(dt, velocityIterations, positionIterations);
    }

}
