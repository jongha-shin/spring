<%@page import="bit.com.a.util.CalendarUtil"%>
<%@page import="bit.com.a.model.CalParam"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bit.com.a.model.CalendarDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
table {
    border-collapse: collapse;    
}

table, td, th {
    border: 1px solid black;
    font-weight: bold;
    font-size: 20px;
}
.sunday{
	color: red; 
	text-align: left;
	vertical-align: top;
	background-color: #ccccff;
}
.satday{
	color: blue; 
	text-align: left;
	vertical-align: top;
	background-color: #ccccff;
	font-weight: bolder;
}
.otherday{
	color: black; 
	text-align: left;
	vertical-align: top;		
}
.days2{
	font-size:20px;
	color: #4D6BB3; 
	text-align: center;
}
.days3{
	font-size:16px;
	color: #4D6BB3; 
	text-align: center;
	vertical-align: middle;
	background-color:#4D6BB3; color:#FFFFFF; line-height:1.7em; font-weight:normal;
}

.innerTable {
    border: 0px ;
}
</style>

<%
List<CalendarDto> list = new ArrayList<>();
Object objlist = request.getAttribute("flist");
if(objlist != null){
	list = (List<CalendarDto>)objlist;
}

CalParam jcal = (CalParam)request.getAttribute("jcal");

// 요일
int dayOfWeek = jcal.getDayOfWeek();
int lastDayOfMonth = jcal.getLastDay();
int year = jcal.getYear();
int month = jcal.getMonth();

//<<	year--
String pp = String.format("<a href='%s?year=%d&month=%d'>"
										+ "<img src='./image/left.gif'></a>" ,
							"calendarlist.do", year-1, month);
//<	month--
String p = String.format("<a href='%s?year=%d&month=%d'>"
										+ "<img src='./image/prec.gif'></a>" ,
							"calendarlist.do", year, month-1);
//>	month++
String n = String.format("<a href='%s?year=%d&month=%d'>"
										+ "<img src='./image/next.gif'></a>" ,
							"calendarlist.do", year, month+1);
//>>	year++
String nn = String.format("<a href='%s?year=%d&month=%d'>"
										+ "<img src='./image/last.gif'></a>" ,
							"calendarlist.do", year+1, month);

// 월별일정
String url = String.format("%s?year=%d&month=%d", 
							"calendarMonth.do", year, month);

%>

<div style="text-align: left;">
	<a href="<%=url %>">
		<%=year + "년 " + month + "월" %>
	</a>
</div>

<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">

<table style="width: 100%">
<col width="100px">
<col width="100px">
<col width="100px">
<col width="100px">
<col width="100px">
<col width="100px">
<col width="100px">

<thead>
	<tr height="100px">
		<td class="days2" colspan="7">
			<%=pp %><%=p %>&nbsp;
			<font color="black" style="font-size: 42px;font-weight:bold;">
				<%=String.format("%d년&nbsp;%d월", year, month) %>
			</font>
			<%=n %><%=nn %>
		</td>
	</tr>
	
	<tr height="50px">
		<th class="days3">일</th>
		<th class="days3">월</th>
		<th class="days3">화</th>
		<th class="days3">수</th>
		<th class="days3">목</th>
		<th class="days3">금</th>
		<th class="days3">토</th>
	</tr>	
</thead>

<tr height="100px">
<%
for(int i = 1;i < dayOfWeek; i++){
	out.println("<td>&nbsp;</td>");	
}

for(int i = 1;i <= lastDayOfMonth; i++){
	// callist, calwrite
	
	if( (i + dayOfWeek - 1) % 7 == 1 ){	// 일요일
		%>
		<td class="sunday"><%=CalendarUtil.callist(year, month, i) %>&nbsp;<%=CalendarUtil.showPen(year, month, i)%>
		<%=CalendarUtil.makeTable(year, month, i, list) %>	
		</td>
		<%
	}else{	
		%>
		<td class="otherday"><%=CalendarUtil.callist(year, month, i) %>&nbsp;<%=CalendarUtil.showPen(year, month, i) %>
			<%=CalendarUtil.makeTable(year, month, i, list) %>	
		</td>	
		<%
	}
	
	if((i + dayOfWeek - 1) % 7 == 0 && i != lastDayOfMonth){
		%>		
		</tr><tr height="100px">
		<%
	}
}

for(int i = 0;i < (7 - (dayOfWeek + lastDayOfMonth - 1)% 7)% 7; i++){
	out.println("<td>&nbsp;</td>");	
}
%>
</tr>


</table>

</div>






