package helpers;

import graph.structures.Point;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class My_File{

    /**
     * get points from file
     * @return points
     */
    public static ArrayList<Point> read_from_file(String file){
        Locale.setDefault(new Locale("en", "US"));

        ArrayList<Point> points = new ArrayList<>();

        try {
            java.io.File myObj = new java.io.File(file);
            Scanner scn = new Scanner(myObj);
            int N = scn.nextInt();
            System.out.println("Points:");

            for (int i = 0; i < N; i++) {
                double x = scn.nextDouble();
                double y = scn.nextDouble();
                System.out.println(x + " : " + y);
                points.add(new Point(x, y));
            }

            scn.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. Time to debug");
            e.printStackTrace();
            return null;
        }
        return points;
    }
}
