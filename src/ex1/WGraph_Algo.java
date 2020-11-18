package ex1;

import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms, java.io.Serializable {

    private weighted_graph _graph;

    public WGraph_Algo() {
        this._graph = new WGraph_DS();
    }

    public WGraph_Algo(WGraph_Algo other) {
        this._graph = other._graph;
    }

    public WGraph_Algo(weighted_graph g) { //need this if I have the init method?
        this._graph = g;
    }

    @Override
    public void init(weighted_graph g) {
        this._graph = g;
    }

    @Override
    public weighted_graph get_graph() {
        return this._graph;
    }

    @Override
    public weighted_graph copy() {
        weighted_graph g = new WGraph_DS(this._graph);
        return g;
    }

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
        if (this._graph.nodeSize() == map_visited.size()) return true; //Means we visited each node in the graph
        else return false;
    }


    @Override
    public double shortestPathDist(int src, int dest) {
        List<node_info> list_path = new LinkedList<>();
        list_path = shortestPath(src, dest);
        if (list_path.size() == 0) return -1; //no such path
        Collections.reverse(list_path);
        node_info last = list_path.iterator().next();
        return last.get_tag();
    }

    @Override
    public List<node_info> shortestPath(int src, int dest) {

        List<node_info> list_path = new LinkedList<>();
        if (src == dest) {
            list_path.add(this._graph.getNode(src));
            return list_path;
        }

        if (this._graph.hasEdge(src, dest)) {
            list_path.add(this._graph.getNode(src));
            list_path.add(this._graph.getNode(dest));
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
            pqueue.remove(current);
            for (node_info neighbor : _graph.getV(current)) {
                if (!map_visited.containsKey(neighbor.get_key())) {
                    if (_graph.getNode(current).get_tag() + _graph.getEdge(current, neighbor.get_key()) < neighbor.get_tag()) {
                        neighbor.set_tag(_graph.getNode(current).get_tag() + _graph.getEdge(current, neighbor.get_key())); //update temporal weight from src to neighbor node
                        map_prev.put(neighbor.get_key(), _graph.getNode(current));
                    }
                }
            }
            map_visited.put(current, _graph.getNode(current).get_tag()); //key --> temporal weight
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



    //@Override
    //public boolean save(String file) {
    //
    //}
//
    //@Override
    //public boolean load(String file) {
    //
    //}

}
