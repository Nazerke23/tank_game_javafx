package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WaterWall {
    private static final String WATER_WALL = "images/water.png";
    public Image waterWall;
    public ImageView waterWallView;

    public WaterWall(){
        waterWall = new Image(WATER_WALL);
        waterWallView = new ImageView(waterWall);
        waterWallView.setFitHeight(45);
        waterWallView.setFitWidth(45);
    }

    public void callWaterWall(int i, int j){
        waterWallView.setLayoutX(j*45);
        waterWallView.setLayoutY(i*45);
    }

    public ImageView getWaterWallView() {
        return waterWallView;
    }
}
