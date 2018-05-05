package Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.IOException;

public class MapController {

    @FXML
    WebView webView;
    @FXML
    Button btn_load;

    public void loadMapView(ActionEvent event) throws IOException
    {
        WebEngine webEngine = webView.getEngine();
        String location =
                new File(
                        System.getProperty("user.dir") + File.separator + "testing.html"
                ).toURI().toURL().toExternalForm();
        System.out.println(location);
        webEngine.load(location);
    }
}
