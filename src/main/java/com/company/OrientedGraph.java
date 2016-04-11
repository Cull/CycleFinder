package com.company;

import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;

public class OrientedGraph {

    public OrientedGraph(Vector<Edge> init) {
        _edges = new Vector<Edge>();
        _nodes = new HashSet<Integer>();
        for (Edge edge : init) {
            _edges.add(edge);
        }
        for (int i = 0; i < init.size(); ++i) {
            _nodes.add(_edges.elementAt(i).get_from());
            _nodes.add(_edges.elementAt(i).get_to());
        }
    }
    public Vector<Edge> get_edges(){
        return _edges;
    }
    public HashSet<Integer> get_nodes() {
        return _nodes;
    }
    private Vector<Edge> _edges;
    private HashSet<Integer> _nodes;
}
