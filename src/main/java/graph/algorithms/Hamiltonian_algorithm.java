//not in use

package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;
import graph.structures.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 - remove all duplicates of same node
 - fix all crossing edges
 */
public class Hamiltonian_algorithm {

    private Eulerian_circuit eu;
    private ArrayList<Node> nodes;
    private boolean[] visited;


    public Hamiltonian_algorithm(Eulerian_circuit e, ArrayList<Node> n){
        this.eu = e;
        this.nodes = n;
        this.visited = new boolean[n.size()];
        make_hamiltonian();

        //check for duplikate nodes
        //check_for_dups();

        //print for test
        //print_visited();

        remove_empty_nodes();

        System.out.println();
        eu.print_path();
    }

    //remove all empty nodes
    private void remove_empty_nodes(){
        int i = 0;
        while(i < eu.get_path().size()) {
            if(eu.get_path().get(i).getNr() == -1)
                eu.get_path().remove(i);
            else
                i++;

        }
    }
    /*
    - remode node from path
    - change edges of two nodes
    -
     */
    private void make_hamiltonian(){
        for (int i = 0; i < eu.get_path().size(); i++) {
            Node n = eu.get_path().get(i);
            int k = n.getNr();
            //boolean b = visited[k];
            //System.out.println("visited: " + n.getNr());
            if(n.getNr() != -1)
                if( !visited[n.getNr()]){
                    if (n.get_edges().size() > 2){
                        //TODO: remove duplicate node in path
                        find_and_remove_dup(n);
                    }
                    visited[n.getNr()] = true;
                }
        }

        this.eu.print_path();
    }

    /**
     * extra
     */

    public boolean check_for_dups(){
        System.out.println("number of nodes: " + nodes.size());
        System.out.println("number of nodes in ec: " + eu.get_path().size());

        //Set<Node> hs = new HashSet<>();
        //hs.addAll(eu.get_path());


        int[] dc = new int[nodes.size()];
        int counter = 0;
        for (Node n : eu.get_path()) {
            if(n.getNr() != -1 || dc[n.getNr()] == 0 ){
                dc[n.getNr()] = 1;
            } else {
                counter ++;
            }
        }

        System.out.println("number of nodes in ec != 0: " + counter);

        return counter == 0;
    }

    //TODO: find better solution for first and last node in EC
    //TODO: redesign, one node can have mpre than 4 edges...

    //[n, side1_front, side1_back, side2_front, side2_back]
    private void find_and_remove_dup(Node n) {
        Node[] node_list = new Node[5];
        int[] places = new int[2];
        node_list[0] = n;

        //find n in eu
        int i = 0;
        int found = 1;
        int place_holder = 0;
        while(found < 4 && i < this.nodes.size()-2){
            if(i == 0){
                if (eu.get_path().get(i).equals(this.nodes.get(n.getNr())) && eu.get_path().get(i).getNr() != -1) {
                    node_list[found++] = eu.get_path().get(eu.get_path().size()-1);
                    node_list[found++] = eu.get_path().get(i + 1);
                    places[place_holder++] = i;

                }
            } else {
                if (eu.get_path().get(i).equals(this.nodes.get(n.getNr())) && eu.get_path().get(i).getNr() != -1) {
                    node_list[found++] = eu.get_path().get(i - 1);
                    node_list[found++] = eu.get_path().get(i + 1);
                    places[place_holder++] = i;

                }
            }

            i++;
        }
        if(found == 5)
            remove_dup_Node(node_list, places);
        //return return_nodes;
    }

    private void remove_dup_Node(Node[] n, int[] p){
        double path_1_2 = n[1].p.distance(n[2].p);
        double dist_1_n = n[0].p.distance(n[1].p);
        double dist_n_2 = n[0].p.distance(n[2].p);

        double path_3_4 = n[3].p.distance(n[4].p);
        double dist_3_n = n[0].p.distance(n[3].p);
        double dist_n_4 = n[0].p.distance(n[4].p);

        //TODO: check if i have to remove edge from nodes too
        Point empty_point = new Point(0,0);
        Node empty = new Node(empty_point, -1);
        if((path_1_2 + dist_3_n + dist_n_4) < (path_3_4 + dist_1_n + dist_n_2)){
            //make path 1-2
            eu.get_path().set(p[0], empty);
        } else {
            eu.get_path().set(p[1], empty);
        }
    }


    /**
     * Extra
     */

    public Eulerian_circuit get_eulerian_circuit(){
        return this.eu;
    }

    public void print_visited(){
        System.out.println("visidet:");
        int counter = 0;
        for (int i = 0; i < visited.length; i++) {
            System.out.println("i: " + i + " : " +  visited[i]);
            System.out.println(nodes.get(i).get_edges());
            if (visited[i] == false)
                counter++;
        }
        System.out.println("counter: " + counter);
    }

}


