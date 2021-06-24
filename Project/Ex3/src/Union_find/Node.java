package Union_find;

import java.util.Objects;

public class Node<G> {

    private Node<G> parent;
    private G value;
    private int rank;

    public Node() {
        parent = null;
        rank = 0;
    }

    public Node(G value) {
        parent = null;
        this.value = value;
        rank = 0;
    }

    public Node(Node<G> parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }

    public G getValue() {
        return this.value;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int new_rank) {
        this.rank = new_rank;
    }

    public Node<G> getParent() {
        return this.parent;
    }

    public void setParent(Node<G> new_p) {
        this.parent = new_p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Node<?> node = (Node<?>) o;

        return rank == node.rank && this.parent.getValue() == node.parent.getValue()
                && Objects.equals(value, node.value);
    }

}
