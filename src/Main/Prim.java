package Main;

import java.util.ArrayList;
//Ormen lange

import Extra.*;

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
        //for all nodes, find closest node
        //use union find to not two nodes twise
        //find closest node to current node, and add as edge

        //for all nodes
        for (int i = 0; i < n.size(); i++) {
            //current node
            Node current = n.get(i);
            //System.out.println("current: " + i + "/" + current.nr);

            //set default node
            Node closest = n.get(0);
            double closest_dist = Integer.MAX_VALUE;

            //find the closest node
            for (int j = 0; j < n.size(); j++) {
                if(i != j){
                    if(current.p.distance(n.get(j).p) < closest_dist && !qf.connected(current.getNr(), n.get(j).getNr())){
                        closest = n.get(j);
                        closest_dist = current.p.distance(closest.p);
                    }
                }
            }

            //add closest node/edge to current node
            //System.out.println("added edge: " + closest.nr + " to node: " + i);
            current.addEdge(closest);
            closest.addEdge(current);
            //System.out.println("node added!");

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


//can sort all edges from all nodes, but that is n*log(n), and just finding the closest is n
//point is usless? same info as node?
