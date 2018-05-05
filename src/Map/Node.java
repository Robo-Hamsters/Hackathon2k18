package Map;

import java.util.HashMap;

public class Node {

   private double lat;
   private double lon;
   private HashMap<Double,Node> distances = new HashMap<>();

   public double getLat() {
      return lat;
   }
   public double getLon() {
      return lon;
   }
   public HashMap<Double, Node> getDestances() {
      return distances;
   }

   public void setLat(double lat) {
      this.lat = lat;
   }
   public void setLon(double lon) {
      this.lon = lon;
   }
   public void setDestances(HashMap<Double, Node> distances) {
      this.distances = distances;
   }
}
