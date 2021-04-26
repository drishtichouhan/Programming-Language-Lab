import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class MonteCarlo extends Thread{
	public void run(){

		int threadCnt=0;
		for(int j=0;j<threadExecution;j++)
		{
			//lock to ensure synchronous execution

			synchronized(lock){		
				double x = Math.random();
				double y = Math.random();
				if((x*x + y*y)<=(double)1)
				{
					threadCnt++;
				}
			}
		}
		circlePoints = circlePoints + threadCnt;

	}
	public static final Object lock = new Object();
	public static int circlePoints = 0;
	public static int number_of_threads;
	public static double threadExecution;
	public static double allPoints=Math.pow(10.0,6.0);
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the number of threads:"); //input the number of threads
		number_of_threads = input.nextInt();

		threadExecution = allPoints/number_of_threads;

		ArrayList<Thread> arr = new ArrayList<Thread>(); //Arraylist of thread objects
		for(int j=0;j<number_of_threads;j++)
		{
			Thread t = new Thread(new MonteCarlo());
			
			t.start();
			arr.add(t);
		}
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
		
		double pi = 4*(circlePoints/allPoints);

		System.out.println("The estimated value of Pi is:");
		System.out.println(pi);




	} 
}
