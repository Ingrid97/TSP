package graph;
import graph.algorithms.Crerry_Blossom_algo;
import graph.algorithms.Eulerian_circuit;
import graph.algorithms.Kruskal;
import graph.algorithms.Perfect_matching;
import graph.structures.Edge;
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
    private Crerry_Blossom_algo cbm;
    private ArrayList<Edge> connected_multigraph;


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

        //TODO: fix... just fix
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
        //cbm = new Crerry_Blossom_algo();


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

        Eulerian_circuit ec = new Eulerian_circuit(nodes, pm);
        /*
        1. check if graph is eulerian circuit
        2. make a path from node1, through all nodes, and back to node1
        3. print the path somehow? Jframe
        */

    }

    public void make_Hamiltonian_citcut(){
        //TODO: make hamiltonian circuit

        /*
        1. remove all duplicates of the same node in the path/circuit
        2.
        */

    }

    /**
     * Helpers
     */

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

    public void show_graph(){
        Graph_print p = new Graph_print(nodes, edges, pm.getPerfect_matching());
    }
}
