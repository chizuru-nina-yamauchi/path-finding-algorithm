package citymap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;

public class Map {
    //attributes
    private List<City> cities;
    private List<Road> roads;

    //constructor
    public Map() {
        cities = new ArrayList<>();
        roads = new ArrayList<>();
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void addRoad(Road road) {
        City source = road.getSource();
        City destination = road.getDestination();

        // add the road to the source city's list of roads
        source.getRoads().add(road);

        // add the road to the map's list of roads
        roads.add(road);

        // if the road is bidirectional, also add the reverse road
        if (road.isBidirectional()) {
            Road reverseRoad = new Road(destination, source, road.getCost(), road.getTime(), road.isBidirectional());

            roads.add(reverseRoad);
        }
    }


    // method to print map details for debugging
    public void printMapDetails() {
        System.out.println("Cities: " + cities);
        System.out.println();
        System.out.println("Roads: " + roads);
    }

    /* the concept:Dijkstra's algorithm
    Let distance of start vertex from start vertex = 0
    Let distance of all other vertices from start = infinity
    While vertices remain unvisited
    Visit unvisited vertex with smallest known distance from start vertex(call this 'current vertex')
    For each unvisited neighbour of the current vertex
    Calculate the distance from start vertex
    If the calculated distance of this vertex is less than the known distance
    Update shortest distance to this vertex
    Update the previous vertex with the current vertex
     */

    public void findFastestPath(City source, City destination) {
        // initialize distance array to store shortest distances from the source
        HashMap<City, Integer> distance = new HashMap<>();
        // initialize priority queue for selecting the next closest city
        // Priority Queue = A FIFO data structure that serves elements with highest priorities first before elements with lower priority
        PriorityQueue<City> pq = new PriorityQueue<>(Comparator.comparingInt(distance::get)); // distance::get = (city)-> distance.get(city)
        // initialize predecessor map to backtrack the shortest path
        // key: represent the city for which the user is finding the fastest path
        // value: represents the previous city in the shortest path from the source to the key city
        HashMap<City, City> predecessor = new HashMap<>(); // predecessor = previous vertex


        // initialize distance of all cities as Infinity and their predecessors as null
        for (City city : cities) {
            distance.put(city, Integer.MAX_VALUE);
            predecessor.put(city, null);
        }
        // set distance of source city to 0 as it is the starting point
        distance.put(source, 0);
        pq.offer(source); // add the source city to the priority queue

        // Dijkstra's algorithm to find the fastest path
        while (!pq.isEmpty()) {
            City current = pq.poll(); // get the city with the shortest distance from the priority queue
            for (Road road : current.getRoads()) {
                City nextCity = road.getDestination();
                int newDistance = distance.get(current) + road.getTime(); // calculate the new distance to the neighbour

                // if the new distance is shorter than the current recorded distance to the neighbour
                // update the distance, predecessor, and add the neighbour to the priority queue
                if (newDistance < distance.get(nextCity)) {
                    distance.put(nextCity, newDistance);
                    predecessor.put(nextCity, current);
                    pq.offer(nextCity);
                }
            }
            // backtrack through reversed roads to explore paths in both directions
            for (Road road : roads) {
                if (road.getDestination().equals(current)) {
                    City previousCity = road.getSource();
                    int newDistance = distance.get(current) + road.getTime();

                    // if the new distance is shorter than the current recorded distance to the neighbour
                    // update the distance, predecessor, and add the neighbour to the priority queue
                    if (newDistance < distance.get(previousCity)) {
                        distance.put(previousCity, newDistance);
                        predecessor.put(previousCity, current);
                        pq.offer(previousCity);
                    }
                }
            }


        }

        // get the fastest time from distance map for the destination city
        int fastestTime = distance.get(destination);

        // construct the fastest path based on predecessor, source and destination
        List<City> fastestPath = constructPath(predecessor, source, destination);

        // check if a path was found(= the fastest time is not the maximum possible value)
        if (fastestTime != Integer.MAX_VALUE) {
            System.out.println("Fastest Path between " + source.getName() + " and " + destination.getName() + " : ");
            // print each city in the fastest path
            for (City city : fastestPath) {
                System.out.print(city.getName() + " - ");
            }
            System.out.println();
            System.out.println("Total Time: " + fastestTime);
        } else {
            System.out.println("No path found between " + source.getName() + " and " + destination.getName());
        }

    }

    private List<City> constructPath(HashMap<City, City> predecessor, City source, City destination) {
        List<City> path = new ArrayList<>();
        int totalTime = 0;
        City current = destination;

        while (current != null) {
            City previousCity = predecessor.get(current);

            if (previousCity != null) {
                Road road = getRoad(previousCity, current);
                if (road != null) {
                    totalTime += road.getTime();
                } else {
                    // handle the case where there is no road between the current and previous city
                    System.out.println("No road found between " + previousCity.getName() + " and " + current.getName());
                    return path;
                }
            }

            path.add(current);
            current = previousCity;
        }

        Collections.reverse(path);
        System.out.println("Total Time between " + source.getName() + " and " + destination.getName() + " is " + totalTime);
        return path;
    }

    private Road getRoad(City source, City destination) {
        for (Road road : roads) {
            if (road.getSource().equals(source) && road.getDestination().equals(destination)) {
                return road;
            }
        }
        return null;
    }

    public void findCheapestPath(City source, City destination) {
        HashMap<City, Integer> cost = new HashMap<>();  // HasMap to store the cost to reach each city
        HashMap<City, City> predecessor = new HashMap<>(); // HashMap to store the predecessor city for each city in the path
        HashMap<City, Boolean> visited = new HashMap<>(); // HashMap to track visited cities during traversal
        PriorityQueue<City> pq = new PriorityQueue<>(Comparator.comparingInt(cost::get));

        // initialize costs and predecessors
        for (City city : cities) {
            cost.put(city, Integer.MAX_VALUE); // set initial cost to reach each city as maximum possible value
            predecessor.put(city, null); // initialize predecessor of each city as null
            visited.put(city, false); // mark each city as not visited initially
        }

        cost.put(source, 0);  // cost to reach the source city is set as 0
        pq.offer(source); // add the source city to the priority queue for processing

        // Dijkstra's algorithm to find the cheapest path
        while (!pq.isEmpty()) {
            City current = pq.poll(); // get the city with the lowest cost from the priority queue
            if (visited.get(current)) continue; // Skip if the city has already been visited
            visited.put(current, true); // Mark the current city as visited

            for (Road road : current.getRoads()) {
                City nextCity = road.getDestination();
                int newCost = cost.get(current) + road.getCost(); // calculate the new cost to reach the neighbour city

                //update cost and predecessor if a cheaper path is found
                if (newCost < cost.get(nextCity)) {
                    cost.put(nextCity, newCost); // update the cost to reach the neighbour city
                    predecessor.put(nextCity, current); // set the predecessor of the neighbour city
                    pq.offer(nextCity); // add the neighbour city to the priority queue for further exploration
                }
            }
        }

        List<City> cheapestPath = constructCheapestPath(predecessor, source, destination);
        printCheapestPathDetails(source, destination, cheapestPath, cost);

    }

    private List<City> constructCheapestPath(HashMap<City, City> predecessor, City source, City destination) {
        List<City> path = new ArrayList<>();
        int totalCost = 0;
        City current = destination;

        while (current != null) {
            City previousCity = predecessor.get(current);

            if (previousCity != null) {
                Road road = getRoad(previousCity, current);
                if (road != null) {
                    totalCost += road.getCost();
                } else {
                    System.out.println("No road found between " + previousCity.getName() + " and " + current.getName());
                    return path;
                }
            }

            path.add(current);
            current = previousCity;
        }

        Collections.reverse(path);
        System.out.println("Total Cheapest Cost between " + source.getName() + " and " + destination.getName() + " is " + totalCost);
        return path;
    }



    public void printCheapestPathDetails(City source, City destination, List<City> cheapestPath, HashMap<City, Integer> cost) {
        System.out.println("Cheapest Path between " + source.getName() + " and " + destination.getName() + ": ");
        int totalCost = 0;
        for (City city : cheapestPath) {
            int cityCost = cost.get(city);
            System.out.println(city.getName() + " - Cost: " + cityCost);
        }
        totalCost = cost.get(destination);
        System.out.println("Total Cost: " + totalCost);
    }






    public void findBestPath(City source, City destination){
        HashMap<City, Integer> time = new HashMap<>(); // stores the time required to reach each city from the source
        HashMap<City, Integer> cost = new HashMap<>(); // stores the cost of reaching each city from the source


        // initializing priority queue and visited cities for backtracking
        HashMap<City, City> predecessor = new HashMap<>(); // stores the previous city in the best path from the source to each city
        HashMap<City, Boolean> visited = new HashMap<>(); // keeps track of whether a city has been visited during exploration

        // initializing priority queue for Dijkstra's algorithm based on combined metric
        PriorityQueue<City> pq = new PriorityQueue<>(Comparator.comparingInt(city -> time.get(city) + cost.get(city)));
        // initializing with maximum values and set source node to 0 for both time and cost
        for(City city : cities){
            time.put(city, Integer.MAX_VALUE);
            cost.put(city, Integer.MAX_VALUE);
            predecessor.put(city, null);
            visited.put(city, false);

        }

        time.put(source, 0);
        cost.put(source, 0);
        pq.offer(source);

        // explore the path using Dijkstra's algorithm
        while(!pq.isEmpty()){
            City current = pq.poll(); // get the city with the shortest combinedMetric value
            if(visited.get(current)) continue; // skip if the city has already been visited
            visited.put(current, true);
            //explore the neighbour of the current city
            for(Road road : current.getRoads()){
                City nextCity = road.getDestination();
                int newTime = time.get(current) + road.getTime();
                int newCost = cost.get(current) + road.getCost();
                // update if a shorter path is found in terms of time or cost
                if(newTime < time.get(nextCity) || newCost < cost.get(nextCity)){
                    time.put(nextCity, newTime);
                    cost.put(nextCity, newCost);
                    predecessor.put(nextCity, current);
                    pq.offer(nextCity);
                }

            }

        }

        // backtracking to get the best path
        List<City> bestPath = new ArrayList<>();
        City current = destination;
        while(current !=null){
            bestPath.add(current);
            current = predecessor.get(current);
        }

        Collections.reverse(bestPath);

        System.out.println("Best Path between " + source.getName() + " and " + destination.getName() + ": ");

        int totalTime = 0;
        int totalCost = 0;

        for (int i = 0; i < bestPath.size() - 1; i++) {
            City fromCity = bestPath.get(i);
            City toCity = bestPath.get(i + 1);

            Road road = getRoad(fromCity, toCity);
            if (road != null) {
                totalTime += road.getTime();
                totalCost += road.getCost();
                System.out.println("From " + fromCity.getName() + " to " + toCity.getName() +
                        " - Time: " + road.getTime() + ", Cost: " + road.getCost());
            }
        }

        System.out.println("Total Time: " + totalTime);
        System.out.println("Total Cost: " + totalCost);
    }


}


