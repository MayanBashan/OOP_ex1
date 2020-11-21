package ex1.src;
import java.io.*;
import java.util.*;

/**
 * This class represents algorithms that operate on an undirected weighted graph.
 * Operations included: checking if a graph is connected, return distance of the
 * shortest path, return the shortest path, etc.
 * In the methods, I used the Dijkstra algorithm and used 2 main data structures - a priority queue and 2 hashmaps:
 * 1) Hashmap for visited nodes
 * 2) Hashmap which represents the parent node of each node
 * The Dijkstra algorithm include the following:
 * Note: a current node's tag is used as temporal data - and represents the weight from the ex1.src node to the current node.
 * At first, all vertices weights are initialized with infinity, and all nodes are being inserted into the priority queue.
 * While priority queue is not empty - the node that is pulled out is the node which its weight is the smallest.
 * Than we go over all its neighbors and checks for each node_neighbor:
 * if node.weight + getEdge(node, node_neighbor) < node_neighbor.weight
 * then update node_neighbor weight to be node.weight + getEdge(node, node_neighbor).
 * After this, we also update in map_prev hashmap, that node is the parent of neighbor_node.
 * After going over all node's neighbors - node is marked as visited in the map_visited hashmap.
 * We keep in the same algorithm until the queue is empty.
 * Do notice - every method in this class contains a fuller explanation above it.
 * @author Mayan Bashan
 */

public class WGraph_Algo implements weighted_graph_algorithms, java.io.Serializable {

    private weighted_graph _graph;

    /**
     *  This is a WGraph_Algo constructor.
     */
    public WGraph_Algo() {
        this._graph = new WGraph_DS();
    }

    /**
     *  This is a WGraph_Algo constructor,  it gets input data:
     * @param g - graph
     */
      public WGraph_Algo(weighted_graph g) { //need this if I have the init method?
        this._graph = g;
    }

    /**
     * Initialize the given graph, on which algorithms operate
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        this._graph = g;
    }

    /**
     * This method returns this WGraph_Algo graph.
     */
    @Override
    public weighted_graph get_graph() {
        return this._graph;
    }

    /**
     * This method is a deep copy of a this graph.
     * @return a graph that represents deep copy of this graph
     */
    @Override
    public weighted_graph copy() {
        weighted_graph g = new WGraph_DS(this._graph);
        return g;
    }

    /**
     * This method checks if a graph is connectd or not.
     * Algorithm fuller explanation - At the end, the graph's number of vertices
     *                                is compared to the hashmap size (reminder - the hashmap contains
     *                                all nodes that were visited).
     *                                It means that if the number is not the same - the graph is not connected and
     *                                the method will return false.
     *                                If the number is the same - it is possible to get from every node to each
     *                                node in the graph and the method will return true.
     */
    @Override
    public boolean isConnected() {

        int node_size = this._graph.nodeSize();
        int edge_size = this._graph.edgeSize();
        if (node_size == 0 || node_size == 1) return true;
        if (edge_size <= node_size - 2) return false;

        HashMap<node_info, Boolean> map_visited = new HashMap<>();
        Queue<node_info> queue = new LinkedList<>();
        node_info n = this._graph.getV().iterator().next();
        if (this._graph.getV(n.get_key()).isEmpty()) return false; //Not connected (there are more than 1 node)
        queue.add(n);
        map_visited.put(n, true);

        while (!queue.isEmpty()) {
            node_info node = queue.poll();
            for (node_info node_neighbor : this._graph.getV(node.get_key())) {
                if (!map_visited.containsKey(node_neighbor)) {
                    queue.add(node_neighbor);
                    map_visited.put(node_neighbor, true);
                }
            }
        }
        return this._graph.nodeSize() == map_visited.size(); //Means we visited each node in the graph
    }

