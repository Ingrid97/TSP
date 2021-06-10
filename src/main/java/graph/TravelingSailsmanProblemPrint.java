package graph;


import graph.structures.Edge;
import graph.structures.Node;
import graph.structures.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

public class TravelingSailsmanProblemPrint extends JFrame {
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    int size = 10;
    int heightWhole = 782;
    int heightCut = 1300;
    String name;
    int len;



    public TravelingSailsmanProblemPrint(ArrayList<Node> nodes, ArrayList<Edge> edges, String name, int len) {
        this.nodes = nodes;
        this.edges = edges;
        this.name = name;
        this.len = len;
        makePrint();
    }

    private void makePrint(){
        final BufferedImage image = new BufferedImage(1000, 1000,
                BufferedImage.TYPE_INT_RGB);
        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());   // <== make panel fill frame
        frame.add(canvas, BorderLayout.CENTER);
        frame.setSize(1000, 1000);
        frame.setTitle(name + ": " + len);
        frame.setVisible(true);

        Graphics g = image.getGraphics();

        g.setColor(Color.WHITE);
        printPoints(g);

        g.setColor(Color.CYAN);
        printEdges(g, this.edges);


        g.dispose();
        canvas.repaint();

    }

    private void printPoints(Graphics g){
        for (int i = 0; i < this.nodes.size(); i++) {
            int x = (int) this.nodes.get(i).p.getX()*size;
            int y = (int) this.nodes.get(i).p.getY()*size;
            g.drawOval(x, 780 - y, 4, 4);
            //g.drawString("." + this.nodes.get(i).getNr() + ".",x, height_cut - 2 - y);
            //g.drawString("." + this.nodes.get(i).getNr() + ".",x, 780 - y);

        }
    }

    private void printEdges(Graphics g, ArrayList<Edge> edges){
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            Point p1 = e.getN1().p;
            Point p2 = e.getN2().p;

            g.drawLine((int)p1.getX()*size + 2, heightWhole - (int)p1.getY()*size, (int)p2.getX()*size + 2, heightWhole - (int)p2.getY()*size);
            //g.drawString("" + i, (int)p1.getX()*size + 2, height_whole - (int)p1.getY()*size);
        }
    }


}