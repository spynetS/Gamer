package com.game.engine.physics2d.components;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Scene;
import com.game.engine.components.Comp;
import com.game.engine.components.Component;
import com.game.engine.components.Transform;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import lombok.*;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class Rigidbody2D extends Body implements Comp {

    @Setter Transform transform;
    @Getter @Setter BodyDef bodyDef = new BodyDef();
    public Rigidbody2D(BodyDef bd, World world) {
        super(bd, world);
    }

    public static Rigidbody2D create(Scene scene){
        World world = scene.getPhysics2D().getWorld();
        BodyDef def = new BodyDef();

        //def.type = BodyType.DYNAMIC;

        Rigidbody2D b = new Rigidbody2D(def,world);
        b.m_prev = null;
        b.m_next = world.getBodyList();
        if (world.getBodyList() != null) {
            world.getBodyList().m_prev = b;
        }

        world.setBodyList(b);
        world.setBodyCount(world.getBodyCount()+1);


        return b;
    }

    @Override
    public Transform transform() {
        return this.transform;
    }

    @Override
    public void start() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(transform.getScale().getX()/2,transform.getScale().getY()/2);

        setTransform(new Vec2(transform.getPosition().getX(), transform.getPosition().getY()), transform.getRotation());


        createFixture(shape, getMass());

    }
    @Override
    public void update() {
        transform.setRotation(getAngle());
        transform.setPosition(new Vector2(getPosition().x, getPosition().y));
    }
    @Override
    public void __update__() {

    }
    @Override
    public <T extends Component> T getComponent(Class<T> tClass) {
        return null;
    }
    @Override
    public void instantiate(GameObject gameObject) {

    }
    @Override
    public GameObject instantiate(GameObject child, GameObject parent) {
        return null;
    }

}
