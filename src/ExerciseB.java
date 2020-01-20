import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class ExerciseB {
    public static final int NUM_ITER = 1000000;
    public static final int NUM_ELEMENTS = 500;

    static class MyThread extends Thread {
        private Map<Integer, AtomicInteger> database;
        private Random numGenerator;

        MyThread (Map<Integer, AtomicInteger> database) {
            this.database = database;
            this.numGenerator = new Random();
        }

        public void run () {
            for (int i = 0; i < NUM_ITER; i++) {
                //select an element to change
                int id = numGenerator.nextInt(NUM_ELEMENTS);

                // add/update the element to the database
                database.putIfAbsent(id, new AtomicInteger(0));
                database.get(id).incrementAndGet();

            }//for
        }//run
    }//Threadclass

    public static void main (String[] args) throws Exception {
        Map<Integer, AtomicInteger> DB = new ConcurrentHashMap<>();
        Thread a = new MyThread(DB);
        Thread b = new MyThread(DB);

        long start = System.currentTimeMillis();
        a.start();
        b.start();

        a.join();
        b.join();
        long end = System.currentTimeMillis();

        // sum the elements in the map
        int total = 0;
        for(int i=0; i < NUM_ELEMENTS; i++) {
            AtomicInteger el = DB.get(i);
            if (el != null){
                int v = el.get();
                System.out.println("Elements in bucket #"+i+":"+v);
                total += v;
            }
        }//for
        System.out.println("Total items:"+total);
        System.out.println("Duration in ms:"+(end-start));
    }
}
