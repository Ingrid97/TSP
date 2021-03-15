package graph;
import graph.algorithms.*;
import graph.structures.Edge;
import graph.structures.Hamiltonian_algorithm_2;
import graph.structures.Node;
import graph.structures.Point;
import helpers.My_File;

import java.io.File;
import java.util.ArrayList;

// NOT IN USE
public class Graph {
    private ArrayList<Point> points;
    private ArrayList<Node> nodes;
    private Kruskal kruskal;
    private ArrayList<Edge> edges;
    private String filename;
    private ArrayList<Node> odd_nodes;
    private Perfect_matching pm;
    private Crerry_Blossom_algo cb_algo;
    private ArrayList<Edge> cb_pm;
    private ArrayList<Edge> connected_multigraph;
    private Eulerian_circuit ec;

    //still not quite right
    private Hamiltonian_algorithm hm;
    private Hamiltonian_algorithm_2 hm2;
    private Hamiltonian_algorithm_2_2 hm2_2;
    public int hm2_2_len;
    private Hamiltonian_algorithm_random hm_r;
    public int hm_r_len;


    public Graph(String f){
        this.points = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.odd_nodes = new ArrayList<>();
        this.filename = f;

        get_points();
        make_nodes();

    }

    public void make_mst_kruskal(){
        this.kruskal = new Kruskal(this.nodes);
        System.out.println(kruskal.getEdges().size());
        this.edges = this.kruskal.getEdges();
    }

    public void make_mst_prim(){
        //TODO: do
    }

    public void get_points(){
        //finding the file...

        //TODO: try to figure out of this...
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(this.filename).getFile());
        //System.out.println(file.getAbsolutePath());

        //TODO: check if this works on other machines (other than mac)
        String file_name = "./src/main/resources/" + this.filename;
        //System.out.println("got the file!");
        this.points = My_File.read_from_file(file_name);
    }

    private void make_nodes(){
        ArrayList<Node> n = new ArrayList<>();
        for (int i = 0; i < this.points.size(); i++) {
            n.add(new Node(this.points.get(i), i));
        }
        this.nodes = n;
    }

    public void odds(){
        //TODO: ja... det e duplikat kode for eg FIKSER PÅ TING FOR FAEN!
        this.odd_nodes = new ArrayList<>();
        for (int i = 0; i < this.nodes.size(); i++) {
            if (this.nodes.get(i).get_edges().size()%2 == 1){
                this.odd_nodes.add(this.nodes.get(i));
                System.out.println(this.nodes.get(i).getNr());
            }
        }
    }

    public void perfect_matching(){
        //TODO: make perfect matching
        pm = new Perfect_matching(odd_nodes);


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

        Blossoming_algorithm ba = new Blossoming_algorithm(odd_nodes);

    }

    private float[][] get_edge_list(ArrayList<Node> odd_nodes){
        float[][] od = new float [2][odd_nodes.size()];

        for (int i = 0; i < odd_nodes.size(); i++) {
            od[0][i] = (float) odd_nodes.get(i).p.getX();
            od[1][i] = (float) odd_nodes.get(i).p.getY();
        }
        return od;
    }

    public void make_connected_multigraph(){
        //TODO: make connected multigraph

        //testing to find structure
        connected_multigraph = new ArrayList<>();

        //add normal edges
        connected_multigraph.addAll(edges);
        connected_multigraph.addAll(pm.getPerfect_matching());
        printedges(connected_multigraph);
    }

    public void make_eulerian_circuit(){
        //TODO: make eulerian circuit

        this.ec = new Eulerian_circuit(nodes, pm);

        /*
        1. check if graph is eulerian circuit
        2. make a path from node1, through all nodes, and back to node1
        3. print the path somehow? Jframe
        */

    }

    public void make_Hamiltonian_citcut(){
        //TODO: make hamiltonian circuit
        //nope
        //this.hm = new Hamiltonian_algorithm(ec, nodes);

        //works but is the easiest, no math
        this.hm_r = new Hamiltonian_algorithm_random(ec, nodes);
        this.hm_r_len = calculate_length(hm_r.HS);


        //do not work, in progress
        this.hm2_2 = new Hamiltonian_algorithm_2_2(ec, nodes);
        this.hm2_2_len = calculate_length(hm2_2.HS);

        //do not work
        //this.hm2 = new Hamiltonian_algorithm_2(ec, nodes);



    }

    /**
     * Extra
     */

    private void print_pm_holder(int[] pm){
        System.out.println("perfect matching");
        for (int i = 0; i < pm.length; i++) {
            System.out.println(i + " : " + pm[i]);
        }
    }

    private int calculate_length(ArrayList<Edge> path){
        int len = 0;
        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i));
            len += path.get(i).getN1().p.distance(path.get(i).getN2().p);
        }
        return len;
    }

    private void printedges(ArrayList<Edge> e){
        for (int i = 0; i < e.size(); i++) {
            System.out.println("edge: " + e.get(i).getN1().getNr() + " - " + e.get(i).getN2().getNr());
        }
    }

    private void print_points(ArrayList<Point> p){
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).getX() + " : " + p.get(i).getY());
        }
    }

    public void show_graph_MST(){
        Graph_print p = new Graph_print(nodes, edges, "", 0);
    }

    public void show_graph_PM(){
        Graph_print p = new Graph_print(nodes, pm.getPerfect_matching(), "", 0);
    }

    public void show_graph_EC(){
        ArrayList<Edge> edges_print = new ArrayList<>();
        for (int i = 0; i < ec.get_path().size()-1; i++) {
            edges_print.add(new Edge(ec.get_path().get(i), ec.get_path().get(i+1)));
        }
        edges_print.add(new Edge(ec.get_path().get(ec.get_path().size()-1), ec.get_path().get(0)));

        int len_graph = calculate_length(edges_print);
        Graph_print p = new Graph_print(nodes, edges_print, "graph", len_graph);
    }

    public void show_graph_EC_testing(){
        ArrayList<Edge> edges_print = new ArrayList<>();
        edges_print.add(new Edge(ec.get_path().get(0), ec.get_path().get(1)));
        edges_print.add(new Edge(ec.get_path().get(1), ec.get_path().get(2)));
        edges_print.add(new Edge(ec.get_path().get(2), ec.get_path().get(3)));

        edges_print.add(new Edge(ec.get_path().get(ec.get_path().size()-3), ec.get_path().get(ec.get_path().size()-2)));
        edges_print.add(new Edge(ec.get_path().get(ec.get_path().size()-2), ec.get_path().get(ec.get_path().size()-1)));
        edges_print.add(new Edge(ec.get_path().get(ec.get_path().size()-1), ec.get_path().get(0)));
        Graph_print p = new Graph_print(nodes, edges_print, "", 0);
    }

    public void show_graph_HC(){
        //for(Edge e : hm2_2.HS)
            //System.out.println(e);

        Graph_print p2 = new Graph_print(nodes, hm2_2.HS, "math", hm2_2_len);
        Graph_print p = new Graph_print(nodes, hm_r.HS, "normal", hm_r_len);
    }

    /*
    public void show_graph_HC(){
        ArrayList<Edge> edges_print = new ArrayList<>();
        for (int i = 1; i < hm.get_eulerian_circuit().get_path().size() ; i++) {
            edges_print.add(new Edge(hm.get_eulerian_circuit().get_path().get(i-1), hm.get_eulerian_circuit().get_path().get(i)));
        }
        Graph_print p = new Graph_print(nodes, edges_print);
    }

     */
}
