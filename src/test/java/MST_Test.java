import graph.*;
import graph.algorithms.Prim;
import graph.algorithms.UF;
import graph.structures.Node;
import graph.structures.Point;
import helpers.My_File;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MST_Test {

    @Test
    public void Connected_graph(){
        String filename = this.getClass().getResource("test_points_2.txt").getPath();
        ArrayList<Point> prim_points = My_File.read_from_file(filename);

        if (prim_points == null) {
            fail();
        }

        //make nodes
        ArrayList<Node> nodes = MST.make_nodes(prim_points);

        //prim
        Prim prim = new Prim(nodes);

        //print result
        //prim.print_Graph();

        //test
        UF uf = prim.getUF();
        for (int i = 0; i < uf.getList().length; i++) {
            assertEquals(uf.getList()[0], uf.getList()[i]);
        }


    }


}
