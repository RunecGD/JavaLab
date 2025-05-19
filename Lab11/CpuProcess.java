package Labs.Lab11;

import java.util.concurrent.atomic.AtomicBoolean;

public class CpuProcess extends Thread {
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

