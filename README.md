# COP4250 - Assignment 1

To compile: javac Prime.java <br/>
To run: java Prime

Simply, my approach for this assignment was to create 8 threads and run them each in a function that simulates the Sieve of Eratosthenes algorithm. Note that since 1 is considered not prime, the effective range used throughout the program is [2, 10^8].

To give each thread an equal amount of work, each one was mapped onto an integer representing its starting point, anywhere in the range [2, 9] This was put to use in the run function, where it obtains the number mapped onto from the thread and increments it by 8 in each outer for loop of the prime finder algorithm. This ensures that all numbers in the range [2, 10^8] will be accounted for throughout the entire span of the program.

As stated, my prime finder implements the Sieve of Eratosthenes algorithm. What occurs is that for every number that we currently believe is prime, we iterate through all of its multiples starting at that number squared, and set the multiple's prime state to false. The reason why we know that these multiples are not prime is that since it is a multiple of a non 1 number, it by definition cannot be prime.

I ran my program under two versions, one with threads and one without. Without using threads, the time averaged around 2 seconds on my computer. Using my submitted approach with 8 threads, the time averaged was around 1.1 seconds on my computer, a deduction of almost half the time compared to not using threads. This shows that concurrency with multiple threads can be used to save a notable
of time in determining what numbers in a certain range are prime.