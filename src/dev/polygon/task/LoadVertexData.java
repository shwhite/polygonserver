package dev.polygon.task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import dev.polygon.util.SystemDataMemory;
import dev.polygon.util.SystemProperties;

public class LoadVertexData {
	
	int nFlag;
	SystemProperties conf;
	
	public LoadVertexData(int nFlag, SystemProperties conf){
		this.nFlag = nFlag;
		this.conf = conf;
	}
	
	
	/*
	 * 
	 */
	public int getLoadData(){
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(conf.getVertexDataPath()));
			
			String strTemp = "";
			
			while((strTemp=br.readLine())!=null){
				String[] arrTemp = strTemp.split("\\|",-1);
				
				String admCode = arrTemp[0];			//행정코드
				String Flag = arrTemp[2];					//Flag
				
				for(int i=3;i<arrTemp.length;i=i+2){
					
				}
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 100;
	}

}
