package org.joat.mow;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Cell extends Actor {

    public Cell(int x, int y, int size) {
        this.setX(x * size);
        this.setY(y * size);
    }
}
