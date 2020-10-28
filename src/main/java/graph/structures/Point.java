package graph.structures;

/**
 * class for points
 */
public class Point implements Comparable {
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    /**
     * Find the distance between two points
     * @param p
     * @return distance
     */
    public double distance(Point p){
        return Math.abs(Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y-p.y,2)));
    }


    public int compareTo(Object o) {
        Point p = (Point) o;
        if (this.x < p.x) return -1;
        if (this.x == p.x && (this.y < p.y)) return -1;
        else return 1;
    }

    @Override
    public String toString(){
        return "(" + this.x +")("+ this.y + ")";
    }
}