package graph;


import graph.structures.Edge;
import graph.structures.Node;
import graph.structures.Point;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

public class Graph_print extends JFrame {
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private ArrayList<Edge> odds;



    public Graph_print(ArrayList<Node> nodes, ArrayList<Edge> edges, ArrayList<Edge> odds) {
        this.nodes = nodes;
        this.edges = edges;
        this.odds = odds;
        test_2();
    }

    public void test_1(){

        // create a new frame to store text field and button
        JFrame f = new JFrame("panel");


        final BufferedImage image = new BufferedImage(1280, 768,
                BufferedImage.TYPE_INT_RGB);

        // create a label to display text
        JLabel l = new JLabel("panel label");


        // create a new buttons
        JButton b, b1, b2;
        b = new JButton("button1");
        b1 = new JButton("button2");
        b2 = new JButton("button3");

        // create a panel to add buttons
        JPanel p = new JPanel();

        // add buttons and textfield to panel
        p.add(b);
        p.add(b1);
        p.add(b2);
        p.add(l);

        // setbackground of panel
        p.setBackground(Color.white);

        // add panel to frame
        f.add(p);

        // set the size of frame
        f.setSize(300, 300);

        f.show();
    }

    private void test_2(){
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
        frame.setVisible(true);

        // do you drawing somewhere else, maybe a different thread
        Graphics g = image.getGraphics();

        g.setColor(Color.WHITE);
        print_points(g);

        g.setColor(Color.CYAN);
        print_edges(g, this.edges);

        g.setColor(Color.GREEN);
        print_edges(g, this.odds);


        g.dispose();
        canvas.repaint();

    }

    private void print_points(Graphics g){
        for (int i = 0; i < this.nodes.size(); i++) {
            int x = (int) this.nodes.get(i).p.getX()*10;
            int y = (int) this.nodes.get(i).p.getY()*10;
            g.drawOval(x, 780 - y, 4, 4);
        }
    }

    private void print_edges(Graphics g, ArrayList<Edge> edges){
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            Point p1 = e.getN1().p;
            Point p2 = e.getN2().p;

            g.drawLine((int)p1.getX()*10 + 2, 782 - (int)p1.getY()*10, (int)p2.getX()*10 + 2, 782 - (int)p2.getY()*10);
        }
    }


}