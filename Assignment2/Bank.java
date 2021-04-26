import java.lang.Math;
import java.util.*;

//Class Bank Account 
class Account{
	public String account_number;
	public int account_balance;
	public int branch_number;
	public Account(int acc_number,int branch_number)
	{
		String s = Integer.toString(branch_number)+"000000000";
		String t = Integer.toString(acc_number);
		this.account_number = findSum(s,t);
		this.account_balance = (int)(Math.random()* 1000);
	}

//This function computes account number using branch and bank account
	static String findSum(String str1, String str2)  {  
	    
	    if (str1.length() > str2.length()){  
	        String t = str1; 
	        str1 = str2;
 	        str2 = t; 
	    } 
	    
	    String str = "";  

	    int n1 = str1.length(), n2 = str2.length();  

	    str1=new StringBuilder(str1).reverse().toString(); 
	    str2=new StringBuilder(str2).reverse().toString();
 	  
	    int carry = 0;  
	    for (int i = 0; i < n1; i++)  
	    {  

	        int sum = ((int)(str1.charAt(i) - '0') +  
	                    (int)(str2.charAt(i) - '0') + carry);  
	        str += (char)(sum % 10 + '0');  
 
	        carry = sum / 10;  
	    }  
	  
	    for (int i = n1; i < n2; i++)  
	    {  
	        int sum = ((int)(str2.charAt(i) - '0') + carry);  
	        str += (char)(sum % 10 + '0');  
	        carry = sum / 10;  
	    }  
	  

	    if (carry > 0)  
	        str += (char)(carry + '0');  

	    str = new StringBuilder(str).reverse().toString(); 
	  
	    return str;  
	} 

	private void depositCash(int depositAmount)
	{
		this.account_balance += depositAmount;
	}
	private void withdrawCash(int withdrawAmount)
	{
		if(this.account_balance > withdrawAmount)
		{
			this.account_balance -= withdrawAmount;
		}
	}
	private void transferCash(int transferAmount,Account acc)
	{
		if( this.account_balance>=transferAmount)
		{
			this.account_balance -= transferAmount;
			acc.account_balance += transferAmount;
		}
		
	}
	private void transferAccount(int transferBranch,String newAccountNumber)
	{
		this.branch_number = transferBranch;
		
		this.account_number = newAccountNumber;
	}

//This function ensures that two threads do not access the same account at the same time
	synchronized public void utility(String request, int amount, Account acc,int transferBranch,String newAccountNumber)
	{
		switch(request)
		{
			case "deposit":
			{
				depositCash(amount);
				break;
			}
			case "withdraw":
			{
				withdrawCash(amount);
				break;
			}
			case "transferCash":
			{
				transferCash(amount,acc);
				break;
			}
			case "transferAccount":
			{
				transferAccount(transferBranch,newAccountNumber);
				break;
			}
			default:
			{
				System.out.println("The selected operation is not available!!");
				break;
			}

		}
	}
	

}
//Updater class
class Updater extends Thread{
	
	public int branch_number;
    ArrayList<LinkedList<Account>> array_updater;
    public int transactions;
    public static final Object lock = new Object();
	
	public Updater(ArrayList<LinkedList<Account>>branch_list,int n,int branchNo)
	{
		this.branch_number = branchNo;
		this.array_updater = branch_list;
		this.transactions =n;

	}
	static String findSum(String str1, String str2)  {  

	    if (str1.length() > str2.length()){  
	        String t = str1; 
	        str1 = str2; 
	        str2 = t; 
	    } 

	    String str = "";  

	    int n1 = str1.length(), n2 = str2.length();  

	    str1=new StringBuilder(str1).reverse().toString(); 
	    str2=new StringBuilder(str2).reverse().toString(); 
	  
	    int carry = 0;  
	    for (int i = 0; i < n1; i++)  
	    {  

	        int sum = ((int)(str1.charAt(i) - '0') +  
	                    (int)(str2.charAt(i) - '0') + carry);  
	        str += (char)(sum % 10 + '0');  

	        carry = sum / 10;  
	    }  
	  

	    for (int i = n1; i < n2; i++)  
	    {  
	        int sum = ((int)(str2.charAt(i) - '0') + carry);  
	        str += (char)(sum % 10 + '0');  
	        carry = sum / 10;  
	    }  

	    if (carry > 0)  
	        str += (char)(carry + '0');  

	    str = new StringBuilder(str).reverse().toString(); 
	  
	    return str;  
	} 

