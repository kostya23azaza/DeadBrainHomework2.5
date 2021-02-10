package homework;

public class Main {
    static final int size = 10000000;
    static final int halfSize = size / 2;
    public float[] countTime(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        return arr;
    }
    public void firstThread() {
        float arr[] = new float[size];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1.0f;
            long time = System.currentTimeMillis();
            countTime(arr);
            System.out.println("Первый метод закончил работу за " + (System.currentTimeMillis() - time));
    }
    public void secondThread() {
        float arr[] = new float[size];
        float arr1[] = new float[halfSize];
        float[] arr2 = new float[halfSize];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1.0f;
        long time = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, halfSize);
        System.arraycopy(arr, halfSize, arr1, 0, halfSize);
        new Thread(() -> {
            float a[] = countTime(arr1);
            System.arraycopy(a, 0, arr1, 0, a.length);
        }).start();
        new Thread(() -> {
            float a1[] = countTime(arr2);
            System.arraycopy(a1, 0, arr2, 0, a1.length);
        }).start();
        System.arraycopy(arr1, 0, arr, 0, halfSize);
        System.arraycopy(arr2, 0, arr, halfSize, halfSize);
        System.out.println("Второй метод закончил работу за " + (System.currentTimeMillis()- time));
    }
    public static void main(String[] args) {
        Main start = new Main();
        start.firstThread();
        start.secondThread();
    }//difficult homework
}
