package Map;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Routing {
    public Routing() {
    }

    double min =10000;
    private List<Node> nodes;
    private Map<Node, Boolean> vis = new HashMap<Node, Boolean>();
    int countOfVisited = 0;
    int countPrison = 0;
    private Map<Node, Node> prev = new HashMap<Node, Node>();
    private List<Node> visited = new ArrayList();
    private List<Node> route = null;
    private Node finalNode;

    public List<Node> getRoute() {
        return this.route;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Routing(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void findTheRightPath() {
        finalNode = null;
        routAlgorithmWeights(nodes.get(0));
        System.out.println(calculateTotalDistance(visited));
        System.out.println(visited.size());
        for(int i =0 ; i<150;i++){
            routAlgorithmWeights(nodes.get(0));
            if(calculateTotalDistance(visited) < min)
            {
                if(visited.size() == 10)
                {
                    min = calculateTotalDistance(visited);
                    route = new ArrayList<>();
                    for(Node node: visited)
                    {
                        route.add(node);
                    }
                }
            }
            visited.clear();
        }
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

        } else {
            while (nextNode.getName().equals("Adelfiko")) {
                nextNode = list.get(previousNode.getDistances().size());
            }
            list.clear();
            visited.add(startingNode);
            routAlgorithm(nextNode, previousNode, previousNodeList);

        }


    }

    public void routAlgorithmWeights(Node startingNode) {
        Node nextNode = null ;
       /* List<Node> list = new ArrayList<>();
        for (Map.Entry<Node, Double> entry : startingNode.getDistances().entrySet()) {

            list.add(entry.getKey());
        }

        if(startingNode.isVisited()){
            nextNode = getMaxConNode(list);
        }else{
            nextNode = getMinConNode(list);
            startingNode.setVisited(true);
        }


        routAlgorithmWeights(nextNode);
*/

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
        } else {
            if(visited.contains(startingNode)){
                startingNode.setVisited(true);
                routAlgorithmWeights(nextNode);
            }else{
                visited.add(startingNode);
                startingNode.setVisited(true);
                routAlgorithmWeights(nextNode);
            }
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
        if (check && atLast.equals(new Node(41.014645, 23.457354))) {
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
            } /*else if (node.getDistances().size() == min) {
                nodeWithMinCon = node;

            }*/
        }

        return nodeWithMinCon;
    }

    private Node getMaxConNode(List<Node> map) {
        int max = -9999;
        Node nodeWithMinCon = null;
        for (Node node : map) {
            if (node.getDistances().size() > max) {

                max = node.getDistances().size();
                nodeWithMinCon = node;
            } /*else if (node.getDistances().size() == min) {
                nodeWithMinCon = node;

            }*/
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


