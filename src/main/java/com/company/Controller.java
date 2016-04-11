package com.company;

import java.util.Vector;


public class Controller {
    public static void run() {
        boolean end_flag = true;
        OrientedGraph cur_graph = new OrientedGraph(Parser.get_edges());
        Vector<Vector<Integer>> result = CycleFinder.get_cycles(cur_graph);
        print_result(result);
    }

    private static void print_result(Vector<Vector<Integer>> Cycles) {
        for (Vector<Integer> cycle : Cycles) {
            for (Integer node : cycle) {
                System.out.format("%d ", node);
            }
            System.out.println();
        }
    }
}
