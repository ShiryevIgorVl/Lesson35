import java.util.concurrent.*;

public class Car implements Runnable {

    public static int getCarsCount() {
        return CARS_COUNT;
    }

    CountDownLatch cdlRaceBegin;
    CyclicBarrier cBarrier;

    private static int CARS_COUNT;

      static {
            CARS_COUNT = 0;
        }
        private Race race;
        private int speed;
        private static String name;
        public static String getName() {
            return name;
        }
        public int getSpeed() {
            return speed;
        }

        public Car(Race race,CountDownLatch c, CyclicBarrier cb, int speed) {
            cdlRaceBegin = c;
            cBarrier = cb;
            this.race = race;
            this.speed = speed;
            CARS_COUNT++;
            this.name = "Участник #" + CARS_COUNT;
        }

        @Override
        public void run() {


            try {
                System.out.println(this.name + " готовится");
                Thread.sleep(500 + (int) (Math.random() * 800));
                System.out.println(this.name + " готов");

                cBarrier.await();
                cdlRaceBegin.countDown();
                cBarrier.await();

            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < race.getStages().size(); i++) {
                    race.getStages().get(i).go(this);

                }
            }

}
