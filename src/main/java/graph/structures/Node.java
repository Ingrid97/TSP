package graph.structures;
import java.util.ArrayList;

public class Node {
    private ArrayList<Node> edges;
    public Point p;
    private double x;
    private double y;
    private final int nr;
    public boolean[] visited_EC;


    public Node(Point p, int i){
        this.edges = new ArrayList<>();
        this.p = p;
        this.x = p.getX();
        this.y = p.getY();
        this.nr = i;
    }

    public void addEdge(Node n){
        this.edges.add(n);
    }

    public ArrayList<Node> getEdges(){
        return edges;
    }

    public ArrayList<Node> get_edges(){
        return this.edges;
    }

    public int getNr(){
        return this.nr;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void makeVisitedEdges(int n){
        this.visited_EC = new boolean[n];
    }

    public void addVisitedEdge(Node n){
        this.visited_EC[n.getNr()] = true;
    }


    @Override
    public String toString() {
        return this.nr + ":" + this.p.toString();
    }

}
