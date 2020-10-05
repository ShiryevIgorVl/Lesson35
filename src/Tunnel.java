import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore smp;
    public Tunnel(Semaphore s) {
        smp = s;
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {


        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);

                Thread.sleep(length / c.getSpeed() * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int carsInTunnel(){
        int x = 0;
     if((Main.CARS_COUNT % 2) != 0){
         x = (int) (Main.CARS_COUNT / 2 + 0.5);
    }else{
            x = Main.CARS_COUNT / 2;
        }
     return x;
    }

}