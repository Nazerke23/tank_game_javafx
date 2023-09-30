package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TreeWall {
    private static final String TREE_WALL = "images/grass.png";
    public Image treeWall;
    public ImageView treeWallView;

    public TreeWall(){
        treeWall = new Image(TREE_WALL);
        treeWallView = new ImageView(treeWall);
        treeWallView.setFitHeight(45);
        treeWallView.setFitWidth(45);
    }

    public void callTreeWall(int i, int j){
        treeWallView.setLayoutX(j*45);
        treeWallView.setLayoutY(i*45);
    }

    public ImageView getTreeWallView() {
        return treeWallView;
    }
}
