public class Elevator{
    public static void main(String[] args){
        Generator generator = new Generator();
        Manager manager =  Manager.getMng();
        Thread thr1 = new Thread(generator);
        thr1.start();
        Thread thr2 = new Thread(manager);
        thr2.start();
        try {
            thr1.join();
            thr2.join();
        } catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}
