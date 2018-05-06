package Map;

import java.util.HashMap;

public class Node {

   private double lat;
   private double lon;
   private HashMap<Node, Double> distances = new HashMap<>();
   private boolean visited;
   private String name;


   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Node()
   {
      this.lon =0;
      this.lat = 0;
   }
   public Node(double lat, double lon)
   {
      this.lat = lat;
      this.lon = lon;
   }

   public double getLat() {
      return lat;
   }
   public double getLon() {
      return lon;
   }
   public HashMap<Node, Double> getDistances() {
      return distances;
   }

   public void setLat(double lat) {
      this.lat = lat;
   }
   public void setLon(double lon) {
      this.lon = lon;
   }
   public void setDistances(HashMap<Node, Double> distances) {
      this.distances = distances;
   }

   @Override
   public String toString()
   {
      return lat+", "+lon;
   }

   @Override
   public boolean equals(Object o)
   {
      return (lat == ((Node)o).lat && lon == ((Node)o).lon);
   }

   public boolean isVisited() {
      return visited;
   }

   public void setVisited(boolean visited) {
      this.visited = visited;
   }
}
