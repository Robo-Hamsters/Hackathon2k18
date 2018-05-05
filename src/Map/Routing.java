package Map;

import java.util.*;

public class Routing {

    private List<Node> nodes;
    private ArrayList<Node> route = new ArrayList<>(10);

    public List<Node> getNodes()
    {
        return nodes;
    }

    public Routing(List<Node> nodes)
    {
        this.nodes = nodes;
    }

    public void walkRoute()
    {
        System.out.println(route);
    }

    public void antCollony(Node startingPoint)
    {
        if(startingPoint.isVisited())
            walkRoute();
        else
        {
            Random generator = new Random();
            Object[] values = startingPoint.getDistances().values().toArray();
            Node randomValue;
            int tryies =-1;
            do
            {
                tryies++;
                randomValue = (Node)values[generator.nextInt(values.length)];
                if(randomValue.getDistances().size() >= tryies)
                    break;
            }while(randomValue.isVisited());
            Node node = nodes.get(nodes.indexOf(startingPoint));
            route.add(randomValue);
            node.setVisited(true);
            antCollony(randomValue);
        }
    }

    public void findTheRightPath(List<Node> nodes) {
        /*
        this.nodes = nodes;

        Node finalNode = null;

        for (Node node : nodes) {
            if (node.getName().equals("Adelfiko")) {
                finalNode = node;
            }
        }

        test(nodes.get(0));
        */
    }

    public void test(Node startingNode) {
        /*
        giveRandom(startingNode.getDistances());
        int min = 10;
        HashMap<Double, Node> map;
        map = startingNode.getDistances();
        Collection<Node> colNode = map.values();
        for (Node current : colNode) {
            if (current.getDistances().size() < min) {
                if (!startingNode.isVisited()) {
                    min = current.getDistances().size();
                }
            }
        }
        */
    }

    public void random(Node startingNode) {
        /*
        HashMap<Double, Node> map;
        map = startingNode.getDistances();
        Collection<Node> colNode = map.values();
        for (Node current : colNode) {

            giveRandom(current.getDistances());
            }
            */
    }


    public void giveRandom(HashMap<Double,Node> map){
        int r = (int) (Math.random() * (map.size()));
        System.out.println(r);
    }

}


