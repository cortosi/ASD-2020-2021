package Test_application;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Graph.*;
import Kruskal_usage.*;
import My_Exception.*;

public class Main<G, T> {

    public static void print_result(List<Arch<String, Float>> list) {
        Map<String, Integer> stored_nodes = new HashMap<>();
        float sum_w = 0;
        int sum_node = 0;
        for (Arch<String, Float> k : list) {
            if (!stored_nodes.containsKey(k.getStart())) {
                stored_nodes.put(k.getStart(), 0);
                sum_node++;
            }
            if (!stored_nodes.containsKey(k.getEnd())) {
                stored_nodes.put(k.getEnd(), 0);
                sum_node++;
            }
            sum_w += k.getWeight();
        }
        System.out.println("----------------------------Kruskal MST results----------------------------\n\n" + 
                 "Number of nodes: " + sum_node + "\nNumber of arches: " + list.size()
                + "\nTotal weight: " + sum_w / 1000 + "Km\n\nDONE\n");
    }

    public static void main(String[] args) {
        String filepath = "./italian_dist_graph.csv";

        System.out.println("Loading data from file...");

        Path inputFilePath = Paths.get(filepath);

        Graph<String, Float> graph = new Graph<String, Float>(true);

        try (BufferedReader fileinputrReader = Files.newBufferedReader(inputFilePath, StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = fileinputrReader.readLine()) != null) {
                String[] lineElements = line.split(",");
                graph.add_arch(lineElements[0], lineElements[1], Float.parseFloat(lineElements[2]));
            }
            System.out.println(graph);
            System.out.println("\nCalculating Kruskal MST...\n");
            Kruskal<String, Float> k = new Kruskal<>();
            print_result(k.KruskalMST(graph, new ArchFloatComparator()));
        } catch (IOException | GraphException | UnionFindException e) {
            e.printStackTrace();
        }
    }

}
