package Map;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Routing {

    private List<Node> nodes;
    private Map<Node, Boolean> vis = new HashMap<Node, Boolean>();
    int countOfVisited = 0;
    int countPrison = 0;
    private Map<Node, Node> prev = new HashMap<Node, Node>();
    private List<Node> visited = new ArrayList();
    private Node finalNode;

    public Routing()
    {

    }

    public List<Node> getRoute()
    {
        return visited;
    }

    public void setNodes(List<Node> nodes)
    {
        this.nodes = nodes;
    }

    public Routing(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void findTheRightPath() {

        finalNode = null;
        routAlgorithm(nodes.get(0), null);
       for(int i = 0 ; i<visited.size();i++) {
           System.out.println(visited.get(i).getName());
       }
    }

    public void exclusizeAlgorithm()
    {

    }

    public void routAlgorithm(Node startingNode, Node previousNode) {
        Node nextNode = null;
        int sum = 0;
        List<Node> list = new ArrayList<>();
        int indexToGet = giveRandom(startingNode.getDistances());
        for (Map.Entry<Node, Double> entry : startingNode.getDistances().entrySet()) {

            list.add(entry.getKey());
        }

        if (list.get(indexToGet).isVisited()) {
            nextNode = startingNode;
        } else {
            countOfVisited++;
            nextNode = list.get(indexToGet);
            previousNode = startingNode;
            startingNode.setVisited(true);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isVisited()) {
                sum++;
            }
        }
        if(previousNode.equals(startingNode)){
            countPrison++;
        }
        if (countPrison >= list.size()) {
            list.get(giveRandom(startingNode.getDistances())).setVisited(false);
        }
        if (sum == list.size()) {
            nextNode = previousNode;
        }

        if (countOfVisited >= nodes.size() && startingNode.getName().equals("Adelfiko")) {
            visited.add(startingNode);
            System.out.println("Finito");
        } else {
            list.clear();
            visited.add(startingNode);
            routAlgorithm(nextNode, previousNode);
        }
    }


    public int giveRandom(HashMap<Node, Double> map) {

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = ThreadLocalRandom.current().nextInt(0, map.size());

        return randomNum;
    }




   /* public HashMap<Node,List<Node>> graphMaker() {
        HashMap<Node,List<Node>> graph = new HashMap<>();
        List<Node> nodeToGo = new ArrayList<>();
        for(Node node : nodes){
            nodeToGo.clear();
            for(Map.Entry<Double,Node> entry : node.getDistances().entrySet()){
                nodeToGo.add(entry.getValue());
            }
            graph.put(node,nodeToGo);
            nodeToGo.clear();
        }
        return graph;
    }*/

}


