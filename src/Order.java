public class Order {
    private int floor;
    private int destination;
    private int weight;
    private Direction direction;

    public Order() {
        this.floor = (int) (Math.random() * 20) + 1;
        this.destination = (int) (Math.random() * 20) + 1;
        while (floor == destination)
            this.destination = (int)(Math.random() * 20 + 1);
        if (floor > destination) {
            this.direction = Direction.DOWN;
        }
        if (floor < destination) {
            this.direction = Direction.UP;
        }
        this.weight = (int) (Math.random() * 70) + 40;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
