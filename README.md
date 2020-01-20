# SISELab3B
Lab3 worksheet Exercise B - Thread-safe data structures - Collections

**This is not a solution.**
 
It only shows that the AtomicInteger alone cannot solve the problem as
we have two critical sections. One at the counter, which the AtomicInteger does solve. But there
is another one at the Map. Since the key is only initialized once and at the beging two threads may
think that the key is not in the map before initialize the counter.