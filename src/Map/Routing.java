package Map;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class Routing {

    private List<Node> nodes;

    public void findTheRightPath(List<Node> nodes) {
        this.nodes = nodes;
        Node finalNode = null;

        for (Node node : nodes) {
            if (node.getName().equals("Adelfiko")) {
                finalNode = node;
            }
        }

        test(nodes.get(0));
    }

    public void test(Node startingNode) {

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

    }



    public void random(Node startingNode) {

        HashMap<Double, Node> map;
        map = startingNode.getDistances();
        Collection<Node> colNode = map.values();
        for (Node current : colNode) {

            giveRandom(current.getDistances());
            }


    }


    public void giveRandom(HashMap<Double,Node> map){
        int r = (int) (Math.random() * (map.size()));
        System.out.println(r);
    }

}


