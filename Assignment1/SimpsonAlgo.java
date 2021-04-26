import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class SimpsonAlgo extends Thread{
	public int start =0; 
  	public int end = 0;

  	//function to evaluate point values
  	public double evaluate(double x)
  	{
      
      double numerator = Math.pow(Math.E, -(x*x)/2.0);
      return (1.0*numerator/denominator);
    }

    //function to set upper and lower limits
	public SimpsonAlgo(int low, int high)
	{
        start =  low;
        end = high;
        
    }
	public void run(){

		for(int i=start;i<=end;i++){
			synchronized(lock){
	          if(i==0||i==intervals){
	            
	            finalValue += evaluate(delta_x*i+lower_limit);
	          } 
	          else if(i%2==1){
	            finalValue += 4*evaluate(delta_x*i+lower_limit);
	          }
	          else if(i%2==0){
	            finalValue += 2*evaluate(delta_x*i+lower_limit);
	          }
      		}
    	       		
       		     
        } 

	}
	public static final Object lock = new Object();
	public static int lower_limit = -1; 
    public static int upper_limit = 1; 
    public double denominator = Math.sqrt(2*Math.PI);
	public static int number_of_threads;
	public static int intervals=1000000;
	public static double delta_x = ((0.0+upper_limit - lower_limit)/intervals);
	public static int threadExecution;
	public static double finalValue = 0.0; 
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the number of threads:");
		number_of_threads = input.nextInt();

		threadExecution = intervals/number_of_threads;
		
		ArrayList<Thread> arr = new ArrayList<Thread>();

		int threadVal = 0;
		for(int j=0;j<number_of_threads-1;j++)
		{
			Thread t = new Thread(new SimpsonAlgo(threadVal,threadVal+threadExecution));
			t.start();
			arr.add(t);
			threadVal = threadVal+threadExecution+1;
		}
		Thread t = new Thread(new SimpsonAlgo(threadVal,intervals));
		t.start();
		arr.add(t);
		for(int j=0;j<number_of_threads;j++)
		{
			try
			{ 
				arr.get(j).join();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		double simpson_approx = (finalValue*(upper_limit - lower_limit))/(3.0*intervals);
		System.out.println("The estimated value of the integral is:");
		System.out.println(simpson_approx);




	} 
}
