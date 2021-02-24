import graph.Graph;
import graph.structures.Node;

import java.util.ArrayList;

public class Traveling_sailsman_problem {
    public static void main(String[] args) {

        //TSP
        System.out.println("Traveling Sailsman problem:");

        String filename1 = "test_points_2.txt";
        String filename2 = "points.txt";


        //the graph whit all the points
        System.out.println("Make the graph");
        Graph g = new Graph(filename2);

        //make an MST to the graph
        System.out.println("\nMake the kruskal");
        g.make_mst_kruskal();
        //MST mst = new MST(filename1);
        //g.show_graph_MST();

        //find all odd edges from tsp
        System.out.println("\nFind the odd nodes");
        g.odds();
        //ArrayList<Node> odd_nodes = mst.odds();

        //find perfect matching from odd edges
        System.out.println("\nFind a perfect matching");
        g.perfect_matching();
        //g.show_graph_PM();

        //find a connected multigraph
        System.out.println("\nMake connected multigraph");
        g.make_connected_multigraph();

        //make an Eulerian circuit
        System.out.println("\nMake eulerian circuit");
        g.make_eulerian_circuit();
        g.show_graph_EC();


        //make an Hamiltonian circuit
        System.out.println("\nMake hamiltonian circuit");
        g.make_Hamiltonian_citcut();
        g.show_graph_EC();

        //print result
        //g.show_graph_HC();

    }
}
