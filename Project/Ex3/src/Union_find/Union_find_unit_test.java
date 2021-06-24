package Union_find;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import My_Exception.UnionFindException;

public class Union_find_unit_test {

    private List<Node<Integer>> single_int;
    private List<Node<Integer>> list_of_int;

    @Before
    public void createUnionFind() throws UnionFindException {
        single_int = new ArrayList<>();
        Node<Integer> node = new Node<>(1);
        node.setRank(0);
        node.setParent(node);
        single_int.add(node);

        list_of_int = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Node<Integer> new_node = new Node<>(i);
            new_node.setRank(0);
            new_node.setParent(new_node);
            list_of_int.add(new_node);
        }

    }

    @Test
    public void testIsEmptyList() throws UnionFindException {
        Union_find<Integer> u = new Union_find<>();
        assertEquals(true, u.isEmptyList());
    }

    @Test
    public void testSingleIntegerMakeSet() throws UnionFindException {
        Union_find<Integer> u = new Union_find<>();
        u.make_set(1);
        assertEquals(single_int, u.getList());
    }

    @Test
    public void testMultipleIntegerMakeSet() throws UnionFindException {
        Union_find<Integer> u = new Union_find<>();
        u.make_set(1);
        u.make_set(2);
        u.make_set(3);
        u.make_set(4);
        assertEquals(list_of_int, u.getList());
    }

    @Test
    public void testSingleIntUnion() throws UnionFindException {
        List<Node<Integer>> expected = new ArrayList<>();
        Node<Integer> node1 = new Node<>(1);
        node1.setRank(0);
        node1.setParent(node1);
        expected.add(node1);

        Node<Integer> node2 = new Node<>(2);
        node2.setRank(1);
        node2.setParent(node2);
        expected.add(node2);

        node1.setParent(node2);
        
        Union_find<Integer> u = new Union_find<>();
        u.make_set(1);
        u.make_set(2);
        u.union(1, 2);
        assertEquals(expected, u.getList());
    }

    @Test
    public void testMultipleIntUnion() throws UnionFindException {
        List<Node<Integer>> expected = new ArrayList<>();
        Node<Integer> node1 = new Node<>(1);
        node1.setRank(0);
        node1.setParent(node1);
        expected.add(node1);

        Node<Integer> node2 = new Node<>(2);
        node2.setRank(1);
        node2.setParent(node2);
        expected.add(node2);

        Node<Integer> node3 = new Node<>(3);
        node3.setRank(0);
        node3.setParent(node3);
        expected.add(node3);

        node1.setParent(node2);
        node3.setParent(node2);
        
        Union_find<Integer> u = new Union_find<>();
        u.make_set(1);
        u.make_set(2);
        u.make_set(3);
        u.union(1, 2);
        u.union(3, 2);
        assertEquals(expected, u.getList());
    }
}
