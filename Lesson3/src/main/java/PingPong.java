import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {

    private final ReentrantLock locker;
    private String lastPlayer = "";

    public String getLastPlayer() {
        return lastPlayer;
    }

    public void hit(String player) throws IOException {
        System.out.println(player);
        lastPlayer = player;
    }

    {
        locker = new ReentrantLock();
    }

    public static void main(String[] params) {
        PingPong pingPong = new PingPong();
        try {
            String[] names = {"ping","pong"};
            for (int i = 0; i < names.length; i++) {
                Thread gamer = new Thread(new GamerThread(pingPong, pingPong.locker));
                gamer.setName(names[i]);
                gamer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class GamerThread implements Runnable {
    PingPong pingPong;
    ReentrantLock locker;

    @Override
    public void run() {
        while (true) {
            {
                if (pingPong.getLastPlayer() != Thread.currentThread().getName()) {
                    if(locker.tryLock())
                    try {
                        pingPong.hit(Thread.currentThread().getName());
                        Thread.sleep(750);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        locker.unlock();
                    }
                }
            }
        }
    }

    public GamerThread(PingPong pingPong, ReentrantLock locker) {
        this.pingPong = pingPong;
        this.locker = locker;
    }
}
