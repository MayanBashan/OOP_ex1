import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {

    @Test
    void getNode() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=1000000; i++){
            graph.addNode(i);
        }
        for (int i=1; i<=500000; i++){
            for (int j=1; j<=20; j++)
                graph.connect(i,j,1);
        }
        //graph.connect(1,2,3);
        //graph.connect(1,4,10);
        //graph.connect(2,4,1.5);
        //graph.connect(3,2,2);
        //graph.connect(3,4,1);
        //graph.connect(3,5,7);
        //graph.connect(4,2,1.5);
        //graph.connect(4,3,1);
        //graph.connect(5,3,26.3);
        //graph.connect(5,4,1);
//
        assertEquals(graph.getNode(2).get_key(),2);
//
        ////if node does not exist in graph
        //boolean flag=false;
        //if (graph.getNode(2000000) == null)
        //    flag = true;
        //assertTrue(flag);
    }

    @Test
    void hasEdge() {
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
        assertTrue(graph.hasEdge(2,3));
        assertFalse(graph.hasEdge(2,5));
    }

    @Test
    void getEdge() {
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
        assertEquals(10, graph.getEdge(1,4));
    }

    @Test
    void addNode() {
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
        graph.addNode(7);
        assertEquals(6,graph.nodeSize());
    }

    @Test
    void connect() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=5; i++){
            graph.addNode(i);
        }
        graph.connect(1,2,3);
        graph.connect(1,2,5); //should update the weight
        assertEquals(1, graph.edgeSize());
        assertEquals(5, graph.getEdge(2,1));
    }

    @Test
    void getV() {
        weighted_graph graph = new WGraph_DS();
        int num = 3;
        boolean flag = true;
        for (int i=1; i<=num; i++){
            graph.addNode(i);
        }
        graph.connect(1,2,3);
        graph.connect(2,1,3);
        graph.connect(2,3,3);
        graph.connect(3,2,2);

        Collection<node_info> col = graph.getV();
        if (col.size()!=num) flag = false;
        else{
            for(int j=1; j<=num; j++) {
                if(!col.contains(graph.getNode(j))) {
                    flag = false;
                }
                if (!flag) break;
            }
        }
        assertTrue(flag);
    }

    @Test
    void testGetV() {
        weighted_graph graph = new WGraph_DS();
        int num = 4;
        boolean flag = true;
        for (int i=1; i<=4; i++){
            graph.addNode(i);
        }
        graph.connect(1,2,3);
        graph.connect(2,1,3);


        Collection<node_info> collection = graph.getV(1); //1 is connected to 2 and 3
        if (collection.size()!=1) flag = false;
        else{
            for(node_info node : collection){
                if (!graph.getV(node.get_key()).contains(graph.getNode(1)))
                    flag = false;
                if (!flag) break;
            }
        }
        assertTrue(flag);
    }

    @Test
    void removeNode() {
        weighted_graph graph = new WGraph_DS();
        int num = 3;
        boolean flag = true;
        for (int i=1; i<=num; i++){
            graph.addNode(i);
        }
        graph.connect(1,2,3);
        graph.connect(1,3,10);
        graph.connect(2,1,3);
        graph.connect(2,3,3);
        graph.connect(3,2,2);
        graph.connect(3,1,10);
        node_info removed_node = graph.removeNode(1);
        assertEquals(1, removed_node.get_key());
        assertEquals( 2, graph.nodeSize());
        assertEquals(1, graph.edgeSize());
    }

    @Test
    void removeEdge() {
        weighted_graph graph = new WGraph_DS();
        int num = 3;
        boolean flag = true;
        for (int i=1; i<=num; i++){
            graph.addNode(i);
        }
        graph.connect(1,2,3);
        graph.connect(1,3,10);
        graph.connect(2,1,3);
        graph.connect(2,3,3);
        graph.connect(3,2,2);
        graph.connect(3,1,10);

        graph.removeEdge(2,3);
        assertEquals(2, graph.edgeSize());
    }

    @Test
    void nodeSize() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=1000; i++){
            graph.addNode(i);
        }
        graph.connect(1,2,3);
        graph.connect(1,4,10);
        graph.connect(2,1,3);
        graph.connect(3,2,2);
        graph.connect(4,2,1.5);
        graph.connect(4,127,1);
        graph.connect(950,3,7);
        graph.addNode(1001);

        assertEquals(1001, graph.nodeSize());
    }

    @Test
    void edgeSize() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=1000; i++){
            graph.addNode(i);
        }
        graph.connect(1,2,3);
        graph.connect(1,4,10);
        graph.connect(2,1,29); //already exist, only updates edge weight
        graph.connect(3,2,2);
        graph.connect(4,2,1.5);
        graph.connect(4,127,1);
        graph.connect(950,3,7);
        graph.addNode(1001);

        assertEquals(6,graph.edgeSize());

    }

    @Test
    void getMC() {
        weighted_graph graph = new WGraph_DS();
        for (int i=1; i<=1000; i++){ //1000 mc
            graph.addNode(i);
        }
        graph.connect(1,2,3); //1001
        graph.connect(1,4,10); //1002
        graph.connect(2,1,29); //already exist, only updates edge weight //1003
        graph.connect(3,2,2); //1004
        graph.connect(4,2,1.5); //1005
        graph.connect(2,4,1.5); //mc does not increase
        graph.connect(4,127,1); //1006
        graph.connect(950,3,7); //1007
        graph.addNode(1001); //1008

        assertEquals(1008, graph.getMC());

    }
}