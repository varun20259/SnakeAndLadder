package com;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Circle {
    private int count_1=0;

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    private boolean turn=true;

    public boolean isWinner() {
        return Winner;
    }

    public void setWinner(boolean winner) {
        Winner = winner;
    }

    private boolean Winner=false;
    private int x, y;

    public int getCount_1() {
        return count_1;
    }

    public void setCount_1(int count_1) {
        this.count_1 = count_1;
    }

    public int getRow() {
        return row;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Circle getC() {
        return c;
    }

    public void setC(Circle c) {
        this.c = c;
    }

    private int row=1;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Circle c;
    private int pos=1;
    private String name;
    public Player(String s,int x, int y, Color color) {
        this.name=s;
        c = new Circle((double) Controller.box_size/2);
        c.setId(s);
        c.getStyleClass().add("style.css");
        c.setFill(color);
        c.setTranslateX(x);
        c.setTranslateY(y);
        this.x=x;
        this.y=y;
    }
}
