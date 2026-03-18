public class ThreadMin extends Thread{
    private final int startIndex;
    private final int endIndex;
    private final ArrClass arrClass;

    public ThreadMin(int startIndex, int endIndex, ArrClass arrClass)
    {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.arrClass = arrClass;
    }

    @Override
    public void run()
    {
        int localMinValue = arrClass.findLocalMin(startIndex, endIndex);
        int localMinIndex = arrClass.findLocalMinIndex(startIndex, endIndex);
        arrClass.updateMin(localMinValue, localMinIndex);
        arrClass.incThreadCount();
    }
}
