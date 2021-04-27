//THIS WORKS
package graph.algorithms;
import graph.structures.*;

import java.util.ArrayList;

public class Hamiltonian_algorithm_random implements Hamiltonian{
    private Eulerian_circuit eu;
    private ArrayList<Node> nodes;
    public ArrayList<Edge> HS;

    public ArrayList<dup_finder> path;


    public Hamiltonian_algorithm_random(Eulerian_circuit e, ArrayList<Node> n) {
        this.eu = e;
        this.nodes = n;
        this.HS = new ArrayList<>();
        this.path = new ArrayList<>();

        add_node_count();
        make_hamiltonian();
        print_path();
        make_printable_HS();
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

    public void make_hamiltonian() {

        for (int j = 0; j < eu.get_path().size(); j++) {
            int i = eu.get_path().get(j).getNr();
            if (path.get(i).getCounter() > 1) {
                System.out.println("removing from (" + i + ")..." );


                int previus = path.get(i).getFrom(1);
                int next = path.get(i).getTo(1);
                path.get(i).remove_intersection(previus, next);

                path.get(previus).change_intersection_to(next, i);
                path.get(next).change_intersection_from(previus, i);
            }
        }
    }


    public void make_printable_HS() {
        for (int i = 0; i < path.size(); i++) {
            HS.add(new Edge(nodes.get(i), nodes.get(path.get(i).getTo(0))));
        }
    }
}


