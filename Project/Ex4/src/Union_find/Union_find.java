package Union_find;

import My_Exception.UnionFindException;

import java.util.*;

public class Union_find<G> {

    private List<Node<G>> array = null;

    public Union_find() throws UnionFindException {
        this.array = new ArrayList<>();
    }

    /**
     * Creates a new Node as "root" with rank 0.
     * Throws an exception if param is null
     * @param new_set
     * @return Node<G>
     * @throws UnionFindException
     * @complexity O(1)
     */
    public Node<G> make_set(G new_set) throws UnionFindException {
        if (new_set == null) {
            throw new UnionFindException("[ERROR] make_set(): parameter cannot be null");
        }
        Node<G> new_node = new Node<>(new_set);
        new_node.setRank(0);
        new_node.setParent(new_node);
        array.add(new_node);
        return new_node;
    }

    /**
     * Links two nodes of the graph
     * Throws an Exception if param are null
     * @param x
     * @param y
     * @throws UnionFindException
     * @complexity O(1)
     */
    public void link(Node<G> x, Node<G> y) throws UnionFindException {
        if (x == null || y == null) {
            throw new UnionFindException("[ERROR] link(): parameter cannot be null");
        }

        if (x.getRank() > y.getRank()) {
            y.setParent(x);
        } else {
            x.setParent(y);
        }
        if (x.getRank() == y.getRank()) {
            y.setRank(y.getRank() + 1);
        }
    }

    /**
     * Extracts the root Node of a generic Node of the graph
     * Throws an exception if the param does not exist in the graph
     * @param node
     * @return Node<G>
     * @throws UnionFindException
     * @complexity O(n)
     */
    public Node<G> find(G node) throws UnionFindException {
        for (Node<G> k : array) {
            if (k.getValue().equals(node)) {
                return get_root_of(k);
            }
        }
        throw new UnionFindException("[ERROR] find(): No root found with value: " + node);
    }

    /**
     * Makes a union between two subtree of the graph
     * Throws an exception if param are null
     * @param x
     * @param y
     * @throws UnionFindException
     * @complexity O(1)
     */
    public void union(G x, G y) throws UnionFindException {
        if (x == null || y == null) {
            throw new UnionFindException("[ERROR] union(): parameter cannot be null");
        }
        link(find(x), find(y));
    }

    /**
     * Extract the root of a Node
     * Throws an exception if param is null
     * @param node
     * @return Node<G>
     * @complexity O(1)
     */
    public Node<G> get_root_of(Node<G> node) throws UnionFindException {
        if (node == null) {
            throw new UnionFindException("[ERROR] get_root_of(): parameter cannot be null");
        }
        if (node.getParent() != node) {
            node.setParent(get_root_of(node.getParent()));
        }
        return node.getParent();
    }

    /**
     * It tells if the Node list is empty
     * @return boolean
     * @complexity O(1)
     */
    public boolean isEmptyList() {
        return array.isEmpty();
    }

    /**
     * It gives the entire Node list
     * @return List<Node<G>>
     * @complexity O(1)
     */
    public List<Node<G>> getList() {
        return array;
    }
}
