package citymap;

public class Road {
    //attributes
    private City source;
    private City destination;
    private int cost;
    private int time;
    private boolean bidirectional;
    //constructor
    public Road(City source, City destination, int cost, int time, boolean bidirectional) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
        this.time = time;
        this.bidirectional = bidirectional;
    }

    public Road(){
        //default constructor
    }

    public boolean isBidirectional() {
        return bidirectional;
    }




    //getters and setters
    public City getSource() {
        return source;
    }

    public void setSource(City source) {
        this.source = source;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "Road{" +
                "source=" + source.getName() +
                ", destination=" + destination.getName() +
                ", cost=" + cost +
                ", time=" + time +
                '}';
    }

    // Add a method to get a summary of the road without invoking toString
    public String getSummary() {
        return "Road{" +
                "source=" + source.getName() +
                ", destination=" + destination.getName() +
                ", cost=" + cost +
                ", time=" + time +
                '}';
    }

}
