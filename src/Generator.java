public class Generator implements Runnable {
    private Manager manager;

    public Generator(){
        System.out.println("Hello world!");
        manager = Manager.getMng();
    }

    public void run() {
        while(true){
            Order order = new Order();
            System.out.println("New order: " + order.getFloor() + " floor, " + order.getDestination() + " destination");
            manager.push(order);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
