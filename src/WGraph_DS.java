import java.util.*;
/**
 * This class represents an undirectional weighted graph,
 * and the set of methods that operate on this kind of graph.
 * Some of the operations included:  add a vertex, remove a vertex, connect between 2 vertices, return num of edges,
 * remove an edge, etc.
 * I used here in a HashMap data structure:
 * 1) hashmap that represents the nodes in the graph (key is the node key, the value is the node)
 * 2) hashmap that represents the connections between nodes in the graph -
 *    the key is the node key and the value is a hashmap that represents the node's neighbors,
 *    when the inside hashmap key is the neighbor_node key, and the value is the weight of
 *    the edge between the two nodes.
 * @author Mayan Bashan
 */
public class WGraph_DS implements weighted_graph, java.io.Serializable {

    private int _mode_count;
    private int _edge_sum;
    private HashMap<Integer, HashMap<Integer,Double>> _edges; //getter and setter?
    private HashMap<Integer, node_info> _nodes;               //getter and setter?

    /**
     * This is a WGraph_DS constructor.
     */
    public WGraph_DS(){
        this._mode_count = 0;
        this. _edge_sum = 0;
        this._edges = new HashMap<>();
        this._nodes = new HashMap<>();
    }

    /**
     * This is a WGraph_DS constructor, it gets input data:
     * @param mode_count - gets num of changes that were made in the graph
     * @param edge_sum - gets num of edges in the graph
     * @param edges - gets the connections between nodes in the graph
     * @param nodes - gets the nodes in the graph
     */
    public WGraph_DS(int mode_count, int edge_sum, HashMap<Integer,HashMap<Integer, Double>> edges, HashMap<Integer, node_info> nodes) {
        this._mode_count = mode_count;
        this._edge_sum = edge_sum;
        this._nodes = nodes;
        this._edges = edges;
    }

    /**
     * This is a WGraph_DS deep copy constructor.
     * @param other - input graph
     */
    public WGraph_DS(weighted_graph other) {
        this._mode_count = other.getMC();
        this._edge_sum = 0;

        this._nodes = new HashMap<>();
        for (node_info node : other.getV()) {
            this._nodes.put(node.get_key(), node);
        }
        int current_key, neighbor_key;
        this._edges = new HashMap<>();

        for (node_info node : other.getV()) {
            current_key = node.get_key();
            if (other.getV(current_key) != null) { //if current node has neighbors
                for (node_info neighbor : other.getV(current_key)) { //go over all neighbors
                    neighbor_key = neighbor.get_key();
                    //if (!this._edges.containsKey(neighbor_key)) //if neighbor still not in _edges
                    connect(current_key, neighbor_key, other.getEdge(current_key, neighbor_key)); //add the edge between both
                }
            }
        }
    }

    /**
     * This method returns the node_info by by it's uniquw node key,
     * @param key - the node key
     * @return the node_info by the node key, returns null if none.
     */
    @Override
    public node_info getNode(int key) {
        if (!this._nodes.containsKey(key)) return null;
        return this._nodes.get(key);
    }

    /**
     * This method returns true if an edge exists betwen two given nodes
     * runs in O(1) time
     * @param node1 - key of node1
     * @param node2 - key of node2
     */
    @Override
    public boolean hasEdge(int node1, int node2) { //O(1)
        if (this._edges.containsKey(node1) && this._edges.containsKey(node2)) //means both nodes have neighbors
                return (this._edges.get(node1).containsKey(node2));
        return false;
    }

    /**
     * This method returns the weight of the edge between 2 given nodes. If edge doesn't
     * exist - it returns -1
     * runs in O(1) time.
     * @param node1 - key of node1
     * @param node2 - key of node2
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (hasEdge(node1, node2))
            return this._edges.get(node1).get(node2);
        return -1; //no edge between 2 given nodes
    }

    /**
     * This method adds new node to the graph, using node key, if node already
     * in graph - do nothing.
     * runs in O(1) time.
     * @param key - node key
     */
    @Override
    public void addNode(int key) {
        if (!this._nodes.containsKey(key)){
            node_info new_node = new Node(key);
            _nodes.put(key,new_node); //adds node to nodes list
            this._mode_count++;
        }
    }

    /**
     * This method connects between node1 and node2, with an edge, if edge already exists -
     * the method updates edge's weight to the input weight.
     * Edge weight can be >= 0 (not negative).
     * runs in O(1) time.
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (node1 == node2) return;
        if (hasEdge(node1, node2)){ //if edge exist
            if (this.getEdge(node1,node2)!=w) { //if edge weight needs to be updated
                this._edges.get(node1).put(node2, w);
                this._edges.get(node2).put(node1, w);
                this._mode_count++;
            }
        }

        else{ //no edge exists between two given nodes - adds nodes to each others neighbor list
            if (this._edges.containsKey(node1)){
                this._edges.get(node1).put(node2, w);
            }
            else{
                HashMap<Integer, Double> new_map = new HashMap<>();
                this._edges.put(node1, new_map);
                this._edges.get(node1).put(node2,w);
            }
            if (this._edges.containsKey(node2)){
                this._edges.get(node2).put(node1, w);
            }
            else {
                HashMap<Integer, Double> new_map = new HashMap<>();
                this._edges.put(node2, new_map);
                this._edges.get(node2).put(node1, w);
            }
            this._edge_sum++;
            _mode_count++;
        }
    }

    /**
     * This method returns a collection of all node_info that are in the graph.
     * runs in O(1)
     * @return Collection<node_info>
     */
    @Override
    public Collection<node_info> getV() {
        return this._nodes.values();
    }

