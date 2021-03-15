//THIS WORKS
package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;
import graph.structures.dup_finder;

import java.util.ArrayList;

public class Hamiltonian_algorithm_2_2 {
    private Eulerian_circuit eu;
    private ArrayList<Node> nodes;
    public ArrayList<Edge> HS;
    private ArrayList<dup_finder> path;


    public Hamiltonian_algorithm_2_2(Eulerian_circuit e, ArrayList<Node> n) {
        this.eu = e;
        this.nodes = n;
        this.HS = new ArrayList<>();
        this.path = new ArrayList<>();

        add_node_count();
        make_hamiltonian();
        print_path();
        make_HS();
        print_path();
    }

    private void add_node_count() {
        //this.node_count = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            path.add(new dup_finder(i));
        }

        //for the wrap around (first and last node)
        add_to_list(0, eu.get_path().size() - 1,1 );
        add_to_list(eu.get_path().size()-1, eu.get_path().size() - 2,0);


        //for the rest
        for (int i = 1; i < eu.get_path().size() - 1; i++) {
            add_to_list(i, i-1, i+1);
        }
    }

    private void add_to_list(int i, int n, int m){
        int previus = eu.get_path().get(n).getNr();
        int next = eu.get_path().get(m).getNr();
        path.get(eu.get_path().get(i).getNr()).add_intersection(previus, next);
    }

    private void print_path(){
        System.out.println("...");
        for (graph.structures.dup_finder df : path) {
            System.out.println(df);
        }
        System.out.println("...");
    }

    private void make_hamiltonian() {

        for (int j = 0; j < eu.get_path().size(); j++) {
            int i = eu.get_path().get(j).getNr();
            if (path.get(i).getCounter() > 1) {
                System.out.println("removing from (" + i + ")..." );
                
                int best_to_keep = findBestNode(i);
                //reove all exept the best one
                int count = path.get(i).getCounter();
                for (int k = 0; k < count; k++) {
                    if(k != best_to_keep){
                        int previus = path.get(i).getFrom(k);
                        int next = path.get(i).getTo(k);
                        path.get(i).remove_intersection(previus, next);

                        path.get(previus).change_intersection_to(next, i);
                        path.get(next).change_intersection_from(previus, i);
                    }
                    System.out.println();
                }
            }
        }
    }

    private int findBestNode(int n){
        double best = Integer.MAX_VALUE;
        int keep = -1;
        for (int i = 0; i < path.get(n).getIntersections().size(); i++) {
            //keep n on i-th node
            int previus = path.get(n).getFrom(i);
            int next = path.get(n).getTo(i);
            double dist_i = nodes.get(previus).p.distance(nodes.get(n).p) + nodes.get(n).p.distance(nodes.get(next).p);

            //the rest not via n
            for (int j = 0; j < path.get(n).getIntersections().size(); j++) {
                if(j != i)
                    dist_i += nodes.get(path.get(n).getFrom(j)).p.distance(nodes.get(path.get(n).getTo(j)).p);
            }

            //check if it is better than curr best
            if(dist_i <= best){
                best = dist_i;
                keep = i;
            }
        }
        
        return keep;
    }

    private void make_HS() {
        //int curr = 0;
        for (int i = 0; i < path.size(); i++) {
            //System.out.println("curr: " + curr);
            HS.add(new Edge(nodes.get(i), nodes.get(path.get(i).getTo(0))));
            //curr = node_count.get(curr).get(2);
        }
    }
}


