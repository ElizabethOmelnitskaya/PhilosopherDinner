public class Philosopher implements Runnable {

    private Fork rightFork; // правая вилка (вилка 1)
    private Fork leftFork; // левая вилка (вилка 2)

    private static int k = 0;
    private int count = k++;

    private long eatTime = 0; // время есть
    private long thinkTime = 1; // время думать

    private long eaten = 0; // всего съел
    private long thinkOfEverything = 0; // всего думал

    public Philosopher(Fork r, Fork l) {
        rightFork = r;
        leftFork = l;
    }

    public void eat() throws InterruptedException{ // функция приема пищи
        rightFork.onTake(); // взяли правую вилку
        leftFork.onTake(); // взяли левую вилку
        Thread.sleep(eatTime);
    }

    public synchronized void think() throws InterruptedException { // функция думать
        Thread.sleep(thinkTime);
    }

    public void put() throws InterruptedException { // функция освобождения вилок
        rightFork.onPut(); // положили правую вилку
        leftFork.onPut(); // положили левую вилку
    }

    public long Eaten(){
        return eaten;
    }

    public long ThinkOfEveryting(){
        return thinkOfEverything;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                eat(); // философ поел
                put(); // освободил вилки
            } catch (InterruptedException e) {
                return;
            }
            this.eaten++;
            try {
                think(); // подумал
            } catch (InterruptedException e) {
                return;
            }
            this.thinkOfEverything++;
        }

    }
}