// Noah Seligson
// COP 4250
// 1/14/23

import java.io.*;
import java.util.*;

public class Prime implements Runnable{
    private static List<Integer> primes = new ArrayList<>();
    private static Map<Thread, Integer> map = new HashMap<>();

    private final static int firstPrime = 2;
    private final static int stop = (int) 1e8; // 10^8

    private final static int numThreads = 8;

    private static long primeSum = 0;
    private static long numPrimes = 0;

    private static boolean isPrime[] = new boolean[stop + 1];

    // Implements the Sieve of Eratosthenes algorithm,
    @Override
    public void run()
    {   
        int num = map.get(Thread.currentThread());     
        int sqrt = (int) Math.sqrt(stop);
        
        // Each iteration is icremented by the number of threads
        // in order to use concurrency and give each thread an
        // equal amount of work
        for (int i = num; i <= sqrt; i += numThreads)
            if(isPrime[i]) // If the number is not prime, its multiples won't be prime as well
                for (int j = i * i; j <= stop; j += i)
                    isPrime[j] = false;
    }

 
    public static void main(String[] args) throws InterruptedException, IOException{
        Prime p = new Prime();

        List<Thread> threadList = new ArrayList<>();
  
        for(int i = 0; i < numThreads; i++){
            Thread t = new Thread(p);
            threadList.add(t);
            map.put(t, i + 2); // Set the starting point for each thread
        }

        Arrays.fill(isPrime, true);

        long start = System.currentTimeMillis();

        for(int i = 0; i < numThreads; i++)
            threadList.get(i).start();
            
        for(int i = 0; i < numThreads; i++)
            threadList.get(i).join();

        long end = System.currentTimeMillis(); // All threads have finished executing by this point

        // Once the threads discover which numbers are not prime
        // do a linear search through the prime array to get
        // the ones that are prime
        for(int i = firstPrime; i <= stop; i++){
            if(isPrime[i]){
                primes.add(i);
                primeSum += i;
                numPrimes++; 
            }
        }

        FileWriter writer = new FileWriter("primes.txt");

        writer.write("Execution Time: " + (end - start) + "ms" + ", Primes Found: "
                     + numPrimes + ", Sum of All Primes: " + primeSum + "\n\n");
           
        for(int i = primes.size() - 10; i < primes.size(); i++){
            writer.write(String.valueOf(primes.get(i)));
            if(i != primes.size() - 1)
                writer.write(", ");
        }

        writer.close();
    }
}