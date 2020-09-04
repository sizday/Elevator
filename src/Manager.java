import java.util.ArrayList;
import java.util.Iterator;

public class Manager implements Runnable{
    private static Order first_order() {
        Order first_order = new Order();
        return first_order;
    }

    private static Manager uniqueManager = new Manager(first_order());
    private ArrayList<Order>orders;
    private Lift lift1;
    private Lift lift2;
    private Thread thrLift1;
    private Thread thrLift2;
    private boolean running = true;

    public static Manager getMng() {
        return uniqueManager;
    }

    private Manager(Order first_order) {
        orders = new ArrayList<>();
        lift1 = new Lift(1);
        lift2 = new Lift(2);
        thrLift1 = new Thread(lift1);
        thrLift2 = new Thread(lift2);
        orders.add(first_order);
    }

    public void run() {
        thrLift1.start();
        thrLift2.start();
        while (running) {
            try {
                thrLift1.join();
                thrLift2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void push(Order order) {
        orders.add(order);
    }

    public synchronized boolean Empty() {
        return orders.isEmpty();
    }

    public synchronized Order first() {
        Order order = orders.get(0);
        if (!orders.isEmpty()) {
            orders.remove(orders.indexOf(order));
        }
        return order;
    }

    public synchronized ArrayList<Order> inFloor(int floor, Direction dir, int freeMass) {
        ArrayList<Order> list = new ArrayList<>();
        Iterator<Order> it = orders.iterator();
        while (it.hasNext()){
            Order order = it.next();
            if (order.getFloor() == floor && order.getDirection().equals(dir) && (order.getWeight()<freeMass)){
                freeMass -= order.getWeight();
                list.add(order);
                it.remove();
            }
        }
        return list;
    }

    public synchronized int OrderSize() {
        return orders.size();
    }
}