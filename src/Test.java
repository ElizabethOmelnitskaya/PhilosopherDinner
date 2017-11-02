public class Test {
    public static int N = 5;

    public static void main(String[] args) throws InterruptedException {

        // создаем вилки
        Fork forks[] = new Fork[N];
        for (int i = 0; i < N; i++) {
            forks[i] = new Fork();
        }

        // количество философов соответствует количеству вилок
        Thread t[] = new Thread[N]; // создаем потоки
        final Philosopher p[] = new Philosopher[N];// coздаем массив философов
        for (int i = 0; i < N; i++) {

            if (i == 0) {
                p[i] = new Philosopher(forks[N - 1], forks[i]);
            } else {
                p[i] = new Philosopher(forks[i - 1], forks[i]);
            }


            t[i] = new Thread(p[i], "Philosopher: " + (i + 1));
        }
        for(int i = 0; i < N; i++){
            t[i].start(); // стартуем
        }

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        //остановка потоков
        for (int i = 0; i < N; i++) {
            t[i].interrupt(); // просит остановить потом, остановка не принужденная
            t[i].join(); // ожидание
        }

        // вывод статистики
        // философ всего поел
        for (int i = 0; i < N; i++) {
            System.out.println("Philosopher: " + (i+1) + " - Eaten:[" + p[i].Eaten() + "]");
        }
    }
}