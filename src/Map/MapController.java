package Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;


public class MapController {

    @FXML
    private WebView webView;
    @FXML
    private Button btn_load;


    public void draw(){
        WebEngine webEngine = webView.getEngine();
        String location = null;
        try {
            location = new File(System.getProperty("user.dir") + File.separator + "testing.html").toURI().toURL().toExternalForm();
            webEngine.load(location);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void loadMapView(ActionEvent event) throws IOException {

        FileGeolocImport nodeImport = new FileGeolocImport("./nodes.txt", "./nodesToNodes.txt");
        nodeImport.saveToFile("./Filedata.txt");
        for(Node node : nodeImport.getNodes())
        {
            webView.getEngine().executeScript("createMarker("+node.getLat()+","+node.getLon()+");");
        }
        for(Node node : nodeImport.getNodes())
        {
            for(Map.Entry<Double, Node> distances : node.getDistances().entrySet())
            {
                webView.getEngine().executeScript("createPolyline("+node.getLat()+","+node.getLon()+","+distances.getValue().getLat()+","+distances.getValue().getLon()+");");
            }
        }
    }
}
