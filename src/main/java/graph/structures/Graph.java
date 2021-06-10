package graph.structures;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph(ArrayList<Node> n){
        this.nodes = n;
        this.edges = makeEdges(nodes);
    }

    public ArrayList<Edge> makeEdges(ArrayList<Node> nodes){
        ArrayList<Edge> edge_list = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i+1; j < nodes.size(); j++) {
                Edge e = new Edge(nodes.get(i), nodes.get(j));
                edge_list.add(e);
            }
        }
        return edge_list;
    }

    public ArrayList<Node> getNodes() {
        return this.nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}



