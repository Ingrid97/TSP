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
        //print_Graph();
        this.all_edges = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.qf = new UF(n.size());
        make_graph();
        //print_Graph();
    }

    public ArrayList<Node> getNodes(){
        return nodes;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public void make_graph() {

        //make all edges
        make_all_edges();
        Collections.sort(all_edges);

        int number_of_edges = 0;
        int i = 0;
        while((number_of_edges < nodes.size()-1) && (i < all_edges.size()) ){

            Node node_1 = all_edges.get(i).getN1();
            Node node_2 = all_edges.get(i).getN2();
            if (!qf.connected(node_1.getNr(), node_2.getNr())){
                node_1.addEdge(node_2);
                node_2.addEdge(node_1);
                number_of_edges++;

                edges.add(new Edge(node_1, node_2));
                qf.connect(node_1.getNr(), node_2.getNr());
            }
            i++;
        }
    }

    public void printGraph(){
        System.out.println("Nodes:..............");
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
}
