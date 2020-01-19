import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ExerciseB {
    public static final int NUM_ITER = 1000;
    public static final int NUM_ELEMENTS = 5;

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
                if(database.containsKey(id)){
                    //update the element
                    AtomicInteger element = database.get(id);
                    element.incrementAndGet();
                    database.put(id, element);
                }
                else{
                    //create the element
                    database.put(id, new AtomicInteger(1));
                }
            }//for
        }//run
    }//Threadclass

    public static void main (String[] args) throws Exception {
        Map<Integer, AtomicInteger> DB = new HashMap<Integer, AtomicInteger>();
        Thread a = new MyThread(DB);
        Thread b = new MyThread(DB);

        a.start();
        b.start();

        a.join();
        b.join();

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
    }
}
