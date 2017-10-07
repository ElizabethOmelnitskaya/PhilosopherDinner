public class Fork {

    private boolean Use = false;
    private static int k = 0;
    private int count = ++k;

    public Fork(int n){
        this.k = n;
    }

    public synchronized void onTake() throws InterruptedException{
        while (Use) {
            wait();
        }
        Use = true;
       //System.out.println(Thread.currentThread().getName() + ": take fork_" + count);
        notify();
    }

    public synchronized void onPut() throws InterruptedException{
        while (!Use) {
            wait();
        }
        Use = false;
        //System.out.println(Thread.currentThread().getName() + ": put fork_" + count);
        notify();
    }
}