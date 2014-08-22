package dev.polygon.task;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dev.polygon.util.SystemDataMemory;

public class FindAdmCode {
	
	public FindAdmCode(){
		
	}
	
	int nMinX = 0;
	int nMaxX = 0;
	int nMinY = 0;
	int nMaxY = 0;
	
	/*
	 * Find AdmCode
	 * Param : posX, posY
	 */
	public String getAdmCode(String strX, String strY, String memFlag){
		String admCode = "";
		
		try {
			HashMap<String, HashMap<String, String>> mHashMap = SystemDataMemory.getFirstIdxMap(memFlag).get(strX.substring(0, strX.length()-4));
			HashMap<String, String> mHash = mHashMap.get(strY.substring(0, strY.length()-4));
						
			for(Map.Entry<String, String> entry : mHash.entrySet()){
				String mSquareXY = entry.getKey();
				String mAdmCode = entry.getValue();

				String[] arrSquareXY = mSquareXY.split("_",-1);
				
				nMinX = Integer.valueOf(arrSquareXY[1]);
				nMaxX = Integer.valueOf(arrSquareXY[0]);
				nMaxY = Integer.valueOf(arrSquareXY[2]);
				nMinY = Integer.valueOf(arrSquareXY[3]);
										
				/*
				 * Square에 들어가는 값을 확인
				 */
				if(Integer.valueOf(strX)>=nMinX && Integer.valueOf(strX)<=nMaxX && Integer.valueOf(strY)>=nMinY && Integer.valueOf(strY)<=nMaxY){
//					System.out.println(nMinX +"_" + nMaxX +"_" + nMinY + "_" + nMaxY +"_");
					ArrayList<int[]> m_ArrX = SystemDataMemory.getGeoVertexXMap(memFlag).get(mAdmCode);
					ArrayList<int[]> m_ArrY = SystemDataMemory.getGeoVertexYMap(memFlag).get(mAdmCode);
					
					if(m_ArrX.size()!=m_ArrY.size()) {
						System.out.println("Data Error");
						break;
					}
					
					for(int j=0;j<m_ArrX.size();j++){
					
						int[] arrPolyX = m_ArrX.get(j);
						int[] arrPolyY = m_ArrY.get(j);
						
						/*
						 * 
						 */
						boolean bResult = CheckPolygon(arrPolyX, arrPolyY, Integer.valueOf(strX), Integer.valueOf(strY));
						if(bResult) {
//								System.out.println("NEW LOGIC검색 결과 : " +bResult);
//							System.out.println("NEW LOGIC행정 코드 : " + mAdmCode);
//							newAdmCode = mAdmCode;
							admCode = mAdmCode;
//							nTotal++;
							break;
						}
					}
				}	
				
//				i++;
				
			}
		} catch(Exception e){
			e.printStackTrace();
		}		
		
		return admCode;
	}
	
	/*
	 * 
	 */
	public boolean CheckPolygon(int[] nPointX, int[] nPointY, int nX, int nY){
		
		Polygon mPoly = new Polygon(nPointX, nPointY, nPointX.length);
		boolean mResult = mPoly.contains(nX, nY);
		
		return mResult;
	}
}
