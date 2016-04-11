package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Parser
{
    public static Vector<Edge> get_edges()
    {
        Scanner scr = new Scanner(System.in);
        String cur_path = new File("").getAbsolutePath();
        System.out.println("Enter input file filename: ");
        System.out.println("(file must be in current working directory - " + cur_path + " )");
        String fileName = scr.next();
        scr.close();
        Vector<Edge> edges = new Vector<>();
        try {
            File file = new File(fileName);
            Scanner fscr = new Scanner(file);
            while (fscr.hasNext()) {
                int to = fscr.nextInt();
                int from = fscr.nextInt();
                Edge cur_edge = new Edge(from, to);
                edges.add(cur_edge);
            }
            fscr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return edges;
    }
}



