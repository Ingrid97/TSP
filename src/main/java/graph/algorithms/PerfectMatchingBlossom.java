package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;

import java.util.ArrayList;

public class PerfectMatchingBlossom implements Matching {
    private ArrayList<Node> odd_nodes;
    private ArrayList<Node> nodes;
    private int[][] edges;
    private BlossomingAlgorithm cb;
    int[] pm;
    private ArrayList<Edge> perfectMatching;

    public PerfectMatchingBlossom(ArrayList<Node> oddn, ArrayList<Node> n){

        this.odd_nodes = oddn;
        int size = findSize();
        this.edges = new int[size][3];
        this.nodes = n;
        this.perfectMatching = new ArrayList<>();

        makePerfectMatching();
    }

    public void makePerfectMatching(){
        convertData();
        cb = new BlossomingAlgorithm(edges, true);
        pm = cb.maxWeightMatching();

        convertBack();
    }

    private void convertData(){
        int counter = 0;
        for (int i = 0; i < odd_nodes.size(); i++) {
            for (int j = i; j < odd_nodes.size(); j++) {
                double len = odd_nodes.get(i).p.distance(odd_nodes.get(j).p);
                int int_len = (int)(len * -10);
                int[] e = {odd_nodes.get(i).getNr(), odd_nodes.get(j).getNr(), int_len};
                edges[counter] = e;
                counter++;
            }
        }
    }

    private void convertBack(){
        for (int i = 0; i < nodes.size(); i++) {
            if(pm[i] != -1){
                perfectMatching.add(new Edge(nodes.get(i), nodes.get(pm[i])));
                pm[pm[i]] = -1;
                pm[i] = -1;
            }
        }
    }


    private int findSize(){
        int count = 0;
        for (int i = 0; i < odd_nodes.size(); i++) {
            for (int j = i; j < odd_nodes.size(); j++) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Edge> getPerfectMatching() {
        return perfectMatching;
    }

}
