package bit.com.a.util;

import java.util.Date;

public class FUpUtil {
	
	// myfile.txt -> f.indexOf('.') = 6
	// 파일명, 확장자명
	// f.substring(6) -> .txt
	// f.substring(0,6) -> myfile
	
	// myfile.txt => 234235124.txt (확장자명 없을수도)
	
	public static String getNewFileName(String f) {
		String filename="";
		String fpost = "";
		
		if(f.indexOf('.') >= 0) {	// 파일이름에 확장자명 존재
			fpost = f.substring(f.lastIndexOf('.'));		// .txt
			filename = new Date().getTime() + fpost;	// 324523452345.txt
			
		}else {						// 파일이름에 확장자명 없음
			filename = new Date().getTime() + ".back";
		}
		
		return filename;
	}
}
