package Map;

import java.util.HashMap;

public class Node {

   private double lat;

   private double lon;

   public double getLat() {
      return lat;
   }

   public void setLat(double lat) {
      this.lat = lat;
   }

   public double getLon() {
      return lon;
   }

   public void setLon(double lon) {
      this.lon = lon;
   }

   public HashMap<Double, String> getDestances() {
      return destances;
   }

   public void setDestances(HashMap<Double, String> destances) {
      this.destances = destances;
   }

   HashMap<Double,String> destances = new HashMap<>();


}
