package Map;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NodeDistance {
    private Node origins;
    private Node destinations;
    private double distance;

    public Node getOrigins() {
        return origins;
    }
    public Node getDestinations() {
        return destinations;
    }
    public double getDistance()
    {
        return distance;
    }

    public void setOrigins(Node origins) {
        this.origins = origins;
    }
    public void setDestinations(Node destinations) {
        this.destinations = destinations;
    }

    public void retriveDistance() throws IOException
    {
        URL urlObj = null;
        try {
            urlObj = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origins.getLat()+","+origins.getLon()+"&destinations="+destinations.getLat()+","+destinations.getLon()+"&key=AIzaSyCmA2N-U9gtKDHf-Zmh1ODAtKnyJ54iJBI");
            URLConnection uc = urlObj.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()))) {
                String inputLine = new String();
                String ln;
                do{
                    ln = in.readLine();
                    inputLine += ln;
                }while(ln != null);
                JSONObject responce = new JSONObject(inputLine);
                String distanceStr = responce.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").get("value").toString();
                distance = Double.parseDouble(distanceStr)/1000;
            } catch (IOException e) {
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
