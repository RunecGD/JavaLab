package Labs.Lab11;

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