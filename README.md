# path-finding-algorithm
My solution for the assignment in the online Java course. My git history is in my private repositroy in the organization on github. Therefore, I copied my code.
Fastest-cheapest-best-path finding with algorithm.
Result below.

# Assignment: Dynamic Programming - Optimal Path Finding

## Objective
The objective of this assignment is to understand the concept of dynamic programming and apply it to solve a real-world problem. You will be implementing a Java program to find the fastest, cheapest, and best path between two cities.

## Problem Statement
Consider a scenario where you have a map of cities and the roads connecting them. Each road has a certain cost (fee) associated with it and a certain time it takes to travel. Your task is to write a Java program that can find the fastest path, the cheapest path, and the best path (a balance between time and cost) between any two given cities.

### Definitions
- **Fastest Path**: The fastest path between two cities is the one that takes the least amount of time to travel from the starting city to the destination city. This does not take into account the cost of travel. When implementing the function to find the fastest path, your goal is to minimize the total travel time.
- **Cheapest Path**: The cheapest path between two cities is the one that costs the least to travel from the starting city to the destination city. This does not take into account the time it takes to travel. When implementing the function to find the cheapest path, your goal is to minimize the total cost of travel.
- **Best Path**: The best path between two cities is a balance between the fastest path and the cheapest path. It is the path that provides the best trade-off between time and cost. This could be defined in several ways depending on the specific requirements. For example, you could define the best path as the one that minimizes the sum of the time and cost, or the one that minimizes the maximum of the time and cost. When implementing the function to find the best path, your goal is to find the path that best balances time and cost according to the chosen definition.

## Starter Code
```java
public class City {
    private String name;
    // Add other necessary attributes and methods
}

public class Map {
    private List<City> cities;
    // Add other necessary attributes and methods
}
```

## Instructions
1. Complete the City and Map classes as per the requirements.
2. Implement methods in the Map class to find the fastest, cheapest, and best paths between two cities.
3. Write a main method to test your program. You should create a map with at least 5 cities and 7 roads. Test your program with different pairs of cities.
4. Your program should handle the case where there is no path between the two cities.
5. Comment your code properly for better understanding.

## Bonus
Calculate the time complexity (Big O notation) of your algorithm and explain it in your report.

## Submission
Submit your Java code file and the report. Make sure your code is properly formatted and commented.

## Deadline
Submit your assignment by next Friday.

## Grading Criteria
Your assignment will be graded on the following:
1. Correctness of the code.
2. Proper use of dynamic programming concepts.
3. Code readability and comments.
4. The report explaining your approach and challenges.
5. Bonus: Calculation and explanation of the time complexity of your algorithm.

Good luck!

## Result
- parameter example

Road roadAB = new Road(cityA, cityB, 50, 2, true);

Road roadBA = new Road(cityB, cityA, 50, 2, true);
![the result photo 1](java/main/resources/pic1.png)
![the result photo 2](java/main/resources/pic2.png)

- change the parameter

Road roadAB = new Road(cityA, cityB, 5, 2, true);

Road roadBA = new Road(cityB, cityA, 5, 2, true);
![the result photo 3](java/main/resources/pic3.png)
![the result photo 4](java/main/resources/pic4.png)




