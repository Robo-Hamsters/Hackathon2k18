package Map;

import java.util.HashMap;

public class Node {

   private double lat;
   private double lon;
   private String name;
   private HashMap<Double,Node> distances = new HashMap<>();

   public Node(String name)
   {
      this.name = name;
      this.lon =0;
      this.lat = 0;
   }
   public Node(String name, double lat, double lon)
   {
      this.name = name;
      this.lat = lat;
      this.lon = lon;
   }

   public double getLat() {
      return lat;
   }
   public double getLon() {
      return lon;
   }
   public HashMap<Double, Node> getDistances() {
      return distances;
   }
   public String getName()
   {
      return name;
   }

   public void setLat(double lat) {
      this.lat = lat;
   }
   public void setLon(double lon) {
      this.lon = lon;
   }
   public void setDistances(HashMap<Double, Node> distances) {
      this.distances = distances;
   }

   @Override
   public String toString()
   {
      return name;
   }
}
