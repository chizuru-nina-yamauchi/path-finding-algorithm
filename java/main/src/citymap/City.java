
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

        // generate a summary of all roads in the map
        private String getRoadsSummary() {
            // StringBuilder to construct the summary string
            StringBuilder summary = new StringBuilder("[");

            //iterate through each road in the lists of roads
            for (Road road : roads) {
                // append the summary of each road along with a comma and space
                summary.append(road.getSummary()).append(", ");
            }
            // check if there are any roads in the list
            if (!roads.isEmpty()) {
                summary.setLength(summary.length() - 2); // Remove the trailing comma and space
            }
            // closing the summary with ']'
            summary.append("]");
            // convert the StringBuilder to a String and return
            return summary.toString();
        }

    }
