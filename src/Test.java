public class Test{
   // public static int N = 5;
    //        Fork forks[] = new Fork[N];
//        for(int i =0;i < N;i++){
//            forks[i] = new Fork(i);
//            new Thread(new Philosopher(forks[i], forks[(i+1)%N])).start();
//        }
//

    // создаем потоки
    // количество философов соответствует количеству вилок
    public static void main(String[] args) {
        // создаем вилки
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        //создаем потоки
        Thread t1 = new Thread(new Philosopher(fork1, fork2), "Philosopher 1"); // философ 1 может взять 1 и 2 вилку
        Thread t2 = new Thread(new Philosopher(fork2, fork3), "Philosopher 2"); // философ 2 может взять 2 и 3 вилку
        Thread t3 = new Thread(new Philosopher(fork3, fork4), "Philosopher 3"); // философ 3 может взять 3 и 4 вилку
        Thread t4 = new Thread(new Philosopher(fork4, fork5), "Philosopher 4"); // философ 4 может взять 4 и 5 вилку
        Thread t5 = new Thread(new Philosopher(fork5, fork1), "Philosopher 5"); // философ 5 может взять 5 и 1 вилку

        // запускаем потоки
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(100);

        } catch (InterruptedException e) {}

        //остановка потоков
        t1.stop();
        t2.stop();
        t3.stop();
        t4.stop();
        t5.stop();

        System.out.println("\n");
    }
}