public class Philosopher implements Runnable {

    private Fork rightFork; // правая вилка (вилка 1) fork_1
    private Fork leftFork; // левая вилка (вилка 2)fork_2

    private static int k = 0;
    private int count = k++;

    private long eatTime = 0; // время есть
    private long thinkTime = 1; // время думать

    private long eaten; // всего съел
    private long thinkOfEverything; // всего думал thought

    public Philosopher(Fork r, Fork l) {
        rightFork = r;
        leftFork = l;
        eaten = 0;
        thinkOfEverything = 0;
    }

    public boolean eat(Fork r, Fork l) throws InterruptedException { // функция приема пищи
        rightFork.onTake(1); // взяли правую вилку
        if(!leftFork.onTake(2)){
            rightFork.onPut();
            eat(rightFork, leftFork);
        }
        else Thread.sleep(eatTime);
        return true;
    }

    public synchronized void think() throws InterruptedException { // функция думать
        Thread.sleep(thinkTime);
    }

    public void put() throws InterruptedException { // функция освобождения вилок
        rightFork.onPut(); // положили правую вилку
        leftFork.onPut(); // положили левую вилку
    }

    public long Eaten() {
        return eaten;
    }

    public long ThinkOfEveryting() {
        return thinkOfEverything;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                eat(this.rightFork, this.leftFork); // философ поел
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
//    public String stat () {
//        String str = "" + eaten;
//        return str;
//    }
