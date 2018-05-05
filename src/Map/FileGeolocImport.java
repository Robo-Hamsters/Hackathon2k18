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

    public FileGeolocImport(String fileNodes, String fileDistances)
    {
        this.fileNodes = fileNodes;
        this.fileDistances = fileDistances;
        try {
            this.fileNodeReader = new BufferedReader(new FileReader(this.fileNodes));
            this.fileDistancesReader = new BufferedReader(new FileReader(this.fileDistances));
            importNodes();
            importDistancesFormFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Node> getNodes() {
        return nodes;
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
                nodes.add(new Node(Double.parseDouble(geo[0]), Double.parseDouble(geo[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importDistances()
    {/*
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
                    //distanceAPI.retriveDistanceFILE("./Filedata.txt");
                    nodes.get(nodes.indexOf(toAdd)).getDistances().put(distanceAPI.getDistance(), otherNode);
                }
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void importDistancesFormFile()
    {
        String fileString = new String();
        List<Node> origins = new ArrayList<>();
        List<Node> destinations = new ArrayList<>();
        List<Double> distances = new ArrayList<>();

        try {
            while((fileString = this.fileDistancesReader.readLine()) != null)
            {
                String[] org1 = fileString.split("[<,>]");

                origins.add(new Node(Double.parseDouble(org1[1]), Double.parseDouble(org1[2])));
                destinations.add(new Node(Double.parseDouble(org1[4]), Double.parseDouble(org1[5])));
                distances.add(Double.parseDouble(org1[7]));

                for(Node node : nodes)
                {
                    for(int i=0; i<origins.size(); i++)
                    {
                        if(node.equals(origins.get(i)))
                        {
                            nodes.get(nodes.indexOf(node)).getDistances().put(origins.get(i), distances.get(i));
                        }
                    }
                }
            }
            System.out.println();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String fileName)
    {   /*
        try {
            FileWriter  outfile = new FileWriter(fileName);
            for(Node node : nodes)
            {
                outfile.write(node.getName()+":"+node.getDistances()+"\n");
            }
            outfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
