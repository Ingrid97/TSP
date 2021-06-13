//THIS WORKS
package graph.algorithms;

import graph.structures.Edge;
import graph.structures.Node;
import graph.structures.dupFinder;

import java.util.ArrayList;

public class HamiltonianAlgorithm implements Hamiltonian {
    private EulerianCircuit eulerian;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> hamiltonian;
    private ArrayList<Edge> hamiltonianPath;
    private ArrayList<dupFinder> path;


    public HamiltonianAlgorithm(EulerianCircuit e, ArrayList<Node> n) {
        this.eulerian = e;
        this.nodes = n;
        this.hamiltonian = new ArrayList<>();
        this.path = new ArrayList<>();
        this.hamiltonianPath = new ArrayList<>();

        addCount();
        makeHamiltonian();
        makePrintableHamiltonian();
    }

    private void addCount() {
        for (int i = 0; i < nodes.size(); i++) {
            path.add(new dupFinder(i));
        }

        addToList(0, eulerian.getPath().size() - 1,1 );
        addToList(eulerian.getPath().size()-1, eulerian.getPath().size() - 2,0);

        for (int i = 1; i < eulerian.getPath().size() - 1; i++) {
            addToList(i, i-1, i+1);
        }
    }

    public ArrayList<Edge> getHamiltonianPath() {
        return this.hamiltonianPath;
    }

    /**
     * @param i current position
     * @param n previus position
     * @param m next position
     */
    private void addToList(int i, int n, int m){
        int previus = eulerian.getPath().get(n).getNr();
        int next = eulerian.getPath().get(m).getNr();
        path.get(eulerian.getPath().get(i).getNr()).addIntersection(previus, next);
    }

    public void makeHamiltonian() {

        for (int j = 0; j < eulerian.getPath().size(); j++) {
            int i = eulerian.getPath().get(j).getNr();
            if (path.get(i).getCounter() > 1) {
                
                int bestToKeep = findBestNode(i);
                int count = path.get(i).getCounter();
                for (int k = count-1; k >= 0 ; k--) {
                    if(k != bestToKeep){
                        int previus = path.get(i).getFrom(k);
                        int next = path.get(i).getTo(k);
                        path.get(i).removeIntersection(previus, next);

                        path.get(previus).changeIntersectionTo(next, i);
                        path.get(next).changeIntersectionFrom(previus, i);
                    }
                }
            }
        }
    }

    private int findBestNode(int n){
        double best = Integer.MAX_VALUE;
        int keep = -1;
        for (int i = 0; i < path.get(n).getIntersections().size(); i++) {

            int previus = path.get(n).getFrom(i);
            int next = path.get(n).getTo(i);
            double distI = nodes.get(previus).p.distance(nodes.get(n).p) + nodes.get(n).p.distance(nodes.get(next).p);

            for (int j = 0; j < path.get(n).getIntersections().size(); j++) {
                if(j != i)
                    distI += nodes.get(path.get(n).getFrom(j)).p.distance(nodes.get(path.get(n).getTo(j)).p);
            }

            if(distI <= best){
                best = distI;
                keep = i;
            }
        }
        
        return keep;
    }

    public void makePrintableHamiltonian() {
        for (int i = 0; i < path.size(); i++)
            hamiltonian.add(new Edge(nodes.get(i), nodes.get(path.get(i).getTo(0))));

        int curr = 0;
        for (int i = 0; i < path.size(); i++) {
            hamiltonianPath.add(new Edge(hamiltonian.get(curr).getN1(), hamiltonian.get(curr).getN2()));
            curr = hamiltonian.get(curr).getN2().getNr();
        }
    }
}


