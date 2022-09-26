//Class Edges (Kanten)


public class clsEdge {
    //private Attributes
    private final clsVertex mTarget;
    private final int mWeight;
    private final clsVertex mStart;

    /**
     * Constructor
     * @param start clsVertex
     * @param target clsVertex
     * @param weight int
     */
    public clsEdge(clsVertex start, clsVertex target, int weight) {
        mStart= start;
        mTarget = target;
        mWeight = weight;
    }


    //getter
    public clsVertex getStart() { return mStart; }
    public clsVertex getTarget() { return mTarget; }
    public int getWeight() { return mWeight; }
    public String toString(){ return "<"+mStart+", "+mTarget+"> "+mWeight;} //Knoten als String zurückgeben, für z.B. Testzwecke

}
