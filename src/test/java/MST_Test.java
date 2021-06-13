import graph.*;
import graph.algorithms.Kruskal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MST_Test {

    TravelingSailsmanProblem TSP;
    Kruskal kruskal;

    @BeforeAll
    public void makeGraph(){
        String filename2 = "points.txt";
        this.TSP = new TravelingSailsmanProblem(filename2);
        this.kruskal = TSP.makeMstKruskal();
    }

    @Test
    public void Connected_graph(){
        for (int i = 0; i < kruskal.getEdges().size(); i++) {

        }

    }

    @Test
    public void minNrEdges(){
        assertEquals(TSP.getGraph().getNodes().size()-1, kruskal.getEdges().size());
    }

}
