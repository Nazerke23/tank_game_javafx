package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SteelWall {
    private static final String STEEL_WALL = "images/steel-wall.png";
    public Image steelWall;
    public ImageView steelWallView;

    public SteelWall(){
        steelWall = new Image("images/steel-wall.png");
        steelWallView = new ImageView(steelWall);
        steelWallView.setFitWidth(40);
        steelWallView.setFitWidth(40);
    }

    public void callSteelWall(int i, int j){
        steelWallView.setLayoutX(j*40);
        steelWallView.setLayoutY(i*40);
    }

    public ImageView getSteelWallView() {
        return steelWallView;
    }
}
