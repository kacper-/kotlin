public class JClass {
    void print(Object o1, Object o2) {
        System.out.println("equals java " + (o1 == o2));
    }

    public void start() {
        new Thread(() -> {
            for(int i=0;i<10;i++) {
                System.out.println("JAVA");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("JAVA InterruptedException");
                }
            }
        }).start();
    }
}
