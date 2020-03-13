<%@page import="bit.com.a.util.CalendarUtil"%>
<%@page import="bit.com.a.model.CalendarDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
CalendarDto dto = (CalendarDto)request.getAttribute("dto");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<col width="200"><col width="500">

<tr>
	<td>아이디</td>
	<td><%=dto.getId() %></td>
</tr>

<tr>
	<td>제목</td>
	<td><%=dto.getTitle() %></td>
</tr>

<tr>
	<td>일정</td>
	<td><%=dto.getRdate()%></td>
</tr>

<tr>
	<td>내용</td>
	<td>
		<textarea rows="20" cols="60" readonly="readonly"><%=dto.getContent() %>
		</textarea>
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="button" value="수정" onclick="location.href='calupdate.jsp?seq=<%=dto.getSeq() %>'">
		<input type="button" value="삭제" onclick="location.href='caldel.jsp?seq=<%=dto.getSeq() %>'">
	</td>
</tr>

</table>



</body>
</html>