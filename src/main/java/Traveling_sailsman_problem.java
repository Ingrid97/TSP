import graph.structures.Node;

import java.util.ArrayList;

public class Traveling_sailsman_problem {
    public static void main(String[] args) {

        //TSP
        System.out.println("Traveling Sailsman problem:");

        //make an MST
        MST mst = new MST();

        //find all odd edges from tsp
        ArrayList<Node> odd_nodes = mst.odds();

        //find perfect matching from odd edges

        //find a connected multigraph

        //make an Eulerian circuit

        //make Hamiltonian circuit


    }
}
