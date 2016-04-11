package com.company;


import java.util.*;


public  class CycleFinder {
    /**
     * Find all simple cycles in grpah
     * @param graph
     * @return vector of cycles.
     */
    public static Vector<Vector<Integer>> get_cycles(OrientedGraph graph){
        Vector<Boolean> used_edges = new Vector<>(graph.get_edges().size());
        Collections.fill(used_edges, false);
        Vector<Boolean> passed_nodes = new Vector<>(graph.get_nodes().size());
        Collections.fill(passed_nodes, false);
        HashMap<Integer, Vector<Integer>> linked_nodes_arrays = form_linked_nodes_arrays(graph);
        Vector<Vector<Integer>> all_cycles = new Vector<>();
        for (Integer nodes_it : graph.get_nodes()) {
            all_cycles.addAll(find_all_cycles_with_node(nodes_it, linked_nodes_arrays));
            remove_node(nodes_it, linked_nodes_arrays);
        }
        return all_cycles;
    }

    /**
     * For each node  cur_node creates graph nodes array for whom edge (cur_node, array_node) exists.
     * @param graph
     * @return associative container; where kay - node, value - array of linked nodes.
     */
    private static HashMap<Integer, Vector<Integer>> form_linked_nodes_arrays(OrientedGraph graph) {
        HashMap<Integer, Vector<Integer>> linked_nodes_arrays = new HashMap<>();
        for (Integer nodes_it : graph.get_nodes()) {
            Vector<Integer> linked_nodes = new Vector<>();
            for (Edge edges_it : graph.get_edges()) {
                if (nodes_it.equals(edges_it.get_from())) {
                    linked_nodes.add(new Integer(edges_it.get_to()));
                }
            }
            linked_nodes_arrays.put(nodes_it, linked_nodes);
        }
        return linked_nodes_arrays;
    }

    /**
     * Find all simple cycles wich contains start_node
     * @param start_node - first node in path
     * @param links_arrays - associative container; where kay - node, value - array of linked nodes.
     * @return vector of cycles with start_node
     */
    private  static Vector<Vector<Integer>> find_all_cycles_with_node(int start_node,
                                                            HashMap<Integer, Vector<Integer>> links_arrays) {
        Vector<Vector<Integer>>  cycles_with_start_node = new Vector<>();
        for (Integer dest_it : links_arrays.get(start_node)) {
            Vector<Integer> cur_path = new Vector<>();
            cur_path.add(start_node);
            find_simple_cycle_for_dest(start_node, dest_it, cur_path, links_arrays, cycles_with_start_node);
        }
        return cycles_with_start_node;
    }

    /**
     * find simple cycle wich start in start_node in destination cur_path -> cur_node
     * @param start_node - first node in path
     * @param cur_node - current node to make cycle
     * @param cur_path - current path without cur_node
     * @param links_arrays - associative container; where kay - node, value - array of linked nodes.
     * @param cycles_with_start_node - result wich accumulate all cyles with start_node
     */
    private static void find_simple_cycle_for_dest(int start_node, int cur_node, Vector<Integer> cur_path,
                                     HashMap<Integer, Vector<Integer>> links_arrays,
                                     Vector<Vector<Integer>>  cycles_with_start_node) {
        Vector<Integer> path_to_cur_node = (Vector)cur_path.clone();
        if (cur_node == start_node) {
            Vector<Integer> new_cycle = new Vector<>();
            new_cycle.addAll(cur_path);
            new_cycle.add(cur_node);
            cycles_with_start_node.add(new_cycle);
            return;
        }
        boolean is_simple_cycle = true;
        for (Integer node : cur_path) {
            if(cur_node == node.intValue()) {
                is_simple_cycle = false;
                break;
            }
        }
        if (!is_simple_cycle) return; // internal cycle
        path_to_cur_node.add(cur_node);
        cur_path.add(cur_node);
        if (!links_arrays.containsKey(cur_node)) return; //all simple cycles contained cur_node has already found
        for (Integer dest_it : links_arrays.get(cur_node)) {
            cur_path = (Vector)path_to_cur_node.clone();
            find_simple_cycle_for_dest(start_node, dest_it, cur_path, links_arrays, cycles_with_start_node);
        }
    }

    /**
     * remove node from linkes_arrays for wich all cyles has found
     * @param node_to_remove - removing node
     * @param links_arrays - associative container; where kay - node, value - array of linked nodes.
     */
    private static void remove_node(Integer node_to_remove, HashMap<Integer, Vector<Integer>> links_arrays) {
        links_arrays.remove(node_to_remove);
        for (HashMap.Entry<Integer, Vector<Integer>> entry : links_arrays.entrySet()) {
            Vector<Integer> links_for_node = entry.getValue();
            for (Integer dest_node : links_for_node) {
                if (dest_node.equals(node_to_remove)) {
                    links_for_node.remove(dest_node);
                    return;
                }
            }
        }
    }
}
