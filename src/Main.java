import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {


    public static final int CARS_COUNT = 4;



    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier  cBarrier = new CyclicBarrier(CARS_COUNT);

        Semaphore smp = new Semaphore(Tunnel.carsInTunnel());

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        CountDownLatch cdlRaceBegin = new CountDownLatch(4);
        CountDownLatch cdlRaceEnd = new CountDownLatch(4);
        CountDownLatch cdlRaceWin = new CountDownLatch(1);

        Race race = new Race(new Road(60, cdlRaceEnd, cdlRaceWin), new Tunnel(smp), new Road(40, cdlRaceEnd, cdlRaceWin));
        Car[] cars = new Car[CARS_COUNT];



        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, cdlRaceBegin, cBarrier,20 + (int) (Math.random() * 10));
        }

            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();

            }

        cdlRaceBegin.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        cdlRaceWin.await();
        System.out.println(Car.getName() + " WIN");

        cdlRaceEnd.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        


    }



}







