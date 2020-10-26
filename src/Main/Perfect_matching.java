package Main;

import Extra.Edge;
import Extra.Node;

import java.util.ArrayList;
import java.util.Collections;

public class Perfect_matching {
    private ArrayList<Node> odd_nodes;
    private Kruskal kruskal;
    private ArrayList<Edge> edges;
    private ArrayList<Edge> mathing;
    private boolean[] connected;
    private boolean[] odds;

    public Perfect_matching(ArrayList<Node> n, Kruskal k){
        this.odd_nodes = n;
        this.kruskal = k;
        this.edges = new ArrayList<>();
        this.mathing = new ArrayList<>();
        this.connected = new boolean[this.kruskal.getNodes().size()];
        this.odds = new boolean[this.kruskal.getNodes().size()];
        add_odds();
        make_edges();
        minimal_perfect_match_bruteforse();
        print_edges();
    }

    private void make_edges(){
        for (int i = 0; i < kruskal.getNodes().size(); i++) {
            Node curr = kruskal.getNodes().get(i);
            for (int j = 0; j < curr.get_edges().size(); j++) {
                if(odds[curr.get_edges().get(j).getNr()]){
                    Edge e = new Edge(curr, curr.get_edges().get(j));
                    edges.add(e);
                }
            }
        }
    }

    /*public void make_edges(){
        for (int i = 0; i < odd_nodes.size(); i++) {
            for (int j = i+1; j < odd_nodes.size(); j++) {
                Edge e = new Edge(odd_nodes.get(i), odd_nodes.get(j));
                this.edges.add(e);
            }
        }
    }*/

    private void add_odds(){
        for (int i = 0; i < odd_nodes.size(); i++) {
            odds[odd_nodes.get(i).getNr()] = true;
        }
    }

    private void minimal_perfect_match_bruteforse(){
        //sort the array
        Collections.sort(edges);

        //find the matching
        for (int i = 0; i < edges.size(); i++) {
            if (!connected[edges.get(i).getN1().getNr()] && !connected[edges.get(i).getN2().getNr()]){
                mathing.add(edges.get(i));
                connected[edges.get(i).getN1().getNr()] = true;
                connected[edges.get(i).getN2().getNr()] = true;
            }
        }
    }

    public void print_edges(){
        System.out.println("perfect matching: ");
        for (int i = 0; i < mathing.size(); i++) {
            System.out.println( mathing.get(i).getN1().getNr() + " : " + mathing.get(i).getN2().getNr());
        }
    }
}

/*
what i can do
- sort the list of edges
- add all IS-edges

 */
