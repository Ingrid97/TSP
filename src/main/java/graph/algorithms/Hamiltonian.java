package graph.algorithms;

import graph.structures.Edge;
import java.util.ArrayList;

interface Hamiltonian {

    void makeHamiltonian(); //the method actually making the HS

    void makePrintableHamiltonian(); //for making a printable HS

    ArrayList<Edge> getHamiltonianPath(); //get edges in HS

}
