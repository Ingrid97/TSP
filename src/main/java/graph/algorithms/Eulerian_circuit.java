package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;
import graph.algorithms.Perfect_matching;

import java.util.ArrayList;

public class Eulerian_circuit {

    private ArrayList<Node> nodes;
    private Perfect_matching pm;

    public Eulerian_circuit(ArrayList<Node> n, Perfect_matching pm){
        this.nodes = n;
        this.pm = pm;

        add_to_nodes();
        find_path();
    }

    private void add_to_nodes(){
        for (int i = 0; i < pm.getPerfect_matching().size(); i++) {
            int n1 = pm.getPerfect_matching().get(i).getN1().getNr();
            int n2 = pm.getPerfect_matching().get(i).getN2().getNr();
            System.out.println("nr 1: " + n1 + "  nr 2: " + n2);
            nodes.get(n1).addEdge(nodes.get(n2));
            nodes.get(n2).addEdge(nodes.get(n1));
        }
    }

    private void find_path(){
        /*
        start in one node (node 0), and find a path going through all edges, and back rto the start
        */
    }


}
