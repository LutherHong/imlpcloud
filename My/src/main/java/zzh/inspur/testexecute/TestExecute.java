package zzh.inspur.testexecute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecute {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            ExeThreads exeThreads = new ExeThreads();
            executorService.execute(exeThreads);
        }
    }
}

class ExeThreads implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("run start-----------------" + System.currentTimeMillis());
            Thread.sleep(15 * 1000);
            System.out.println("run end-----------------" + System.currentTimeMillis());

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }

}
//事例总结：线程池大小为2，但是要执行的线程是3个。所以正在执行的线程只有2个，正在执行的2个线程的开始时间为1536907573338，
// 2个线程的结束时间为1536907588338，第3个线程的开始时间，刚刚好是前面2个线程执行结束时间

