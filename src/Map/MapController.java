package Map;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;


public class MapController {

    @FXML
    private WebView webView;
    @FXML
    private Button btn_load;
    @FXML
    private Button clearMapBtn;
    @FXML
    private Button openFileBtn;
    @FXML
    private Label filePath;
    @FXML
    private Button secondaryAlgorithm;

    @FXML
    private Label labelKm;

    public Label getLabelKm() { return labelKm;}

    public void setLabelKm(Label labelKm) { this.labelKm = labelKm; }
    private String nodeFile = "./nodes.txt";

    private String distancesFile = "";

    private Routing routing = new Routing();

    private FileGeolocImport nodeImport = null;

    public void loadMapView(){
        WebEngine webEngine = webView.getEngine();
        String location = null;
        try {
            location = new File(System.getProperty("user.dir") + File.separator + "testing.html").toURI().toURL().toExternalForm();
            webEngine.load(location);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void walkMap(List<Node> route)
    {

        int clr = 0x0000ff;
        webView.getEngine().executeScript("clearPolys();");
        for(int i=0; i<route.size()-1; i++)
        {
            PauseTransition delay = new PauseTransition(Duration.millis(i*100));
            delay.playFromStart();

            clr -= 30;
            String colour = Integer.toHexString(Math.abs(clr));
            String argument = "createPolyline(" + route.get(i).getLat() + "," + route.get(i).getLon() + "," + route.get(i + 1).getLat() + "," + route.get(i + 1).getLon() + ",\"" + colour + "\");";

            delay.setOnFinished(event1 -> webView.getEngine().executeScript(argument));
            delay.play();
        }
    }

    public void secondaryAlgorithm(ActionEvent event)
    {
        routing.setNodes(nodeImport.getNodes());
        routing.findPathWithConditionalNodes();
        List<Node> route = routing.getRoute();
        walkMap(route);
    }

    public void runAlgorithm1(ActionEvent event)
    {
        routing.setNodes(nodeImport.getNodes());
        routing.findTheRightPath();
        List<Node> route = routing.getRoute();
        walkMap(route);
    }

    public void loadMarkers(ActionEvent event) throws IOException {

        if(!distancesFile.equals(""))
        {
            nodeImport = new FileGeolocImport(nodeFile, distancesFile);
            for(Node node : nodeImport.getNodes())
            {
                webView.getEngine().executeScript("createMarker("+node.getLat()+","+node.getLon()+");");
            }
            for(Node node : nodeImport.getNodes())
            {
                for(Map.Entry<Node, Double> distances : node.getDistances().entrySet())
                {
                    webView.getEngine().executeScript("createPolyline("+node.getLat()+","+node.getLon()+","+distances.getKey().getLat()+","+distances.getKey().getLon()+", \"FF0000\");");
                }
            }
        }
    }

    public void clearMap(ActionEvent event)
    {
        webView.getEngine().executeScript("clearMarkers();clearPolys();");
    }

    public void openFile(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        File distancesFile = fc.showOpenDialog(null);
        if(distancesFile.exists()) {
            this.distancesFile = distancesFile.toString();
            filePath.setText(distancesFile.toString());
        }
    }

    public void openAbout(ActionEvent event)
    {
        try {
            File index = new File("About/About.html");
            Desktop.getDesktop().open(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
