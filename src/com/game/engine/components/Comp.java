package com.game.engine.components;

import com.game.engine.GameObject;

public interface Comp {

    void setTransform(Transform transform);
    Transform transform();
    void start();

    void update();

    void __update__();
    <T extends Component> T getComponent(Class<T> tClass);

    void instantiate(GameObject gameObject);

    GameObject instantiate(GameObject child, GameObject parent);

}
