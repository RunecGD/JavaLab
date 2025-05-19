
package Labs.Lab11;
import java.util.LinkedList;
import java.util.Queue;

public class CpuQueue {
    private final Queue<ProcessTask> queue = new LinkedList<>();
    private int maxSize = 0;
    private final String name;

    public CpuQueue(String name) { this.name = name; }

    public synchronized void add(ProcessTask task) {
        queue.add(task);
        if (queue.size() > maxSize) maxSize = queue.size();
        System.out.printf("Процесс %d добавлен в %s. Размер очереди: %d\n", task.id, name, queue.size());
        notifyAll();
    }

    public synchronized ProcessTask poll() {
        ProcessTask task = queue.poll();
        if (task != null) {
            System.out.printf("Процесс %d взят из %s на обслуживание. Размер очереди: %d\n", task.id, name, queue.size());
        }
        return task;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getMaxSize() { return maxSize; }
}
