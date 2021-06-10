package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;
import java.util.ArrayList;

public class RemovingCrossingEdges {
    public ArrayList<Node> nodes;
    public ArrayList<Edge> path;


    public RemovingCrossingEdges(ArrayList<Edge> edges, ArrayList<Node> n){
        this.nodes = n;
        this.path = new ArrayList<>();
        for (Edge e : edges) {
            path.add(e);
        }
    }

    public void remove(){
        for (int k = 0; k < path.size()*2; k++) {
            for (int i = 0; i < path.size(); i++) {
                for (int j = i+1; j < path.size(); j++) {
                    if (containsNoEqualNodes(path.get(i), path.get(j)) && crosses(path.get(i), path.get(j))){
                        turnPartOfGraph(i, j);
                    }
                }
            }
        }

    }

    private boolean containsNoEqualNodes(Edge e_1, Edge e_2){
        int n_1_1 = e_1.getN1().getNr();
        int n_1_2 = e_1.getN2().getNr();
        int n_2_1 = e_2.getN1().getNr();
        int n_2_2 = e_2.getN2().getNr();
        return n_1_1 != n_2_1 && n_1_1 != n_2_2 && n_1_2 != n_2_1 && n_1_2 != n_2_2;
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

    private void turnPartOfGraph(int e_1, int e_2){
        int count = 0;
        for (int i = e_1+1, j = e_2-1; i <= ((e_2-e_1)/2)+e_1; i++, j--) {
            Edge holder = this.path.get(i);
            this.path.set(i, this.path.get(j));
            this.path.set(j, holder);
        }

        for (int i = e_1+1; i < e_2; i++) {
            Edge h = new Edge(path.get(i).getN2(), path.get(i).getN1());
            path.set(i, h);
        }

        Edge h_1 = new Edge(path.get(e_1).getN1(), path.get(e_2).getN1());
        Edge h_2 = new Edge(path.get(e_1).getN2(), path.get(e_2).getN2());

        path.set(e_1, h_1);
        path.set(e_2, h_2);
    }

    public ArrayList<Edge> getPath() {
        return path;
    }
}
