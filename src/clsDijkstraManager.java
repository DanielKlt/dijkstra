import java.util.*;

// In the readme.md you can find a full documentation of how the Algorithmen works.
public class clsDijkstraManager implements iConstant {
    private final List<clsVertex> mVertexes;
    private final List<clsEdge> mEdges;
    private Set<clsVertex> mVisitedVertex;
    private Set<clsVertex> mUnvisitedVertex;
    private Map<clsVertex, clsVertex> mPrevious;
    private Map<clsVertex, Integer> mWeight;


    /**
     * Constructor
     * @param graph clsGraph
     */
    public clsDijkstraManager(clsGraph graph){
        mVertexes = new ArrayList<clsVertex>(graph.getVertexes());
        mEdges = new ArrayList<clsEdge>(graph.getEdges());
    }



    /**
     * Initialising unvisited vertexes
     * @param start clsVertex
     */
    public void init(clsVertex start){
        mUnvisitedVertex = new HashSet<clsVertex>();
        mVisitedVertex = new HashSet<clsVertex>();
        mWeight = new HashMap<clsVertex, Integer>();
        mPrevious = new HashMap<clsVertex,clsVertex>();

        mWeight.put(start,0);
        mUnvisitedVertex.add(start);

        while(mUnvisitedVertex.size()>0){
            clsVertex minVertex = getSmallest(mUnvisitedVertex);
            mVisitedVertex.add(minVertex);
            mUnvisitedVertex.remove(minVertex);
            findFastestWay(minVertex);
        }

    }

    /**
     * Checks if Node is in the Set from the visited Nodes
     * @param vert clsVertex
     * @return boolean - if Note is already in the Set of Visited Nodes
     */
    private boolean isVisited(clsVertex vert){ return mVisitedVertex.contains(vert); }

    /**
     * Return the Node with the smallest distance from the given Node
     * @param vertexes Set<clsVertex>
     * @return  clsVertex
     */
    private clsVertex getSmallest(Set<clsVertex> vertexes) {
        clsVertex smallest = null;
        for(clsVertex vert : vertexes){
            if(smallest == null) smallest = vert;
            else
            {
                if (getShortestDistance(vert) < getShortestDistance(smallest)) smallest = vert;
        }}
        return smallest;
    }

    /**
     * Checks if the direct way or another way is the fastest
     * @param vertex clsVertex
     */
    private void findFastestWay(clsVertex vertex){
        List<clsVertex> lVertex = getNeighbours(vertex);
        for (clsVertex target : lVertex){
            if(getShortestDistance(target) > getShortestDistance(vertex)+getWeight(vertex, target)){
                mWeight.put(target,getShortestDistance(vertex)+getWeight(vertex,target));
                mPrevious.put(target, vertex);
                mUnvisitedVertex.add(target);
            }
        }
    }

    /**
     * Finds all Neighbours from a Node
     * @param vert List<clsVertex>
     * @return List of Nodes from all Neighbours
     */
    private List<clsVertex> getNeighbours(clsVertex vert) {
        List<clsVertex> neighbours = new ArrayList<clsVertex>();
        for (clsEdge edge : mEdges) {
            if (edge.getStart()==(vert) && !isVisited(edge.getTarget())) {
                neighbours.add(edge.getTarget());
            }
        }
        return neighbours;
    }

    /**
     *  gets the distance between two Nodes
     * @param vertex clsVertex
     * @param target clsVertex
     * @return distance as Integer between two Nodes
     */
    private Integer getWeight(clsVertex vertex, clsVertex target) {
        for (clsEdge edge: mEdges ) {
            if (edge.getStart() == (vertex) && edge.getTarget() == (target))
            {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Edge does not exist");
    }


    /**
     * Checks the shortest Distance from the given Node, which is saved in a Map
     * @param vert clsVertex
     * @return Integer
     */
    public Integer getShortestDistance(clsVertex vert) {
        Integer result = mWeight.get(vert);
        if (result != null){
            return result;
        }
        return MAX_INTEGER_VALUE;
    }

    /**
     * Creates the shortest Path
     * @param target clsVertex
     * @return LinkedList<clsVertex> of the fastest path
     */
    public LinkedList<clsVertex> getPath(clsVertex target){
        LinkedList<clsVertex> graph = new LinkedList<clsVertex>();
        clsVertex next = target;
        if (mPrevious.get(next)==null) return null;

        graph.add(next);

        while(mPrevious.get(next)!=null) {
            next = mPrevious.get(next);
            graph.add(next);
        }

        Collections.reverse(graph);
        return graph;
    }


}