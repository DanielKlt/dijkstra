import java.util.*;

// Full Documentation in readme.md
public class clsMain {
    //Attributes
    private static List<clsVertex> nodes;
    private static List<clsEdge> edges;

    //To not have to write that much code -> pushing the array into an ArrayList of nodes
    public static List<String> cities = Arrays.asList("Frankfurt", "Mannheim", "Würzburg", "Kassel", "Karlsruhe", "Erfurt", "Nürnberg", "Stuttgart", "Augsburg", "München");


    public static void main(String[] args) {
        int startNodeInt = -1;
        int endNodeInt = -1;
        nodes = new ArrayList<clsVertex>();
        edges = new ArrayList<clsEdge>();
        for (String city : cities) {
            clsVertex location = new clsVertex(city);
            nodes.add(location);
        }
        //User input
        System.out.println("Wählen Sie den Start aus, geben Sie dafür die Zahl vor der Stadt ein: \n");
        for (int i = 0; i < cities.size() ; i++) {
            System.out.println(i+ ": "+cities.get(i));
        }

        //catch wrong Input
        try {
            Scanner startNode = new Scanner(System.in);
             startNodeInt = startNode.nextInt();
        }catch(InputMismatchException e){
            System.out.println("String eingabe, anstatt eines Integers, Programm ende");
            System.exit(0);
        }
        //Check if Input is Valid
        if(startNodeInt>=0 && startNodeInt<cities.size()){
            System.out.println("Input akzeptiert"+", nun das Ziel");
        }else {
            System.out.println("Falscher Input, Zahl muss zwischen 0 und "+cities.size()+" sein. Programm beendet");
            System.exit(0);
        }
        //catch wrong Input
        try{
            Scanner endNode = new Scanner(System.in);
            endNodeInt =endNode.nextInt();
        }catch(InputMismatchException e){
            System.out.println("String eingabe, anstatt eines Integers, Programm ende");
            System.exit(0);
        }
        //Check if Input is Valid
        if (endNodeInt>=0 && endNodeInt<cities.size()){
            System.out.println("Input aktzeptiert");
        }else{
            System.out.println("Falscher Input, Zahl muss zwischen 0 und "+cities.size()+" sein. Programm beendet");
            System.exit(0);
        }


        addEdge(0, 1, 85);
        addEdge(0, 2, 217);
        addEdge(0, 3, 173);
        addEdge(1, 4, 80);
        addEdge(2, 5, 186);
        addEdge(2, 6, 103);
        addEdge(6, 7, 183);
        addEdge(4, 8, 250);
        addEdge(8, 9, 84);
        addEdge(3, 9, 502);
        addEdge(6, 9, 167);


        clsGraph graph = new clsGraph(edges, nodes);
        clsDijkstraManager dijkstra = new clsDijkstraManager(graph);
        dijkstra.init(nodes.get(startNodeInt)); //Initialising the start, which is Frankfurt on Position 0
        LinkedList<clsVertex> path = dijkstra.getPath(nodes.get(endNodeInt)); //Destination Node, which is München on Position 9
        int i = 0;
        for (clsVertex vertex : path) {
            System.out.print(vertex);
            if (i < path.size()-1) {
                System.out.print(" -> ");
            }
            i++;
        }
        System.out.println("\nDistanz: "+startEndDistance(path)+ " Km");


    }

    /**
     *
     * @param start
     * @param target
     * @param weight
     */
    private static void addEdge(int start, int target, int weight) {
        clsEdge edge = new clsEdge(nodes.get(start), nodes.get(target), weight);
        edges.add(edge);
    }

    /**
     * Function that computes the distance from start to destination
     * @param path
     * @return Integer - Sum of Edges of the Path
     */
    private static int startEndDistance(LinkedList<clsVertex> path) {
        int sum = 0;
        for (int i = 0; i < path.size() - 1 ; i++) {
            for (clsEdge edge : edges) {
                if (edge.getStart().equals(path.get(i)) && edge.getTarget().equals(path.get(i + 1))) {
                    sum += edge.getWeight();
                }
            }
        }
        return sum;
    }
}






