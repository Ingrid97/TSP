package Extra;
import java.util.ArrayList;

public class Node {
    private ArrayList<Node> edges;
    public Point p;
    private double x;
    private double y;
    private int nr;

    public Node(Point p, int i){
        edges = new ArrayList<>();
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

    /**
     * find the closest node
     * @return
     */
    public Node find_closest_node(){
        Node closest = edges.get(0);
        for(Node n : edges){
            if (n.p.distance(this.p) < closest.p.distance(this.p)){
                closest = n;
            }
        }
        return closest;
    }

    @Override
    public String toString() {
        return p.toString();
    }

}
