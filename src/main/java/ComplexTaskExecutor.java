import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComplexTaskExecutor {
    private ComplexTask complexTask;
    public static CyclicBarrier BARRIER;
    private ExecutorService executorService;
    private int count;

    public ComplexTaskExecutor(int count) {
        this.count = count;

    }


    public void executeTasks(int numberOfTasks) throws ExecutionException, InterruptedException {
        BARRIER = new CyclicBarrier(count, (Runnable) complexTask);
        executorService = Executors.newFixedThreadPool(numberOfTasks);
        List<ComplexTask> taskList = createTask(numberOfTasks);
        List<Future<Void>> futures = executorService.invokeAll(taskList, numberOfTasks, TimeUnit.SECONDS);
        executorService.isShutdown();
        executorService.isTerminated();
    }

    public List<ComplexTask> createTask(int i) {
        List<ComplexTask> taskList = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            taskList.add(new ComplexTask(new int[]{57687 + j, 89 + j, 907 + j, 9999 + j, 78687, 898}));
        }
        return taskList;
    }

    public CyclicBarrier getBARRIER() {
        return BARRIER;
    }
}
