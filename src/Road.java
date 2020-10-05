import java.util.concurrent.CountDownLatch;

public class Road extends Stage {
    public static CountDownLatch cdlRaceEnd;
    public static CountDownLatch cdlRaceWin;

    public Road(int length, CountDownLatch cdlRaceE, CountDownLatch cdlRaceW ) {
        cdlRaceEnd = cdlRaceE;
        cdlRaceWin = cdlRaceW;

        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {


        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
           cdlRaceWin.countDown();
           cdlRaceEnd.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}