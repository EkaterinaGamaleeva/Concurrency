import java.util.concurrent.*;
public class  ComplexTask implements Runnable {

private int [] arrays;
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

    @Override
    public void run() {
        execute();
        for (int i = arrays.length/2; i <arrays.length ; i++) {
            sum+=arrays[i];
        }
        sum=sum+amount;
        try {
            complexTaskExecutor.getBARRIER().await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
    public void execute(){
        for (int i = 0; i < arrays.length/2; i++) {
            amount=amount+arrays[i];
        }
    }
}
