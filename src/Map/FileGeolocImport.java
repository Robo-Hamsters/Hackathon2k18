package Map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileGeolocImport {
    private String fileNodes;
    private String fileDistances;
    private BufferedReader fileNodeReader;
    private BufferedReader fileDistancesReader;
    private List<Node> nodes;

    public FileGeolocImport(String fileNodes)
    {
        this.fileNodes = fileNodes;
        try {
            this.fileNodeReader = new BufferedReader(new FileReader(this.fileNodes));
            importNodes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FileGeolocImport(String fileNodes, String fileDistances)
    {
        this.fileNodes = fileNodes;
        this.fileDistances = fileDistances;
        try {
            this.fileNodeReader = new BufferedReader(new FileReader(this.fileNodes));
            this.fileDistancesReader = new BufferedReader(new FileReader(this.fileDistances));
            importNodes();
            importDistances();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void importNodes()
    {
        nodes = new ArrayList<>();
        String fileString = new String();
        try {
            while((fileString = this.fileNodeReader.readLine()) != null)
            {
                String[] arr = fileString.split("\t\t");
                String[] geo = arr[1].split(" ");
                nodes.add(new Node(arr[0], Double.parseDouble(geo[0]), Double.parseDouble(geo[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importDistances()
    {
        NodeDistance distanceAPI = new NodeDistance();
        String fileString = new String();
        Node toAdd = null;
        Node otherNode = null;
        try {
            while((fileString = this.fileDistancesReader.readLine()) != null)
            {
                String[] arr = fileString.split(":");
                for(Node node : nodes)
                {
                    if(node.getName().equals(arr[0]))
                    {
                        toAdd = node;
                        break;
                    }
                }

                String[] nodesStrArr = arr[1].split(",");
                for(String nodeStr : nodesStrArr)
                {
                    for(Node node : nodes)
                    {
                        if(node.getName().equals(nodeStr))
                        {
                            otherNode = node;
                            break;
                        }
                    }

                    distanceAPI.setOrigins(toAdd);
                    distanceAPI.setDestinations(otherNode);
                    distanceAPI.retriveDistance();

                    nodes.get(nodes.indexOf(toAdd)).getDistances().put(distanceAPI.getDistance(), otherNode);

                }
            }
            Routing routing = new Routing();
            routing.findTheRightPath(nodes);

            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
