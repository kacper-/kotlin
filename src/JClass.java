import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class JClass {
    void print(Object o1, Object o2) {
        System.out.println("equals java " + (o1 == o2));
    }

    public void start() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("JAVA");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("JAVA InterruptedException");
                }
            }
            System.out.println("JAVA END");
        }).start();
    }

    public CompletableFuture<String> run() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                System.out.println("FUTURE InterruptedException");
            }
            System.out.println("JAVA NATURAL END");
            return "JAVA FUTURE END";
        });
        return future;
    }

    public static void main(String[] args) {
        start2();
    }

    public static void start2() {
        long start = new Date().getTime();
        System.out.println(Runtime.getRuntime().availableProcessors());
        Thread[] t = new Thread[3];
        t[0] = Thread.ofPlatform().start(JClass::load);
        t[1] = Thread.ofPlatform().start(JClass::load);
        t[2] = Thread.ofPlatform().start(JClass::load);
//        t[3] = Thread.ofPlatform().start(JClass::load);
//        t[4] = Thread.ofPlatform().start(JClass::load);
//        t[5] = Thread.ofPlatform().start(JClass::load);
//        t[6] = Thread.ofPlatform().start(JClass::load);
//        t[7] = Thread.ofPlatform().start(JClass::load);
//        t[8] = Thread.ofPlatform().start(JClass::load);
//        t[9] = Thread.ofPlatform().start(JClass::load);
//        t[10] = Thread.ofPlatform().start(JClass::load);
//        t[11] = Thread.ofPlatform().start(JClass::load);
//        t[12] = Thread.ofPlatform().start(JClass::load);
//        t[13] = Thread.ofPlatform().start(JClass::load);
//        t[14] = Thread.ofPlatform().start(JClass::load);
        try {
            for (Thread thread : t) thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long stop = new Date().getTime();
        System.out.println(stop - start);
    }

    public static void load() {
        double result = 0.1;
        for (int i = 1; i < 100000000; i++) {
            result += Math.log(Math.log(i + 1));
        }
        System.out.println(result);
    }
}
