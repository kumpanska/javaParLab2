//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int dim = 1000000;
        int threadNum = 4;
        ArrClass arrClass = new ArrClass(dim, threadNum);
        long startTimePar = System.currentTimeMillis();
        arrClass.splitPartsAndStartThreads();
        long endTimePar = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTimePar - startTimePar) + " ms");
        System.out.println("Minimum value: " + arrClass.getMinValue() + " at index: " + arrClass.getMinIndex());
    }
}