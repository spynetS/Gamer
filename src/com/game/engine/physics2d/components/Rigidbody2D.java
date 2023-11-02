package com.game.engine.physics2d.components;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Scene;
import com.game.engine.components.Comp;
import com.game.engine.components.Component;
import com.game.engine.components.Transform;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.shapes.Rect;
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
    @Getter @Setter PolygonShape shape;
    public Rigidbody2D(BodyDef bd, World world) {
        super(bd, world);
    }

    public static Rigidbody2D create(Scene scene){
        World world = scene.getPhysicsWorld();
        BodyDef def = new BodyDef();

        //def.type = BodyType.DYNAMIC;

        Rigidbody2D b = new Rigidbody2D(def,world);
        world.addBody(b);


        return b;
    }

    @Override
    public Transform transform() {
        return this.transform;
    }

    private Vec2[] getShape(){
        Vec2[] vertices;
        Renderer render = getComponent(Renderer.class);
        if(render != null){
            Debug.log("Has renderer");
            vertices = new Vec2[render.getShapeGlobal().size()];
            int i = 0;
            for(Vector2 point : render.getShapeGlobal()){
                vertices[i] = new Vec2(point.getX()-transform.getGlobalPosition().getX()*100, point.getY() - transform.getGlobalPosition().getY()*100);
                i++;
            }
        }else{
            vertices = new Vec2[]{new Vec2(0,1), new Vec2(1,1), new Vec2(1,0), new Vec2(0,0)};
        }
        return vertices;
    }

    @Override
    public void start() {
        PolygonShape shape = new PolygonShape();
        this.shape = shape;
        shape.setAsBox(transform.getScale().getX()/2,transform.getScale().getY()/2);



        setTransform(new Vec2(transform.getPosition().getX(), transform.getPosition().getY()), (float) Math.toRadians(transform.getRotation()));


        createFixture(shape, getMass());

    }
    @Override
    public void update() {
        transform.setRotation((float) Math.toDegrees(getAngle()));
        transform.setPosition(new Vector2(getPosition().x, getPosition().y));

        if(shape.getRadius()*100 != transform.getScale().getX()){

            PolygonShape shape = new PolygonShape();
            this.shape = shape;
            shape.setAsBox(transform.getScale().getX()/2,transform.getScale().getY()/2);
            createFixture(shape, getMass());
        }

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
