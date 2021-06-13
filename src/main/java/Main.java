import graph.TravelingSailsmanProblem;
import graph.algorithms.*;
import graph.structures.Node;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Traveling Sailsman problem:");

        String filename1 = "test_points_2.txt";
        String filename2 = "points.txt";
        String filename3 = "points_2.txt";
        String filename4 = "points_3.txt";


        System.out.println("Making the graph...");
        TravelingSailsmanProblem TSP = new TravelingSailsmanProblem(filename2);

        System.out.println("\nMaking the kruskal...");
        Kruskal kruskal = TSP.makeMstKruskal();

        System.out.println("\nFinding the odd nodes...");
        ArrayList<Node> oddNodes = TSP.getOddNodes(kruskal);

        System.out.println("\nFinding a perfect matching...");
        Matching perfectMatching = TSP.makePerfectMatchingRandom(oddNodes);
        Matching perfectMatchingBlossom = TSP.makePerfectMatchingBlossom(oddNodes);

        System.out.println("\nMaking an eulerian circuit...");
        EulerianCircuit eulerianCircut = TSP.makeEulerianCircuit(perfectMatching);
        EulerianCircuit eulerianCircutBlossom = TSP.makeEulerianCircuit(perfectMatchingBlossom);

        System.out.println("\nMaking a hamiltonian circuit...\n\n");
        HamiltonianAlgorithmRandom hamiltonan = TSP.makeHamiltonianCitcut(eulerianCircut);
        HamiltonianAlgorithmRandom hamiltonanBlossom = TSP.makeHamiltonianCitcut(eulerianCircutBlossom);

        HamiltonianAlgorithm hamiltonianImproved = TSP.makeHamiltonianCitcutMath(eulerianCircut);
        HamiltonianAlgorithm hamiltonianImprovedBlossom = TSP.makeHamiltonianCitcutMath(eulerianCircutBlossom);


        System.out.println("\nMaking Improvements to the walk...\n\n");
        Improvements improvements = TSP.makeImprovements(hamiltonianImproved);
        //Improvements improvementsBlossom = TSP.makeImprovements(hamiltonanBlossom);
        Improvements improvementsBlossom = TSP.makeImprovements(hamiltonianImprovedBlossom);

        TSP.printResult(improvements, improvementsBlossom, hamiltonanBlossom, hamiltonan);



        //print the graph

        //matching
        TSP.showGraph(perfectMatching.getPerfectMatching(), "Random");
        TSP.showGraph(perfectMatchingBlossom.getPerfectMatching(), "blossom");

        //hamiltonian
        //TSP.showGraph(hamiltonan.getHamiltonian(), "Hamiltonain");
        TSP.showGraph(hamiltonanBlossom.getHamiltonian(), "Hamiltonain Blossom");

        //Improvements
        TSP.showGraph(improvements.getBestPath(), "Hamiltonian improved");
        TSP.showGraph(improvementsBlossom.getBestPath(), "Hamiltonian Blossom improved");

    }
}
