package graph.algorithms;
import graph.structures.Edge;
import graph.structures.Node;

import java.util.ArrayList;

public class Perfect_matching {

    private ArrayList<Edge> perfect_matching;
    private ArrayList<Node> odd_nodes;

    public Perfect_matching(ArrayList<Node> odd_nodes){
        this.perfect_matching = new ArrayList<>();
        this.odd_nodes = odd_nodes;
        make_perfet_matching();
    }

    public void make_perfet_matching(){
        //testing
        for (int i = 0; i < this.odd_nodes.size(); i+= 2) {
            this.perfect_matching.add(new Edge(this.odd_nodes.get(i), this.odd_nodes.get(i+1)));
            System.out.println("edge: " + this.odd_nodes.get(i).getNr() + " - " + this.odd_nodes.get(i+1).getNr());
        }
    }

    public ArrayList<Edge> getPerfect_matching(){
        return this.perfect_matching;
    }
}
