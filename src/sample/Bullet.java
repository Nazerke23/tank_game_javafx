package sample;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Bullet {
    private static  double W , H;
    private static final String BULLET_IMAGE = "images/explosion1.gif";

    private Image bulletImage;
    private ArrayList<Node> bullets = new ArrayList<Node>();
    private Tank tank;

    public Bullet(Tank tank, Map map){
        bulletImage = new Image(BULLET_IMAGE);
        this.tank = tank;
        H = map.getSize()*45;
        W = map.getSize()*45;

    }

    public Node callBullet(){
        ImageView aWeapon = new ImageView(bulletImage);
        aWeapon.setFitHeight(15);
        aWeapon.setFitWidth(15);
        Node newWeapon = aWeapon;
        newWeapon.relocate(tank.hero.getLayoutX()+tank.hero.getBoundsInLocal().getWidth()/2,
                tank.hero.getLayoutY() + tank.hero.getBoundsInLocal().getHeight()/2);
        bullets.add(newWeapon);

        return newWeapon;
    }

    public ArrayList<Node> getBullets() {
        return bullets;
    }

    public void fireBullet(char direction){
        int speedOfBullet = 20;
        int delta = speedOfBullet;

        if(direction == 'U'){
            //UP
            for (int i = 0; i < bullets.size(); i++){
                if(bullets.get(i).getLayoutY() >= -100){
                    bullets.get(i).relocate(bullets.get(i).getLayoutX(), bullets.get(i).getLayoutY()-delta);
                }
                else bullets.remove(i);
            }
        }
        else if(direction == 'D'){
            //DOWN
            for (int i = 0; i < bullets.size(); i++){
                if(bullets.get(i).getLayoutY() <= H+100){
                    bullets.get(i).relocate(bullets.get(i).getLayoutX(), bullets.get(i).getLayoutY()+delta);
                }
                else bullets.remove(i);
            }
        }
        else if(direction == 'R'){
            //RIGHT
            for (int i = 0; i < bullets.size(); i++){
                if(bullets.get(i).getLayoutX() < W+100){
                    bullets.get(i).relocate(bullets.get(i).getLayoutX()+delta, bullets.get(i).getLayoutY());
                }
                else bullets.remove(i);
            }
        }
        else if(direction == 'L'){
            //LEFT
            for (int i = 0; i < bullets.size(); i++){
                if(bullets.get(i).getLayoutX() > -100){
                    bullets.get(i).relocate(bullets.get(i).getLayoutX()-delta, bullets.get(i).getLayoutY());
                }
                else bullets.remove(i);
            }
        }
    }
}
