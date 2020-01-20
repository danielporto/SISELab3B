# SISELab3B
Lab3 worksheet Exercise B - Thread-safe data structures - Collections

This is an easy solution but a not very efficient one synce we block on the whole structure to 
ensure atomicity of both operations to check the existence of the key and to update the key
in case it exits.

We also added the code to show how long it takes to execute the solution using wrappers 
and a synchronized block.