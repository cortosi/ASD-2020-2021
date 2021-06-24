package Kruskal_usage;

import java.util.Comparator;

import Graph.*;

public class ArchIntComparator implements Comparator<Arch<String, Integer>> {

    @Override
    public int compare(Arch<String, Integer> r1, Arch<String, Integer> r2) {
        return Integer.compare(r1.getWeight(), r2.getWeight());
    }
}