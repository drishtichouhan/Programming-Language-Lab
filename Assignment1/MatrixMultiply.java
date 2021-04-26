import java.lang.Math;
import java.io.*; 
import java.util.*; 

public class MatrixMultiply extends Thread{

	public static int number_of_threads;
	public static int R=1000;
	public static int[][]A = new int[1000][1000];
	public static int[][]B = new int[1000][1000];
	public static int[][]C = new int[1000][1000];

	//util function to print matrix
	public static void printMatrix(int mat[][])
	{
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<R;j++)
			{
				System.out.print(mat[i][j] +" ");
			}
			System.out.println();
			
		}
		
	}
	
	public int init;
	public int rowNum;
	public MatrixMultiply(int init,int rowNum)
	{
		this.init = init;
		this.rowNum = rowNum;
	}
	

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the number of threads:");
		number_of_threads = input.nextInt();

		//Two thread arrays, one for initialistion and one for multiplication
		MatrixMultiply initialisationThreads[] = new MatrixMultiply[number_of_threads];
		MatrixMultiply multiplyThreads[] = new MatrixMultiply[number_of_threads];

		//Loop for initialising A and B
		for(int i=0;i<R;i++)
		{
			if((i%number_of_threads)==0)
			{
				for(int j=0;j<number_of_threads;j++)
				{
					try
					{
						initialisationThreads[j].join();
					}
					catch(Exception e)
					{
						System.out.println(e);
					}

				}
				for(int j=0;j<number_of_threads;j++)
				{
					if((i+j)>=R)break;
					initialisationThreads[j] = new MatrixMultiply(1,i+j);
				}

			}
			initialisationThreads[(i%number_of_threads)].start();
		}
		for(int i=0;i<number_of_threads;i++)
		{
			try
			{
				initialisationThreads[i].join();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

		//Loop for multiplying A and B
		for(int i=0;i<R;i++)
		{
			if((i%number_of_threads)==0)
			{
				for(int j=0;j<number_of_threads;j++)
				{
					try
					{
						multiplyThreads[j].join();
					}
					catch(Exception e)
					{
						System.out.println(e);
					}

				}
				for(int j=0;j<number_of_threads;j++)
				{
					if((i+j)>=R)break;
					multiplyThreads[j] = new MatrixMultiply(0,i+j);
				}

			}
			multiplyThreads[(i%number_of_threads)].start();
		}
		for(int i=0;i<number_of_threads;i++)
		{
			try
			{
				multiplyThreads[i].join();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		System.out.println("Matrix A:");
		printMatrix(A);
		System.out.println("Matrix B:");
		printMatrix(B);
		System.out.println("Matrix C:");
		printMatrix(C);
		

	}
	public void run(){

		if(init!=0)
		{
			System.out.println("Initialising..");
			Random rand = new Random();
			for(int i=0;i<R;i++)
			{
				A[rowNum][i] = rand.nextInt(10);
				B[rowNum][i] = rand.nextInt(10);
			}
		}
		else
		{

			for(int i=0;i<R;i++)
			{
				int x=0;
				for(int j=0;j<R;j++)
				{
					x = x + (A[rowNum][j]*B[j][i]);
				}
				C[rowNum][i] = x;
			}
		}

	}

}