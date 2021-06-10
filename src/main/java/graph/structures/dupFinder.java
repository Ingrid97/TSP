package graph.structures;

import java.util.ArrayList;

public class dupFinder {
    private int counter;
    private ArrayList<Pair> intersections;
    private int node;

    public dupFinder(int n){
        this.counter = 0;
        this.intersections = new ArrayList<>();
        this.node = n;
    }

    public void addIntersection(int from, int to){
        this.counter++;
        intersections.add(new Pair(from, to));
    }

    public void removeIntersection(int from, int to){
        for (int i = 0; i < intersections.size(); i++) {
            if(this.intersections.get(i).getFrom() == from && this.intersections.get(i).getTo() == to){
                intersections.remove(i);
                break;
            }
        }
        this.counter--;
    }

    public void changeIntersectionTo(int to, int node){
        for (Pair intersection : intersections) {
            if (intersection.getTo() == node) {
                intersection.setTo(to);
                break;
            }
        }
    }

    public void changeIntersectionFrom(int from, int node){
        for (Pair intersection : intersections) {
            if (intersection.getFrom() == node) {
                intersection.setFrom(from);
                break;
            }
        }
    }

    @Override
    public String toString() {
        String r = "";
        for (Pair intersection : intersections) {
            r += ": " + intersection.getFrom() + " -> " + this.node + " -> " + intersection.getTo();
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
        return intersections.get(i).getTo();
    }

    public int getFrom(int i){
        return intersections.get(i).getFrom();
    }
}

class Pair{
    private int from;
    private int to;

    Pair(int a, int b){
        this.from = a;
        this.to = b;
    }

    int getFrom(){
        return this.from;
    }

    int getTo(){
        return this.to;
    }

    void setFrom(int f){
        this.from = f;
    }

    void setTo(int t){
        this.to = t;
    }
}