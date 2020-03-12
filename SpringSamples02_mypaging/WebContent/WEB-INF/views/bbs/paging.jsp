<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% System.out.println("paging.jsp 실행"); %>
<!-- 연산 -->
<%
/*
	   처음						 끝
		<< < [1] [2] [3] [4] > >>

		[1]~[10] -> [11]~[20]
		100개 이상 글이 있을경우  '<', '>' 나타남

*/

int totalRecordCount;	// 전체 글의 수			23			-> 3
int pageNumber; 		// 현재 페이지			0~9			-> 1~10
int pageCountPerScreen;	// 스크린 당 페이지 수		[1]~[10]	-> 10
int recordCountPerPage;	// 페이지 당 글의 수		[1] 		-> 10
System.out.println("1 실행");
	String st1 = request.getParameter("totalRecordCount");
	if(st1 == null) totalRecordCount = 0;
	else totalRecordCount = Integer.parseInt(st1);
System.out.println("2 실행");	
	String st2 = request.getParameter("pageNumber");
	if(st2 == null) pageNumber = 0;
	else pageNumber = Integer.parseInt(st2);
System.out.println("3 실행");	
	String st3 = request.getParameter("pageCountPerScreen");
	if(st3 == null) pageCountPerScreen = 0;
	else pageCountPerScreen = Integer.parseInt(st3);
System.out.println("4 실행");	
	String st4 = request.getParameter("recordCountPerPage");
	if(st4 == null) recordCountPerPage = 0;
	else recordCountPerPage = Integer.parseInt(st4);
	
	// 총 페이지 수		2			23				10
	int totalPageCount = totalRecordCount / recordCountPerPage;
	if( (totalRecordCount % recordCountPerPage) != 0 ){
		totalPageCount++;
	}
	
	// 시작 페이지	 [1] [11] [21]	0  =	0						10					10
	//							10 =	9						10					10
	int screenStartPageIndex = ((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen;
					
	// 끝 페이지	[10] [20] [30]		0					10						10						10
	int screenEndPageIndex = (((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen) + pageCountPerScreen;
			
	// 끝 페이지 다시 계산	[1]~[3] 일경우
	if(screenEndPageIndex > totalPageCount){
		screenEndPageIndex = totalPageCount;
	}
	
	// 0~9 -> 1~ 10
	if((pageNumber +1) % pageCountPerScreen == 0){
		screenStartPageIndex = (((pageNumber + 1)/pageCountPerScreen) * pageCountPerScreen) - pageCountPerScreen;
		screenEndPageIndex = pageNumber + 1; // 0 -> 1 , 9 -> 10
	}
	
	System.out.println("screenStartPageIndex"+screenStartPageIndex);
	System.out.println("screenEndPageIndex"+screenEndPageIndex);
%>





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 출력 -->
<div style="float: left; width: 96%; text-align: center;">
	<!-- << -->
	<a href="#none" title="처음페이지" onclick="goPage('0')">
		<img alt="" src="./image/arrow_first.gif" style="width: 9px; height: 9px;">
	</a>
	
	<!-- < -->
	<!-- [11]~[20] -> [1]~[10] -->
	
	<%
System.out.println("5 실행");
	if(screenStartPageIndex >1){	//	총 글의 수가 100를 넘겨야 한다
	%>
		<a href="#none" title="이전페이지" onclick="goPage(<%=screenStartPageIndex-1 %>)">
			<img alt="" src="./image/arrow_back.gif" style="width: 9px; height: 9px;">
		</a>
	<%} %>
	
	
	<!-- [1]  2  [3] -->
	<%
System.out.println("6 실행");	
	for(int i = screenStartPageIndex; i<screenEndPageIndex; i++){
		if(i==pageNumber){	// 현재 페이지 2
		%>
			<span style="font-size: 9pt; color: #000; font-weight: bold;">
				<%=i+1 %>
			</span>
		<%	
		}else{	// 그 외 페이지 [1] 2 [3]
		%>
			<a href=#none title="<%=i+1%>페이지" onclick="goPage(<%=i %>)" style="font-size: 7.5pt; color: #000; font-weight: normal;">
			[<%=i+1 %>]
			</a>
		<%}
	}
	%>
	
	<!-- > -->
	<%
System.out.println("7 실행");	
	if(screenStartPageIndex < totalPageCount){	
	%>
		<a href="#none" title="다음페이지" onclick="goPage(<%=screenEndPageIndex %>)">
			<img alt="" src="./image/arrow_next.gif" style="width: 9px; height: 9px;">
		</a>
	<%}
System.out.println("8 실행");	
	int end_page = 0;
	if(totalPageCount > 0){
		end_page = totalPageCount - 1;
	}
	
	%>
	
	<!-- >> -->
	<a href="#none" title="마지막페이지" onclick="goPage(<%=end_page %>)">
		<img alt="" src="./image/arrow_end.gif" style="width: 9px; height: 9px;">
	</a>
	
</div>


</body>
</html>