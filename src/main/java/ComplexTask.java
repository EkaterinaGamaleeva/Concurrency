import java.util.Arrays;
import java.util.concurrent.*;

public class ComplexTask implements Callable<Void> {

    private int[] arrays;
    private int amount;
    private int sum;

    private ComplexTaskExecutor complexTaskExecutor;

    public int[] getArrays() {
        return arrays;
    }

    public void setArrays(int[] arrays) {
        this.arrays = arrays;
    }

    public int getSum() {
        return sum;
    }

    public ComplexTask(int[] arrays) {
        this.arrays = arrays;
    }

    public void execute() {
        for (int i = 0; i < arrays.length / 2; i++) {
            amount = amount + arrays[i];
        }
    }

    @Override
    public Void call() throws Exception {
        execute();
        for (int i = arrays.length / 2; i < arrays.length; i++) {
            sum += arrays[i];
        }

        sum = sum + amount;
        System.out.println("Сумма массива :" + " ");
        System.out.println(sum);
        complexTaskExecutor.getBARRIER().await();
        return null;
    }
}
