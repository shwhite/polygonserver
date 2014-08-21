package dev.polygon.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SystemProperties {

	Properties m_Proper = new Properties();
	
	public SystemProperties(){
		
	}
	
	private int nServerPort = 0;
	private int nMaxThreadCnt = 5;
	
	private String vertexDataPath = "";
	
	/*
	 * Load ProPerties Value
	 */
	public int getPropertiesValue(){
		
		try {
			m_Proper.load(new FileInputStream("devPolygon.properties"));
			
			nServerPort = Integer.valueOf(m_Proper.getProperty("Server Port", "19999"));
			nMaxThreadCnt = Integer.valueOf(m_Proper.getProperty("MaxThreadCnt", "5"));
			vertexDataPath = m_Proper.getProperty("VerTexDataFilePath");			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return 100;
	}
	
	
	public String getVertexDataPath() { return vertexDataPath; }
	public int getServerPort() { return nServerPort; }
	public int getMaxThreadCnt() { return nMaxThreadCnt; }
}
