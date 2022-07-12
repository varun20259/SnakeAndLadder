package com;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

public class Controller  {

    private Rectangle r1 = new Rectangle();
    private Rectangle r2 = new Rectangle();
    protected static final int box_size = 50, ROW = 10, COL = 10;
    Label output = new Label("");
    private Player p1 = new Player("Player 1", box_size/2, (ROW *box_size)-(box_size/2), Color.RED);
    private Player p2 = new Player("Player 2", box_size/2, (ROW *box_size)-(box_size/2), Color.BLUE);

    private Group SL = new Group();
    Label l = new Label();

    Image image = new Image("D:\\AP_Project\\src\\main\\resources\\dice1.png");
    private ImageView dice = new ImageView(image);

    private myth thread ;
    private myth thread2 ;

    Image arrow = new Image("D:\\AP_Project\\src\\main\\resources\\img_1.png");
    private ImageView arrow_t = new ImageView(arrow);


     protected Parent create_content(){
        Pane node = new Pane();
        node.setPrefSize(COL *box_size, (ROW +2)*box_size);
        node.getChildren().addAll(SL);
        for(int i = 0; i< ROW; i++){
            for(int j = 0; j< COL; j++){
                Box b = new Box();
                b.setTranslateX(j*box_size);
                b.setTranslateY(i*box_size);
                SL.getChildren().add(b);
            }
        }

        arrow_t.setFitHeight(30);
        arrow_t.setFitWidth(30);
        arrow_t.setTranslateX(50);
        arrow_t.setTranslateY(535);

        r1.setFill(Color.RED);
        r2.setFill(Color.BLUE);
        r1.setTranslateX(60);
        r1.setTranslateY(533);
        r1.setHeight(40);
        r1.setWidth(130);
        r2.setTranslateX(330);
        r2.setTranslateY(533);
        r2.setHeight(40);
        r2.setWidth(130);

        Ladder l = new Ladder();
        Snake s = new Snake();
        Button b1 = new Button("Player 1");
        b1.setTextFill(Color.RED);
        b1.setStyle("-fx-font-size:15px;");
        p2.setTurn(false);
        b1.setTranslateX((double)(COL *box_size/4)-(double)box_size/2);
        b1.setTranslateY((double)box_size*(ROW +0.75));

        dice.setTranslateX((double)(COL *box_size/2)-25);
        dice.setTranslateY((double)box_size*(ROW +0.75)-10);
        dice.setFitHeight(50);
        dice.setFitWidth(50);
        Button b2 = new Button("Player 2");
        b2.setStyle("-fx-font-size:15px;");
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(p1.isTurn()){
                    thread=new myth(dice, p1);
                    thread.start();
                    TranslateArrow(false);
                    p2.setTurn( true);
                    p1.setTurn(false);
                    b2.setTextFill(Color.RED);
                    b1.setTextFill(Color.BLACK);
                }
            }
        });

        b2.setTranslateX((COL *box_size*(0.75))-(double)box_size/2);
        b2.setTranslateY((double)box_size*(ROW +0.75));
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (p2.isTurn()) {
                    thread2=new myth(dice, p2);
                    thread2.start();
                    TranslateArrow(true);
                    p1.setTurn (true) ;
                    p2.setTurn (false);
                    b2.setTextFill(Color.BLACK);
                    b1.setTextFill(Color.RED);
                }
            }
        });

        output.setTranslateX((double)(COL *box_size/2));
        output.setTranslateY((double)box_size*(ROW +0.75)+60);
        Image img = new Image("SL.jpeg");
        ImageView bg = new ImageView();
        bg.setImage(img);
        bg.setFitHeight(box_size* ROW);
        bg.setFitWidth(box_size* COL);

        SL.getChildren().addAll(bg,r1,r2 ,b1, b2, output,dice,p1.getC(),p2.getC(),arrow_t);
        return node;
    }

    void TranslateArrow(boolean a){
        arrow_t.setRotate(arrow_t.getRotate()+180);
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000),arrow_t);
        if(a){
            tt.setFromX(50);
            tt.setToX(60);
            arrow_t.setFitHeight(30);
            arrow_t.setFitWidth(30);
            arrow_t.setTranslateX(50);
            arrow_t.setTranslateY(535);
        }
        else{
            tt.setFromX(440);
            tt.setToX(430);
            arrow_t.setFitHeight(30);
            arrow_t.setFitWidth(30);
            arrow_t.setTranslateX(440);
            arrow_t.setTranslateY(535);
        }
        tt.setAutoReverse(false);
        tt.play();
    }
}

class myth extends Thread{
    private Random random = new Random();
    private int dice_value;
    private ImageView dice;
    private Player p;
    private Label l = new Label() ;

    myth(ImageView dice, Player p){
        this.dice=dice;
        this.p = p;
    }
    public void run(){
        try {
            for (int i = 0; i < 10; i++) {
                dice_value = random.nextInt(6) + 1;
                File file = new File("D:\\AP_Project\\src\\main\\resources\\dice" + dice_value + ".png");
                dice.setImage(new Image(file.toURI().toString()));
                Thread.sleep(50);
            }
            if(dice_value==1){
                p.setCount_1(p.getCount_1()+1);
            }
            if(p.getCount_1() < 1){
                return;
            }
                movement();
                translate_player(p.getX(), p.getY());
                if( Ladder.search(p.getPos())){
                    p.setPos(Ladder.get(p.getPos()));
                    p.setX(Ladder.x[p.getPos()-1]);
                    p.setY(Ladder.y[p.getPos()-1]);
                    p.setRow((p.getPos()/10)+1);
                    try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
                    translate_player(p.getX(),p.getY());
                }
                if(Snake.search(p.getPos())){
                    p.setPos(Snake.get(p.getPos())) ;
                    p.setX(Ladder.x[p.getPos()-1]);
                    p.setY(Ladder.y[p.getPos()-1]);
                    p.setRow((p.getPos()/10)+1);
                    try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
                    translate_player(p.getX(),p.getY());
                }
                if(p.isWinner()){
                    try{Thread.sleep(2000);}catch(InterruptedException e){e.printStackTrace();}
                    System.exit(0);
                }
        }
        catch(InterruptedException e){e.printStackTrace();}
    }
    void movement(){
        if(dice_value>100-p.getPos()){
            return;
        }
        if(dice_value==100-p.getPos()){
            p.setWinner(true);
            System.out.println(p.getName()+" WINS");
        }
        for(int j=0;j<dice_value;j++){
            if(p.getRow() %2==1){
                p.setX(p.getX()+50);
            }
            if(p.getRow() %2==0){
                p.setX(p.getX()-50);
            }
            if(p.getX() > 475){
                p.setX(p.getX()-50);
                p.setY(p.getY()-50);
                p.setRow(p.getRow()+1);
            }
            if(p.getX() < 25){
                p.setX(p.getX()+50);
                p.setY(p.getY()-50);
                p.setRow(p.getRow()+1);
            }
            p.setPos(p.getPos()+1);
        }
    }
    void translate_player(int x, int y){
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), p.getC());
        tt.setToX(x);
        tt.setToY(y);
        tt.setAutoReverse(false);
        tt.play();
    }
}

