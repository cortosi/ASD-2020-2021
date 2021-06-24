package Kruskal_usage;

import My_Exception.UnionFindException;
import Union_find.*;
import Graph.*;

import java.util.Comparator;
import java.util.*;

public class Kruskal<G, T> {

    /**
     * Extracts the Graph's Minimun Spanning Tree 
     * applying the Kruskal algorithm
     * 
     * @param Graph<G
     * @param g
     * @param Comparator<Arch<G
     * @param comparator
     * @return List<Arch<G, T>>
     * @throws UnionFindException
     */
    public List<Arch<G, T>> KruskalMST(Graph<G, T> g, Comparator<Arch<G, T>> comparator) throws UnionFindException {

        if (g == null || comparator == null) {
            throw new UnionFindException("[ERROR] KruskalMST(): parameters cannot be null");
        }

        Union_find<G> union_find = new Union_find<G>();
        List<Arch<G, T>> arches = g.get_arches();
        List<Arch<G, T>> MST_result = new ArrayList<Arch<G, T>>();

        for (G k : g.get_vertexes()) {
            union_find.make_set(k);
        }

        arches.sort(comparator);

        while (!arches.isEmpty()) {
            G n1 = arches.get(0).getStart();
            G n2 = arches.get(0).getEnd();

            if (union_find.find(n1) != union_find.find(n2)) {
                union_find.union(n1, n2);
                MST_result.add(arches.get(0));
            }
            arches.remove(0);
        }
        return MST_result;
    }
}
