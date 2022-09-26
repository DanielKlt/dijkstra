# README

## Autor

* Name: Daniel Kalotai

## Programmversionen
* Java 14 2020-03-17
* IntelliJ IDEA 2020.1.2 (Ultimate Edition)
   Build #IU-201.7846.76, built on June 1, 2020
   

## Der Algorithmus
Bei der Umsetzung habe ich folgende Idee verfolgt:
Die Knoten in zwei Mengen zu unterteilen, einmal unbesuchte und besuchte Knoten. Beim Start des Algorithmus befinden sich alle
Knoten in der Menge der unbesuchten Koten, das heißt sie wurden noch nicht ausgewertet. Ein Knoten wird aus
der unbesuchten Menge in die besuchte Menge verschoben, wenn der kürzeste Weg vom Start-Knoten dort hin gefunden wurde.

Die Distanz vom Start-Knoten zu allen Knoten wird am Anfang mit dem Wert positiv Unendlich initialisiert.
Alle Knoten bis auf den Start-Knoten befinden sich in der Menge der unbesuchten Knoten.

Es gibt so viele Iterationen, bis die Menge der unbesuchten Knoten leer ist.
Bei jeder Iteration wird aus den unbesuchten Knoten wird der mit der kürzesten Distanz genommen.
Von dort aus werden alle Kanten zu dieser Quelle ausgelesen und geprüft, ob eine Distanz dadurch verringert werden kann.
Sprich, es wird geprüft, ob durch diese Route die Distanz vom Start-Knoten geringer ist als der direkte Weg.
Wenn diese Distanz kürzer ist, wird Sie in die zu den noch auszuwertetenden Knoten hinzugefügt.

Was noch eine effizientere Implementation wäre, wäre der Algorithmus mit einer Priority Queue. Diese haben wir allerdings erst später in der Vorlesung Algorithmen kennen gelernt.

## Pseudo-Code
Foreach node set distance[node] = POSITIVE_INFINITY  
SettledNodes = empty  
UnSettledNodes = empty

Add sourceNode to UnSettledNodes  
distance[sourceNode]= 0

while (UnSettledNodes > 0) {  
    evaluationNode = getNodeWithLowestDistance(UnSettledNodes)  
    remove evaluationNode from UnSettledNodes  
    add evaluationNode to SettledNodes  
    evaluatedNeighbors(evaluationNode)  
}  

getNodeWithLowestDistance(UnSettledNodes){  
    find the node with the lowest distance in UnSettledNodes and return it  
}

evaluatedNeighbors(evaluationNode){  
    Foreach destinationNode which can be reached via an edge from evaluationNode AND which is not in SettledNodes {  
        edgeDistance = getDistance(edge(evaluationNode, destinationNode))  
        newDistance = distance[evaluationNode] + edgeDistance  
        if (distance[destinationNode]  > newDistance ) {  
            distance[destinationNode]  = newDistance  
            add destinationNode to UnSettledNodes  
        }
    }
}
## Dokumentation

* **clsMain.java** -> Startet das Programm, hier wird festgelegt, wie der Graph aussieht, sowie zwischen welchen Knoten der schnellste Weg ist.

* **clsDijkstraManager** -> Bekommt einen Graphen, sowie Start Knoten und Ziel Knoten und findet den kürzesten Weg zwischen beiden (Genauere Beschreibung siehe Algorithmus)

* **clsEdge** -> Modelliert Kanten zwischen zwei Knoten.

* **clsVertex** -> Modelliert Knoten

* UML ist in der UML.png Datei zu finden.

## Fragen

* __Nach welchem Prinzip arbeitet der Algorithmus von Dijkstra? Erläutern Sie__    <br/>
     <br/> 
Der Dijkstra-Algorithmuss arbeitet nach dem prinzip der Greedy Algorithmen, d.h. 
er arbeitet Schrittweise den Folgezustand aus, der das beste Ergebnis bietet.
Dies ist auch beim Dijkstra Algorithmus der Fall. Es wird vom Startpunkt aus jede 
Kante zum nächstes Knoten betrachtet und bekommt den Wert der Distanz zugeordnet. Von 
dort aus werden sich ebenfalls alle Kanten angeschaut und die Summe der Distanzen vom Start Knoten 
bis zu dem jetzigen Knoten wird als Wert hinterlegt.
Nun wird die Summe mit der Summe der Nachbarknoten verglichen, es wird die Kante mit der kleinsten Distanze/kleinsten 
Summe gewählt und dieses Schema wird erneut wiederholt.
Dies gewährleistet die geringste Distanz.

* __Wie könnten Sie Ihre Lösung erweitern, so dass nicht nur der kürzeste Weg,
    sondern auch der ökologisch beste Weg (”Green Route”) ermittelt werden kann? Was
    müssten Sie hierfür an Ihrem Programm ändern/erweitern?__  
    <br/>
    In der Implementierung des Algorithmus wird als Kosten nur die Distanz zwischen zwei Knoten in betracht  
    gezogen. Es müsste ein neuer Parameter eingeführt werden, welcher in die Kosten zwischen zwei Knoten mit einfließt.
    Wie z.B. der CO2 Ausstoß von dieser Strecke oder ob Stau herrscht. Es müsste eine Funktion eingeführt
    werden, die unter der Berücksichtigung dieser Parameter inklusive der Distanz die Kosten für eine "Green Route"
    berechnet. 
    

## Methoden

### clsMain
* **void** addEdge(int start, int target, int weight)  

* **int** startEndDistance(LinkedList<clsVertex> path)
### clsEdge
* **constructor** clsEdge(clsVertex start, clsVertex target, int weight)

* getter Klasse clsEdge

* **String** toString()
### clsVertex
* **constructor** clsVertex(String name)

* getter & setter Klasse clsVertex
### clsGraph
* **constructor** clsGraph(List<clsEdge> edges, List<clsVertex> vertexes)

* getters Klasse clsGraph
### clsDijkstraManager
* **constructor** clsDijkstraManager(clsGraph graph)

* **void** init(clsVertex start)

* **boolean** isVisited(clsVertex vert)

* **clsVertex** getSmallest(Set<clsVertex> vertexes)

* **void** findFastestWay(clsVertex vertex)

* **List<clsVertex>** getNeighbours(clsVertex vert)

* **Integer** getWeight(clsVertex vertex, clsVertex target)

* **Integer** getShortestDistance(clsVertex vert)

* **LinkedList<clsVertex>** getPath(clsVertex target)

## Starten des Programms

* Der untere Befehl hängt davon ab, in welchem Pfad man sich befindet, er muss ggf. angepasst werden
* `cd dijsktra/src`
* `java clsMain`
* Anschließend wird nach dem Start-Knoten gefragt (Input muss ein Integer sein, sonst schließt das Programm)
* Danach muss der End-Knoten eingegeben werden (Input muss ein Integer sein, sonst schließt das Programm)

Siehe QuickStart.png

