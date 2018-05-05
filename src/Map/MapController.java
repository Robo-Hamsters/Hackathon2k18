package Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MapController {

    @FXML
    Button btn_load = new Button();

    @FXML
    public void loadMapView(javafx.event.ActionEvent event) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        String webData = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />\n" +
                "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "<style>html,body{height:100%;margin:0;padding:0;}#map_canvas{height:100%;}</style>\n" +
                "<script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js?sensor=false\"></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "function initialise() {\n" +
                "    var options = { zoom:13, mapTypeId:google.maps.MapTypeId.ROADMAP, center:new google.maps.LatLng(40.777949,22.5753938)};\n" +
                "    var map = new google.maps.Map(document.getElementById('map_canvas'), options);\n" +
                "    var marker;\n" +
                "\tmarker = new google.maps.Marker({\n" +
                "\t\tposition:new google.maps.LatLng(40.777949,22.5753938), map:map, title:\"\"});\n" +
                "\t\tgoogle.maps.event.addListener(marker, 'click', function() { document.location = \"\"; });\n" +
                "}\n" +
                "</script>\n" +
                "</head>\n" +
                "<body onload=\"initialise()\">\n" +
                "<div id=\"map_canvas\"></div>\n" +
                "</body>\n" +
                "</html>";
        webEngine.load(webData);
    }
}
