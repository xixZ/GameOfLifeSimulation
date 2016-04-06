package gameOfLife;

/**
 * Created by xi on 2016/4/4.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        GOLSimulation sim = new GOLSimulation(10, 10);
        sim.start();
    }
}
