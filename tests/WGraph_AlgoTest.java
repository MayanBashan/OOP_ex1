import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {
    private String filename="text.txt";

    @Test
    void init() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=5; i++){
            graph.addNode(i);
        }
        graph.connect(1,2,3);
        graph.connect(1,4,10);
        graph.connect(2,1,3);
        graph.connect(2,1,3);
        graph.connect(2,3,2);
        graph.connect(2,4,1.5);
        graph.connect(3,2,2);
        graph.connect(3,4,1);
        graph.connect(3,5,7);
        graph.connect(4,1,10);
        graph.connect(4,2,1.5);
        graph.connect(4,3,1);
        graph.connect(4,5,1);
        graph.connect(5,3,7);
        graph.connect(5,4,1);

        WGraph_Algo graph_algo = new WGraph_Algo();
        graph_algo.init(graph);
        assertEquals(graph, graph_algo.get_graph());
    }

    @Test
    void get_graph() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(7,2,3);
        graph.connect(1,4,10);
        graph.connect(2,1,3);
        graph.connect(2,1,3);
        graph.connect(2,3,2);
        graph.connect(2,4,1.5);
        graph.connect(3,2,2);
        graph.connect(3,4,1);
        graph.connect(3,5,7);
        graph.connect(4,1,10);
        graph.connect(4,2,1.5);
        graph.connect(4,3,1);
        graph.connect(4,5,1);
        graph.connect(5,3,7);
        graph.connect(5,4,1);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);
        assertEquals(graph,graph_algo.get_graph());
    }

    @Test
    void copy() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=10; i++){
            graph.addNode(i);
        }
        graph.connect(7,2,3);
        graph.connect(1,4,10);
        graph.connect(2,1,3);
        graph.connect(2,1,3);
        graph.connect(2,3,2);
        graph.connect(2,4,1.5);
        graph.connect(3,2,2);
        graph.connect(3,4,1);
        graph.connect(3,5,7);
        graph.connect(4,1,10);
        graph.connect(4,2,1.5);
        graph.connect(4,3,1);
        graph.connect(4,5,1);
        graph.connect(5,3,7);
        graph.connect(5,4,1);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);
        assertEquals(graph, graph_algo.copy());
    }

    @Test
    void isConnected() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=10; i++){
            graph.addNode(i);
        }
        graph.connect(7,2,3);
        graph.connect(1,4,10);
        graph.connect(2,1,3);
        graph.connect(2,1,3);
        graph.connect(2,3,2);
        graph.connect(2,4,1.5);
        graph.connect(3,2,2);
        graph.connect(3,4,1);
        graph.connect(3,5,7);
        graph.connect(4,1,10);
        graph.connect(4,2,1.5);
        graph.connect(4,3,1);
        graph.connect(4,5,1);
        graph.connect(5,3,7);
        graph.connect(5,4,1);
        graph.connect(6,8,1);
        graph.connect(8,9,1);
        graph.connect(9,10,1);
        graph.connect(5,4,1);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);
        assertFalse(graph_algo.isConnected());
    }

    @Test
    void isConnected2() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=10; i++){
            graph.addNode(i);
        }
        graph.connect(7,2,3);
        graph.connect(1,4,10);
        graph.connect(2,1,3);
        graph.connect(2,1,3);
        graph.connect(2,3,2);
        graph.connect(2,4,1.5);
        graph.connect(3,2,2);
        graph.connect(3,4,1);
        graph.connect(3,5,7);
        graph.connect(4,1,10);
        graph.connect(4,2,1.5);
        graph.connect(4,3,1);
        graph.connect(4,5,1);
        graph.connect(5,3,7);
        graph.connect(5,4,1);
        graph.connect(6,8,1);
        graph.connect(8,9,1);
        graph.connect(9,10,1);
        graph.connect(4,6,1);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);
        assertTrue(graph_algo.isConnected());
    }

    @Test
    void isConnected3() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=10; i++){
            graph.addNode(i);
        }
        graph.connect(7,2,3);
        graph.connect(1,4,10);
        graph.connect(2,1,3);
        graph.connect(2,1,3);
        graph.connect(2,3,2);
        graph.connect(2,4,1.5);
        graph.connect(3,2,2);
        graph.connect(3,4,1);
        graph.connect(3,5,7);
        graph.connect(4,1,10);
        graph.connect(4,2,1.5);
        graph.connect(4,3,1);
        graph.connect(4,5,1);
        graph.connect(5,3,7);
        graph.connect(5,4,1);
        graph.connect(8,9,1);
        graph.connect(9,10,1);
        graph.connect(4,6,1);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);
        assertFalse(graph_algo.isConnected());
    }


    @Test
    void shortestPathDist() {
        weighted_graph graph = new WGraph_DS();
        for (int i=0; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,3,3);
        graph.connect(0,4,1);
        graph.connect(1,2,2);
        graph.connect(1,3,6);
        graph.connect(2,4,1);
        graph.connect(4,5,9);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);

        double actual_dist = graph_algo.shortestPathDist(2,5);

        assertEquals(10,actual_dist);
    }

    @Test
    void shortestPathDist2() {
        weighted_graph graph = new WGraph_DS();
        for (int i=0; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,3,3);
        graph.connect(0,4,1);
        graph.connect(1,2,2);
        graph.connect(1,3,6);
        graph.connect(2,4,10);
        graph.connect(4,5,9);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);

        double actual_dist = graph_algo.shortestPathDist(2,5);

        assertEquals(13,actual_dist);
    }

    @Test
    void shortestPathDist3() {
        weighted_graph graph = new WGraph_DS();
        for (int i=0; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,3,3);
        graph.connect(0,4,1);
        graph.connect(1,2,2);
        graph.connect(1,3,6);
        graph.connect(2,4,10);
        graph.connect(4,5,9);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);

        double actual_dist = graph_algo.shortestPathDist(3,3);

        assertEquals(0,actual_dist);
    }

    @Test
    void shortestPathDist4() {
        weighted_graph graph = new WGraph_DS();
        for (int i=0; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,3,3);
        graph.connect(0,4,1);
        graph.connect(1,2,2);
        graph.connect(1,3,6);
        graph.connect(2,4,10);
        graph.connect(4,5,9);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);

        double actual_dist = graph_algo.shortestPathDist(1,6);

        assertEquals(-1,actual_dist);
    }

    @Test
    void shortestPathDist5() {
        weighted_graph graph = new WGraph_DS();
        for (int i=0; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,3,3);
        graph.connect(0,4,1);
        graph.connect(1,2,2);
        graph.connect(1,3,6);
        graph.connect(2,4,10);
        graph.connect(4,5,9);
        graph.connect(6,7,100);

        WGraph_Algo graph_algo = new WGraph_Algo(graph);

        double actual_dist = graph_algo.shortestPathDist(7,6);

        assertEquals(100,actual_dist);
    }


    @Test
    void shortestPath() {
        weighted_graph graph = new WGraph_DS();
        for (int i=0; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,3,3);
        graph.connect(0,4,1);
        graph.connect(1,2,2);
        graph.connect(1,3,6);
        graph.connect(2,4,10);
        graph.connect(4,5,9);

        List<node_info> expected_lst = new LinkedList<>();
        expected_lst.add(graph.getNode(5));
        expected_lst.add(graph.getNode(4));
        expected_lst.add(graph.getNode(0));
        expected_lst.add(graph.getNode(1));
        expected_lst.add(graph.getNode(2));
        Collections.reverse(expected_lst);
        WGraph_Algo graph_algo = new WGraph_Algo(graph);

        List<node_info> actual_list = graph_algo.shortestPath(2,5);

        assertEquals(expected_lst,actual_list);
    }

    @Test
    void shortestPath2() {
        weighted_graph graph = new WGraph_DS();
        for (int i=0; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,3,3);
        graph.connect(0,4,1);
        graph.connect(1,2,2);
        graph.connect(1,3,6);
        graph.connect(2,4,1);
        graph.connect(4,5,9);

        List<node_info> expected_lst = new LinkedList<>();
        expected_lst.add(graph.getNode(2));
        expected_lst.add(graph.getNode(4));
        expected_lst.add(graph.getNode(5));

        WGraph_Algo graph_algo = new WGraph_Algo(graph);

        List<node_info> actual_list = graph_algo.shortestPath(2,5);

        assertEquals(expected_lst,actual_list);
    }

    @Test
    void shortestPath3() {
        weighted_graph graph = new WGraph_DS();
        for (int i=0; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,3,3);
        graph.connect(0,4,1);
        graph.connect(1,2,2);
        graph.connect(1,3,6);
        graph.connect(2,4,1);
        graph.connect(4,5,9);
        graph.connect(6,7,100);

        List<node_info> expected_lst = new LinkedList<>();
        expected_lst.add(graph.getNode(6));
        expected_lst.add(graph.getNode(7));


        WGraph_Algo graph_algo = new WGraph_Algo(graph);

        List<node_info> actual_list = graph_algo.shortestPath(6,7);

        assertEquals(expected_lst,actual_list);
    }

    @Test
    void shortestPath4() {
        weighted_graph graph = new WGraph_DS();
        for (int i=0; i<=7; i++){
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,3,3);
        graph.connect(0,4,1);
        graph.connect(1,2,2);
        graph.connect(1,3,6);
        graph.connect(2,4,1);
        graph.connect(4,5,9);
        graph.connect(6,7,100);

        List<node_info> expected_lst = new LinkedList<>();
        expected_lst.add(graph.getNode(1));
        expected_lst.add(graph.getNode(0));
        expected_lst.add(graph.getNode(3));


        WGraph_Algo graph_algo = new WGraph_Algo(graph);

        List<node_info> actual_list = graph_algo.shortestPath(1,3);

        assertEquals(expected_lst,actual_list);
        assertEquals(4,graph_algo.shortestPathDist(1,3));
    }

    @Test
    void save() {
        weighted_graph g = new WGraph_DS();
        this.get_graph();
        weighted_graph loaded = new WGraph_DS();
        WGraph_Algo big = new WGraph_Algo(loaded);
        big.save(filename);
        big.load(filename);
        boolean flag = loaded.equals(big);
        assertTrue(flag);
    }

    @Test
    void load() {
    }
}