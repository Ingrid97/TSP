package Tests;

import Extra.My_File;
import Extra.Node;
import Extra.Point;
import Extra.UF;
import Main.Prim;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static Main.MST.make_nodes;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class MST_Testing {

    public static ArrayList<Point> prim_points;

    @Before
    public void read_from_file(){
        //read points from file
        String filename = "points.txt";
        prim_points = My_File.read_from_file(filename);
    }

    @Test
    public void Connected_graph(){

        //make nodes
        ArrayList<Node> nodes = make_nodes(prim_points);

        //prim
        Prim prim = new Prim(nodes);

        //print result
        //prim.print_Graph();

        //test
        UF uf  = prim.getUF();
        for (int i = 0; i < uf.getList().length; i++) {
            assertEquals(uf.getList()[0], uf.getList()[i]);
        }


    }


}
