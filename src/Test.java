import java.util.TimerTask;

public class Test {

    public static int N = 5;

    public static void main(String[] args) throws InterruptedException {

        // создаем вилки
        Fork forks[] = new Fork[N];
        for (int i = 0; i < N; i++) { forks[i] = new Fork(i); }

        // количество философов соответствует количеству вилок
        Thread t[] = new Thread[N]; // создаем потоки
        Philosopher p[] = new Philosopher[N];// coздаем массив философов
        for (int i = 0; i < N; i++) {
            p[i]= new Philosopher(forks[i], forks[(i+1)%N]);
            t[i]=new Thread(p[i], "Philosopher: " + i);
            t[i].start(); // стартуем
        }

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {}

        //остановка потоков
        for(int i=0; i < N; i++) {
            t[i].interrupt(); // просит остановить потом, остановка не принужденная
            t[i].join(); // ожидание
        }
        System.out.println("\n");

        // вывод статистики
        // философ всего поел
        for(int i = 0; i < N; i++){
            System.out.println("Philosopher: " + i + " - Eaten:[" + p[i].Eaten() + "]");
        }
        System.out.println("\n");
        // философ всего подумал
        for(int i=0; i<N; i++){
            System.out.println("Philosopher: " + i + " - ThinkOfEverything:[" + p[i].ThinkOfEveryting() + "]");
        }
    }
}

/*
ждем 1 с
вывод статистики

* */