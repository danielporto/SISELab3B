# SISELab3B
Lab3 worksheet Exercise B - Thread-safe data structures - Collections


## Notes on the solution.
Note that using concurrent data structures such as ConcurrentHashMap is more efficient if
your program will change different keys of the map. We added the code to measure the duration
of the operations and print out the total time to execute at the end, for comparison.

In this solution we split the critical section into two parts: one is how we read/write elements 
in the map. The other one is the counter itself.
Multiple threads can put new counters and read existing counters in the map concurrently.
Multiple threads can update the counter concurrently.

Note that in this solution the [putIfAbsent](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html#putIfAbsent-K-V-) 
method from the [ConcurrentHashMap class](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html) is responsible for
ensure that the element selected `int id = numGenerator.nextInt(NUM_ELEMENTS);` is already initialized before used. Thus it can be retrieved
and updated (atomicinteger) and updated with `incrementAndGet()`.

Note that because we have to different calls to the collection (putIfAbsent followed by a get),
there is no guarantee that the thread that initializes the element in the map is the same
that increases the first value. But that's not a problem for this specification, since the goal
is to ensure that the updates occur atomically but not in a given order. That is, one thread can
call putIfAbsent(), switch control to another thread that will increase from `0->1` then gets 
back to initial thread that will increse from `1->2`. 
This effect is desired and increase concurrency.

If we want to enforce the thread that initializes the object to be the first to update its value
we can use a synchronized block or another way to make both calls atomic.

