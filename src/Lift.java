import java.util.ArrayList;
import java.util.Iterator;

public class Lift implements Runnable {
    private int current_floor;
    private final int max_weight = 700;
    private int current_weight;
    private int id;
    private int floor;
    private ArrayList<Order>inLift;
    private Direction direction;
    private boolean work;
    private Order current_order;

    Lift(int id) {
        this.inLift = new ArrayList<>();
        this.id = id;
        this.current_floor = 1;
        this.current_weight = 0;
        this.direction = Direction.UP;
        this.work = true;
    }

    public void run() {
        while (work) {
            current_order = Manager.getMng().first();
            current_weight += current_order.getWeight();
            floor = current_order.getFloor();
            while (current_floor != floor) {
                move();
            }
            floor = current_order.getDestination();
            direction = current_order.getDirection();
            inLift.add(current_order);

            while (current_floor != floor) {
                move();
                ArrayList<Order> orderInFloor = Manager.getMng().inFloor(current_floor, direction, max_weight - current_weight);
                if (orderInFloor != null) {
                    for (Order order : orderInFloor) {
                        if ((order.getDestination() > floor && direction.equals(Direction.UP)) ||
                                (order.getDestination() < floor && direction.equals(Direction.DOWN)) ) {
                            floor = order.getDestination();
                        }
                        current_weight += order.getWeight();
                        inLift.add(order);
                    }
                }
                Iterator<Order> it = inLift.iterator();
                while (it.hasNext()) {
                    Order order = it.next();
                    if (order.getDestination() == current_floor) {
                        current_weight -= order.getWeight();
                        it.remove();
                    }
                }
            }
        }
    }

    private void move() {
        if (current_floor < floor){
            current_floor++;
        }
        else {
            current_floor--;
        }
        System.out.println("Lift " + id + " on " + current_floor + " floor.");
        try {
            Thread.sleep(900);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
