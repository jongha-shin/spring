package bit.com.a.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import bit.com.a.model.CalendarDto;

public class CalendarUtil {

	// 1 -> 01
	public static String two(int tt) {
		return (tt + "").length()>1?(tt+""):"0"+ tt;
	}
	
	// 20203 -> 202003
	public static String yyyymm(int year, int month) {
		return "" + year + two(month);
	}
	
	// 202032 -> 20200302
	public static String yyyymmdd(int year, int month, int day) {
		return "" + year + two(month) + two(day);
	}
	
	// 03(String) -> 3(int)
	public static int toOne(String tt) {
		if(tt.charAt(0) == '0') {
			return Integer.parseInt( "" + tt.charAt(1) );			
		}else {
			return Integer.parseInt( tt ); 
		}
	}
	
	public static String callist(int year, int month, int day){
		String str = "";
				
		str += String.format("<a href='%s?year=%d&month=%d&day=%d'title='" + day + "일별일정'>", 
								"callist.do", year, month, day);		
		str += String.format("%2d", day); 	
		str += "</a>";			
		return str;
	}
	
	public static String showPen(int year, int month, int day){
		String str = "";
		
		String image = "<img src='./image/pen.png' width='18px' height='18px' title='일정추가'>";	
		str = String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>", 
								"calwrite.do", year, month, day, image);

		return str;
	}
	
	public static String two(String msg){
		return msg.trim().length()<2?"0"+msg.trim():msg.trim();
	}
	
	public static String makeTable(int year, int month, int day, List<CalendarDto> list){
		String str = "";
		
		// 2020 2 5 -> 20200205
		String dates = (year + "") + two(month + "") + two(day + "");
		
		str += "<table>";
		str += "<col width='98'>";
		
		for(CalendarDto dto : list){
			if(dto.getRdate().substring(0, 8).equals(dates)){
				str += "<tr bgcolor='#0000ff'>";
				str += "<td style='border:hidden'>";
				str += "<a href='caldetail.do?seq=" + dto.getSeq() + "'>";
				str += "<font style='font-size:12px;color:white'>";
				str += dot3(dto.getTitle());
				str += "</font>";
				str += "</a>";
				str += "</td>";
				str += "</tr>";
			}		
		}	
		str += "</table>";	
		return str;
	}
	
	public static String dot3(String msg){
		String str = "";
		if(msg.length() >= 6){
			str = msg.substring(0, 6);
			str += "..."; 
		}else{
			str = msg.trim();	
		}
		return str;
	}
	
	public static String toDates(String mdate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
		
		String s = mdate.substring(0, 4) + "-" 	// yyyy
				+ mdate.substring(4, 6) + "-"	// MM
				+ mdate.substring(6, 8) + " " 	// dd
				+ mdate.substring(8, 10) + ":"	// hh
				+ mdate.substring(10, 12) + ":00"; 
		Timestamp d = Timestamp.valueOf(s);
		
		return sdf.format(d);	
	}
}






