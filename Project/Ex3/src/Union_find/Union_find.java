package Union_find;

import My_Exception.UnionFindException;
import java.util.*;

public class Union_find<G> {

    private List<Node<G>> array = null;

    public Union_find() throws UnionFindException {
        this.array = new ArrayList<>();
    }

    public Node<G> make_set(G new_set) throws UnionFindException {
        if (new_set == null) {
            throw new UnionFindException("[ERROR] make_set(): element parameter cannot be null");
        }
        Node<G> new_node = new Node<>(new_set);
        new_node.setRank(0);
        new_node.setParent(new_node);
        array.add(new_node);
        return new_node;
    }

    public void link(Node<G> x, Node<G> y) {
        if (x.getRank() > y.getRank()) {
            y.setParent(x);
        } else {
            x.setParent(y);
        }
        if (x.getRank() == y.getRank()) {
            y.setRank(y.getRank() + 1);
        }
    }

    public Node<G> find(G node) throws UnionFindException {
        for (Node<G> k : array) {
            if (k.getValue().equals(node)) {
                return get_root_of(k);
            }
        }
        throw new UnionFindException("[ERROR] find(): No root found with value: " + node);
    }

    public void union(G x, G y) throws UnionFindException {
        link(find(x), find(y));
    }

    public Node<G> get_root_of(Node<G> node) {
        if (node.getParent() != node) {
            node.setParent(get_root_of(node.getParent()));
        }
        return node.getParent();
    }

    public boolean isEmptyList() {
        return array.isEmpty();
    }

    public List<Node<G>> getList() {
        return array;
    }
}
