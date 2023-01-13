package homework03;

import java.awt.Point;

public class Grid {

    private Point position; 
    private boolean isAlive;

    //constructors

    public Grid() {
        this.position = new Point(0 ,0);
        this.isAlive = false;
    }

    public Grid(Point position, boolean isAlive) {
        this.position = position;
        this.isAlive = isAlive;
    }

    //methods
    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position = new Point (x ,y);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public String toString() {
        return "Grid [isAlive=" + isAlive + ", position=" + position + "]";
    }
   
}
