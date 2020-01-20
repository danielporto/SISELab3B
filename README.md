# SISELab3B
Lab3 worksheet Exercise B - Thread-safe data structures - Collections

** This is not a solution **.
It only shows that the ConcurrentHashMap alone cannot solve the problem as there are two critical sections.
One at the map and another one at the counter. Both accesses must be handled safely.