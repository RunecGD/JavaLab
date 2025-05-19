package Labs.Lab11;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class CpuSimulation {
    public static void main(String[] args) throws InterruptedException {
        // Начальные параметры
        int numProcesses1 = 20; // Количество процессов для потока 1
        int numProcesses2 = 20; // Количество процессов для потока 2

        int genMin1 = 200; // Минимальный интервал генерации (мс) поток 1
        int genMax1 = 350; // Максимальный интервал генерации (мс) поток 1
        int genMin2 = 100; // Минимальный интервал генерации (мс) поток 2
        int genMax2 = 200; // Максимальный интервал генерации (мс) поток 2

        int cpuMin = 100; // Минимальное время обработки (мс)
        int cpuMax = 300; // Максимальное время обработки (мс)

        CpuQueue queue1 = new CpuQueue("Очередь 1");
        CpuQueue queue2 = new CpuQueue("Очередь 2");

        AtomicBoolean done1 = new AtomicBoolean(false);
        AtomicBoolean done2 = new AtomicBoolean(false);

        CpuProcess processStream1 = new CpuProcess(
                1, numProcesses1, genMin1, genMax1, queue1, done1
        );
        CpuProcess processStream2 = new CpuProcess(
                2, numProcesses2, genMin2, genMax2, queue2, done2
        );

        Cpu cpu = new Cpu(queue1, queue2, cpuMin, cpuMax, done1, done2);

        System.out.println("=== Старт моделирования ===");

        processStream1.start();
        processStream2.start();
        cpu.start();

        processStream1.join();
        processStream2.join();
        cpu.join();

        System.out.println("=== Моделирование завершено ===");
        System.out.println("Максимальный размер очереди 1: " + queue1.getMaxSize());
        System.out.println("Максимальный размер очереди 2: " + queue2.getMaxSize());
    }
}




class CpuProcess extends Thread {
    private final int streamNumber;
    private final int count;
    private final int minInterval;
    private final int maxInterval;
    private final CpuQueue queue;
    private final AtomicBoolean done;

    public CpuProcess(int streamNumber, int count, int minInterval, int maxInterval,
                      CpuQueue queue, AtomicBoolean done) {
        this.streamNumber = streamNumber;
        this.count = count;
        this.minInterval = minInterval;
        this.maxInterval = maxInterval;
        this.queue = queue;
        this.done = done;
    }

    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            try {
                int interval = randomInRange(minInterval, maxInterval);
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ProcessTask task = new ProcessTask(i, streamNumber);
            synchronized (Cpu.lock) {
                if (Cpu.isIdle) {
                    Cpu.currentTask = task;
                    Cpu.isIdle = false;
                    System.out.printf(
                            "Процесс %d из потока %d поступил напрямую на процессор (очередь пуста)\n",
                            task.id, streamNumber);
                    Cpu.lock.notifyAll();
                } else {
                    queue.add(task);
                }
            }
        }
        System.out.println("Поток генерации процессов " + streamNumber + " завершил работу.");
        done.set(true);
    }

    private int randomInRange(int min, int max) {
        return min + (int)((max - min) * Math.random());
    }
}

class Cpu extends Thread {
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
        while (!done1.get() || !done2.get() || !queue1.isEmpty() || !queue2.isEmpty() || currentTask != null) {
            ProcessTask taskToProcess = null;
            synchronized (lock) {
                if (currentTask == null && !queue1.isEmpty()) {
                    taskToProcess = queue1.poll();
                    isIdle = false;
                } else if (currentTask == null && queue1.isEmpty() && !queue2.isEmpty()) {
                    taskToProcess = queue2.poll();
                    isIdle = false;
                } else if (currentTask != null) {
                    taskToProcess = currentTask;
                    currentTask = null;
                } else {
                    isIdle = true;
                    try {
                        lock.wait(100);
                    } catch (InterruptedException ignored) {}
                }
            }
            if (taskToProcess != null) {
                System.out.printf("Процессор обслуживает процесс %d из потока %d\n",
                        taskToProcess.id, taskToProcess.streamNumber);
                try {
                    int serviceTime = randomInRange(minService, maxService);
                    Thread.sleep(serviceTime);
                } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.printf("Процесс %d из потока %d завершён (обслуживание %d мс)\n",
                        taskToProcess.id, taskToProcess.streamNumber, minService);
            }
        }
        System.out.println("Процессор завершил обслуживание всех процессов.");
    }

    private int randomInRange(int min, int max) {
        return min + (int)((max - min) * Math.random());
    }
}