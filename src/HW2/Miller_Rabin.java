package HW2;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class Miller_Rabin {
	public static void main(String[] args) throws FileNotFoundException{
	    Miller_Rabin solution_power = new Miller_Rabin();

	    try{
	      Scanner scanner = new Scanner(new File("hw2.dat"));
	      while(scanner.hasNext()){
	        long test_number = scanner.nextLong();
	        if(solution_power.miller_test(test_number, 10)){
	          System.out.println(test_number + " true");
	        }
	        else{
	          System.out.println(test_number + " false");
	        }
	      }
	    }

	    catch (FileNotFoundException ex){
	      System.out.println("no such file!");
	    }





	  }
	  public long mypower(long x, long n){
	    long result = 1L;
	    while(n > 0){
	      if(n % 2 != 0){
	        result = result * x;
	      }
	      x = x*x;
	      n = n/2;
	    }
	    return result;
	  }

	  public long powermod(long x, long n, long m){
	    long result = 1L;
	    while(n > 0){
	      if(n % 2 != 0){
	        result = result * x % m;
	      }
	      x = x*x % m;
	      n = n/2;
	    }
	    return result;
	  }

	  public String fermat_test(int p, int k){
	    for(int i =0; i<k;i++){
	      Random rand = new Random();
	      int a = rand.nextInt((p - 1 - 2) + 1) + 2;
	      System.out.println(a);
	      if(this.powermod(a,p-1,p) != 1){
	        return "this is not a prime";
	      }
	    }
	    return "probably prime";

	  }
	  public boolean miller_test(long n, int iteration){
	    if(n < 2){
	      return false;
	    }
	    if(n == 2){
	      return true;
	    }
	    if(n % 2 == 0){
	      return false;
	    }
	    long d = n - 1;
	    long s = Long.numberOfTrailingZeros(d);
	    d >>= s;

	    long lower_range = 2L;
	    long upper_range = n-2;
	    Random rand = new Random();
	    outer:
	    for(int i = 0; i < iteration; i++){
	      long a = lower_range + (long)(rand.nextDouble()*(upper_range-lower_range));
	      long x = powermod(a,d,n);
	      if(x == 1 || x == n - 1){
	        continue;
	      }
	      for(long j = 1; j < s; j++ ){
	        x = powermod(x,2,n);
	        if(x ==1){
	          return false;
	        }
	        if(x == n-1){
	          continue outer;
	        }

	      }
	      return false;
	    }

	    return true;
	  }
}
