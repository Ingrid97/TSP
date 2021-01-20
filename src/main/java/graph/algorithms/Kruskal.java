package graph.algorithms;



import graph.structures.Edge;
import graph.structures.Node;
import graph.structures.Point;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private ArrayList<Node> nodes;
    private ArrayList<Edge> all_edges;
    private ArrayList<Edge> edges;
    private UF qf;

    public Kruskal(ArrayList<Node> n){
        this.nodes = n;
        this.all_edges = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.qf = new UF(n.size());
        make_graph();
    }

    public ArrayList<Node> getNodes(){
        return nodes;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public void make_graph() {

        //sort all edges with vertices
        //for all edges, find the shortest edge and add to list
        //continue til all vertices are covered


        //make all edges
        make_all_edges();

        //sort all edges
        Collections.sort(all_edges);

        //print edges
        //print_edges();

        //make kruskal

        //for all edges
        int number_of_edges = 0;
        int i = 0;
        while((number_of_edges < nodes.size()-1) && (i < all_edges.size()) ){
            //if there are more than nodes.size()-1 edges, or you have gone through all the edges, break

            //check if edge i is added
            Node node_1 = all_edges.get(i).getN1();
            Node node_2 = all_edges.get(i).getN2();
            //System.out.println("i: " + i + ", edges.size(): " + edges.size());
            //System.out.println("edge 1: " + node_1.getNr() + ", edge 2: " + node_2.getNr());
            //System.out.println("connected?: " + qf.connected(node_1.getNr(), node_2.getNr()));
            if (!qf.connected(node_1.getNr(), node_2.getNr())){
                //add the edge
                node_1.addEdge(node_2);
                node_2.addEdge(node_1);
                number_of_edges++;
                System.out.println("edge: " + node_1.getNr() + " - " + node_2.getNr());
                edges.add(new Edge(node_1, node_2));

                //connect the nodes
                qf.connect(node_1.getNr(), node_2.getNr());
            }

            //next node
            i++;

        }
    }

    private void print_edges() {
        for (Edge e : this.edges) {
            System.out.println(e);
        }
    }

    public void print_Graph(){
        System.out.println("QF:");
        System.out.println(qf);
        System.out.println();

        System.out.println("Nodes:");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(nodes.get(i).getNr() + ": ");
            for (Node n : nodes.get(i).get_edges()) {
                System.out.print(n.getNr() + ", ");
            }
            System.out.println();
        }
    }

    private void make_all_edges(){
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i+1; j < nodes.size(); j++) {
                Edge e = new Edge(nodes.get(i), nodes.get(j));
                this.all_edges.add(e);
            }
        }
    }

    public static void print_points(ArrayList<Point> p){
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).getX() + " : " + p.get(i).getY());
        }
    }
}
