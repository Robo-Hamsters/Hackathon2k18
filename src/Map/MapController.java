package Map;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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

    public void runAlgorithm1(ActionEvent event)
    {
        int clr = 0x0000ff;
        routing.setNodes(nodeImport.getNodes());
        routing.findTheRightPath();
        List<Node> route = routing.getRoute();
        webView.getEngine().executeScript("clearPolys();");

        for(int i=0; i<route.size()-1; i++)
        {
            PauseTransition delay = new PauseTransition(Duration.seconds(i+1));
            delay.playFromStart();

            clr -= 30;
            String colour = Integer.toHexString(Math.abs(clr));
            String argument = "createPolyline(" + route.get(i).getLat() + "," + route.get(i).getLon() + "," + route.get(i + 1).getLat() + "," + route.get(i + 1).getLon() + ",\"" + colour + "\");";


            delay.setOnFinished(event1 -> webView.getEngine().executeScript(argument));
            delay.play();
        }
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
}
