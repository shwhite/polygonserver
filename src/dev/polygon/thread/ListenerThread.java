package dev.polygon.thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ListenerThread implements Runnable{

	Logger logger_ = Logger.getLogger(ListenerThread.class); 
	
	ServerSocketChannel socketchannel  = null;
	ServerSocket serSocket = null;
	
	RejectedExecutionHandlerImpl rejectionHandler = null;	
	ThreadFactory threadFactory = null;
	ThreadPoolExecutor executorPool = null;
	
	private volatile boolean  bStop = false; 
	
	int nPort = 0;
	
	public ListenerThread(int nPort){
		this.nPort = nPort;
		rejectionHandler = new RejectedExecutionHandlerImpl();
		threadFactory = Executors.defaultThreadFactory();
		
	}
	
	public boolean init(){
		InetSocketAddress sAddr = null;
		
		serSocket = null;
		socketchannel = null;
		
		String strListenAdrr = "0.0.0.0";
		
		sAddr = new InetSocketAddress(strListenAdrr, nPort);
		
		try {
			socketchannel = ServerSocketChannel.open();
			serSocket = socketchannel.socket();

			serSocket.setReuseAddress(true);

			serSocket.bind(sAddr, 1024);

        } catch (IOException e) {
//            logger_.fatal("create ServerSocket error : " + e.getMessage());

            try {
            	serSocket.close();
            } catch (IOException el) {}

            return false;
        }        
        
        return true;
		
	}
	
	public void start(){
		bStop = false;
		
		executorPool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory, rejectionHandler);
		
        new Thread(this).start();
        logger_.info("Listener Thread start.");
	}
	
	public void stop(){
		bStop = true;
		
		logger_.info("Listener Thread stop.");
		
		try {
			serSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!bStop){
			
			try {
				SocketChannel s = socketchannel.accept();
                Socket s_sock = s.socket();

                s_sock.setSoLinger(false, 0);
                s_sock.setTcpNoDelay(true);

                //작업 실행
//                devTajoTask t = new devTajoTask(s);
//                tpm.addTask(t);
                executorPool.execute(new WorkerThread(s));

                Thread.yield();				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		executorPool.shutdown();
	}

}
