package graph.structures;

public class Edge implements Comparable{
    private Node n1;
    private Node n2;

    public Edge(Node n1, Node n2){
        this.n1 = n1;
        this.n2 = n2;
    }

    public Node getN1(){
        return this.n1;
    }

    public Node getN2(){
        return this.n2;
    }

    @Override
    public String toString() {
        return n1 + " -> " + n2;
    }

    @Override
    public int compareTo(Object o) {
        Edge p = (Edge) o;
        double thisDist = this.n1.p.distance(n2.p);
        double pDist = p.n1.p.distance(p.n2.p);
        if (thisDist > pDist) return 1;
        else return -1;
    }
}
