package dev.polygon.task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import dev.polygon.util.SystemDataMemory;
import dev.polygon.util.SystemProperties;

public class LoadVertexData {
	
	Logger logger_ = Logger.getLogger(LoadVertexData.class);
	
	String memFlag;
	SystemProperties conf;
	SetVertexInMem setMem;
	
	public LoadVertexData(String memFlag, SystemProperties conf){
		this.memFlag = memFlag;
		this.conf = conf;
	}
	
	
	/*
	 * Read VertexData From File
	 * Set into Memory
	 */
	public int getLoadData(){
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(conf.getVertexDataPath()));
			
			String strTemp = "";
			
			setMem = new SetVertexInMem();
			
			int nCount = 0;
			
			while((strTemp=br.readLine())!=null){
				String[] arrTemp = strTemp.split("\\|",-1);
				
				String admCode = arrTemp[0];			//행정코드
				String InterFlag = arrTemp[2];					//Flag
				
				int nMaxX = 0;
				int nMinX = 0;
				int nMaxY = 0;
				int nMinY = 0;
				
				int nLen = (arrTemp.length-3)/2;
				
				int[] arrPosX = new int[nLen];
				int[] arrPosY = new int[nLen];
				
				int nIdx = 0;
				
				for(int i=3;i<arrTemp.length;i=i+2){
					int nX = Integer.valueOf(arrTemp[i]);
					int nY = Integer.valueOf(arrTemp[i+1]);
					
					if(nMaxX<nX){ 
						nMaxX = nX;
					}
					
					if(nMinX>nX){
						nMinX = nX;
					}
					
					if(nMaxY<nY){
						nMaxY = nY;
					}
					
					if(nMinY>nY){
						nMinY = nY;
					}
					
					arrPosX[nIdx] = nX;
					arrPosY[nIdx] = nY;
					
					nIdx++;
				}	//for
				
				int setMemReuslt = setMem.saveVertexData(nMaxX, nMinX, nMaxY, nMinY, admCode, arrPosX, arrPosY, memFlag, InterFlag);
				
				if(setMemReuslt != 100){
					logger_.error("VertexData Insert into Memory Fail! Resturn Code : "+ setMemReuslt);
				}
				
				nCount++;
				
			} //while
			
			logger_.info("VertexData Insert Into Memory Done. Completed Data Cnt : " + nCount);
			
			br.close();
			
			return 100;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger_.error("VertexData File Not Found Error ");
			return -101; 				//
		} catch (IOException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
			logger_.error("VertexData IO Error ");
			return -102;
		}
		
		
	}

}
