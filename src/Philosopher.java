public class Philosopher implements Runnable {

    private Fork rightFork; // правая вилка (вилка 1)
    private Fork leftFork; // левая вилка (вилка 2)

    private long eatTime = 0; // время есть
    private long thinkTime = 0; // время думать

    private long eaten = 0; // всего съел
    private long thinkOfEverything = 0; // всего думал


    public Philosopher(Fork r, Fork l) {
        rightFork = r;
        leftFork = l;
    }

    public void eat() { // функция приема пищи
        rightFork.onTake(); // взяли правую вилку
        leftFork.onTake(); // взяли левую вилку
        try {
            Thread.sleep(eatTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void think() { // функция думать
        try {
            Thread.sleep(thinkTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void put() { // функция освобождения вилок
        rightFork.onPut(); // положили правую вилку
        leftFork.onPut(); // положили левую вилку
    }

    @Override
    public void run() {
        while(true) {
            eat(); // философ поел
            put(); // освободил вилки
            System.out.println(Thread.currentThread().getName()+": Eaten: [" + eaten + "]");
            this.eaten++;
            think(); // подумал
            this.thinkOfEverything++;
            System.out.println(Thread.currentThread().getName()+": ThinkOfEverything:[" + thinkOfEverything + "]");
        }
    }

}