package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.blockingFIFO.BlockFIFO;
import edu.utdallas.blockingFIFO.Taskrunner;

public class TaskExecutorImpl implements TaskExecutor
{
	Taskrunner tpool[];
	private BlockFIFO queue = new BlockFIFO(100);
	public TaskExecutorImpl(int pool)
	{
		tpool = new Taskrunner[pool];
		for(int i = 0; i< pool; i++)
		{ 
			tpool[i] = new Taskrunner(queue);
		}
	}

	@Override
	public void addTask(Task task)
	{
		queue.put(task);
	}

}
