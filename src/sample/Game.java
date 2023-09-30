package sample;

public class Game {
    Map map;
    Player player;

    public Game(Map map){
        this.map = map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void addPlayer(Player player){

        this.player = player;
        this.player.setMap(map);
    }
}