    /**
     * This method returns the weight of edges of the shortest path (considering weight) between ex1.src to dest.
     * It uses the method 'shortestPath' which has an explanation.
     * @param src - start node
     * @param dest - end node
     * @return - if there is no such path, returns -1
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        List<node_info> list_path = new LinkedList<>();
        list_path = shortestPath(src, dest);
        if (list_path == null) return -1; //no such path
        Collections.reverse(list_path);
        node_info last = list_path.iterator().next();
        return last.get_tag();
    }

    /**
     * This method returns a list of the shortest path (considering weight) between ex1.src to dest.
     * Algorithm fuller explanation - Here as well there is a use in the Dijkstra algorithm.
     *                                There are 2 hashmaps in this method:
     *                                - hashmap for nodes that already have been visited.
     *                                - hashmap to store the preveious (parent) node of some nodes.
     *                                After the queue is empty, the method is going from the end node to the start node
     *                                by using the hashmap of parent nodes, until it gets to null - that is how it
     *                                creates the list from dest to ex1.src and in the end only reverse it (so it will
     *                                return the path from ex1.src to dest) and returns the list.
     *                                If there is no such path - the method will return null.
     * @param src - start node
     * @param dest - end node
     * @return - list from ex1.src to dest, or null if there is no such path
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {

        List<node_info> list_path = new LinkedList<>();
        if (src == dest) {
            list_path.add(this._graph.getNode(src));
            return list_path;
        }

        HashMap<Integer, node_info> map_prev = new HashMap<>(); //key - node key, value - node's parent's key
        HashMap<Integer, Double> map_visited = new HashMap<>();
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();

        _graph.getNode(src).set_tag(0); //source node weight should be 0
        node_info node_insert = _graph.getNode(src);
        pqueue.add(node_insert.get_key());
        double inf = Double.MAX_VALUE;
        _graph.getNode(dest).set_tag(inf);
        for (node_info node : _graph.getV()) {
            if (node.get_key()!=src && node.get_key()!=dest) {
                node.set_tag(inf); //initialize node's temporal weight with infinity
                map_prev.put(node.get_key(), null);
                pqueue.add(node.get_key());
            }
        }


        while (!pqueue.isEmpty()) {
            int current = ExtractMin(pqueue);
            if (current != -1) {
                pqueue.remove(current);
                if (_graph.getV(current)!=null) {
                    for (node_info neighbor : _graph.getV(current)) {
                        if (!map_visited.containsKey(neighbor.get_key())) {
                            if (_graph.getNode(current).get_tag() + _graph.getEdge(current, neighbor.get_key()) < neighbor.get_tag()) {
                                neighbor.set_tag(_graph.getNode(current).get_tag() + _graph.getEdge(current, neighbor.get_key())); //update temporal weight from ex1.src to neighbor node
                                map_prev.put(neighbor.get_key(), _graph.getNode(current));
                            }
                        }
                    }
                }
                map_visited.put(current, _graph.getNode(current).get_tag()); //key --> temporal weight
            }

            else{
                pqueue.poll();
            }
        }

        int node_key = dest;
        node_info prev_node = _graph.getNode(node_key);
        while (prev_node != null) {
            node_key = prev_node.get_key();
            list_path.add(_graph.getNode(node_key));
            prev_node = map_prev.get(node_key);
        }

        Collections.reverse(list_path);
        node_info first = list_path.iterator().next();
        if (first.get_key() == src) return list_path;
        else return null;
    }

    /**
     * This method returns the key of the node with the smallest tad
     * Note: the tag represents a temporal data - it is the weight between ex1.src node to current node
     * @param pq - priority queue
     */
    public int ExtractMin(PriorityQueue<Integer> pq){
        int nodeKey_min_tag=-1; //need to change
        double min_tag = Double.MAX_VALUE;
        for (Integer key_node : pq){
            if (_graph.getNode(key_node).get_tag() < min_tag){
                min_tag =  _graph.getNode(key_node).get_tag();
                nodeKey_min_tag = key_node;
            }
        }
        return nodeKey_min_tag;
    }

    /**
     * Saves this undirected weighted graph to the given file name.
     * @return true - if the file was successfully saved, otherwise - return false
     */
    @Override
    public boolean save(String file) {
        try{
            FileOutputStream fos= new FileOutputStream(file);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(this._graph);
            fos.close();
            oos.close();
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true; //if no exception
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the graph
     * of this class will be updated to the loaded one.
     * In case the graph was not loaded the original graph should remain as is.
     * @param file - file name
     * @return true - if the graph was successfully loaded, otherwise - return false
     */
    @Override
    public boolean load(String file) {
        try{
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            weighted_graph graph=(weighted_graph)ois.readObject();
            fis.close();
            ois.close();
            this._graph=graph;
        }
        catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        return true; //if no exception - it means graph was loaded successfully to the file
    }

    /**
     * This method checks if two graph algorithms are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_Algo that = (WGraph_Algo) o;
        return Objects.equals(_graph, that._graph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_graph);
    }





}
