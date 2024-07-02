import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private  ComplexTask complexTask;
    public  static CyclicBarrier BARRIER;
    private ExecutorService executorService;
    private final int count;
    public ComplexTaskExecutor( int count,int[] array) {
        this.count=count;
        complexTask =new ComplexTask();
        complexTask.setArrays(array);
    }

    public ComplexTask getComplexTask() {
        return complexTask;
    }

    public void executeTasks(int numberOfTasks) throws ExecutionException, InterruptedException {
        BARRIER=new CyclicBarrier(numberOfTasks,complexTask);
        executorService= Executors.newFixedThreadPool(numberOfTasks);
        Future future= executorService.submit(complexTask);
        executorService.shutdown();
    }

    public CyclicBarrier getBARRIER() {
        return BARRIER;
    }
}
