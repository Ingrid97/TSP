package helpers;

import graph.structures.Point;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class My_File{

    /**
     * get points from file
     * @return points
     */
    public static ArrayList<Point> read_from_file(String file){
        ArrayList<Point> points = new ArrayList<>();

        try {
            //java.io.File myObj = new java.io.File("test_points_2.txt");
            //java.io.File myObj = new java.io.File("points.txt");
            java.io.File myObj = new java.io.File(file);
            Scanner myReader = new Scanner(myObj);
            int N = myReader.nextInt();

            //System.out.println(N);
            for (int i = 0; i < N; i++) {
                double x = myReader.nextDouble();
                double y = myReader.nextDouble();
                points.add(new Point(x, y));
                //System.out.println("x: " + x + "  y: " + y);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        return points;
    }
}
