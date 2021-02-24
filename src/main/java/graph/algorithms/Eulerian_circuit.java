package graph.algorithms;

import graph.structures.Node;
import java.util.ArrayList;
import java.util.Stack;

public class Eulerian_circuit {

    private ArrayList<Node> nodes;
    private Perfect_matching pm;
    private Stack<Node> working_stack;
    private Stack<Node> path;
    private ArrayList<Node> finale_path;

    public Eulerian_circuit(ArrayList<Node> n, Perfect_matching pm){
        this.nodes = n;
        this.pm = pm;

        add_pm_to_nodes();
        find_path_recursive();
    }

    private void add_pm_to_nodes(){
        for (int i = 0; i < pm.getPerfect_matching().size(); i++) {
            int n1 = pm.getPerfect_matching().get(i).getN1().getNr();
            int n2 = pm.getPerfect_matching().get(i).getN2().getNr();
            nodes.get(n1).addEdge(nodes.get(n2));
            nodes.get(n2).addEdge(nodes.get(n1));
        }
    }

    /**
       recursive
     */
    private void find_path_recursive(){
        /*
        start in one node (node 0), and find a path going through all edges, and back rto the start
        */
        this.working_stack = new Stack<>();
        this.path = new Stack<>();
        this.finale_path = new ArrayList<>();

        for (Node n : nodes) {
            n.make_visited_edges(nodes.size());
        }


        Node start = nodes.get(0);
        make_path_recursive(start);
        //finale_path.add(nodes.get(0));
        print_path();

    }

    private void make_path_recursive(Node curr){
        working_stack.push(curr);

        for (Node n : curr.get_edges()) {
            if (!curr.visited_EC[n.getNr()]){
                curr.add_visited_edge(n);
                n.add_visited_edge(curr);
                make_path_recursive(n);
            }
        }

        finale_path.add(working_stack.pop());

    }
     /**
        normal
        TODO: check if recursive or no-recursive is faster
      */
    private void find_path(){
        /*
        start in one node (node 0), and find a path going through all edges, and back rto the start
        */
        this.working_stack = new Stack<>();
        this.path = new Stack<>();

        Node start = nodes.get(0);
        //make_path(start);
    }

    private void make_path(Node curr){
        working_stack.push(curr);

        for (Node n : curr.get_edges()) {
            //make_path(n);
        }

        path.push(curr);
        working_stack.pop();

    }

    /**
     * extra
     */

    public ArrayList<Node> get_path(){
        return this.finale_path;
    }

    private void print_nodes(){
        int edges = 0;
        for (int i = 0; i < this.nodes.size(); i++) {
            edges += nodes.get(i).get_edges().size();
        }
        System.out.println("number of edges: " + (edges/2));
    }

    public void print_path(){
        System.out.println("length of path: " + finale_path.size());
        for (int i = 0; i < finale_path.size(); i++) {
            System.out.println("node " + i + ": " + finale_path.get(i).getNr());

        }
    }

}
