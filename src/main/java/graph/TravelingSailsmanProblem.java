package graph;
import graph.algorithms.*;
import graph.structures.Edge;
import graph.structures.Graph;
import graph.structures.Node;
import graph.structures.Point;
import helpers.My_File;
import java.util.ArrayList;

public class TravelingSailsmanProblem {
    private Graph g;
    private String fileName;

    public TravelingSailsmanProblem(String f){
        this.fileName = f;
        this.g = getGraph();
    }

    public Graph getGraph(){
        ArrayList<Point> p = getPoints();
        ArrayList<Node> n = makeNodes(p);
        return new Graph(n);
    }

    public ArrayList<Point> getPoints(){
        String fileName = "./src/main/resources/" + this.fileName;
        return My_File.readFromFile(fileName);
    }

    private  ArrayList<Node> makeNodes(ArrayList<Point> p){
        ArrayList<Node> n = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            n.add(new Node(p.get(i), i));
        }
        return n;
    }

    public Kruskal makeMstKruskal(){
        return new Kruskal(this.g.getNodes());
    }

    public ArrayList<Node> getOddNodes(Kruskal k){
        ArrayList<Node> odd_nodes = new ArrayList<>();
        for (int i = 0; i < k.getNodes().size(); i++) {
            if (k.getNodes().get(i).get_edges().size()%2 == 1){
                odd_nodes.add(k.getNodes().get(i));
            }
        }
        return odd_nodes;
    }

    public PerfectMatchingBlossom makePerfectMatchingBlossom(ArrayList<Node> odd_nodes){
        return new PerfectMatchingBlossom(odd_nodes,g.getNodes());
    }

    public PerfectMatching makePerfectMatchingRandom(ArrayList<Node> odd_nodes){
        return new PerfectMatching(odd_nodes);
    }

    /**
     * test
     */
    /*public PerfectMatchingControll makePerfectMatchingC(ArrayList<Node> odd_nodes){
        return new PerfectMatchingControll(odd_nodes);
    }*/

    public EulerianCircuit makeEulerianCircuit(Matching pm){
        return new EulerianCircuit(this.g.getNodes(), pm);
    }

    public HamiltonianAlgorithmRandom makeHamiltonianCitcut(EulerianCircuit ec){
        return new HamiltonianAlgorithmRandom(ec, this.g.getNodes());
    }

    public HamiltonianAlgorithm makeHamiltonianCitcutMath(EulerianCircuit ec){
        return new HamiltonianAlgorithm(ec, this.g.getNodes());
    }

    public Improvements makeImprovements(HamiltonianAlgorithm h){
        Improvements i = new Improvements(h.getHamiltonianPath(), g.getNodes());
        i.makeBestPath();
        return i;
    }

    public Improvements makeImprovements(HamiltonianAlgorithmRandom h){
        Improvements i = new Improvements(h.getHamiltonianPath(), g.getNodes());
        i.makeBestPath();
        return i;
    }

    /**
     * Extra
     */

    private int calculateLength(ArrayList<Edge> path){
        int len = 0;
        for (Edge edge : path) {
            len += edge.getN1().p.distance(edge.getN2().p);
        }
        return len;
    }

    public void showGraph(ArrayList<Edge> path, String name){
        int len = calculateLength(path);
        TravelingSailsmanProblemPrint p2 = new TravelingSailsmanProblemPrint(this.g.getNodes(), path, name, len);
    }

    public void printResult(Improvements hImproved, HamiltonianAlgorithmRandom hBlossom, HamiltonianAlgorithmRandom h){
        int lengthBlossom = calculateLength(hBlossom.getHamiltonian());
        int lengthImproved = calculateLength(hImproved.getBestPath());
        int length = calculateLength(h.getHamiltonian());

        System.out.println("TSP results:\n");

        System.out.println("TSP with Blossom matching:");
        System.out.println("length:" + lengthBlossom);

        System.out.println("TSP with Random matching:");
        System.out.println("length:" + length);

        System.out.println("TSP with Improvements:");
        System.out.println("length:" + lengthImproved);

    }
}
