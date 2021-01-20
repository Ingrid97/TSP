import java.util.ArrayList;

import graph.algorithms.Kruskal;
import graph.algorithms.Prim;
import helpers.My_File;
import graph.structures.Node;
import graph.structures.Point;


public class MST {
    private Kruskal kruskal;
    private Prim prim;
    private String filename;

    public MST(String f){
        //kruskals algorithm
        this.filename = f;
        this.kruskal = make_kruskal();
        this.prim = make_prim();


    }

    public void make() {


        //testing Algorithm
        //TODO
        //tester();


        //Prims Algorithm, not in use
        //prim();


        //kruskals algorithm
        Kruskal kruskal = make_kruskal();

    }

    /*private ArrayList<Node> find_odds(ArrayList<Node> n){
        ArrayList<Node> odd_nodes = new ArrayList<>();
        for (int i = 0; i < n.size(); i++) {
            if (n.get(i).get_edges().size()%2 == 1){
                odd_nodes.add(n.get(i));
            }
        }

        //print list
        System.out.println("odd nodes");
        print_nodes(odd_nodes);

        return odd_nodes;
    }*/

    public ArrayList<Node> odds(){
        ArrayList<Node> odd_nodes = new ArrayList<>();
        ArrayList<Node> n = kruskal.getNodes();
        for (int i = 0; i < n.size(); i++) {
            if (n.get(i).get_edges().size()%2 == 1){
                odd_nodes.add(n.get(i));
            }
        }
        System.out.println("odd nodes");
        print_nodes(odd_nodes);

        return odd_nodes;
    }

    private void print_nodes(ArrayList<Node> nodes){
        for (Node n : nodes ) {
            System.out.print(n.getNr() + ": ");
            for (Node e : n.get_edges()) {
                System.out.print(e.getNr() + ", ");
            }
            System.out.println();
        }
    }

    private Kruskal make_kruskal() {
        System.out.println("locking for the file");
        //TODO: find where the error is with points.txt
        String filename = MST.class.getResource(this.filename).getPath();
        System.out.println("got the file");
        System.out.println(filename);
        ArrayList<Point> kruskal_points = My_File.read_from_file(filename);

        //make nodes
        ArrayList<Node> nodes = make_nodes(kruskal_points);

        //kruskals
        Kruskal kruskal = new Kruskal(nodes);

        //make graph
        kruskal.make_graph();

        //print result
        kruskal.print_Graph();

        //return
        return kruskal;
    }

    private Prim make_prim(){

        //read points from file
        String filename = MST.class.getResource("test_points_2.txt").getPath();
        System.out.println(filename);
        ArrayList<Point> prim_points = My_File.read_from_file(filename);

        //print for test
        print_points(prim_points);

        //make nodes
        ArrayList<Node> nodes = make_nodes(prim_points);

        //prim
        Prim prim = new Prim(nodes);

        //print result
        prim.print_Graph();

        //return
        return prim;
    }

    public Prim get_prim(){
        return this.prim;
    }

    public Kruskal get_kruskal(){
        return this.kruskal;
    }

    private void print_points(ArrayList<Point> p){
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).getX() + " : " + p.get(i).getY());
        }
    }

    //TODO cant be private due to tests?
    public static ArrayList<Node> make_nodes(ArrayList<Point> p){
        ArrayList<Node> n = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            n.add(new Node(p.get(i), i));
        }
        return n;
    }

    private void tester(){

        //read points from file
        String filename = MST.class.getResource("test_points_2.txt").getPath();;
        ArrayList<Point> prim_points = My_File.read_from_file(filename);

        //print for test
        print_points(prim_points);

        for (int i = 0; i < prim_points.size(); i++) {

            for (int j = 0; j < prim_points.size(); j++) {

            }
        }

    }
}
