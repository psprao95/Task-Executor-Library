package edu.utdallas.blockingFIFO;

import edu.utdallas.taskExecutor.Task;

public class BlockFIFO 
{
	private Task buffer[];
	private int nextin, nextout, count, n;
	private Object notfull, notempty;

	public BlockFIFO (int qsize)
	{
		buffer = new Task[qsize];
		n = qsize;
		count = 0;
		nextin = 0;
		nextout = 0;
		notfull = new Object();
		notempty = new Object();
	}
	

	public void put(Task task)
	{ 
		if (count == n) 
		{
			synchronized (notfull) 
			{
				try
				{
					notfull.wait();
				}
				catch(InterruptedException e) {}
			}
		}
		
		synchronized (notempty)
		{ 
			buffer[nextin] = task;
			nextin = (nextin + 1) % n;
			count++;
			notempty.notify();
		}
	}
	
	public Task take()
	{
		if (count == 0)
			{
				synchronized (notempty)
				{
					try 
					{
						notempty.wait();
					}
					catch(InterruptedException e) {}
				}
			}
		
		synchronized (notfull)
		{
			Task runtask = buffer[nextout];
			nextout = (nextout + 1) % n;
			count--;
			notfull.notify();
			return runtask;
		}	
	}
	
}
