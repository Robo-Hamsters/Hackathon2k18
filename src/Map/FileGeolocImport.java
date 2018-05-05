package Map;

import Map.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileGeolocImport {
    private String fileName;
    private BufferedReader fileReader;
    private List<Node> nodes;

    public FileGeolocImport()
    {
        this.fileName = null;
    }
    public FileGeolocImport(String fileName)
    {
        this.fileName = fileName;
        try {
            this.fileReader = new BufferedReader(new FileReader(this.fileName));
            importData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }
    public List<Node> getNodes() {
        return nodes;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void importData()
    {
        nodes = new ArrayList<>();
        String fileString = new String();
        try {
            while((fileString = this.fileReader.readLine()) != null)
            {
                String[] arr = fileString.split("\t\t");
                String[] geo = arr[1].split(" ");
                nodes.add(new Node(arr[0], Double.parseDouble(geo[0]), Double.parseDouble(geo[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