	public void run(){
		
		Random prob = new Random();
		// int cnt=0;
		for(int i=0;i<transactions;i++)
		{

			int req = prob.nextInt(1000);
			int amt = prob.nextInt(100);
			int branch1 = prob.nextInt(9);
			int branch2 = prob.nextInt(9);
			int l1 = array_updater.get(branch1).size()-1;
			int l2 = array_updater.get(branch2).size()-1;
			
			int account1 = prob.nextInt(array_updater.get(branch1).size()-1);
			int account2 = prob.nextInt(array_updater.get(branch2).size()-1);
			
			
			try
			{
				Account a1 = array_updater.get(branch1).get(account1);
				Account a2 = array_updater.get(branch2).get(account2);
				if(req<330)
				{
					a1.utility("deposit",amt,a2,0,"");
					// System.out.println("deposit");
				}
				else if(req>=330 && req<660)
				{
					a1.utility("withdraw",amt,a2,0,"");
					// System.out.println("withdraw");
				}
				else if(req>=660 && req<990)
				{
					a1.utility("transferCash",amt,a2,0,"");
					// System.out.println("transfer cash");
				}
				else if(req>=990 && req<993)
				{
					synchronized(array_updater.get(branch_number))
					{
						int s = array_updater.get(branch1).size();
						Account new_acc = new Account(branch_number,s+1);
						array_updater.get(branch1).add(new_acc);
					}
					// System.out.println("Add account");
					
				}
				else if(req>=993 && req<996)
				{
					synchronized(array_updater.get(branch1))
					{

						array_updater.get(branch1).remove(account1);
						// System.out.println("Cannot execute this function yet!! ");
					}
				}
				else 
				{
					synchronized(array_updater)
					{
						int a = array_updater.get(branch2).size();
						String s = Integer.toString(branch2)+"000000000";
						String t = Integer.toString(a+1);
						String newacc = findSum(s,t);
						a1.utility("transferAccount",0,a1,branch2,newacc);
						array_updater.get(branch2).add(a1);
						array_updater.get(branch1).remove(account1);
					    
					}
					// System.out.println("Cannot execute this function yet!! ");
					// cnt++;

				}
			}
			catch(Exception e)
			{
				// System.out.println(e);
			}
			finally{}
		}
		// System.out.println(cnt);
	}

}


//The main executer class
public class Bank{

	public static ArrayList<LinkedList<Account>> branches = new  ArrayList<LinkedList<Account>>();
	public static int number_of_transactions;
    public static void main(String[] args)
	{
		long startTime = System.nanoTime();

		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the number of transactions:");
		number_of_transactions = input.nextInt();

						
		for(int i=0;i<10;i++)
		{
			// System.out.println(1);
			LinkedList<Account> account_list = new LinkedList<Account>();
			for(int j=0;j<1000;j++)
			{

				Account new_acc = new Account(j,i);
				account_list.add(new_acc);
			}
			branches.add(account_list);		
		}

		for(int i=0;i<10;i++)
		{
			// System.out.println(2);
			System.out.println("Branch number"+i+1);
			for(int j=0;j<10;j++)
			{
				System.out.println("account number : " + Bank.branches.get(i).get(j).account_number);

				// System.out.println("account number : " + Bank.branches.get(i).account_list.get(j).account_number);

				System.out.println("account balance: " + Bank.branches.get(i).get(j).account_balance);
			}
			System.out.println("\n");

		}
		
		//The data structure that stores all updaters
		ArrayList<ArrayList<Updater>> updater_array = new ArrayList<ArrayList<Updater>>(10);

		//The data structure that stores threads
		ArrayList<ArrayList<Thread>> arr = new ArrayList<ArrayList<Thread>>(10);

		for(int i=0;i<10;i++)
		{
			// System.out.println(3);
			ArrayList<Thread> thread_arr = new ArrayList<Thread>();
			
			for(int j=0;j<10;j++)
			{
				Thread t = new Thread(new Updater(branches,number_of_transactions,i));
				t.start();
				thread_arr.add(t);
				// System.out.println("Hi");
			}
			arr.add(thread_arr);
		}

		// System.out.println("Hello");
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				try
				{
					arr.get(i).get(j).join();
				}
				catch(Exception e)
				{
					// System.out.println(e);
				}
			}

		}
		
		
		long xp = (long)Math.pow(10,9);
		long endtime = System.nanoTime();
		// System.out.println("Hello");

		System.out.println("Time taken for execution : " + (endtime - startTime)/xp+" seconds");


	}

}
