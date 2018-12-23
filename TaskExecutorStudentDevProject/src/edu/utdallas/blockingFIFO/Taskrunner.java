package edu.utdallas.blockingFIFO;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.blockingFIFO.BlockFIFO;

public class Taskrunner implements Runnable {
	BlockFIFO queue;
	
	public Taskrunner(BlockFIFO qref)
	{
		queue = qref;
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			Task newtask = queue.take();
			try
			{
				newtask.execute();
			}
			catch(Throwable th)
			{
				System.out.println(th.getMessage());
			}
		}
	}

}
