
    package citymap;

import java.util.ArrayList;
import java.util.List;

    public class City {

        //attributes
        private String name;
        private List<Road> roads;

        //constructor
        public City(String name) { // there might not be any road yet and no argument for road
            this.name = name;
            roads = new ArrayList<>();
        }

        public City() {
            // default constructor
        }

        // getters and setters for name and roads
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Road> getRoads() {
            return roads;
        }

        public void setRoads(List<Road> roads) {
            this.roads = roads;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", roads=" + getRoadsSummary() +
                    '}';
        }

        private String getRoadsSummary() {
            StringBuilder summary = new StringBuilder("[");
            for (Road road : roads) {
                summary.append(road.getSummary()).append(", ");
            }
            if (!roads.isEmpty()) {
                summary.setLength(summary.length() - 2); // Remove the trailing comma and space
            }
            summary.append("]");
            return summary.toString();
        }

    }
