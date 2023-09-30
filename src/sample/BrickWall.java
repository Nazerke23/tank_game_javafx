package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BrickWall {

    public Image brickWall;
    public ImageView brickWallView;

    public BrickWall(){
        brickWall = new Image("images/brick-wall.png");
        brickWallView = new ImageView(brickWall);
        brickWallView.setFitHeight(45);
        brickWallView.setFitWidth(45);

    }

    public void callBrickWall(int i, int j){
        brickWallView.setLayoutX(j*45);
        brickWallView.setLayoutY(i*45);
    }

    public Position getBrickWallViewPosition() {
        return new Position((int)this.brickWallView.getLayoutY()/45, (int)this.brickWallView.getLayoutX()/45);
    }

    public ImageView getBrickWallView() {
        return brickWallView;
    }
}
