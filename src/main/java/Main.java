import graph.Traveling_sailsman_problem;
import graph.algorithms.*;
import graph.structures.Node;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //TSP
        System.out.println("Traveling Sailsman problem:");

        String filename1 = "test_points_2.txt";
        String filename2 = "points.txt";
        String filename3 = "points_2.txt";


        System.out.println("Make the graph");
        Traveling_sailsman_problem tsp = new Traveling_sailsman_problem(filename2);

        System.out.println("\nMake the kruskal");
        Kruskal kruskal = tsp.make_mst_kruskal();

        System.out.println("\nFind the odd nodes");
        ArrayList<Node> odd_nodes = tsp.get_odd_nodes(kruskal);

        System.out.println("\nFind a perfect matching");
        Perfect_matching pm = tsp.perfect_matching(odd_nodes);

        System.out.println("\nMake eulerian circuit");
        Eulerian_circuit ec = tsp.make_eulerian_circuit(pm);
        tsp.show_graph_EC(ec);

        System.out.println("\nMake hamiltonian circuit");
        Hamiltonian_algorithm_random hmr = tsp.make_Hamiltonian_citcut(ec);
        Hamiltonian_algorithm_2_2 hm = tsp.make_Hamiltonian_citcut_math(ec);

        tsp.show_graph_HC(hm, hmr);

    }
}
