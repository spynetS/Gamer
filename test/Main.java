import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.Scene;
import com.game.engine.components.*;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.Sprite;
import com.game.engine.rendering.SpriteRenderer;

import java.awt.*;
import java.util.LinkedList;

import static com.game.engine.msc.Debug.*;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();

        Scene scene = new Scene();

        showWhere =true;

        Sprite grass = new Sprite("/tiles/GrassTile.png");
        Sprite tree = new Sprite("/tiles/trees.png");
        Sprite tree2 = new Sprite("/tiles/trees2.png");
        int d = 0;
        for(int i = 0 ; i < 1000; i ++){
            for(int j = 0 ; j < 100; j ++){
                GameObject g = new GameObject();
                SpriteRenderer renderer = new SpriteRenderer();

                renderer.addSprite(grass);

                g.addComponent(renderer);
                g.transform.setPosition(new Vector2(i*110,j*110));
                scene.add(g);
                d++;
            }
        }

        Debug.log(d);

        Player player = new Player();

        scene.add(player);

        GameEngine.setSelectedScene(scene);

        gameEngine.start();
    }

    public static class Player extends GameObject{
        int an = 0;
        private boolean attack = false;

        public Player(){
            SpriteRenderer renderer = new SpriteRenderer(){
                @Override
                public void onAnimationDone() {
                    super.onAnimationDone();
                    an = 0;
                    attack = false;
                }
            };

            LinkedList<Rectangle> tiles = new LinkedList<>();
            int w = 48;
            int h = 48;

            for(int i = 0; i < 4; i ++){
                tiles.add(new Rectangle(i*w, 0*h, w, h));
            }
            renderer.addAnimation(Sprite.getSprites("/sprites/characters/player.png", tiles));
            tiles.clear();
            for(int i = 0; i < 4; i ++){
                tiles.add(new Rectangle(i*w, 3*h, w, h));
            }
            renderer.addAnimation(Sprite.getSprites("/sprites/characters/player.png", tiles));
            tiles.clear();
            for(int i = 0; i < 4; i ++){
                tiles.add(new Rectangle(i*w, 4*h, w, h));
            }
            renderer.addAnimation(Sprite.getSprites("/sprites/characters/player.png", tiles));
            tiles.clear();
            for(int i = 0; i < 4; i ++){
                tiles.add(new Rectangle(i*w, 5*h, w, h));
            }
            renderer.addAnimation(Sprite.getSprites("/sprites/characters/player.png", tiles));
            tiles.clear();
            for(int i = 0; i < 4; i ++){
                tiles.add(new Rectangle(i*w, 6*h, w, h));
            }
            renderer.addAnimation(Sprite.getSprites("/sprites/characters/player.png", tiles));


            addComponent(renderer);
            transform.setScale(new Vector2(200,200));
        }
        float speed = 200;
        @Override
        public void update() {
            super.update();
            float speed = (float) (this.speed * GameEngine.deltaTime);
            getComponent(SpriteRenderer.class).setAnimationIndex(an);
            an = 0;

            if(Input.isKeyDown(Keys.D)){
                transform.getPosition().adds(Vector2.right.multiply(speed));
                getComponent(SpriteRenderer.class).setInverted(false);
                an = 2;
            }
            if(Input.isKeyDown(Keys.A)){
                transform.getPosition().adds(Vector2.left.multiply(speed));
                getComponent(SpriteRenderer.class).setInverted(true);
                an = 2;
            }
            if(Input.isKeyDown(Keys.W)){
                transform.getPosition().adds(Vector2.up.multiply(speed));
                an = 3;
            }
            if(Input.isKeyDown(Keys.S)){
                transform.getPosition().adds(Vector2.down.multiply(speed));
                an = 1;
            }

            GameEngine.getSelectedScene().setCameraOffset(transform.getPosition());
        }
    }

}
