// Noah Seligson
// COP 4250
// 1/14/23

import java.io.*;
import java.util.*;

public class Prime implements Runnable{
    static List<Integer> primes = new ArrayList<>();
    static Map<Thread, Integer> map = new HashMap<>();

    private final static int start = 2;
    private final static int stop = (int) 1e8; // 10^8

    private final static int numThreads = 8;

    private static long primeSum = 0;
    private static long numPrimes = 0;

    @Override
    public void run()
    {   
        int num = map.get(Thread.currentThread());
                
        while(num <= stop){
            if(prime(num)){
                // Synchornize this part so other threads don't modify
                // the values before it is finished
                synchronized(this){
                    primes.add(num);
                    primeSum += num;
                    numPrimes++;
                }
            }

            num += numThreads;
        }   

    }

    static boolean prime(int n){
        int sqrt = (int) Math.sqrt(n);

        // It is only necessary to check up to the square root of n
        // to determine if it is prime
        for(int i = start; i <= sqrt; i++)
            if(n % i == 0)
                return false;

        return true;
    }
 
    public static void main(String[] args) throws InterruptedException, IOException{
        Prime p = new Prime();

        List<Thread> threadList = new ArrayList<>();

        for(int i = 0; i < 8; i ++){
            Thread t = new Thread(p);
            threadList.add(t);
            map.put(t, i + 2); // Represents the point where incrementing by 8 starts
        }

        long start = System.currentTimeMillis();

        for(int i = 0; i < 8; i++)
            threadList.get(i).start();
            
        for(int i = 0; i < 8; i++)
            threadList.get(i).join();

        long end = System.currentTimeMillis(); // All threads have finished executing by this point

        Collections.sort(primes);

        FileWriter writer = new FileWriter("primes.txt");

        writer.write("Execution Time: " + (end - start) + "ms"+ ", Primes Found: "
                     + numPrimes + ", Sum of All Primes: " + primeSum + "\n\n");
           
        for(int i = primes.size() - 10; i < primes.size(); i++){
            writer.write(String.valueOf(primes.get(i)));
            if(i != primes.size() - 1)
                writer.write(", ");
        }

        writer.close();
    }
}