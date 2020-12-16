import graph.structures.Node;

import java.util.ArrayList;

public class Traveling_sailsman_problem {
    public static void main(String[] args) {

        //TSP
        System.out.println("Traveling Sailsman problem:");

        String filename:
        Graph g = new Graph(filename);
        
        //make an MST
        MST mst = new MST(g);

        //find all odd edges from tsp
        ArrayList<Node> odd_nodes = mst.odds();
        Graph subgraph = g.getSubgraph(odd_nodes);

        //find perfect matching from odd edges
        Blossom matching = new Blossom(subgraph);

        //find a connected multigraph

        //make an Eulerian circuit

        //make Hamiltonian circuit


    }
}
