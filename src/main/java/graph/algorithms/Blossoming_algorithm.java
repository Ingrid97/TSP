package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;

import java.util.ArrayList;

public class Blossoming_algorithm {

    private ArrayList<Node> odd_nodes;
    private int[][] edges;
    private Crerry_Blossom_algo cb;
    int[] pm;
    private ArrayList<Edge> perfect_matching;
    
    public Blossoming_algorithm(ArrayList<Node> odd_n) {
        this.odd_nodes = odd_n;
        int size = find_size();
        this.edges = new int[size][3];

        convert_data();

        cb = new Crerry_Blossom_algo(edges, false);
        pm = cb.maxWeightMatching();

        print_pm(pm);

    }

    private void convert_data(){
        int counter = 0;
        for (int i = 0; i < odd_nodes.size(); i++) {
            for (int j = i; j < odd_nodes.size(); j++) {
                double len = odd_nodes.get(i).p.distance(odd_nodes.get(j).p);
                int int_len = (int)(len * 100);
                int[] e = {odd_nodes.get(i).getNr(), odd_nodes.get(j).getNr(), int_len};
                edges[counter++] = e;
            }
        } 
    }

    private void convert_back(){
        for (int i = 0; i < perfect_matching.size(); i++) {
            
        }
    }


    private int find_size(){
        int count = 0;
        for (int i = 0; i < odd_nodes.size(); i++) {
            for (int j = i; j < odd_nodes.size(); j++) {
                count++;
            }
        }
        return count;
    }

    private void print_pm(int[] pm){
        System.out.println("Blossom Algo: ");
        for (int i = 0; i < pm.length; i++) {
            System.out.println("i: " + pm[i]);
        }
    }

    public Crerry_Blossom_algo getCb(){
        return this.cb;
    }
}

/*
- add one and one edge
- build an augmented path
- for every added edge
  1. finds no augmented path, has found max matching - add new edge
  2. can find new augmented path, make better matching - find the new matching
  3. can find a blossom - make the blossom to one node


 Augmented path:
 a path with no cycles

 */
