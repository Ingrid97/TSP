package graph.algorithms;

import java.util.ArrayList;
//Ormen lange

import graph.structures.Node;

public class Prim {

    private ArrayList<Node> n;
    private UF qf;

    public Prim(ArrayList<Node> n){
        this.n = n;
        this.qf = new UF(n.size());
        make_graph();
    }

    /**
     * make the graph
     */
    private void make_graph(){
        for (int i = 0; i < n.size(); i++) {
            Node current = n.get(i);

            Node closest = n.get(0);
            double closest_dist = Integer.MAX_VALUE;

            for (int j = 0; j < n.size(); j++) {
                if(i != j){
                    if(current.p.distance(n.get(j).p) < closest_dist && !qf.connected(current.getNr(), n.get(j).getNr())){
                        closest = n.get(j);
                        closest_dist = current.p.distance(closest.p);
                    }
                }
            }
            current.addEdge(closest);
            closest.addEdge(current);

            //connect
            qf.connect(current.getNr(), closest.getNr());

        }
    }

    public void print_Graph(){
        System.out.println("QF:");
        System.out.println(qf);
        System.out.println();

        System.out.println("Nodes:");
        for (int i = 0; i < n.size(); i++) {
            System.out.print(n.get(i).getNr() + ": ");
            for (Node n : n.get(i).get_edges()) {
                System.out.print(n.getNr() + ", ");
            }
            System.out.println();
        }
    }

    public UF getUF(){
        return this.qf;
    }

}
