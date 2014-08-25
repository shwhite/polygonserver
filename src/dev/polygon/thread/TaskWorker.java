package dev.polygon.thread;

import dev.polygon.task.LoadVertexData;
import dev.polygon.task.SetVertexInMem;
import dev.polygon.util.SystemProperties;

public class TaskWorker {
	
	SetVertexInMem saveVerMem = null;
	LoadVertexData loadVer = null;
	SystemProperties conf = null;
	
	public TaskWorker(SystemProperties conf){
		this.conf = conf;
//		saveVerMem = new SetVertexInMem();
		
		
	}
	
	
	 
	
	
	/*
	 * LoadData 
	 */
	public int InsertVertexDataIntoMemProc(String memFlag){
		
		loadVer = new LoadVertexData(memFlag, conf);
		
		int nResult = loadVer.getLoadData();
		
		if(nResult==100) {
			return conf.setMemPlaceValue(memFlag);
		} else {
			return nResult;
		}
	}
	
	/*
	 * Find AdmCode
	 */
	public String getAdmCode(int nX, int nY){
		String strAdmCode = "";
		
		return strAdmCode;
	}

}
