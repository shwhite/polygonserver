package dev.polygon.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler{

	Logger logger_  = Logger.getLogger(RejectedExecutionHandlerImpl.class);
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor tp) {
		// TODO Auto-generated method stub
		logger_.info(r.toString() + " rejected!!");
		
	}

}
