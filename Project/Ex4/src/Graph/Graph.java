package Graph;

import My_Exception.GraphException;

import java.util.*;

public class Graph<G, T> {

    private Map<G, List<Arch<G, T>>> map = new HashMap<>();
    private boolean bidirectional;

    public Graph(boolean bidirectional) {
        this.bidirectional = bidirectional;
    }

    /**
     * Graph constructor: 
     * Throws an exception if the vertex already exist,
     * otherwise the new vertex with an empty adjiacency list 
     * will be appended to the HashMap
     * @param new_v
     * @throws GraphException
     * @complexity O(1)
     */
    public void add_vertex(G new_v) throws GraphException {
        if (new_v == null)
            throw new GraphException("[ERROR] add_vertex(): Null vertex cannot be added in the Graph");

        if (hasVertex(new_v))
            throw new GraphException("[ERROR] add_vertex(): Vertex already exists in this Graph");

        map.put(new_v, new LinkedList<Arch<G, T>>());
    }

    /**
     * It creates a new arch:
     * Thros an exception if one of both param is null,
     * otherwise it creates a new Arch which will be appended
     * to the src's adjacency list. If bidirectional, 
     * the arch will be appended to the dest too.
     * @param src
     * @param dest
     * @param i
     * @throws GraphException
     * @complexity O(1)
     */
    public void add_arch(G src, G dest, T i) throws GraphException {
        if (src == null || dest == null)
            throw new GraphException("[ERROR] add_arch(): you can't insert arches with null sources/destinations");

        if (!map.containsKey(src))
            add_vertex(src);

        if (!map.containsKey(dest))
            add_vertex(dest);

        map.get(src).add(new Arch<G, T>(src, dest, i));

        if (is_bidirect()) {
            map.get(dest).add(new Arch<G, T>(dest, src, i));
        }
    }

    /**
     * Checks if the graph has been created as bidirectional
     * @return boolean
     * @complexity O(1)
     */
    public boolean is_bidirect() {
        return bidirectional;
    }

    /**
     * Checks if a vertex already exist
     * It throws an exception if param is null
     * @param s
     * @return boolean
     * @throws GraphException
     * @complexity O(1)
     */
    public boolean hasVertex(G s) throws GraphException {
        if (s == null)
            throw new GraphException("[ERROR] hasVertex(): vertex cannot be NULL");

        return map.containsKey(s);
    }

    /**
     * Checks if an Arch already exist
     * It throws an exception if param are null
     * @param src
     * @param dest
     * @return boolean
     * @throws GraphException
     */
    public boolean hasArch(G src, G dest) throws GraphException {
        if (src == null || dest == null)
            throw new GraphException("[ERROR] hasArch(): source and destination cannot be NULL");

        // if (src.equals(dest))
        // throw new GraphException("Error: a null arch cannot be searched");

        if (map.get(src) != null)
            return map.get(src).contains(new Arch<G, T>(src, dest));

        return false;
    }

    /**
     * It removes a vertex from the graph
     * Throws an exception if the param is null
     * @param val
     * @throws GraphException
     * @complexity O(n)
     */
    public void remove_vertex(G val) throws GraphException {
        if (val == null)
            throw new GraphException("Error: a null vertex cannot be removed");

        if (map.containsKey(val)) {
            for (G k : map.keySet()) {
                if (hasArch(k, val)) {
                    remove_arch(k, val);
                }
            }
            map.remove(val);
        } else {
            throw new GraphException("Error: vertex not found. Can't remove this vertex: " + val);
        }
    }

    /**
     * Removes an arch from the graph
     * It trhows an exception if param are null
     * or if the graph does not contain one of 
     * the two boundary
     * @param src
     * @param dest
     * @throws GraphException
     * @complexity O(1)
     */
    public void remove_arch(G src, G dest) throws GraphException {
        if (src == null || dest == null)
            throw new GraphException("Error: a null arch cannot be removed");

        if (!map.containsKey(src) || !map.containsKey(dest))
            throw new GraphException("Error: cannot remove an arch with a null boundary");

        if (hasArch(src, dest)) {
            map.get(src).remove(dest);
        }
        if (is_bidirect()) {
            map.get(dest).remove(src);
        }
    }

    /**
     * It gives the number of the vertex contained in the Graph
     * @return int
     * @complexity O(1)
     */
    public int get_n_vertex() {
        return map.keySet().size();
    }

    /**
     * It gives the number of the arches contained in the Graph.
     * The half of the value will be returned the graph is 
     * bidirectional
     * @return int
     * @complexity O(n)
     */
    public int get_n_arch() {
        int count = 0;
        for (G v : map.keySet()) {
            count += map.get(v).size();
        }
        if (this.bidirectional == true) {
            count = count / 2;
        }
        return count;
    }

    /**
     * Extracts a list of vertex contained in the graph
     * @return List<G>
     * @complexity O(n)
     */
    public List<G> get_vertexes() {
        List<G> v_list = new ArrayList<>();

        for (G k : map.keySet())
            v_list.add(k);

        return v_list;
    }

    /**
     * Extracts a list of arches contained in the graph
     * @return List<Arch<G, T>>
     * @complexity O(n)
     */
    public List<Arch<G, T>> get_arches() {
        
        //-------!!!!IL METODO NON È CORRETTO PER GRAFI NON ORIENTATI!!!!!--------
        List<Arch<G, T>> a_list = new ArrayList<>();
        for (G k : map.keySet())
            for (int i = 0; i < map.get(k).size(); i++)
                a_list.add(map.get(k).get(i));
        return a_list;
        
    }

    /**
     * Extracts a vertex's adjacency list
     * It throws an exception if param is null or
     * if it is not contained in the graph
     * @param vertex
     * @return List<G>
     * @throws GraphException
     * @complexity O(1)
     */
    public List<G> adj_list_of(G vertex) throws GraphException {
        if (vertex == null)
            throw new GraphException("Error: vertex null. Adjacent list cannot be printed");

        if (!map.containsKey(vertex))
            throw new GraphException("Error: vertex does not exist. Adjacent list cannot be printed");

        List<G> adj_list = new ArrayList<>();
        for (int i = 0; i < map.get(vertex).size(); i++)
            adj_list.add(map.get(vertex).get(i).getEnd());
        return adj_list;
    }

    /**
     * It gives the weight of a specific arch
     * An exception will be throwed if one of the
     * two param are null or if the arch does not exist
     * @param v1
     * @param v2
     * @return T
     * @throws GraphException
     * @complexity O(1)
     */
    public T get_w_btw(G v1, G v2) throws GraphException {
        if (v1 == null || v2 == null)
            throw new GraphException("Error: get_w_btw: Parameters cannot be null");

        if (hasArch(v1, v2)) {
            for (int i = 0; i < map.get(v1).size(); i++)
                if (map.get(v1).get(i).getEnd() == v2)
                    return map.get(v1).get(i).getWeight();
        } else
            throw new GraphException("Error: get_w_btw: arch (" + v1 + ", " + v2 + ") doesn't exist");

        return null;
    }

    public Map<G, List<Arch<G, T>>> getMap() {
        return map;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (G v : map.keySet()) {
            builder.append("Vertex: " + v + "\n\tAdj_list:");
            for (int i = 0; i < map.get(v).size(); i++) {
                builder.append("\n\t\t\t" + map.get(v).get(i));
            }
            builder.append("\n");
        }
        return (builder.toString());
    }

    public void add_arch(int src, int dest, int i) {
    }
}
