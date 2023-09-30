package sample;

public class MyPlayer implements Player {
    Position position = new Position(0,0);
    Map map;

    @Override
    public void setMap(Map map) {
        this.map = map;
        for(int i = 0; i < map.getSize(); i++){
            for(int j = 0; j < map.getSize(); j++){
                if(map.getValueAt(i,j) == 'P')
                    this.position = new Position(i,j);
            }
        }
    }

    @Override
    public void moveRight() {
        if(position.getY() < map.getSize()-1){
            if(map.getValueAt(position.getX(), position.getY()+1) == 'E' || map.getValueAt(position.getX(), position.getY()+1) == 'P' ||
            map.getValueAt(position.getX(), position.getY()+1) == 'T'){
                position.setY(position.getY()+1);
            }
        }
    }

    @Override
    public void moveLeft() {
        if(position.getY() > 0){
            if(map.getValueAt(position.getX(), position.getY()-1) == 'E' || map.getValueAt(position.getX(), position.getY()-1) == 'P' ||
                    map.getValueAt(position.getX(), position.getY()-1) == 'T' ){
                position.setY(position.getY()-1);
            }
        }
    }

    @Override
    public void moveDown() {
        if(position.getX() < map.getSize()-1){
            if(map.getValueAt(position.getX()+1, position.getY()) == 'E' || map.getValueAt(position.getX()+1, position.getY()) == 'P' ||
                    map.getValueAt(position.getX()+1, position.getY()) == 'T'){
                position.setX(position.getX()+1);
            }
        }

    }

    @Override
    public void moveUp() {
        if(position.getX() > 0){
            if(map.getValueAt(position.getX()-1, position.getY()) == 'E' || map.getValueAt(position.getX()-1, position.getY()) == 'P' ||
                    map.getValueAt(position.getX()-1, position.getY()) == 'T'){
                position.setX(position.getX()-1);
            }
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }
}