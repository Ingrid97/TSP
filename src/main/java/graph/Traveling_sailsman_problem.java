package graph;
import graph.algorithms.*;
import graph.structures.Edge;
import graph.structures.Graph;
import graph.structures.Node;
import graph.structures.Point;
import helpers.My_File;

import java.io.File;
import java.util.ArrayList;

public class Traveling_sailsman_problem {
    private Graph g;
    private String filename;

    public Traveling_sailsman_problem(String f){
        this.filename = f;
        this.g = getGraph();
    }

    public Graph getGraph(){
        ArrayList<Point> p = get_points();
        ArrayList<Node> n = make_nodes(p);
        return new Graph(n);

    }

    public ArrayList<Point> get_points(){
        //finding the file...

        //TODO: try to figure out of this...
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(this.filename).getFile());

        //TODO: check if this works on other machines (other than mac)
        String file_name = "./src/main/resources/" + this.filename;
        return My_File.read_from_file(file_name);

    }

    private  ArrayList<Node> make_nodes(ArrayList<Point> p){
        ArrayList<Node> n = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            n.add(new Node(p.get(i), i));
        }
        return n;
    }

    public Kruskal make_mst_kruskal(){

        Kruskal kruskal = new Kruskal(this.g.getNodes());
        return kruskal;
    }

    public void make_mst_prim(){
        //TODO: do
    }

    public ArrayList<Node> get_odd_nodes(Kruskal k){
        //TODO: ja... det e duplikat kode for eg FIKSER PÅ TING FOR FAEN!
        ArrayList<Node> odd_nodes = new ArrayList<>();
        for (int i = 0; i < k.getNodes().size(); i++) {
            if (k.getNodes().get(i).get_edges().size()%2 == 1){
                odd_nodes.add(k.getNodes().get(i));
                System.out.println(k.getNodes().get(i).getNr());
            }
        }
        return odd_nodes;
    }

    public Perfect_matching perfect_matching(ArrayList<Node> odd_nodes){
        //TODO: make perfect matching
        Perfect_matching pm = new Perfect_matching(odd_nodes);


        //use the simlu algorithms (what is maxcardinality???)
        //maxcardinality = true, not always a perfect matching

        //not up to date yet
        /*
        float[][] od = get_edge_list(odd_nodes);
        cb_algo = new Crerry_Blossom_algo(od, false);
        int[] pm_holder = cb_algo.maxWeightMatching();
        print_pm_holder(pm_holder);
        */

        /*
        //convert result
        for (int i = 0; i < pm_holder.length; i++) {
            Edge e = new Edge(new Node(), new Node());
            this.cb_pm =
        }

         */

        //Blossoming_algorithm ba = new Blossoming_algorithm(odd_nodes);
        return pm;
    }

    public Eulerian_circuit make_eulerian_circuit(Perfect_matching pm){
        Eulerian_circuit ec = new Eulerian_circuit(this.g.getNodes(), pm);
        return ec;
    }

    public Hamiltonian_algorithm_random make_Hamiltonian_citcut(Eulerian_circuit ec){
        Hamiltonian_algorithm_random hm_r = new Hamiltonian_algorithm_random(ec, this.g.getNodes());
        return hm_r;
    }

    public Hamiltonian_algorithm_2_2 make_Hamiltonian_citcut_math(Eulerian_circuit ec){

        Hamiltonian_algorithm_2_2 hm2_2 = new Hamiltonian_algorithm_2_2(ec, this.g.getNodes());
        return hm2_2;
    }

    /**
     * Extra
     */

    private int calculate_length(ArrayList<Edge> path){
        int len = 0;
        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i));
            len += path.get(i).getN1().p.distance(path.get(i).getN2().p);
        }
        return len;
    }

    public void show_graph_EC(Eulerian_circuit ec){
        ArrayList<Edge> edges_print = new ArrayList<>();
        for (int i = 0; i < ec.get_path().size()-1; i++) {
            edges_print.add(new Edge(ec.get_path().get(i), ec.get_path().get(i+1)));
        }
        edges_print.add(new Edge(ec.get_path().get(ec.get_path().size()-1), ec.get_path().get(0)));

        int len_graph = calculate_length(edges_print);
        Traveling_sailsman_problem_print p = new Traveling_sailsman_problem_print(this.g.getNodes(), edges_print, "graph", len_graph);
    }

    public void show_graph_HC(Hamiltonian_algorithm_2_2 hm2_2, Hamiltonian_algorithm_random hm_r){
        int hm_r_len = calculate_length(hm_r.HS);
        int hm2_2_len = calculate_length(hm2_2.HS);
        Traveling_sailsman_problem_print p2 = new Traveling_sailsman_problem_print(this.g.getNodes(), hm2_2.HS, "math", hm2_2_len);
        Traveling_sailsman_problem_print p = new Traveling_sailsman_problem_print(this.g.getNodes(), hm_r.HS, "normal", hm_r_len);
    }
}
