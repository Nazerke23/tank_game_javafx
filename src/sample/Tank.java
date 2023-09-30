package sample;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tank extends MyPlayer {
    private static final String HERO_IMAGE_LOC = "images/yellow-tank-up.gif";
    private static double W, H;

    private MyPlayer player;
    private Image heroImage;
    public Node hero;

    public Tank(MyPlayer player, Map map) {
        heroImage = new Image(HERO_IMAGE_LOC);
        hero = new ImageView(heroImage);
        this.player = player;
        this.W = map.getSize()*45;
        this.H = map.getSize()*45;
    }
}

