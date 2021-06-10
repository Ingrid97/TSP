package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;
import java.util.ArrayList;

public class ChangePlacementOfNode {

    public ArrayList<Node> nodes;
    public ArrayList<Edge> path;

    public ChangePlacementOfNode(ArrayList<Edge> edges, ArrayList<Node> nodes){
        this.path = new ArrayList<>();
        this.nodes = nodes;
        for (int i = 0; i < edges.size(); i++) {
            Edge e = new Edge(edges.get(i).getN1(), edges.get(i).getN2());
            path.add(e);
        }
    }

    public void changePath(){
        for (int k = 0; k < path.size(); k++) {
            for (int i = 0; i < path.size(); i++) {
                for (int j = 0; j < path.size()-1; j++) {
                    if(i != j && i != j+1 && isCloser(path.get(i), path.get(j), path.get(j+1))){
                        changePlace(path.get(i), path.get(j), path.get(j+1), i, j);
                    }
                }
            }
        }
    }

    public boolean isCloser(Edge e, Edge e_1, Edge e_2){

        double orig = e.getN1().p.distance(e.getN2().p) + e_1.getN1().p.distance(e_1.getN2().p) + e_2.getN1().p.distance(e_2.getN2().p);
        double new_path = e.getN1().p.distance(e_1.getN2().p) + e.getN2().p.distance(e_1.getN2().p) + e_1.getN1().p.distance(e_2.getN2().p);
        if(orig <= new_path)
            return false;
        return true;
    }

    public void changePlace(Edge e, Edge e_1, Edge e_2, int i, int j){

        Edge new_e = new Edge(e_1.getN1(), e_2.getN2());
        Edge new_1 = new Edge(e.getN1(), e_1.getN2());
        Edge new_2 = new Edge(e_1.getN2(), e.getN2());

        if(i < j){
            path.set(j, new_e);
            path.remove(j+1);

            path.set(i, new_1);
            path.add(i+1, new_2);
        } else {
            path.set(i, new_1);
            path.add(i+1, new_2);

            path.set(j, new_e);
            path.remove(j+1);
        }
    }

    public boolean containsCrossingEdges() {
        for (int i = 0; i < path.size(); i++) {
            for (int j = 0; j < path.size(); j++) {
                if (containsNoEqualNodes(path.get(i), path.get(j)) && crosses(path.get(i), path.get(j))) {
                    return true;
                }
            }
        }
        return false;
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

    private boolean containsNoEqualNodes(Edge e_1, Edge e_2){
        int n_1_1 = e_1.getN1().getNr();
        int n_1_2 = e_1.getN2().getNr();
        int n_2_1 = e_2.getN1().getNr();
        int n_2_2 = e_2.getN2().getNr();
        return n_1_1 != n_2_1 && n_1_1 != n_2_2 && n_1_2 != n_2_1 && n_1_2 != n_2_2;
    }

    public ArrayList<Edge> getPath() {
        return path;
    }
}
