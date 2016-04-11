package com.company;

public class Edge {
    public Edge(int from, int to) {
        _from = from;
        _to = to;
    }

    public int get_from() {
        return _from;
    }

    public int get_to() {
        return _to;
    }

    private int _from;
    private int _to;
}
