import org.junit.jupiter.api.Test;

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
    }

    @Test
    void shortestPath() {
    }

    @Test
    void save() {

    }

    @Test
    void load() {
    }
}