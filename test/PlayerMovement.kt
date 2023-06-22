import com.game.engine.Input.Input
import com.game.engine.Input.InputComponent
import com.game.engine.Input.Keys
import com.game.engine.components.Component
import com.game.engine.msc.Vector2
import com.game.engine.physics.Rigidbody
import com.game.engine.physics2d.components.Rigidbody2D

class PlayerMovement : Component {

    var input: InputComponent = InputComponent();
    val speed = 1f;
    constructor() {
        Input.addContext("Movement")
        input.context = "Movement";
    }

    override fun start() {
        super.start()
    }
    override fun update() {
        super.update()
        if(input.isKeyPressed(Keys.SPACE))
            getComponent(Rigidbody::class.java).addForce(Vector2.up)
        if(input.isKeyDown(Keys.A))
            getComponent(Rigidbody::class.java).addForce(Vector2.left * speed)
        if(input.isKeyDown(Keys.D))
            getComponent(Rigidbody::class.java).addForce(Vector2.right * speed)
        if(input.isKeyDown(Keys.W))
            getComponent(Rigidbody::class.java).addForce(Vector2.up * speed)

        if(input.isKeyDown(Keys.S))
            getComponent(Rigidbody::class.java).addForce(Vector2.down * speed)
    }


}