import java.util.*;

public class WGraph_DS implements weighted_graph, java.io.Serializable {

    private int _mode_count;
    private int _edge_sum;
    private HashMap<Integer, HashMap<Integer,Double>> _edges; //getter and setter?
    private HashMap<Integer, node_info> _nodes;               //getter and setter?

    //constructor
    public WGraph_DS(){
        this._mode_count = 0;
        this. _edge_sum = 0;
        this._edges = new HashMap<>();
        this._nodes = new HashMap<>();
    }

    //constructor
    public WGraph_DS(int mode_count, int edge_sum, HashMap<Integer,HashMap<Integer, Double>> edges, HashMap<Integer, node_info> nodes) {
        this._mode_count = mode_count;
        this._edge_sum = edge_sum;
        this._nodes = nodes;
        this._edges = edges;
    }

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
        return true; //?? maybe need to check to other side as well? another for?? to include eveything
    }


    @Override
    public int hashCode() {
        return Objects.hash(_mode_count, _edge_sum, _edges, _nodes);
    }

    //deep constructor
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


  //  public boolean equals(weighted_graph graph) {


    @Override
    public node_info getNode(int key) {
        if (!this._nodes.containsKey(key)) return null;
        return this._nodes.get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2) { //O(1)
        if (this._edges.get(node1) != null && this._edges.get(node2) != null) {
            return (this._edges.get(node1).containsKey(node2));
        }
        return false;
    }

    @Override
    public double getEdge(int node1, int node2) {
        if (hasEdge(node1, node2))
            return this._edges.get(node1).get(node2);
        return -1; //means hasEdge(node1, node2) == false
    }

    @Override
    public void addNode(int key) {
        if (!this._nodes.containsKey(key)){
            node_info new_node = new Node(key);
            _nodes.put(key,new_node); //adds node to nodes list
            //HashMap<Integer, Double> map = new HashMap<>();
            //this._edges.put(key,map); //still no edges for this key node
            this._mode_count++;
        }
    }

    @Override
    public void connect(int node1, int node2, double w) {
        if (node1 == node2) return;
        if (hasEdge(node1, node2)){ //if edge exist
            if (this._edges.get(node1).get(node2)!=w) { //if edge weight needs to be updated
                this._edges.get(node1).put(node2, w);
                this._edges.get(node2).put(node1, w);
                this._mode_count++;
            }
        }

        else{ //no edge exists between two given nodes
            //add nodes to each other neighbor list
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

    @Override
    public Collection<node_info> getV() {
        return this._nodes.values();
    }

    @Override
    public Collection<node_info> getV(int node_id) {
        if (this._edges.get(node_id) == null) return null; //node_id has no neighbors
        Collection<Integer> neighborsKeyCollection = this._edges.get(node_id).keySet();
        Collection<node_info> col = new LinkedList<>();
        for (Integer key : neighborsKeyCollection){ //create neighbors node collection
                col.add(getNode(key));
        }
        return col;
    }

    @Override
    public node_info removeNode(int key) {
        if (!this._nodes.containsKey(key)) return null; //node does not exist in graph

        node_info n = this.getNode(key);
        Collection<node_info> neighbors = this.getV(key);
        for (node_info neighbor_key : neighbors) {
            removeEdge(key, neighbor_key.get_key());
        }
        _mode_count++;
        this._edges.remove(key); //remove from edge list - means remove all edges connected to key node
        this._nodes.remove(key); //remove from vertices list
        return n;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        if (!hasEdge(node1, node2)) return; //no edge between nodes - do nothing
        _edges.get(node1).remove(node2);
        _edges.get(node2).remove(node1);
        _edge_sum--;
        _mode_count++;

    }

    @Override
    public int nodeSize() {
        return this._nodes.size();
    }

    @Override
    public int edgeSize() {
        return this._edge_sum; //check if equals to (this.edges.size()) / 2
    }

    @Override
    public int getMC() {
        return this._mode_count;
    }

    /**
     * This is a private class which represents .....
     */

    private class Node implements node_info {

        int _key;
        String _info;
        double _tag;

        public Node(int key){
            this._key = key;
            this._info = null;
            this._tag = 0;
        }

        public Node(int key, double tag, String info){
            this._key = key;
            this._tag = tag;
            this._info = info;
        }

        public Node(Node other) {
            this._key = other._key;
            this._tag = other._tag;
            this._info = other._info;
        }

        public boolean equals(node_info n){
            return this._info.equals(n.get_info()) && this._tag == n.get_tag() && this._key == n.get_key();
        }

        public int get_key() {
            return this._key;
        }

        public String get_info() {
            return this._info;
        }

        public void set_info(String s) {
            this._info = s;
        }

        public double get_tag() {
            return this._tag;
        }

        public void set_tag(double t) {
            this._tag = t;
        }


    }

}
