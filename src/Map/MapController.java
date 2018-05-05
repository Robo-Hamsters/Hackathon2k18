package Map;

import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class MapController {

    @FXML
    private WebView webView;
    @FXML
    private Button btn_load;


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

    public void loadMarkers(ActionEvent event) throws IOException {

        FileGeolocImport nodeImport = new FileGeolocImport("./nodes.txt", "./nodesToNodes.txt");
        for(Node node : nodeImport.getNodes())
        {
            webView.getEngine().executeScript("createMarker("+node.getLat()+","+node.getLon()+");");
        }
    }
}
