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

    public static ArrayList<Point> prim_points;

    @BeforeAll
    public static void read_from_file(){
        //read points from file
        String filename = "points.txt";
        prim_points = My_File.read_from_file(filename);
    }

    @Test
    public void Connected_graph(){

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
