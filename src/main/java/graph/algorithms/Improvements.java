package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;

import java.util.ArrayList;

public class Improvements {
    private ArrayList<Edge> path;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> bestPath;

    public Improvements (ArrayList<Edge> edges, ArrayList<Node> n){
        this.nodes = n;
        this.bestPath = new ArrayList<>();
        this.path = new ArrayList<>();
        for (Edge e : edges) {
            path.add(e);
        }
    }

    public void makeBestPath(){
        RemovingCrossingEdges remove = new RemovingCrossingEdges(this.path, this.nodes);
        remove.remove();

        ChangePlacementOfNode change = new ChangePlacementOfNode(remove.getPath(), this.nodes);
        change.changePath();

        while(change.containsCrossingEdges()){
            remove = new RemovingCrossingEdges(change.path, this.nodes);
            remove.remove();

            change = new ChangePlacementOfNode(remove.path, this.nodes);
            change.changePath();
        }
        for (Edge e : change.getPath()) {
            bestPath.add(e);
        }

    }

    public ArrayList<Edge> getBestPath() {
        return bestPath;
    }
}
