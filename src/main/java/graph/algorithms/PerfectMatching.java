package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;

import java.util.ArrayList;

public class PerfectMatching implements Matching {

    private ArrayList<Edge> perfectMatching;
    private ArrayList<Node> oddNodes;

    public PerfectMatching(ArrayList<Node> odd_nodes){
        this.perfectMatching = new ArrayList<>();
        this.oddNodes = odd_nodes;
        makePerfectMatching();
    }

    public void makePerfectMatching(){
        for (int i = 0; i < this.oddNodes.size(); i+= 2) {
            this.perfectMatching.add(new Edge(this.oddNodes.get(i), this.oddNodes.get(i+1)));
        }

        //check for crossing edges
        for (int i = 0; i < this.perfectMatching.size(); i++) {
            for (int j = 0; j < this.perfectMatching.size(); j++) {
                if(i != j && crosses(perfectMatching.get(i), perfectMatching.get(j))){
                    changeEdges(i, j);
                }
            }
        }
    }

    private void changeEdges(int i, int j){
        double length1 = perfectMatching.get(i).getN1().p.distance(perfectMatching.get(j).getN1().p)
                + perfectMatching.get(i).getN2().p.distance(perfectMatching.get(j).getN2().p);
        double length2 = perfectMatching.get(i).getN1().p.distance(perfectMatching.get(j).getN2().p)
                + perfectMatching.get(i).getN2().p.distance(perfectMatching.get(j).getN1().p);
        if(length1 < length2){
            perfectMatching.add(new Edge(perfectMatching.get(i).getN1(), perfectMatching.get(j).getN1()));
            perfectMatching.add(new Edge(perfectMatching.get(i).getN2(), perfectMatching.get(j).getN2()));
        } else {
            perfectMatching.add(new Edge(perfectMatching.get(i).getN1(), perfectMatching.get(j).getN2()));
            perfectMatching.add(new Edge(perfectMatching.get(i).getN2(), perfectMatching.get(j).getN1()));
        }
        if(i < j) {
            perfectMatching.remove(j);
            perfectMatching.remove(i);
        } else {
            perfectMatching.remove(i);
            perfectMatching.remove(j);
        }
    }

    private boolean crosses(Edge e_1, Edge e_2){
        double V_1_X = e_1.getN2().getX() - e_1.getN1().getX();
        double V_1_Y = e_1.getN2().getY() - e_1.getN1().getY();
        double V_2_X = e_2.getN2().getX() - e_2.getN1().getX();
        double V_2_Y = e_2.getN2().getY() - e_2.getN1().getY();

        double M = (-V_1_X * V_2_Y + V_2_X * V_1_Y);
        double s = (-V_2_Y * (e_2.getN1().getX() - e_1.getN1().getX()) + V_2_X * (e_2.getN1().getY() - e_1.getN1().getY())) / M;
        double t = (V_1_X * (e_2.getN1().getY() - e_1.getN1().getY()) - V_1_Y * (e_2.getN1().getX() - e_1.getN1().getX())) / M;

        return (0 <= s && s <= 1 && 0 <= t && t <= 1);
    }

    public ArrayList<Edge> getPerfectMatching(){
        return this.perfectMatching;
    }

}
