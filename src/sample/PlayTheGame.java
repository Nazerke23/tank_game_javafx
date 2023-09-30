package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayTheGame extends Application {

    private ArrayList<Node> brickWalls = new ArrayList<Node>();
    private ArrayList<Position> brickWallsPosition = new ArrayList<>();
    private ArrayList<Node> steelWalls = new ArrayList<Node>();

    private int spaceCount;

    private Group board;
    private int dWeapon = 10;

    boolean throwing;
    boolean isDirectionLeft, isDirectionRight, isDirectionUp, isDirectionDown;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InvalidMapException, FileNotFoundException {
        File map_file = new File("src/sample/map1.txt");
        Scanner input = new Scanner(map_file);

        Map map = new Map(input);
        Game game = new Game(map);
        MyPlayer player = new MyPlayer();
        game.addPlayer(player);
        player.setMap(map);

        player.setMap(map);
        Tank myTank = new Tank(player, map);
        myTank.setMap(map);
        Bullet myBullet = new Bullet(myTank, map);

        board = new Group();

        final double W = map.getSize()*45, H = map.getSize()*45;


        for(int i = 0; i < map.getSize(); i++){
            for(int j = 0; j < map.getSize(); j++){

                if(game.map.getValueAt(i, j) == 'W'){
                    WaterWall waterWall = new WaterWall();
                    waterWall.callWaterWall(i,j);
                    board.getChildren().add(waterWall.getWaterWallView());
                }
                else if(game.map.getValueAt(i, j) == 'B'){
                    BrickWall brickWall = new BrickWall();
                    brickWall.callBrickWall(i,j);
                    System.out.println(brickWall.getBrickWallViewPosition());
                    System.out.println("works perfectly");
                    brickWallsPosition.add(brickWall.getBrickWallViewPosition());
                    board.getChildren().add(brickWall.getBrickWallView());
                    brickWalls.add(brickWall.getBrickWallView());
                }
                else if(game.map.getValueAt(i, j) == 'S'){
                    Image steelWall = new Image("images/stone.png");
                    ImageView steelWallView = new ImageView(steelWall);
                    steelWallView.setFitHeight(45);
                    steelWallView.setFitWidth(45);
                    steelWallView.setLayoutX(j*45);
                    steelWallView.setLayoutY(i*45);
                    board.getChildren().add(steelWallView);
                    steelWalls.add(steelWallView);

                }
                else if(game.map.getValueAt(i, j) == 'T'){
                    TreeWall treeWall = new TreeWall();
                    treeWall.callTreeWall(i,j);
                    board.getChildren().add(treeWall.getTreeWallView());
                }
                else if(game.map.getValueAt(i, j) == 'E' || game.map.getValueAt(i, j) == 'P'){
                    Rectangle rect = new Rectangle(45, 45);
                    rect.setFill(Color.BLACK);
                    rect.setLayoutX(j*45);
                    rect.setLayoutY(i*45);
                    board.getChildren().add(rect);
                }

            }
        }

        board.getChildren().addAll(myTank.hero);
        myTank.hero.relocate((player.getPosition().getY() )*45,(player.getPosition().getX())*45);
        System.out.println("Tanks position is " + player.getPosition().getY() + " " + player.getPosition().getX());
        Scene scene = new Scene(board, W, H, Color.BLACK);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            int spaceCount = 0;
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()){
                    case UP: myTank.hero.setRotate(0);
                        player.moveUp();
                        myTank.hero.relocate(player.getPosition().getY()*45,player.getPosition().getX()*45);
                        System.out.println(player.getPosition());
                        setTankVisible(map, player, myTank.hero);
                        isDirectionUp = true;
                        isDirectionDown = false;
                        isDirectionRight = false;
                        isDirectionLeft = false;
                        break;
                    case DOWN:
                        myTank.hero.setRotate(180);
                        player.moveDown();
                        myTank.hero.relocate(player.getPosition().getY()*45 ,player.getPosition().getX()*45);
                        System.out.println(player.getPosition());
                        setTankVisible(map, player, myTank.hero);
                        isDirectionUp = false;
                        isDirectionDown = true;
                        isDirectionRight = false;
                        isDirectionLeft = false;
                        break;
                    case LEFT:
                        myTank.hero.setRotate(-90);
                        player.moveLeft();
                        myTank.hero.relocate(player.getPosition().getY()*45 ,player.getPosition().getX()*45);
                        System.out.println(player.getPosition());
                        setTankVisible(map, player, myTank.hero);
                        isDirectionUp = false;
                        isDirectionDown = false;
                        isDirectionRight = false;
                        isDirectionLeft = true;
                        break;
                    case RIGHT:
                        myTank.hero.setRotate(90);
                        player.moveRight();
                        myTank.hero.relocate(player.getPosition().getY()*45 ,player.getPosition().getX()*45);
                        System.out.println(player.getPosition());
                        setTankVisible(map, player, myTank.hero);
                        isDirectionUp = false;
                        isDirectionDown = false;
                        isDirectionRight = true;
                        isDirectionLeft = false;
                        break;
                    case SPACE:
                        board.getChildren().add(myBullet.callBullet());
                        spaceCount++;
                        System.out.println(spaceCount);
                        throwing=true;
                        setSpaceCount(spaceCount);
                        checkHitBrickWall(myBullet.getBullets(), spaceCount, map);
                        break;
                }
            }

        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){

                    case SPACE: throwing = false; break;

                }
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                myBullet.fireBullet(returnDirection());
                checkHitBrickWall(myBullet.getBullets(), spaceCount, map);
            }
        };
        timer.start();
    }

    private char returnDirection() {
        if(isDirectionDown)
            return 'D';
        else if(isDirectionLeft)
            return 'L';
        else if(isDirectionRight)
            return 'R';
        else if(isDirectionUp)
            return 'U';

        return '0';
    }


    public void checkHitBrickWall(ArrayList<Node> bullets, int spaceCount, Map map){

        if(spaceCount % 4 == 0){
            for(int i = 0; i < bullets.size(); i++){
                for(int j = 0; j < brickWalls.size(); j++){
                    if(bullets.get(i).getBoundsInParent().intersects(brickWalls.get(j).getBoundsInParent())){
                        System.out.println();
                        if(map.getValueAt(brickWallsPosition.get(j).getX(), brickWallsPosition.get(j).getY()) == 'B'){
                            map.setValueAt(brickWallsPosition.get(j).getX(), brickWallsPosition.get(j).getY(), 'E');
                        }

                        System.out.println("after setting");
                        System.out.println(map.getValueAt(brickWallsPosition.get(j).getX(), brickWallsPosition.get(j).getY()));
                        board.getChildren().remove(brickWalls.get(j));
                        brickWalls.remove(j);

                        board.getChildren().remove(bullets.get(i));
                        bullets.remove(i);

                    }
                }
            }
        }

    }

    public void setTankVisible(Map map, MyPlayer player, Node hero){
        if(map.getValueAt(player.position.getX(), player.position.getY()) == 'T'){
            hero.setVisible(false);
        }
        else{
            hero.setVisible(true);
        }
    }

    public void setSpaceCount(int spaceCount){
        this.spaceCount = spaceCount;
    }

}
