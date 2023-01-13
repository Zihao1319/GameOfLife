package homework03;
import java.util.Objects;


public class Coordinate {
    
    // private Point position;  
    protected int x;
    protected int y;
    protected int hashCode;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.hashCode = Objects.hash(x, y);
        // this.position = new Point(x ,y);
    }

    public int getX(int x) {
        return this.x;
    }

    public int getY() {
        return y;
    }


    @Override
    public String toString() {
        return "Coordinate [x=" + x + ", y=" + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
   
}


