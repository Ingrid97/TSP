package graph.algorithms;

import graph.structures.Edge;

import java.util.ArrayList;

public interface Matching {

    ArrayList<Edge> getPerfectMatching();

    void makePerfectMatching();
}
