package dev.polygon.util;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemDataMemory {
	
		//Vertex 전체 정보 MAP - A
		public static HashMap<String, ArrayList<int[]>> m_GeoVertexXMapA = new HashMap<String, ArrayList<int[]>>();
		public static HashMap<String, ArrayList<int[]>> m_GeoVertexYMapA = new HashMap<String, ArrayList<int[]>>();
		
		//Vertex 좌하, 우상 정보 MAP - A
		public static HashMap<String, String> m_GeoVertexSquareMapA = new HashMap<String, String>();
		
		//Vertex INDEX - X좌표 -> Y좌표 -> Vertex - A
		public static HashMap<String, HashMap<String, HashMap<String, String>>> m_FirstIdxMapA = 
				new HashMap<String, HashMap<String, HashMap<String, String>>>();
		
		//Vertex 전체 정보 MAP - B
		public static HashMap<String, ArrayList<int[]>> m_GeoVertexXMapB = new HashMap<String, ArrayList<int[]>>();
		public static HashMap<String, ArrayList<int[]>> m_GeoVertexYMapB = new HashMap<String, ArrayList<int[]>>();
		
		//Vertex 좌하, 우상 정보 MAP - B
		public static HashMap<String, String> m_GeoVertexSquareMapB = new HashMap<String, String>();
		
		//Vertex INDEX - X좌표 -> Y좌표 -> Vertex - B
		public static HashMap<String, HashMap<String, HashMap<String, String>>> m_FirstIdxMapB = 
				new HashMap<String, HashMap<String, HashMap<String, String>>>();

}
