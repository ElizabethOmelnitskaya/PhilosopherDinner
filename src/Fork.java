// класс Fork представляет вилки, которые
// нужны философу, чтобы поесть

public class Fork {

    private volatile boolean Use;
    private static int k = 0;
    private int count = ++k;
    private int a;
    private static boolean[] A = new boolean[5]; // массив первых поднятых вилок(философ поднимает 2 вилки, а в массив
                                                 // будет записываться та, которую он поднял первой)
    private boolean write = false; // если мы поднимаем вилку и она первая, то соответствующему номеру массива пишем
                                   // значение true

    public Fork(){
        Use = false;
        for(int i = 0; i < A.length; i++)
            A[i] = false;
    }

    public synchronized boolean onTake(int n) throws InterruptedException{
        a=0;
        for(int i = 0; i < A.length; i++){
            if(A[i]) a+=1;
        }
        if(a == A.length) {return false;}
        else {
            while (Use) wait();
            if(n==1){
                A[count - 1] = true;
                Use = A[count - 1];
            }
            if(n==2) Use = true;
            if(count == 1 && write) {
                /*System.out.println(Thread.currentThread().getName() + ": take fork_" + count);*/
            }
        }
        notify();
        return true;
    }

    public synchronized void onPut() throws InterruptedException{
        while (!Use) {
            wait();
        }
        A[count-1] = false;
        Use = A[count-1];
        if(count == 1 && write){
            /*System.out.println(Thread.currentThread().getName() + ": put fork_" + count);*/
        }
        notify();
    }
}
