package dev.polygon.task;

import java.util.ArrayList;
import java.util.HashMap;

import dev.polygon.util.SystemDataMemory;

public class SetVertexInMem {
	
	public SetVertexInMem(){
		
	}
			
	/*
	 * VerTex Data save to Memory
	 */
	public int saveVertexData(int nMaxX, int nMinX, int nMaxY, int nMinY, String admCode, int[] arrPosX , int[] arrPosY, String Flag, String InterFlag){
		StringBuffer sb = new StringBuffer();
		
		String strMaxX = String.valueOf(nMaxX);
		String strMinX = String.valueOf(nMinX);
		String strMaxY = String.valueOf(nMaxY);
		String strMinY = String.valueOf(nMinY);
		
		sb.append(strMaxX);
		sb.append("_");
		sb.append(strMinX);
		sb.append("_");
		sb.append(strMaxY);
		sb.append("_");
		sb.append(strMinY);
		
		String m_RectangleIdx = sb.toString();
		
		String minIdxX = strMinX.substring(0, strMinX.length()-4);
		String maxIdxX = strMaxX.substring(0, strMaxX.length()-4);
		
		String minIdxY = strMinY.substring(0, strMinY.length()-4);
		String maxIdxY = strMaxY.substring(0, strMaxY.length()-4);
		
		if(minIdxX.equals("")) minIdxX = "0";
		if(maxIdxX.equals("")) maxIdxX = "0";
		if(minIdxY.equals("")) minIdxY = "0";
		if(maxIdxY.equals("")) maxIdxY = "0";				
		
		HashMap<String, HashMap<String, String>> mHash = new HashMap<String, HashMap<String, String>>();

		//First INDEX
		if(minIdxX.equals(maxIdxX)){
			
			if(SystemDataMemory.getFirstIdxMap(Flag).get(minIdxX)!=null){
				mHash = SystemDataMemory.getFirstIdxMap(Flag).get(minIdxX);						
			} 
			
			setIndex(minIdxY, maxIdxY, m_RectangleIdx, admCode, mHash);					
			SystemDataMemory.getFirstIdxMap(Flag).put(minIdxX, mHash);
			
		} else {
			//최대 최소 값의 앞자리 두 숫자가 차이가 클때
			int MinX = 0;
			int MaxX = 0;
		
			MinX = Integer.valueOf(minIdxX);
			MaxX = Integer.valueOf(maxIdxX);
			
			while(MinX <=MaxX){
				mHash = new HashMap<String, HashMap<String, String>>();
				
				minIdxX = String.valueOf(nMinX);
				
				if(SystemDataMemory.getFirstIdxMap(Flag).get(minIdxX)!=null){
					mHash = SystemDataMemory.getFirstIdxMap(Flag).get(minIdxX);						
				} 
			
				setIndex(minIdxY, maxIdxY, m_RectangleIdx, admCode, mHash);					
				SystemDataMemory.getFirstIdxMap(Flag).put(minIdxX, mHash);
				
				MinX++;
			}					
			
		}		
		
		//MIN-MAX MAP 저장
//		m_GeoVertexSquareMap.put(sbRangeValue.toString(), strAdmCode);
//		
//		DBObject mPolyXY = (DBObject)mResult.get("PoiXY");
//		BasicDBList bListPolyX = (BasicDBList) mPolyXY.get("PolygonX");
//		BasicDBList bListPolyY = (BasicDBList)mPolyXY.get("PolygonY");
//		
//		int[] arrPolyX = new int[bListPolyX.size()];
//		int[] arrPolyY = new int[bListPolyY.size()];
//		
//		for(int i=0;i<bListPolyX.size();i++){
//			arrPolyX[i] = (Integer) bListPolyX.get(i);
//			arrPolyY[i] = (Integer) bListPolyY.get(i);
//		}
//		
		ArrayList<int[]> m_ArrX = new ArrayList<int[]>();
		ArrayList<int[]> m_ArrY = new ArrayList<int[]>();
		
		//Polygon Info X
		if(SystemDataMemory.getGeoVertexXMap(Flag).get(admCode)!=null){
			m_ArrX = SystemDataMemory.getGeoVertexXMap(Flag).get(admCode);					
		}
		
		m_ArrX.add(arrPosX);
		SystemDataMemory.getGeoVertexXMap(Flag).put(admCode, m_ArrX);
		
		//Polygon Info Y;
		if(SystemDataMemory.getGeoVertexYMap(Flag).get(admCode)!=null){
			m_ArrY = SystemDataMemory.getGeoVertexXMap(Flag).get(admCode);					
		}
		
		m_ArrY.add(arrPosY);
		SystemDataMemory.getGeoVertexXMap(Flag).put(admCode, m_ArrY);								
		
		return 100;
	}
	
	/*
	 * Set LastIdx Data
	 */
	public void setIndex(String minIdxY, String maxIdxY, String strKey, String strValue, HashMap<String, HashMap<String, String>> mHash){
		HashMap<String, String> mYHash = new HashMap<String, String>();
		
		if(minIdxY.equals(maxIdxY)){
			
			if(mHash.get(minIdxY)!=null){
				mYHash = mHash.get(minIdxY);
			}
			
			mYHash.put(strKey, strValue);
			mHash.put(minIdxY, mYHash);
		} else {
			/*
			 * MinY, MaxY, 앞자리 2자리가 다르면 각자 저장
			 */
			int nMinY = Integer.valueOf(minIdxY);
			int nMaxY = Integer.valueOf(maxIdxY);
			
			while(nMinY<=nMaxY){
				mYHash = new HashMap<String, String>();
				minIdxY = String.valueOf(nMinY);
				
				if(mHash.get(minIdxY)!=null){
					mYHash = mHash.get(minIdxY);
				}
				
				mYHash.put(strKey, strValue);
				mHash.put(minIdxY, mYHash);
				
				nMinY++;
			}
		}		
	}

}
