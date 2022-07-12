package com;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class HomeController extends Thread {
    Group group = new Group();
    myth2 thread2;
    Button start = new Button("Start");

    protected Parent create_content(){
        Pane root = new Pane();
        Scene scene = new Scene(root);
        root.setPrefSize(600,600);
        group.getChildren().add(start);
        root.getChildren().addAll(group);
        start.setOnAction(actionEvent ->{
            thread2.start();
            Thread.dumpStack();
        });
        return root;
    }

}
class myth2 extends Thread{
    private Parent root (){
        Controller c = new Controller();
        return c.create_content();
    }

    @Override
    public void run() {
        root();
    }
}