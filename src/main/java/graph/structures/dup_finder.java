package graph.structures;

import java.util.ArrayList;

public class dup_finder {
    private int counter;
    private ArrayList<Pair> intersections;
    private int node;

    public dup_finder(int n){
        this.counter = 0;
        this.intersections = new ArrayList<>();
        this.node = n;
    }

    public void add_intersection(int from, int to){
        this.counter++;
        intersections.add(new Pair(from, to));
    }

    public void remove_intersection(int from, int to){
        for (int i = 0; i < intersections.size(); i++) {
            if(this.intersections.get(i).get_from() == from && this.intersections.get(i).get_to() == to){
                intersections.remove(i);
                break;
            }
        }
        this.counter--;
    }

    public void change_intersection_to(int to, int node){
        for (Pair intersection : intersections) {
            if (intersection.get_to() == node) {
                intersection.set_to(to);
                break;
            }
        }
    }

    public void change_intersection_from(int from, int node){
        for (Pair intersection : intersections) {
            if (intersection.get_from() == node) {
                intersection.set_from(from);
                break;
            }
        }
    }

    @Override
    public String toString() {
        String r = "";
        for (Pair intersection : intersections) {
            r += ": " + intersection.get_from() + " -> " + this.node + " -> " + intersection.get_to();
        }
        return r;
    }


    public int getCounter(){
        return this.counter;
    }

    public int getNode() {
        return node;
    }

    public ArrayList<Pair> getIntersections() {
        return intersections;
    }

    public int getTo(int i){
        return intersections.get(i).get_to();
    }

    public int getFrom(int i){
        return intersections.get(i).get_from();
    }
}

class Pair{
    private int from;
    private int to;

    Pair(int a, int b){
        this.from = a;
        this.to = b;
    }

    int get_from(){
        return this.from;
    }

    int get_to(){
        return this.to;
    }

    void set_from(int f){
        this.from = f;
    }

    void set_to(int t){
        this.to = t;
    }
}