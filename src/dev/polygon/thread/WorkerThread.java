package dev.polygon.thread;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

public class WorkerThread implements Runnable{
	
	Logger logger_ = Logger.getLogger(WorkerThread.class);
	
	SocketChannel s;
	
	public WorkerThread(SocketChannel s){
		this.s = s;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
        int readedLen = 0;
        
        SocketChannel rChannel = s;

        buf.clear();

        try {
            readedLen = rChannel.read(buf);

        } catch (Throwable e) {
         
        }
        
        if(readedLen > 0) {  // Valid state

            byte ch[] = new byte[buf.position()];
            buf.flip();
            buf.get(ch);
            String data = new String(ch);

            // Read Length
            int p = data.indexOf('\n');

            if(p > 0) {
                String dataLen_str = data.substring(0, p);
                int dataLen = Integer.parseInt(dataLen_str);

                String query_str = data.substring(p+1);

                if(dataLen > query_str.length()) {
                    // ...there is remaining data exists.

                    while(dataLen > query_str.length()) {
                        buf.clear();

                        try {
                            readedLen = rChannel.read(buf);

                        } catch (Throwable e) {
                            if(logger_.isDebugEnabled())
                                logger_.debug("Exception occured in reading data", e);
                        }

                        if(readedLen > 0) {
                            byte chp[] = new byte[buf.position()];
                            buf.flip();
                            buf.get(chp);
                            String data2 = new String(chp);

                            query_str = query_str.concat(data2);
                        }
                    }
                }
              //logger_.info("Query : [" + query_str + "]");

                try {
//              	task.setJSONQuery(new JSONObject(query_str));
//                    
//                  devTajoQueryTask qt = new devTajoQueryTask(task);
//                  qt.run();
                	
                	TaskWorker tk = new TaskWorker();
                	
                	
                } catch (Exception e) {
                    logger_.error("JSONException", e);
                    logger_.error("Passed JSON : [" + query_str + "]");
//                    errorProc.doErrorProc(rChannel, "JSON Parsing Error", 101);
                }
            }
        } else {
//        	return -1;
        }
        
//        return 0;		
	}

}
