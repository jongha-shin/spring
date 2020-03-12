package bit.com.a.util;

public class BbsArrow {
	private int depth = 0;
	private String arrow;
	
	public void setDepth(int depth) {	// 외부에서 들어오는 곳
		this.depth = depth;
	}

	public String getArrow(){	// 외부로 나가는 문자열
		   String rs = "<img src='./image/arrow.png' width='20px' height='20px'/>";
		   String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
		   
		   String ts = "";
		   for(int i = 0;i < depth; i++){
		      ts += nbsp;
		   }   
		   return depth==0?"":ts + rs;
		}
	
	
}
