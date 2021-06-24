package Graph;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import My_Exception.GraphException;

public class Graph_unit_test {

    @Before
    public void createGraph() throws GraphException {

    }

    @Test
    public void testSingleVertex() throws GraphException {
        Graph<Integer, Float> g = new Graph<Integer, Float>(true);
        g.add_vertex(5);
        Map<Integer, List<Arch<Integer, Float>>> m = new HashMap<>();
        m.put(5, new LinkedList<Arch<Integer, Float>>());

        assertEquals(g.getMap(), m);
    }

    @Test
    public void testMultipleVertex() throws GraphException {
        Graph<Integer, Float> g = new Graph<Integer, Float>(true);
        g.add_vertex(5);
        g.add_vertex(6);
        g.add_vertex(7);
        Map<Integer, List<Arch<Integer, Float>>> m = new HashMap<>();
        m.put(5, new LinkedList<Arch<Integer, Float>>());
        m.put(6, new LinkedList<Arch<Integer, Float>>());
        m.put(7, new LinkedList<Arch<Integer, Float>>());

        assertEquals(g.getMap(), m);
    }

    @Test
    public void testSingleNodeAdjList() throws GraphException {
        Graph<Integer, Float> g = new Graph<Integer, Float>(true);
        g.add_vertex(5);
        g.add_vertex(6);
        g.add_arch(5, 6, (float) 2);
        Map<Integer, List<Arch<Integer, Float>>> m = new HashMap<>();
        m.put(5, new LinkedList<Arch<Integer, Float>>());
        m.put(6, new LinkedList<Arch<Integer, Float>>());
        m.get(5).add(new Arch<Integer, Float>(5, 6, (float) 2));
        m.get(6).add(new Arch<Integer, Float>(6, 5, (float) 2));

        assertEquals(g.getMap(), m);
    }

    @Test
    public void test_n_nodes() throws GraphException {
        Graph<Integer, Float> g = new Graph<Integer, Float>(true);
        g.add_vertex(5);
        g.add_vertex(6);
        Map<Integer, List<Arch<Integer, Float>>> m = new HashMap<>();
        m.put(5, new LinkedList<Arch<Integer, Float>>());
        m.put(6, new LinkedList<Arch<Integer, Float>>());

        assertEquals(g.get_n_vertex(), m.keySet().size());
    }

    @Test
    public void test_n_arches() throws GraphException {
        Graph<Integer, Float> g = new Graph<Integer, Float>(true);
        g.add_vertex(5);
        g.add_vertex(6);
        g.add_arch(5, 6, (float) 2);
        Map<Integer, List<Arch<Integer, Float>>> m = new HashMap<>();
        m.put(5, new LinkedList<Arch<Integer, Float>>());
        m.put(6, new LinkedList<Arch<Integer, Float>>());
        m.get(5).add(new Arch<Integer, Float>(5, 6, (float) 2));
        m.get(6).add(new Arch<Integer, Float>(6, 5, (float) 2));
        
        int count = 0;
        for (Integer v : m.keySet()) {
            count += m.get(v).size();
        }
        count = count / 2;
        
        assertEquals(g.get_n_arch(), count);
    }
}