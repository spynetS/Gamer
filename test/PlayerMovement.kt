import com.game.engine.Input.Input
import com.game.engine.Input.InputComponent
import com.game.engine.Input.Keys
import com.game.engine.components.Component
import com.game.engine.msc.Vector2
import com.game.engine.physics.Rigidbody

class PlayerMovement : Component {

    var input: InputComponent = InputComponent();
    constructor() {
        Input.addContext("Movement")
        input.context = "Movement";
    }

    override fun start() {
        super.start()
        getComponent(Rigidbody::class.java).isUseGravity = true;
    }
    override fun update() {
        super.update()
        if(input.isKeyDown(Keys.SPACE))
            getComponent(Rigidbody::class.java).velocity += Vector2.up * 10f;
        if(input.isKeyDown(Keys.A))
            getComponent(Rigidbody::class.java).velocity += Vector2.left * 10f;
        if(input.isKeyDown(Keys.D))
            getComponent(Rigidbody::class.java).velocity += Vector2.right * 10f;


    }


}