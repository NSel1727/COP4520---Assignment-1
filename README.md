# COP4250 - Assignment 1

To compile: javac Prime.java <br/>
To run: java Prime

Simply, my approach for this assignment was to create 8 threads and run them concurrently in
a function that obtained info on all prime numbers within the range [ 1 , 108 ]. Note that since 1
is not prime, and that 2 is the only even prime number, the effective range used throughout the
program is [ 3 , 108 ].

To give each thread an equal amount of work, each one was mapped onto an integer
representing its starting point, anywhere in the range [ 3 , 17 ], skipping every even number. This
was put to use in the run function, where it obtains the number mapped onto from the thread
and increments it by 16 in each iteration of the while loop. This ensures that all odd numbers in
the range [ 3 , 108 ] will be accounted for throughout the entire span of the program. Even
numbers were skipped because all even numbers greater than 2 are automatically not prime as
they are divisible by 1 and 2.

In terms of the prime function to determine if a number is prime, my approach runs in 𝑂(√𝑛)
time. Mathematically, a prime number cannot be divisible by any number that is not itself or 1 ,
so if 𝑛 % 𝑖= 0 , the number cannot be prime. The important part about my implementation
that makes it efficient is that last possible iteration of the while loop is at 𝑖 = √𝑛. This is because
if 𝑛 is not divisible by any ≥ 2 number by √𝑛, it will not be divisible by any number from
(√𝑛,𝑛). This works because considering all possible pair of factors, all numbers >√𝑛 must be
multiplied by a number <√𝑛 to be equal to 𝑛. However, if at this point 𝑛 is not divisible by any
number from [ 2 ,√𝑛), then it cannot be divisible by any number from (√𝑛,𝑛). Thus, it is
optimal to stop the loop at 𝑖 = √𝑛.

I ran my program under two versions, one with threads and one without. Without using threads
and just directly iteration from [ 3 , 108 ] skipping even numbers, the time averaged around 71
seconds on my computer. Using my submitted approach with 8 threads, the time averaged was
around 19 seconds on my computer, a deduction of almost 4 times of the version with no
threads. This shows that concurrency with multiple threads can be used to save a great amount
of time in determining what numbers in a certain range are prime.
