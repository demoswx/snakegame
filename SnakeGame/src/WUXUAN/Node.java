package WUXUAN;


import java.util.Random;

public class Node {
    private int x,y;

    public Node( ) {
    }
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void random(){
        Random r=new Random();
        this.x=r.nextInt(40);
        this.y=r.nextInt(40);
    }

}