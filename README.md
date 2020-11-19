## README
This task is a development of a data structure of an *undirected *weighted graph.
The graph was realized by using HashMap data structure, and the operations were written by realizing BFS algorithm (please see explanation in the algorithm itself).
Included methods: find shortest path and its distance, add or remove vertices and edges, deep copy cunstructor, etc.

##### Definitions
 * *undirected graph - each edge can be traversed in either direction*
 * *weighted graph - edges have weight*
 
##### Example:
 
 ![](/undirected_weighted_graph.png)
 
 
This graph contains 5 vertices and 7 edges (and it is a connected graph).<br />
shortestPathDist method between node 0 to node 2 will return **4** (3+1=4).<br />
shortestPath method between node 0 to node 2 will return the list **{0,1,2}** (the green path in the image above, and not the red one).<br />
Do notice that the shortest path depends on the sum of the weights of the edges between the 2 nodes
and not on the sum of the nodes between 2 nodes.

###### For more information please check the Wiki page


