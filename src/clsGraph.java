//Class Graph is build on Edges and Vertexes

import java.util.List;

public class clsGraph {
    //A Graph is a List of Edges and Vertexes
    private final List<clsEdge> mEdges;
    private final List<clsVertex> mVertexes;

    /**
     *
     * @param edges List<clsEdge>
     * @param vertexes List<clsVertex>
     */
    public clsGraph(List<clsEdge> edges, List<clsVertex> vertexes) {
        mEdges = edges;
        mVertexes = vertexes;
    }

    //getters
    public List<clsEdge> getEdges() { return mEdges; }

    public List<clsVertex> getVertexes() { return mVertexes; }
}
