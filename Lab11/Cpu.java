package Labs.Lab11;

import java.util.concurrent.atomic.AtomicBoolean;

public class Cpu extends Thread {
    private final CpuQueue queue1, queue2;
    private final int minService, maxService;
    private final AtomicBoolean done1, done2;
    public static final Object lock = new Object();
    public static volatile boolean isIdle = true;
    public static volatile ProcessTask currentTask = null;

    public Cpu(CpuQueue queue1, CpuQueue queue2, int minService, int maxService,
               AtomicBoolean done1, AtomicBoolean done2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
        this.minService = minService;
        this.maxService = maxService;
        this.done1 = done1;
        this.done2 = done2;
    }

    @Override
    public void run() {
        while (true) {
            ProcessTask taskToProcess = null;

            synchronized (lock) {
                if (done1.get() && done2.get() && queue1.isEmpty() && queue2.isEmpty() && currentTask == null) {
                    break; // Выход из цикла, если все завершено
                }
            }
            synchronized (lock) {

                synchronized (queue1) {
                    if (!queue1.isEmpty()) {
                        taskToProcess = queue1.poll();
                        isIdle = false;
                    }
                }
            }
            synchronized (lock) {
                synchronized (queue2) {
                    if (!queue2.isEmpty()) {
                        taskToProcess = queue2.poll();
                        isIdle = false;
                    }
                }
            }

            synchronized (lock) {
                if (currentTask != null) {
                    taskToProcess = currentTask;
                    currentTask = null;
                }
            }

            if (taskToProcess != null) {
                System.out.printf("Процессор обслуживает процесс %d из потока %d\n",
                        taskToProcess.id, taskToProcess.streamNumber);
                try {
                    int serviceTime = randomInRange(minService, maxService);
                    Thread.sleep(serviceTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Процесс %d из потока %d завершён (обслуживание %d мс)\n",
                        taskToProcess.id, taskToProcess.streamNumber, minService);
                isIdle = true;
            }
        }
        System.out.println("Процессор завершил обслуживание всех процессов.");
    }

    private int randomInRange(int min, int max) {
        return min + (int) ((max - min) * Math.random());
    }
}