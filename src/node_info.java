public interface node_info {
    /**
     * Return the key (id) associated with this node.
     * Note: each node_data should have a unique key.
     * @return
     */
    public int get_key();

    /**
     * return the remark (meta data) associated with this node.
     * @return
     */
    public String get_info();
    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s
     */
    public void set_info(String s);
    /**
     * Temporal data (aka distance, color, or state)
     * which can be used be algorithms
     * @return
     */
    public double get_tag();
    /**
     * Allow setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    public void set_tag(double t);
}