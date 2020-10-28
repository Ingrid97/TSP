import java.util.ArrayList;

import graph.algorithms.Kruskal;
import graph.algorithms.Prim;
import helpers.My_File;
import graph.structures.Node;
import graph.structures.Point;


public class MST {
    public static void main(String[] args) {


        //testing Algorithm
        tester();


        //Prims Algorithm
        //prim();


        //kruskals algorithm
        Kruskal kruskal = make_kruskal();

        //find odd verticies
        ArrayList<Node> odds = find_odds(kruskal.getNodes());

        //find perfect matching
        Perfect_matching pm = new Perfect_matching(odds, kruskal);

    }

    private static ArrayList<Node> find_odds(ArrayList<Node> n){
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
    }

    private static void print_nodes(ArrayList<Node> nodes){
        for (Node n : nodes ) {
            System.out.print(n.getNr() + ": ");
            for (Node e : n.get_edges()) {
                System.out.print(e.getNr() + ", ");
            }
            System.out.println();
        }
    }

    private static Kruskal make_kruskal() {
        String filename = "target/classes/test_points_2.txt";
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

    /**
     * Main.graph.algorithms.Prim
     */
    private static void prim(){

        //read points from file
        String filename = "target/classes/points.txt";
        ArrayList<Point> prim_points = My_File.read_from_file(filename);

        //print for test
        print_points(prim_points);

        //make nodes
        ArrayList<Node> nodes = make_nodes(prim_points);

        //prim
        Prim prim = new Prim(nodes);

        //print result
        prim.print_Graph();
    }

    /**
     * print the points
     * @param p
     */
    public static void print_points(ArrayList<Point> p){
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).getX() + " : " + p.get(i).getY());
        }
    }

    /**
     * make nodes
     * @param p
     * @return list of nodes
     */
    public static ArrayList<Node> make_nodes(ArrayList<Point> p){
        ArrayList<Node> n = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            n.add(new Node(p.get(i), i));
        }
        return n;
    }

    public static void tester(){

        //read points from file
        String filename = "test_points_2.txt";
        ArrayList<Point> prim_points = My_File.read_from_file(filename);

        //print for test
        print_points(prim_points);

        for (int i = 0; i < prim_points.size(); i++) {

            for (int j = 0; j < prim_points.size(); j++) {

            }
        }

    }
}
