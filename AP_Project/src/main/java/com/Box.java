package com;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Box extends Rectangle {
    public Box(){
        setWidth(Controller.box_size);
        setHeight(Controller.box_size);
        setFill(Color.AQUA);
        setStroke(Color.BLACK);
    }
}
