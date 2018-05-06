package Map;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Routing {
    public Routing() {
    }

    private List<Node> nodes;
    private Map<Node, Boolean> vis = new HashMap<Node, Boolean>();
    int countOfVisited = 0;
    int countPrison = 0;
    private Map<Node, Node> prev = new HashMap<Node, Node>();
    private List<Node> visited = new ArrayList();
    private Node finalNode;

    public List<Node> getRoute() {
        return this.visited;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Routing(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void findTheRightPath() {

        finalNode = null;

        HashMap<List<Node>,Double> multy = new HashMap<>();

        //random(graphMaker());
        for(int i =0 ; i<15;i++){
            routAlgorithmWeights(nodes.get(0), null, null)
            multy.put(visited,calculateTotalDistance(visited));
            visited.clear();
        }
        double min = 1000;
        for(Map.Entry<List<Node>,Double> entry : multy.entrySet()){
            System.out.println();
        }
        /*for (int i = 0; i < visited.size(); i++) {
            System.out.println(visited.get(i).getName());
        }*/
    }


    public void routAlgorithm(Node startingNode, Node previousNode, List<Node> previousNodeList) {
        Node nextNode = null;
        int sum = 0;
        List<Node> list = new ArrayList<>();
        int indexToGet = giveRandom(startingNode.getDistances().size());
        for (Map.Entry<Node, Double> entry : startingNode.getDistances().entrySet()) {

            list.add(entry.getKey());
        }

        if (list.get(indexToGet).isVisited()) {
            nextNode = startingNode;
        } else {

            nextNode = list.get(indexToGet);
            previousNode = startingNode;
            previousNodeList = list;
            startingNode.setVisited(true);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isVisited()) {
                sum++;
            }
        }
        /*if(previousNode.isVisited() && startingNode.isVisited()){
            nextNode = previousNodeList.get(giveRandom(previousNode.getDistances().size()));
        }*/

        if (sum == list.size()) {
            nextNode = previousNode;
        }

        if (isFinished(startingNode)) {
            visited.add(startingNode);
            System.out.println("Finito");

        } else {
            while (nextNode.getName().equals("Adelfiko")) {
                nextNode = list.get(previousNode.getDistances().size());
            }
            list.clear();
            visited.add(startingNode);
            routAlgorithm(nextNode, previousNode, previousNodeList);

        }


    }

    public void routAlgorithmWeights(Node startingNode, Node previousNode, List<Node> previousNodeList) {
        Node nextNode = null;

        List<Node> list = new ArrayList<>();
        for (Map.Entry<Node, Double> entry : startingNode.getDistances().entrySet()) {

            list.add(entry.getKey());
        }
        if (getMinConNode(list).isVisited()) {
            nextNode = list.get(giveRandom(startingNode.getDistances().size()));
        } else {
            nextNode = getMinConNode(list);
        }


        if (isFinished(startingNode)) {
            visited.add(startingNode);
            System.out.println("Finito");
        } else {
            previousNode = startingNode;
            previousNodeList = list;
            visited.add(startingNode);
            startingNode.setVisited(true);
            routAlgorithmWeights(nextNode, previousNode, previousNodeList);

        }
    }


    private boolean isFinished(Node atLast) {
        boolean check = true;
        for (Node node : nodes) {
            if (node.isVisited()) {

            } else {
                check = false;
                break;
            }
        }
        if (check && atLast.getName().equals("Adelfiko")) {
            return true;
        } else {
            return false;
        }

    }


    public int giveRandom(int size) {
        return (int) (Math.random() * size);
    }


    private Node getMinConNode(List<Node> map) {
        int min = 9999;
        Node nodeWithMinCon = null;
        for (Node node : map) {
            if (node.getDistances().size() < min) {

                min = node.getDistances().size();
                nodeWithMinCon = node;
            } else if (node.getDistances().size() == min) {
                nodeWithMinCon = node;

            }
        }

        return nodeWithMinCon;
    }


    private double calculateTotalDistance(List<Node> nodes) {
        double totDist = 0;

        for (int i = 0; i < nodes.size() - 1; i++) {
            for (Map.Entry<Node, Double> entry : nodes.get(i).getDistances().entrySet()) {
                if (entry.getKey().equals(nodes.get(i + 1))) {
                    totDist += entry.getValue();
                    break;
                }
            }
        }
        return totDist;
    }

    private Node getOtherThanVisited(Node unwanted, List<Node> nodes) {
        for (Node node : nodes) {
            if (!unwanted.equals(node)) {

            } else {
                unwanted = node;
            }

        }
        return unwanted;
    }

}


