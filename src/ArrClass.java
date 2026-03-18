import java.util.Random;

public class ArrClass {
    private final int dim;
    private final int threadNum;
    public final int[] arr;
    private int minValue = Integer.MAX_VALUE;
    private int minIndex = -1;
    private int threadCount = 0;

    public ArrClass(int dim, int threadNum)
    {
        this.dim = dim;
        this.threadNum = threadNum;
        arr = new int[dim];
        initializeArray();
    }

    private void initializeArray()
    {
        Random random = new Random();
        for (int i = 0; i < dim; i++) {
            arr[i] = random.nextInt(999999) + 1;
        }
        int negIndex = random.nextInt(dim);
        arr[negIndex] = -(random.nextInt(999999) + 1);
    }

    public int[] findLocalMinWithIndex(int start, int end) {
        int localMin = Integer.MAX_VALUE;
        int localMinIndex = -1;
        for (int i = start; i < end; i++) {
            if (arr[i] < localMin) {
                localMin = arr[i];
                localMinIndex = i;
            }
        }
        return new int[]{localMin, localMinIndex};
    }

    synchronized public void updateMin(int localMinValue, int localMinIndex) {
        if (localMinValue < minValue) {
            minValue = localMinValue;
            minIndex = localMinIndex;
        }
    }

    synchronized public void incThreadCount() {
        threadCount++;
        notify();
    }

    synchronized private void waitForThreads()
    {
        while (threadCount < threadNum)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void splitPartsAndStartThreads()
    {
        int partSize = dim / threadNum;
        ThreadMin[] threads = new ThreadMin[threadNum];
        for (int i = 0; i < threadNum; i++) {
            int start = i * partSize;
            int end = (i == threadNum - 1) ? dim : (i + 1) * partSize;
            threads[i] = new ThreadMin(start, end, this);
            threads[i].start();
        }
        waitForThreads();
    }

    public int getMinValue()
    {
        return minValue;
    }
    public int getMinIndex()
    {
        return minIndex;
    }
}