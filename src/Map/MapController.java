package Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapController {

    @FXML
    WebView webView;
    @FXML
    Button btn_load;

    public void loadMapView(ActionEvent event) throws IOException {
        WebEngine webEngine = webView.getEngine();
        String location =
                new File(
                        System.getProperty("user.dir") + File.separator + "testing.html"
                ).toURI().toURL().toExternalForm();
        System.out.println(location);
        webEngine.load(location);
    }

    public void draw(HashMap<Double,Double> map) {
        for(Map.Entry<Double,Double> entry : map.entrySet())
        {
            //initialize(entry.getKey(),entry.getValue());
        }
    }
}
