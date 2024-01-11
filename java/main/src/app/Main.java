
    package app;

import citymap.City;
import citymap.Map;
import citymap.Road;

    public class Main {
        public static void main(String[] args) {
            //cities
            City cityA = new City("City A");
            City cityB = new City("City B");
            City cityC = new City("City C");
            City cityD = new City("City D");
            City cityE = new City("City E");


            //roads
            //roads
            Road roadAB = new Road(cityA, cityB, 50, 2, true);
            Road roadBA = new Road(cityB, cityA, 50, 2, true);
            Road roadBC = new Road(cityB, cityC, 20, 3, true);
            Road roadCB = new Road(cityC, cityB, 20, 3, true);
            Road roadAC = new Road(cityA, cityC, 30, 1, true);
            Road roadCA = new Road(cityC, cityA, 30, 1, true);
            Road roadCD = new Road(cityC, cityD, 40, 4, true);
            Road roadDC = new Road(cityD, cityC, 40, 4, true);
            Road roadDE = new Road(cityD, cityE, 25, 2, true);
            Road roadED = new Road(cityE, cityD, 25, 2, true);
            Road roadCE = new Road(cityC, cityE, 80, 9, true);
            Road roadEC = new Road(cityE, cityC, 80, 9, true);
            Road roadAE = new Road(cityA, cityE, 150, 13, true);
            Road roadEA = new Road(cityE, cityA, 150, 13, true);



            //create map object and add cities and roads
            Map map = new Map();
            // add cities
            map.addCity(cityA);
            map.addCity(cityB);
            map.addCity(cityC);
            map.addCity(cityD);
            map.addCity(cityE);

            // add roads
            map.addRoad(roadAB);
            map.addRoad(roadBA);
            map.addRoad(roadBC);
            map.addRoad(roadCB);
            map.addRoad(roadAC);
            map.addRoad(roadCA);
            map.addRoad(roadCD);
            map.addRoad(roadDC);
            map.addRoad(roadDE);
            map.addRoad(roadED);
            map.addRoad(roadCE);
            map.addRoad(roadEC);
            map.addRoad(roadAE);
            map.addRoad(roadEA);


            // check if toString is working correctly
            map.printMapDetails();

            System.out.println();

            // Find the fastest path and time from source to destination
            int fastestTimeAC = map.findFastestPath(cityA, cityC);
            if (fastestTimeAC != Integer.MAX_VALUE) {
                System.out.println("Fastest time between City A and City C: " + fastestTimeAC);
            } else {
                System.out.println("No path found between City A and City C");
            }

            System.out.println();

            int fastestTimeAD = map.findFastestPath(cityA, cityD);
            if (fastestTimeAD != Integer.MAX_VALUE) {
                System.out.println("Fastest time between City A and City E: " + fastestTimeAD);
            } else {
                System.out.println("No path found between City A and City E");
            }

            System.out.println();

            int fastestTimeAE = map.findFastestPath(cityA, cityE);
            if (fastestTimeAE != Integer.MAX_VALUE) {
                System.out.println("Fastest time between City A and City E: " + fastestTimeAE);
            } else {
                System.out.println("No path found between City A and City E");
            }

            System.out.println();

            map.findCheapestPath(cityA, cityE);

            System.out.println();

            map.findCheapestPath(cityA, cityD);

            System.out.println();

            map.findCheapestPath(cityA, cityC);

            System.out.println();

            map.findBestPath(cityA, cityE);


        }
    }


