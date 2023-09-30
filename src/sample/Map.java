package sample;

import java.util.Arrays;
import java.util.Scanner;

public class Map {
    char[][] array;
    int size;

    public Map(Scanner input) throws InvalidMapException {
        size = input.nextInt();

        if(size == 0)
            throw new InvalidMapException("Map size can not be zero");

        array = new char[size][size];


        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                array[i][j] = input.next().charAt(0);
                char current = array[i][j];

                if(!validMap(current))
                    throw new InvalidMapException("Not enough map elements");
            }
        }
    }


    public int getSize() {
        return size;
    }

    public boolean validMap(char c){
        switch (c){
            case 'B':
            case 'W':
            case 'T':
            case 'S':
            case 'E':
            case 'P': return true;
            default: return false;
        }
    }

    public char getValueAt(int x, int y){
        return array[x][y];
    }

    public void setValueAt(int x, int y, char value){
        array[x][y] = value;
    }


    public void print(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Map{" +
                "size=" + size +
                ", array=" + Arrays.toString(array) +
                '}';
    }

}