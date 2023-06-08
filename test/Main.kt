import com.game.engine.GameEngine
import com.game.engine.GameObject
import com.game.engine.Input.Input
import com.game.engine.Input.Keys
import com.game.engine.Scene
import com.game.engine.collision.CircleCollider
import com.game.engine.collision.Collider
import com.game.engine.msc.Debug
import com.game.engine.msc.Vector2
import com.game.engine.physics.Rigidbody
import com.game.engine.rendering.ShapeRender
import com.game.engine.rendering.Sprite
import com.game.engine.rendering.SpriteRenderer
import java.awt.Color

fun getForce (gameObject:GameObject, gameObject2: GameObject): Vector2 {
    val G = 6.67*Math.pow(10.0,-10.0);
    val m1 = gameObject.getComponent(Rigidbody::class.java).mass;
    val m2 = gameObject2.getComponent(Rigidbody::class.java).mass;
    var r = gameObject.transform.position.getDistance(gameObject2.transform.position) / 100;
    var direction = gameObject2.transform.position.subtract(gameObject.transform.position)
    if(r == 0.0)
        return Vector2.zero
    direction = direction.multiply (G*((m1*m2)/(r*r)));

    return direction

}

fun main(){


    var engine = GameEngine()

    var earth2 = Planet(Sprite("/PLANETS/Earth.png"),2000f);
    earth2.transform.position = Vector2(-800f,0f)
    earth2.getComponent(Rigidbody::class.java).velocity = Vector2(0f,-550f)
    earth2.getComponent(Rigidbody::class.java).angularVelocity = 70f;


    var earth = Planet(Sprite("/PLANETS/Earth.png"),1000f);
    earth.transform.position = Vector2(400f,0f)
    earth.getComponent(Rigidbody::class.java).velocity = Vector2(0f,550f)

    var sun = Planet(Sprite("/PLANETS/Sun.png"),1000000f);
    sun.getComponent(Rigidbody::class.java).angularVelocity = 100f;


    val scene = object : Scene(){
        override fun update(){
            if(Input.isMouseDown()){
                var g = gameObjectHandler.instantiate(Planet(Sprite("/PLANETS/Earth.png"),1000f))
                g.transform.position = Input.getMousePosition();
            }
            super.update()
            //cameraOffset = sun.transform.position;

            GameEngine.game.title = GameEngine.fps.toString()

            if(Input.isKeyDown(Keys.DOWNARROW)) scaleFactor -= (0.001f*GameEngine.deltaTime.toFloat())
            if(Input.isKeyDown(Keys.UPARROW)) scaleFactor += (0.001f*GameEngine.deltaTime.toFloat())

            //earth.getComponent(Rigidbody::class.java).addForce(getForce(earth, sun)*1000f)
            //h2.getComponent(Rigidbody::class.java).addForce(getForce(earth2, sun)*1000f)
            //getComponent(Rigidbody::class.java).addForce(getForce(sun, earth)*1000f)

            for (g1 in this.gameObjects){
                for (g2 in this.gameObjects){
                    g1.getComponent(Rigidbody::class.java).addForce(getForce(g1,g2)*1000f)
                }
            }


        }
    }


    for(x in 1..98) {
        println("instans")
        //var g = scene.add(Planet(Sprite("/PLANETS/Earth.png"),1000f))
        //g.transform.position = Vector2(x*100f,x*200f)
    }
    scene.background = Color(50,50,50)

    scene.add(earth)
    scene.add(earth2)
    scene.add(sun)


    GameEngine.setSelectedScene(scene)
    engine.start()


}


class Planet : GameObject {

    constructor(sprite: Sprite, mass:Float){
        var renderer = SpriteRenderer();
        renderer.addSprite(sprite)
        addComponent(renderer)
        var rigidbody = Rigidbody();
        rigidbody.mass = mass;
        rigidbody.linerDrag = 0f;
        addComponent(rigidbody)
        addComponent(PlayerMovement())
        addComponent(CircleCollider())

    }

    override fun onCollisionEnter(collider: Collider) {

        val vel1 = collider.getComponent(Rigidbody::class.java).velocity;

        if(vel1.magnitude > 1000){
            collider.transform.gameObject.destroy();
            this.transform.setScale(this.transform.getScale()+5f)
            //this.getComponent(Rigidbody::class.java).mass += collider.getComponent(Rigidbody::class.java).mass;
        }

    }
    override fun update() {
        super.update()
    }
}
