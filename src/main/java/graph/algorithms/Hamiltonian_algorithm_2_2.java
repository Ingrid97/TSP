package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;

import java.util.ArrayList;

public class Hamiltonian_algorithm_2_2 {
    private Eulerian_circuit eu;
    private ArrayList<Node> nodes;
    private boolean[] visited;
    private ArrayList<ArrayList<Integer>> node_count;
    public ArrayList<Edge> HS;


    public Hamiltonian_algorithm_2_2(Eulerian_circuit e, ArrayList<Node> n) {
        this.eu = e;
        this.nodes = n;
        this.visited = new boolean[n.size()];
        this.node_count = new ArrayList<>();
        this.HS = new ArrayList<>();

        add_node_count();
        make_hamiltonian();
        //print_counter();
        make_HS();
    }

    private void add_node_count() {
        //this.node_count = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            ArrayList<Integer> a = new ArrayList<>();
            node_count.add(a);
            node_count.get(i).add(0);
        }

        //for the wrap around
        System.out.println("first: " + eu.get_path().get(0).getNr());
        node_count.get(eu.get_path().get(0).getNr()).set(0, node_count.get(eu.get_path().get(0).getNr()).get(0) + 1);
        node_count.get(eu.get_path().get(0).getNr()).add(eu.get_path().get(eu.get_path().size() - 1).getNr());
        node_count.get(eu.get_path().get(0).getNr()).add(eu.get_path().get(1).getNr());
        node_count.get(eu.get_path().get(0).getNr()).add(0);

        System.out.println("last: " + eu.get_path().get(eu.get_path().size()-1).getNr());
        node_count.get(eu.get_path().get(eu.get_path().size()-1).getNr()).set(0, node_count.get(eu.get_path().get(eu.get_path().size()-1).getNr()).get(0) + 1);
        node_count.get(eu.get_path().get(eu.get_path().size()-1).getNr()).add(eu.get_path().get(eu.get_path().size() - 2).getNr());
        node_count.get(eu.get_path().get(eu.get_path().size()-1).getNr()).add(eu.get_path().get(0).getNr());
        node_count.get(eu.get_path().get(eu.get_path().size()-1).getNr()).add(eu.get_path().size()-1);


        //for the rest
        for (int i = 1; i < eu.get_path().size() - 1; i++) {
            System.out.println("i: " + i);
            System.out.println("Node: " + eu.get_path().get(i).getNr());
            node_count.get(eu.get_path().get(i).getNr()).set(0, node_count.get(eu.get_path().get(i).getNr()).get(0) + 1);
            node_count.get(eu.get_path().get(i).getNr()).add(eu.get_path().get(i - 1).getNr());
            node_count.get(eu.get_path().get(i).getNr()).add(eu.get_path().get(i + 1).getNr());
            node_count.get(eu.get_path().get(i).getNr()).add(i);
        }


        //print_counter();
    }

    public void print_counter() {
        System.out.println("...");
        for (int i = 0; i < node_count.size(); i++) {
            System.out.print(i + ": (" + node_count.get(i).get(0) + ") ");
            for (int j = 1; j < node_count.get(i).size(); j++) {
                System.out.print(node_count.get(i).get(j) + ", ");
            }
            System.out.println();
        }
        System.out.println("...");
    }

    private void make_hamiltonian() {
        for (int i = node_count.size()-1; i >= 0; i--) {
            if (node_count.get(i).get(0) > 1) {
                System.out.println("removing from (" + i + ")..." );
                //System.out.println("alt_1_2 (" + alt_1_2 + ") < alt_3_4(" + alt_3_4 + ")");

                //do the math x = i
                double dist_1_x = nodes.get(node_count.get(i).get(1)).p.distance(nodes.get(i).p);
                double dist_x_2 = nodes.get(i).p.distance(nodes.get(node_count.get(i).get(2)).p);
                double dist_1_2 = nodes.get(node_count.get(i).get(1)).p.distance(nodes.get(node_count.get(i).get(2)).p);

                double dist_4_x = nodes.get(node_count.get(i).get(4)).p.distance(nodes.get(i).p);
                double dist_x_5 = nodes.get(i).p.distance(nodes.get(node_count.get(i).get(5)).p);
                double dist_4_5 = nodes.get(node_count.get(i).get(4)).p.distance(nodes.get(node_count.get(i).get(5)).p);

                //keep
                double alt_1_x_2 = dist_1_x + dist_x_2 + dist_4_5;
                double alt_4_x_5 = dist_4_x + dist_x_5 + dist_1_2;

                if(alt_1_x_2 < alt_4_x_5){
                    //change nodes in counter 1
                    int place = 2;
                    if(node_count.get(node_count.get(i).get(4)).get(0) > 1 && node_count.get(node_count.get(i).get(4)).get(2) != i)
                        place = 5;
                    node_count.get(node_count.get(i).get(4)).set(place, node_count.get(i).get(5));
                    System.out.println("1 place: " + place);

                    //change nodes in counter 2
                    place = 1;
                    if(node_count.get(node_count.get(i).get(5)).get(0) > 1 && node_count.get(node_count.get(i).get(5)).get(1) != i)
                        place = 4;
                    node_count.get(node_count.get(i).get(5)).set(place, node_count.get(i).get(4));
                    System.out.println("2 place: " + place);

                    //remove from counter
                    System.out.println(node_count.get(i).get(6));
                    System.out.println(node_count.get(i).get(5));
                    System.out.println(node_count.get(i).get(4));
                    node_count.get(i).remove(6);
                    node_count.get(i).remove(5);
                    node_count.get(i).remove(4);



                    node_count.get(i).set(0, node_count.get(i).get(0)-1);
                } else {
                    //change nodes in counter 1
                    int place = 2;
                    if(node_count.get(node_count.get(i).get(1)).get(0) > 1 && node_count.get(node_count.get(i).get(1)).get(2) != i)
                        place = 5;
                    node_count.get(node_count.get(i).get(1)).set(place, node_count.get(i).get(2));
                    System.out.println("1 place: " + place);

                    //change nodes in counter 2
                    place = 1;
                    if(node_count.get(node_count.get(i).get(2)).get(0) > 1 && node_count.get(node_count.get(i).get(2)).get(1) != i)
                        place = 4;
                    node_count.get(node_count.get(i).get(2)).set(place, node_count.get(i).get(1));
                    System.out.println("2 place: " + place);

                    //remove from counter
                    System.out.println(node_count.get(i).get(3));
                    System.out.println(node_count.get(i).get(2));
                    System.out.println(node_count.get(i).get(1));
                    node_count.get(i).remove(3);
                    node_count.get(i).remove(2);
                    node_count.get(i).remove(1);

                    node_count.get(i).set(0, node_count.get(i).get(0)-1);
                }
            }
        }
    }


    private void make_HS() {
        int curr = 0;
        for (int i = 0; i < node_count.size(); i++) {
            //System.out.println("curr: " + curr);
            HS.add(new Edge(nodes.get(curr), nodes.get(node_count.get(curr).get(2))));
            curr = node_count.get(curr).get(2);
        }
    }

}


