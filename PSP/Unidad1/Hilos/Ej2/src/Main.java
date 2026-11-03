public class Main {

    private static int i = 0;

    private static Thread hilo1 = new Thread(){
        @Override
        public void run() {
            super.run();
            i+=2;
        }
    };
    private static Thread hilo2 = new Thread(){
        @Override
        public void run() {
            super.run();
            i+=3;
        }
    };
    private static Thread hilo3 = new Thread(){
        @Override
        public void run() {
            super.run();
            i+=5;
        }
    };

    static void main(String[] args) throws InterruptedException {

        while (true){
            hilo1.start();
            hilo2.start();
            hilo3.start();

            hilo1.join();
            hilo2.join();
            hilo3.join();

            hilo1.
            System.out.println(i);
            i=0;
        }

    }

}
