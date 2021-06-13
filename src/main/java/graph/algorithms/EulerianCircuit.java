package graph.algorithms;

import graph.structures.Node;
import java.util.ArrayList;
import java.util.Stack;

public class EulerianCircuit {

    private ArrayList<Node> nodes;
    private Matching perfectMatching;
    private Stack<Node> workingStack;
    private ArrayList<Node> finalePath;

    public EulerianCircuit(ArrayList<Node> n, Matching pm){
        this.nodes = n;
        this.perfectMatching = pm;

        combineNodesPM();
        findPath();
    }

    private void combineNodesPM(){
        for (int i = 0; i < perfectMatching.getPerfectMatching().size(); i++) {
            int n1 = perfectMatching.getPerfectMatching().get(i).getN1().getNr();
            int n2 = perfectMatching.getPerfectMatching().get(i).getN2().getNr();
            nodes.get(n1).addEdge(nodes.get(n2));
            nodes.get(n2).addEdge(nodes.get(n1));
        }
    }

    private void findPath(){
        this.workingStack = new Stack<>();
        this.finalePath = new ArrayList<>();

        for (Node n : nodes) {
            n.makeVisitedEdges(nodes.size());
        }

        Node start = nodes.get(0);
        makePath(start);
    }

    private void makePath(Node curr){
        workingStack.push(curr);

        for (Node n : curr.get_edges()) {
            if (!curr.visited_EC[n.getNr()]){
                curr.addVisitedEdge(n);
                n.addVisitedEdge(curr);
                makePath(n);
            }
        }

        finalePath.add(workingStack.pop());
    }

    /**
     * extra
     */

    public ArrayList<Node> getPath(){
        return this.finalePath;
    }

}