    /**
     *This method returns a collection of all neighbors of the given node_info
     * @param node_id - unique key of a node_data
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        if (!this._edges.containsKey(node_id)) return null; //node_id has no neighbors
        Collection<Integer> neighborsKeyCollection = this._edges.get(node_id).keySet();
        Collection<node_info> col = new LinkedList<>();
        for (Integer key : neighborsKeyCollection){ //create neighbors node collection
                col.add(getNode(key));
        }
        return col;
    }

    /**
     * This method removes a node_info and returns it,
     * it removes it also from it's neighbors node_info lists
     * @param key - unique key of the node_info that should be removed
     * @return - node_info
     */
    @Override
    public node_info removeNode(int key) {
        if (!this._nodes.containsKey(key)) return null; //node does not exist in graph

        node_info n = this.getNode(key);
        if (this.getV(key) != null) {
            Collection<node_info> neighbors = this.getV(key);
            for (node_info neighbor_key : neighbors) {
                removeEdge(key, neighbor_key.get_key());
            }
            this._edges.remove(key); //remove from edge list - means remove all edges connected to key node
        }
        _mode_count++;
        this._nodes.remove(key); //remove from vertices list
        return n;
    }

    /**
     * This method removes the edge between two given nodes,
     * if one of the nodes doesn't exist - do nothing.
     * @param node1 - key of node1
     * @param node2 - key of node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (!hasEdge(node1, node2)) return; //no edge between nodes - do nothing
        _edges.get(node1).remove(node2);
        _edges.get(node2).remove(node1);
        if (_edges.get(node1).size() == 0) _edges.remove(node1); //node1 has no more neighbors
        if (_edges.get(node2).size() == 0) _edges.remove(node2); //node2 has no more neighbors
        _edge_sum--;
        _mode_count++;

    }

    /**
     * This method returns number of vertices in this graph.
     */
    @Override
    public int nodeSize() {
        return this._nodes.size();
    }

    /**
     * This method returns number of edges in this graph.
     */
    @Override
    public int edgeSize() {
        return this._edge_sum; //check if equals to (this.edges.size()) / 2
    }

    /**
     * This method returns number of changes that were made in the graph.
     */
    @Override
    public int getMC() {
        return this._mode_count;
    }

    /**
     * This method checks if two graphs are equal or not.
     * @param o - gets other graph
     */
    @Override
    public boolean equals(Object o) {
        weighted_graph graph = (weighted_graph) o;
        if (this._edge_sum != graph.edgeSize() || this.nodeSize() != graph.nodeSize()) {
            return false;
        }

        for (Integer key : this._edges.keySet()) {
            for (Integer key2 : this._edges.get(key).keySet()){
                if (!graph.hasEdge(key,key2)) // should work goot (integer and double)
                    return false;
            }
        }
        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(_mode_count, _edge_sum, _edges, _nodes);
    }

    /**
     * This is a private class the represents the set of methods that operate on a vertex in
     * an undirectional weighted graph.
     * This class contains node info about a vertex which include its unique key, its info (String) and its
     * tag (Double) which will is used as a temporary data.
     * @author Mayan Bashan
     */
    private class Node implements node_info, java.io.Serializable {

        int _key;
        String _info;
        double _tag;

        /**
         * This is a NodeData constructor which gets a unique key.
         */
        public Node(int key){
            this._key = key;
            this._info = null;
            this._tag = 0;
        }

        /**
         *  This is a Node constructor, it gets input data:
         * @param key
         * @param tag
         * @param info
         */
        public Node(int key, double tag, String info){
            this._key = key;
            this._tag = tag;
            this._info = info;
        }

        /**
         *  This is a Node deep copy constructor.
         * @param other - input Node
         */
        public Node(Node other) {
            this._key = other._key;
            this._tag = other._tag;
            this._info = other._info;
        }

        /**
         * This method checks if 2 nodes are equal.
         */
        public boolean equals(node_info n){
            return this._info.equals(n.get_info()) && this._tag == n.get_tag() && this._key == n.get_key();
        }

        /**
         * Each node_info has a unique key
         * @return the key of this node_data
         */
        public int get_key() {
            return this._key;
        }

        /**
         * This method returns the information about this node_info as a string value.
         */
        public String get_info() {
            return this._info;
        }

        /**
         * This method updates this node_info info with the input string value.
         * @param s - it is the updated info of this node_info
         */
        public void set_info(String s) {
            this._info = s;
        }

        /**
         * This method returns this node_data's tag.
         * The tag is a temporal data which is used in algorithm
         */
        public double get_tag() {
            return this._tag;
        }

        /**
         * This method updates this node_data's tag.
         * @param t - the new temporary value of the tag
         */
        public void set_tag(double t) {
            this._tag = t;
        }
    }

}
