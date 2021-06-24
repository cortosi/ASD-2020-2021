package Graph;

import java.util.*;

public class Arch<G, T> {

    G start;
    G end;
    T weight;
    
    public Arch(G start, G end, T weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Arch(G start, G end) {
        this.start = start;
        this.end = end;
        this.weight = null;
    }

    /**
     * @return T
     */
    public T getWeight() {
        return this.weight;
    }

    /**
     * @return G
     */
    public G getStart() {
        return this.start;
    }

    /**
     * @return G
     */
    public G getEnd() {
        return this.end;
    }

    /**
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Arch<?, ?> arch = (Arch<?, ?>) o;
        return Objects.equals(start, arch.start) && Objects.equals(end, arch.end);
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "start: " + start + "\t end: " + end + "\t weight:" + weight;
    }
}