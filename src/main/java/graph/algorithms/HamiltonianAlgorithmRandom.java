//THIS WORKS
package graph.algorithms;
import graph.structures.*;

import java.util.ArrayList;

public class HamiltonianAlgorithmRandom implements Hamiltonian{
    private EulerianCircuit eu;
    private ArrayList<Node> nodes;
    public ArrayList<Edge> hamiltonian;
    public ArrayList<Edge> hamiltonianPath;

    public ArrayList<dupFinder> path;


    public HamiltonianAlgorithmRandom(EulerianCircuit e, ArrayList<Node> n) {
        this.eu = e;
        this.nodes = n;
        this.hamiltonian = new ArrayList<>();
        this.path = new ArrayList<>();
        this.hamiltonianPath = new ArrayList<>();

        add_node_count();
        makeHamiltonian();
        makePrintableHamiltonian();
    }

    private void add_node_count() {
        for (int i = 0; i < nodes.size(); i++) {
            path.add(new dupFinder(i));
        }

        add_to_list(0, eu.getPath().size() - 1,1 );
        add_to_list(eu.getPath().size()-1, eu.getPath().size() - 2,0);

        for (int i = 1; i < eu.getPath().size() - 1; i++) {
            add_to_list(i, i-1, i+1);
        }
    }

    public ArrayList<Edge> getHamiltonianPath() {
        return this.hamiltonianPath;
    }

    private void add_to_list(int i, int n, int m){
        int previus = eu.getPath().get(n).getNr();
        int next = eu.getPath().get(m).getNr();
        path.get(eu.getPath().get(i).getNr()).addIntersection(previus, next);
    }

    private void print_path(){
        System.out.println("...");
        for (dupFinder df : path) {
            System.out.println(df);
        }
        System.out.println("...");
    }

    public void makeHamiltonian() {

        for (int j = 0; j < eu.getPath().size(); j++) {
            int i = eu.getPath().get(j).getNr();
            if (path.get(i).getCounter() > 1) {

                int previus = path.get(i).getFrom(1);
                int next = path.get(i).getTo(1);
                path.get(i).removeIntersection(previus, next);

                path.get(previus).changeIntersectionTo(next, i);
                path.get(next).changeIntersectionFrom(previus, i);
            }
        }
    }


    public void makePrintableHamiltonian() {
        for (int i = 0; i < path.size(); i++) {
            hamiltonian.add(new Edge(nodes.get(i), nodes.get(path.get(i).getTo(0))));
        }

        int curr = 0;
        for (int i = 0; i < path.size(); i++) {
            hamiltonianPath.add(new Edge(hamiltonian.get(curr).getN1(), hamiltonian.get(curr).getN2()));
            curr = hamiltonian.get(curr).getN2().getNr();
        }
    }

    public ArrayList<Edge> getHamiltonian() {
        return hamiltonian;
    }
}


